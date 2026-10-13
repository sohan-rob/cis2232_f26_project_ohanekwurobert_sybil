package ca.hccis.squash.threads;

import ca.hccis.squash.Controller;
import ca.hccis.squash.entity.ClubMember;
import ca.hccis.squash.util.CisUtility;

import javax.swing.JOptionPane;

/**
 * Thread that runs the whole program using JOptionPane only.
 */
public class DialogRunner implements Runnable {

    @Override
    public void run() {
        String menuOption;
        do {
            String entered = JOptionPane.showInputDialog(Controller.MENU);
            // Cancel (null) is treated as exit
            menuOption = (entered == null) ? Controller.EXIT : entered.trim().toUpperCase();

            switch (menuOption) {
                case "A":
                    add();
                    break;
                case "V":
                    JOptionPane.showMessageDialog(null, Controller.getAllAsText());
                    break;
                case Controller.EXIT:
                    JOptionPane.showMessageDialog(null, Controller.MESSAGE_EXIT);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, Controller.MESSAGE_ERROR);
                    break;
            }
        } while (!menuOption.equals(Controller.EXIT));
    }

    /** Add a member using dialog input and display the accumulator. */
    private void add() {
        try {
            ClubMember newMember = new ClubMember();
            newMember.getInformationDialog();

            int total = Controller.addMember(newMember);

            JOptionPane.showMessageDialog(null,
                    "Balance owing: " + CisUtility.toCurrency(newMember.getActualBalance())
                            + "\nTotal members saved (both threads): " + total);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    Controller.MESSAGE_ERROR + ": please enter a valid number. Member not added.");
        }
    }
}