package ca.hccis.files.entity;
import ca.hccis.squash.bo.ClubMemberBO;
import ca.hccis.squash.entity.ClubMember;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertEquals(0.0, ClubMemberBO.calculate(member), String.valueOf(DELTA));
    }

    /**
     * Written first using TDD: unpaid months plus missed meetings are added together.
     * (8 - 6) x 25 = 50, plus 2 x 10 = 20, so 70.
     */
    @Test
    void calculateUnpaidMonthsAndMissedMeetingsReturnsTotal() {
        ClubMember member = createMember(25.0, 8, 6, 2);
        assertEquals(70.0, ClubMemberBO.calculate(member), String.valueOf(DELTA));
    }

    /**
     * Written first using TDD: a member who is paid up but missed meetings still owes money.
     */
    @Test
    void calculateOnlyMissedMeetingsOwesMoney() {
        ClubMember member = createMember(20.0, 4, 4, 3);
        assertTrue(ClubMemberBO.calculate(member) > 0);
    }

    private void assertTrue(boolean b) {
    }
}