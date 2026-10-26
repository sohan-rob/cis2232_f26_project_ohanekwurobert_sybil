# cis2232_f26_project_ohanekwurobert_sybil

## Project Title

Friends Book & Social Club Dues Tracker

## Project Group

| Role | Name |
| --- | --- |
| BA / Business Client | Hagai Audax Rukonge |
| Developer | Sybil |
| Project Manager / QA | Thomal |

## Project Base Color

Burgundy (#800020)

## Description

This web application allows the treasurer of a local neighborhood club to track member profiles, monitor their monthly meeting attendance, and calculate their current account balance including any accumulated dues or late fees.

## Fields

| Name | Data Type | Description |
| --- | --- | --- |
| id | int | Unique identifier for database table |
| memberName | String | Full name of the club member |
| joinDate | String | Date the member joined the club |
| monthlyDuesRate | double | Standard monthly fee agreed upon |
| monthsActive | int | Total number of months enrolled |
| monthsPaid | int | Total number of monthly dues paid so far |
| missedMeetings | int | Number of mandatory club meetings missed |
| actualBalance | double | Calculated total balance/amount owed |

## Calculation

When a member's record is updated, the application calculates the balance as:

`(monthsActive x monthlyDuesRate) - (monthsPaid x monthlyDuesRate) + ($10 x missedMeetings)`

The final dollar amount is stored in the database row to show whether the member has a zero balance or owes money.

---

## Reports

### Report 1: Outstanding Balances Report

**Purpose:** Lets the treasurer see which members owe money so they can be followed up with for payment.

**Input (entered on the report input form)**

| Input | Type | Required | Description |
| --- | --- | --- | --- |
| minimumBalance | double | Yes | Only members whose actualBalance is greater than or equal to this amount are included. Must be 0 or greater. Entering 0 lists all members. |

**Output (shown on the report results view)**

- A table of the matching members showing: memberName, joinDate, monthlyDuesRate, monthsActive, monthsPaid, missedMeetings and actualBalance.
- Members are sorted by actualBalance, highest first.
- A summary line showing the number of members listed and the total amount owed.
- A bar chart showing actualBalance for each member listed.
- If no members match, the message "No members found with a balance of at least $X" is shown.

**File output**

The report results are also written to a text file in the `c:\cis2232\` folder (created if it does not exist). The file name is the report type plus the current date and time in the format `yyyyMMddHHmmss`, for example:

`OutstandingBalancesReport20261023143015.txt`

**Implementation notes**

- The report data is retrieved using a DAO class with JDBC (not Spring Data JPA).
- The report is reached from the Reports menu in the navigation bar.

**Testing (BA)**

1. Enter a minimum balance of 0 and confirm every member appears.
2. Enter a minimum balance of 50 and confirm only members owing $50 or more appear.
3. Enter a very large amount and confirm the "no members found" message is shown.
4. Confirm the file is created in `c:\cis2232\` and matches what is on the screen.

---

## Console Application (CIS2232 File I/O Assignment)

This is a Java console-based application for the CIS2232 File I/O assignment.

### Features
- `A) Add` a club member
- `V) View` all saved club members
- `X) Exit` the application
- Creates `C:\cis2232` if it does not already exist
- Saves records as JSON to `C:\cis2232\data_OhanekwuRobert_Sybil.json`
- Loads saved records when the program starts again
- Uses the fields from the approved project topic
- Does **not** calculate `actualBalance` yet, because the assignment says that calculation is to be completed later during unit testing/TDD

### Requirements
- Java 17 or newer
- Maven

### Run in IntelliJ IDEA
1. Open IntelliJ IDEA.
2. Choose **Open**.
3. Select the `CIS2232_SocialClubTracker` folder.
4. Allow IntelliJ to load the Maven project and download Gson.
5. Open `src/main/java/Main.java`.
6. Click the green Run button beside `main()`.

### Run from a terminal
From the project folder:

```bash
mvn compile
mvn exec:java
```

### Test persistent storage
1. Run the program.
2. Enter `A` and add a member.
3. Enter `X` to close.
4. Run the program again.
5. Enter `V`.
6. The previously added member should still be displayed.

### Important
The file name is currently set to:

`C:\cis2232\data_OhanekwuRobert_Sybil.json`

If your instructor requires a different exact spelling for your first or last name, change the `FILE_NAME` constant in `Main.java` before submitting.

### JSON file
A starter `data_OhanekwuRobert_Sybil.json` file is included in this project folder so you can see the required JSON format.

When the Java program runs on Windows, it creates/uses the assignment-required persistent file at:

`C:\cis2232\data_OhanekwuRobert_Sybil.json`

If that file does not already exist, the program now creates it automatically with an empty JSON array (`[]`) before loading data. After you add members, that Windows file will contain their saved records.
