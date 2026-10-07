# For hccis.ca version of the database
# DROP DATABASE IF EXISTS yourusername_club_dues_w26;
# CREATE DATABASE yourusername_club_dues_w26;
# use yourusername_club_dues_w26;

#For localhost
DROP DATABASE IF EXISTS cis2232_club_dues;
CREATE DATABASE cis2232_club_dues;
use cis2232_club_dues;

-- ----------------------------------------------------------------------------
-- Table to hold the club member / dues data for the Friends Book & Social
-- Club Dues Tracker project.
-- ----------------------------------------------------------------------------

CREATE TABLE ClubMemberDues
(
    id              int(5),
    memberName      varchar(50) NOT NULL COMMENT 'Full name of the club member',
    joinDate        varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
    monthlyDuesRate double COMMENT 'Standard monthly fee agreed upon',
    monthsActive    int(5) COMMENT 'Total number of months enrolled',
    monthsPaid      int(5) COMMENT 'Total number of monthly dues paid so far',
    missedMeetings  int(5) COMMENT 'Number of mandatory club meetings missed',
    actualBalance   double COMMENT 'Calculated total balance/amount owed'
) COMMENT 'This table holds club member dues details';

ALTER TABLE ClubMemberDues
    ADD PRIMARY KEY (id);
ALTER TABLE ClubMemberDues
    MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;

-- ----------------------------------------------------------------------------
-- Sample data
-- ----------------------------------------------------------------------------
INSERT INTO ClubMemberDues (id, memberName, joinDate, monthlyDuesRate, monthsActive,
                            monthsPaid, missedMeetings, actualBalance)
VALUES (1, 'Maria Smith',   '2025-01-15', 25.00, 10, 10, 0,   0.00),
       (2, 'John Carter',   '2025-03-10', 25.00,  8,  6, 2,  70.00),
       (3, 'Aisha Bello',   '2024-11-05', 30.00, 12,  9, 1, 100.00),
       (4, 'David Chen',    '2025-06-20', 20.00,  4,  4, 3,  30.00),
       (5, 'Grace Okoro',   '2024-09-01', 35.00, 14, 12, 0,  70.00),
       (6, 'Peter Njoroge', '2025-02-12', 30.00,  9,  9, 0,   0.00);