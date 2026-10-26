package ca.hccis.squash.entity;

import ca.hccis.squash.util.CisUtility;

public class ClubMember {
    private int id;
    private String memberName;
    private String joinDate;
    private double monthlyDuesRate;
    private int monthsActive;
    private int monthsPaid;
    private int missedMeetings;
    private double actualBalance; // set using ClubMemberBO.calculate when the member is added

    public ClubMember(int id, double actualBalance, String memberName, double monthlyDuesRate, String joinDate, int monthsActive, int monthsPaid, int missedMeetings) {
        this.id = id;
        this.actualBalance = actualBalance;
        this.memberName = memberName;
        this.monthlyDuesRate = monthlyDuesRate;
        this.joinDate = joinDate;
        this.monthsActive = monthsActive;
        this.monthsPaid = monthsPaid;
        this.missedMeetings = missedMeetings;
    }

    public ClubMember() {
    }

    /** Prompt the user for the member's details using the console. */
    public void getInformation() {
        memberName = CisUtility.getInputString("Member name");
        joinDate = CisUtility.getInputString("Join date (yyyy-mm-dd)");
        monthlyDuesRate = CisUtility.getInputDouble("Monthly dues rate");
        monthsActive = CisUtility.getInputInt("Months active");
        monthsPaid = CisUtility.getInputInt("Months paid");
        missedMeetings = CisUtility.getInputInt("Missed meetings");
    }

    /** Prompt the user for the member's details using JOptionPane dialogs. */
    public void getInformationDialog() {
        memberName = CisUtility.getInputStringDialog("Member name");
        joinDate = CisUtility.getInputStringDialog("Join date (yyyy-mm-dd)");
        monthlyDuesRate = CisUtility.getInputDoubleDialog("Monthly dues rate");
        monthsActive = CisUtility.getInputIntDialog("Months active");
        monthsPaid = CisUtility.getInputIntDialog("Months paid");
        missedMeetings = CisUtility.getInputIntDialog("Missed meetings");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public double getMonthlyDuesRate() {
        return monthlyDuesRate;
    }

    public void setMonthlyDuesRate(double monthlyDuesRate) {
        this.monthlyDuesRate = monthlyDuesRate;
    }

    public int getMonthsActive() {
        return monthsActive;
    }

    public void setMonthsActive(int monthsActive) {
        this.monthsActive = monthsActive;
    }

    public int getMonthsPaid() {
        return monthsPaid;
    }

    public void setMonthsPaid(int monthsPaid) {
        this.monthsPaid = monthsPaid;
    }

    public int getMissedMeetings() {
        return missedMeetings;
    }

    public void setMissedMeetings(int missedMeetings) {
        this.missedMeetings = missedMeetings;
    }

    public double getActualBalance() {
        return actualBalance;
    }

    public void setActualBalance(double actualBalance) {
        this.actualBalance = actualBalance;
    }

    @Override
    public String toString() {
        return  "\nID: " + id +
                "\nMemberName: " + memberName +
                "\nJoinDate: " + joinDate +
                "\nMonthlyDuesRate: " + monthlyDuesRate +
                "\nMonthsActive: " + monthsActive +
                "\nMonthsPaid: " + monthsPaid +
                "\nMissedMeetings: " + missedMeetings +
                "\nActualBalance: " + actualBalance +
                "\n";
    }
}