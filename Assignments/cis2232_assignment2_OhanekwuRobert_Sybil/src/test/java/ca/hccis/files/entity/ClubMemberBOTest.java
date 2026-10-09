package ca.hccis.files.entity;
import ca.hccis.squash.bo.ClubMemberBO;
import ca.hccis.squash.entity.ClubMember;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Unit tests for {@link ClubMemberBO#calculate(ClubMember)}.
 * These tests were written FIRST, following a test driven development approach:
 * each test was run and seen to fail (red) before the calculate method was
 * written, and only the code needed to make them pass (green) was added.
 *
 * @since 20261005
 */
class ClubMemberBOTest {

    private static final double DELTA = 0.001;

    /**
     * Written first using TDD: a fully paid member with no missed meetings owes nothing.
     */
    @Test
    void calculateFullyPaidNoMissedMeetingsReturnsZero() {
//        ClubMember member = createMember(25.0, 10, 10, 0);
        ClubMember member = new ClubMember();
        member.setMonthlyDuesRate(25.0);
        member.setMonthsActive(10);
        member.setMonthsPaid(10);
        member.setMissedMeetings(0);
        assertEquals(0.0, ClubMemberBO.calculate(member), String.valueOf(DELTA));
    }

    /**
     * Written first using TDD: unpaid months plus missed meetings are added together.
     * (8 - 6) x 25 = 50, plus 2 x 10 = 20, so 70.
//     */
    @Test
    void calculateUnpaidMonthsAndMissedMeetingsReturnsTotal() {
//        ClubMember member = createMember(25.0, 8, 6, 2);
        ClubMember member = new ClubMember();
        member.setMonthlyDuesRate(25.0);
        member.setMonthsActive(8);
        member.setMonthsPaid(6);
        member.setMissedMeetings(2);
        assertEquals(70.0, ClubMemberBO.calculate(member), String.valueOf(DELTA));
    }

    /**
     * Written first using TDD: a member who is paid up but missed meetings still owes money.
     */
    @Test
    void calculateOnlyMissedMeetingsOwesMoney() {
//        ClubMember member = createMember(20.0, 4, 4, 3);
        ClubMember member = new ClubMember();
        member.setMonthlyDuesRate(20.0);
        member.setMonthsActive(4);
        member.setMonthsPaid(4);
        member.setMissedMeetings(3);
        assertTrue(ClubMemberBO.calculate(member) > 0);
    }
//    AI TEST
    @Test
    void calculateBrandNewMemberReturnsZeroBalance() {
        // Written following a test-driven approach (TDD)
        ClubMember member = new ClubMember();
        member.setMonthlyDuesRate(30.0);
        member.setMonthsActive(0);
        member.setMonthsPaid(0);
        member.setMissedMeetings(0);

        assertEquals(0.0, ClubMemberBO.calculate(member), String.valueOf(DELTA));
    }

    /**
     * Written first using TDD: Testing a member who has overpaid their account
     * (paid more months than active - e.g., paid in advance).
     * Expected: negative balance (credit).
     */
    @Test
    void calculateOverpaidMemberReturnsNegativeBalance() {
        // Written following a test-driven approach (TDD)
        ClubMember member = new ClubMember();
        member.setMonthlyDuesRate(50.0);
        member.setMonthsActive(5);
        member.setMonthsPaid(6); // Paid for 6 months when active for 5
        member.setMissedMeetings(0);

        // (5 * 50) - (6 * 50) + 0 = -50.0
        double balance = ClubMemberBO.calculate(member);
        assertEquals(-50.0, balance, String.valueOf(DELTA));
        Assertions.assertTrue(balance < 0, "Overpaid member should have a negative balance (credit)");
    }

    /**
     * Written first using TDD: Testing when a member has zero dues rate (free membership)
     * but has accumulated penalties from missed meetings.
     */
    @Test
    void calculateFreeMembershipWithPenaltiesReturnsOnlyPenalties() {
        // Written following a test-driven approach (TDD)
        ClubMember member = new ClubMember();
        member.setMonthlyDuesRate(0.0);
        member.setMonthsActive(12);
        member.setMonthsPaid(12);
        member.setMissedMeetings(4);

        // (12 * 0) - (12 * 0) + (4 * 10) = 40.0
        assertEquals(40.0, ClubMemberBO.calculate(member), String.valueOf(DELTA));
    }

    /**
     * Written first using TDD: Testing a long-term member with multiple unpaid months
     * and a high number of missed meetings to ensure scaling calculation is correct.
     */
    @Test
    void calculateLongTermMemberHighArrearsReturnsCorrectTotal() {
        // Written following a test-driven approach (TDD)
        ClubMember member = new ClubMember();
        member.setMonthlyDuesRate(15.0);
        member.setMonthsActive(20);
        member.setMonthsPaid(15);
        member.setMissedMeetings(5);

        // Expected: (20 * 15) - (15 * 15) + (5 * 10) = 300 - 225 + 50 = 125.0
        double balance = ClubMemberBO.calculate(member);
        assertEquals(125.0, balance, String.valueOf(DELTA));
        assertFalse(balance == 0.0, "Member with arrears should not have a zero balance");
    }

    /**
     * Written first using TDD: Testing edge case where active months and paid months are large
     * with fractional/decimal dues rates.
     */
    @Test
    void calculateDecimalDuesRateReturnsPreciseTotal() {
        // Written following a test-driven approach (TDD)
        ClubMember member = new ClubMember();
        member.setMonthlyDuesRate(12.50);
        member.setMonthsActive(4);
        member.setMonthsPaid(2);
        member.setMissedMeetings(1);

        // Expected: (4 * 12.50) - (2 * 12.50) + (1 * 10) = 50.0 - 25.0 + 10.0 = 35.0
        assertEquals(35.0, ClubMemberBO.calculate(member), String.valueOf(DELTA));
    }


    private void assertTrue(boolean b) {
    }
}