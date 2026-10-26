package ca.hccis.squash.threads;

import ca.hccis.squash.Controller;
import ca.hccis.squash.entity.ClubMember;
import ca.hccis.squash.util.CisUtility;

/**
 * Thread that runs the whole program in the console only.
 */
public class ConsoleRunner implements Runnable {

    @Override
    public void run() {
        String menuOption;
        do {
            menuOption = CisUtility.getInputString(Controller.MENU).trim().toUpperCase();

            switch (menuOption) {
                case "A":
                    add();
                    break;
                case "V":
                    System.out.println(Controller.getAllAsText());
                    break;
                case Controller.EXIT:
                    System.out.println(Controller.MESSAGE_EXIT);
                    break;
                default:
                    System.out.println(Controller.MESSAGE_ERROR);
                    break;
            }
        } while (!menuOption.equals(Controller.EXIT));
    }

    /** Add a member using console input and display the accumulator. */
    private void add() {
        System.out.println("--Add Member--");
        try {
            ClubMember newMember = new ClubMember();
            newMember.getInformation();

            int total = Controller.addMember(newMember);

            System.out.println("Balance owing: " + CisUtility.toCurrency(newMember.getActualBalance()));
            System.out.println("Total members saved (both threads): " + total);
        } catch (NumberFormatException e) {
            System.out.println(Controller.MESSAGE_ERROR + ": please enter a valid number. Member not added.");
        }
    }
}