cis2232_f26_project_ohanekwurobert_sybil
Project Title

Friends Book & Social Club Dues Tracker

Project Group
Role	Name
BA / Business Client	Hagai Audax Rukonge
Developer	Sybil
Project Manager / QA	Thomal
Project Base Color

Burgundy (
#800020)

Description

This web application allows the treasurer of a local neighborhood club to track member profiles, monitor their monthly meeting attendance, and calculate their current account balance including any accumulated dues or late fees.

Fields
Name	Data Type	Description
id	int	Unique identifier for database table
memberName	String	Full name of the club member
joinDate	String	Date the member joined the club
monthlyDuesRate	double	Standard monthly fee agreed upon
monthsActive	int	Total number of months enrolled
monthsPaid	int	Total number of monthly dues paid so far
missedMeetings	int	Number of mandatory club meetings missed
actualBalance	double	Calculated total balance/amount owed
Calculation

When a member's record is updated, the application calculates the balance as:

(monthsActive x monthlyDuesRate) - (monthsPaid x monthlyDuesRate) + ($10 x missedMeetings)

The final dollar amount is stored in the database row to show whether the member has a zero balance or owes money.

Reports
Report 1: Outstanding Balances Report

Purpose: Lets the treasurer see which members owe money so they can be followed up with for payment.

Input (entered on the report input form)

Input	Type	Required	Description
minimumBalance	double	Yes	Only members whose actualBalance is greater than or equal to this amount are included. Must be 0 or greater. Entering 0 lists all members.

Output (shown on the report results view)

A table of the matching members showing: memberName, joinDate, monthlyDuesRate, monthsActive, monthsPaid, missedMeetings and actualBalance.
Members are sorted by actualBalance, highest first.
A summary line showing the number of members listed and the total amount owed.
A bar chart showing actualBalance for each member listed.
If no members match, the message "No members found with a balance of at least $X" is shown.

File output

The report results are also written to a text file in the c:\cis2232\ folder (created if it does not exist). The file name is the report type plus the current date and time in the format yyyyMMddHHmmss, for example:

OutstandingBalancesReport20261023143015.txt

Implementation notes

The report data is retrieved using a DAO class with JDBC (not Spring Data JPA).
The report is reached from the Reports menu in the navigation bar.

Testing (BA)

Enter a minimum balance of 0 and confirm every member appears.
Enter a minimum balance of 50 and confirm only members owing $50 or more appear.
Enter a very large amount and confirm the "no members found" message is shown.
Confirm the file is created in c:\cis2232\ and matches what is on the screen.
