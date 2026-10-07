package ca.hccis.squash;

import ca.hccis.squash.entity.ClubMember;
import ca.hccis.squash.entity.ClubMember;
import ca.hccis.squash.util.CisUtility;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls the overall flow of the program.
 */
public class Controller {

    public static final String EXIT = "X";

    public static final String MENU = "A) Add" + System.lineSeparator()
            + "V) View" + System.lineSeparator()
            + "X) eXit" + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";

    public static final String DIRECTORY_NAME = "c:\\cis2232";
    // TODO replace "last" and "first" with YOUR last and first name, e.g. data_smith_jane.json
    public static final String PATH_NAME = DIRECTORY_NAME + "\\data_OhanekwuRobert_Sybil.json";

    private static final Gson gson = new Gson();
    private static final List<ClubMember> members = new ArrayList<>();

    public static void main(String[] args) {

        initialize();

        String menuOption;
        do {
            menuOption = CisUtility.getInputString(MENU).trim().toUpperCase();

            switch (menuOption) {
                case "A":
                    add();
                    break;
                case "V":
                    viewAll();
                    break;
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }
        } while (!menuOption.equals(EXIT));
    }

    /** Add a member and save everything to the file. */
    public static void add() {
        System.out.println("--Add Member--");
        ClubMember newMember = new ClubMember();
        newMember.getInformation();

        int nextId = 1;
        for (ClubMember m : members) {
            nextId = Math.max(nextId, m.getId() + 1);
        }
        newMember.setId(nextId);

        members.add(newMember);
        writeAll();
    }

    /** Reload from the file so we show what is actually saved, then print. */
    public static void viewAll() {
        readAll();
        System.out.println("--Members--");
        for (ClubMember m : members) {
            System.out.println(m);
        }
    }

    /** Write all members to the file, one JSON object per line. */
    public static void writeAll() {
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

    /** Replace the in-memory list with what is in the file. */
    public static void readAll() {
        members.clear();
        try {
            for (String line : Files.readAllLines(Paths.get(PATH_NAME))) {
                if (!line.isBlank()) {
                    members.add(gson.fromJson(line, ClubMember.class));
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