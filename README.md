# CIS2232 File I/O Assignment
## Friends Book & Social Club Dues Tracker

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

### Project fields
- id
- memberName
- joinDate
- monthlyDuesRate
- monthsActive
- monthsPaid
- missedMeetings
- actualBalance

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

## JSON file
A starter `data_OhanekwuRobert_Sybil.json` file is included in this project folder so you can see the required JSON format.

When the Java program runs on Windows, it creates/uses the assignment-required persistent file at:

`C:\cis2232\data_OhanekwuRobert_Sybil.json`

If that file does not already exist, the program now creates it automatically with an empty JSON array (`[]`) before loading data. After you add members, that Windows file will contain their saved records.
# cis2232_f26_project_ohanekwurobert_sybil