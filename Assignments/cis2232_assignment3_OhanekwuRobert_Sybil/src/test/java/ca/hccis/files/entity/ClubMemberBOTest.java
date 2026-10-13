package ca.hccis.files.entity;

import ca.hccis.squash.bo.ClubMemberBO;
import ca.hccis.squash.entity.ClubMember;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    /** Build a member with the values the calculation uses. */
    private ClubMember createMember(double rate, int active, int paid, int missed) {
        ClubMember member = new ClubMember();
        member.setMonthlyDuesRate(rate);
        member.setMonthsActive(active);
        member.setMonthsPaid(paid);
        member.setMissedMeetings(missed);
        return member;
    }

    /**
     * Written first using TDD: a fully paid member with no missed meetings owes nothing.
     */
    @Test
    void calculateFullyPaidNoMissedMeetingsReturnsZero() {
        ClubMember member = createMember(25.0, 10, 10, 0);
        assertEquals(0.0, ClubMemberBO.calculate(member), DELTA);
    }

    /**
     * Written first using TDD: unpaid months plus missed meetings are added together.
     * (8 - 6) x 25 = 50, plus 2 x 10 = 20, so 70.
     */
    @Test
    void calculateUnpaidMonthsAndMissedMeetingsReturnsTotal() {
        ClubMember member = createMember(25.0, 8, 6, 2);
        assertEquals(70.0, ClubMemberBO.calculate(member), DELTA);
    }

    /**
     * Written first using TDD: a member who is paid up but missed meetings still owes money.
     */
    @Test
    void calculateOnlyMissedMeetingsOwesMoney() {
        ClubMember member = createMember(20.0, 4, 4, 3);
        assertTrue(ClubMemberBO.calculate(member) > 0);
    }

    /**
     * Written first using TDD: a brand new member has not been active, so owes nothing.
     */
    @Test
    void calculateBrandNewMemberReturnsZeroBalance() {
        ClubMember member = createMember(30.0, 0, 0, 0);
        assertEquals(0.0, ClubMemberBO.calculate(member), DELTA);
    }

    /**
     * Written first using TDD: a member who has overpaid (paid more months than
     * active, e.g. paid in advance) has a negative balance (credit).
     */
    @Test
    void calculateOverpaidMemberReturnsNegativeBalance() {
        ClubMember member = createMember(50.0, 5, 6, 0);

        // (5 * 50) - (6 * 50) + 0 = -50.0
        double balance = ClubMemberBO.calculate(member);
        assertEquals(-50.0, balance, DELTA);
        assertTrue(balance < 0, "Overpaid member should have a negative balance (credit)");
    }

    /**
     * Written first using TDD: a zero dues rate (free membership) with missed
     * meetings owes only the penalties.
     */
    @Test
    void calculateFreeMembershipWithPenaltiesReturnsOnlyPenalties() {
        ClubMember member = createMember(0.0, 12, 12, 4);

        // (12 * 0) - (12 * 0) + (4 * 10) = 40.0
        assertEquals(40.0, ClubMemberBO.calculate(member), DELTA);
    }

    /**
     * Written first using TDD: a long-term member with several unpaid months and
     * many missed meetings, to make sure the calculation scales correctly.
     */
    @Test
    void calculateLongTermMemberHighArrearsReturnsCorrectTotal() {
        ClubMember member = createMember(15.0, 20, 15, 5);

        // (20 * 15) - (15 * 15) + (5 * 10) = 300 - 225 + 50 = 125.0
        double balance = ClubMemberBO.calculate(member);
        assertEquals(125.0, balance, DELTA);
        assertFalse(balance == 0.0, "Member with arrears should not have a zero balance");
    }

    /**
     * Written first using TDD: a fractional dues rate gives a precise total.
     */
    @Test
    void calculateDecimalDuesRateReturnsPreciseTotal() {
        ClubMember member = createMember(12.50, 4, 2, 1);

        // (4 * 12.50) - (2 * 12.50) + (1 * 10) = 50.0 - 25.0 + 10.0 = 35.0
        assertEquals(35.0, ClubMemberBO.calculate(member), DELTA);
    }
}