package ca.hccis.squash.bo;

import ca.hccis.squash.entity.ClubMember;

public class ClubMemberBO {
        /** Penalty charged for each mandatory meeting a member has missed. */
        public static final double FEE_PER_MISSED_MEETING = 10.0;

        /**
         * Calculate the member's balance: the total expected dues
         * (monthsActive x monthlyDuesRate), minus the dues already paid
         * (monthsPaid x monthlyDuesRate), plus a $10 fee for every missed meeting.
         *
         * @param member the member whose balance is to be calculated
         * @return the amount owed (0 means nothing owing)
         * @since 20261005
         */
        public static double calculate(ClubMember member) {
            double expectedDues = member.getMonthsActive() * member.getMonthlyDuesRate();
            double paidDues = member.getMonthsPaid() * member.getMonthlyDuesRate();
            double penalties = member.getMissedMeetings() * FEE_PER_MISSED_MEETING;
            return expectedDues - paidDues + penalties;
        }
}
