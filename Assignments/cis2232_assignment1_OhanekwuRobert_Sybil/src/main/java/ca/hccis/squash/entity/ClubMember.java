package ca.hccis.squash.entity;

import ca.hccis.squash.util.CisUtility;
import com.google.gson.Gson;

public class ClubMember {
    private int id;
    private String memberName;
    private String joinDate;
    private double monthlyDuesRate;
    private int monthsActive;
    private int monthsPaid;
    private int missedMeetings;
    private double actualBalance; // calculation is NOT coded for this assignment

    public ClubMember() {
    }
    /** Prompt the user for the member's details. */
    public void getInformation() {
        memberName = CisUtility.getInputString("Member name");
        joinDate = CisUtility.getInputString("Join date (yyyy-mm-dd)");
        monthlyDuesRate = CisUtility.getInputDouble("Monthly dues rate");
        monthsActive = CisUtility.getInputInt("Months active");
        monthsPaid = CisUtility.getInputInt("Months paid");
        missedMeetings = CisUtility.getInputInt("Missed meetings");
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
                "\nMonthsPaid " + monthsPaid +
                "\bMissedMeetings " + missedMeetings +
                "\nActualBalance " + actualBalance ;
    }
}
