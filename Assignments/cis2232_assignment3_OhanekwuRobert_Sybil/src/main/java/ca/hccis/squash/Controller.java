package ca.hccis.squash;

import ca.hccis.squash.bo.ClubMemberBO;
import ca.hccis.squash.entity.ClubMember;
import ca.hccis.squash.threads.ConsoleRunner;
import ca.hccis.squash.threads.DialogRunner;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controls the overall flow of the program.
 *
 * The program runs in two separate threads: one works completely in the
 * console (ConsoleRunner) and the other works completely using JOptionPane
 * (DialogRunner). Both threads share the members list and the data file, so
 * every method that touches them is synchronized.
 */
public class Controller {

    public static final String EXIT = "X";

    public static final String MENU = "A) Add" + System.lineSeparator()
            + "V) View" + System.lineSeparator()
            + "X) eXit" + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";

    public static final String DIRECTORY_NAME = "c:\\cis2232";
    public static final String PATH_NAME = DIRECTORY_NAME + "\\data_OhanekwuRobert_Sybil.json";

    private static final Gson gson = new Gson();
    private static final List<ClubMember> members = new ArrayList<>();

    public static void main(String[] args) {

        initialize();

        Thread consoleThread = new Thread(new ConsoleRunner(), "console");
        Thread dialogThread = new Thread(new DialogRunner(), "dialog");

        consoleThread.start();
        dialogThread.start();
    }

    /**
     * Add a member (shared by both threads). Assigns the next id, calculates
     * the balance, saves everything to the file and returns the accumulator.
     *
     * @param newMember the member to add
     * @return total number of members saved so far (from both threads)
     */
    public static synchronized int addMember(ClubMember newMember) {
        int nextId = 1;
        for (ClubMember m : members) {
            nextId = Math.max(nextId, m.getId() + 1);
        }
        newMember.setId(nextId);
        newMember.setActualBalance(ClubMemberBO.calculate(newMember));

        members.add(newMember);
        writeAll();
        return members.size();
    }

    /**
     * Reload from the file so we show what is actually saved, and return all
     * members as text (shared by both threads).
     *
     * @return the members formatted for display
     */
    public static synchronized String getAllAsText() {
        readAll();
        StringBuilder sb = new StringBuilder("--Members--");
        for (ClubMember m : members) {
            sb.append(m);
        }
        sb.append(System.lineSeparator()).append("Total members: ").append(members.size());
        return sb.toString();
    }

    /** Write all members to the file, one JSON object per line. */
    public static synchronized void writeAll() {
        try {
            Files.createDirectories(Paths.get(DIRECTORY_NAME)); // safe if it already exists
            List<String> lines = new ArrayList<>();
            for (ClubMember m : members) {
                lines.add(gson.toJson(m));
            }
            Files.write(Paths.get(PATH_NAME), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replace the in-memory list with what is in the file. Handles both a
     * single JSON array and one JSON object per line.
     */
    public static synchronized void readAll() {
        members.clear();
        try {
            String content = new String(Files.readAllBytes(Paths.get(PATH_NAME)),
                    StandardCharsets.UTF_8).trim();
            if (content.startsWith("[")) {
                // older format: one JSON array
                members.addAll(Arrays.asList(gson.fromJson(content, ClubMember[].class)));
            } else {
                // current format: one JSON object per line
                for (String line : content.split("\\R")) {
                    if (!line.isBlank()) {
                        members.add(gson.fromJson(line, ClubMember.class));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Make sure the directory exists and load any previously saved data. */
    public static void initialize() {
        try {
            Files.createDirectories(Paths.get(DIRECTORY_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path = Paths.get(PATH_NAME);
        if (Files.exists(path)) {
            readAll();
        }
    }
}