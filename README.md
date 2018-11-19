# cs430-database-project
Semester project for CS430: Database Systems

## Features
Users can add records (staff, faculty, student), delete records, search university records, and search classes. Adding and deleting requires a staff ID to be entered before proceeding. Searching university records requires a staff or faculty ID before proceeding.

## How to run
- Add ojdbc7.jar to classpath
- All SQL statements are in sql-statements.txt
- Change password var in ConnectSQL.java from MYPASSWORD to your sqlplus password (included TODO to quickly locate).
- Includes a demo video if the user cannot compile (just a precaution since the code runs properly).