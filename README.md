# Automatic Grading Tool

This is a Java console application that can be executed at the command line. The application is designed to evaluate students’ assignments, which are Java source codes, and store the generated feedback and score for each student. The application is intended to be used as a tool for grading students’ **"phone number formatting function"** assignments in a community college computer information systems (CIS) course.

## How it Works
The Automatic Grading Tool works by taking a directory containing students’ submissions as an input where the students’ work saved in files that have a format “FirstName_LastName.java”. The tool then compiles the Java source code in all files then evaluates the output, based on that generates a feedback for the student and assigns a score and updates the students’ records.
The feedback and score for each student are then stored in a in data base that can be easily accessed by the instructor using the same application.
## How to Use
To use the Automatic Grading Tool, simply download the JAR file application **“hekmat.jar”** from this Github repository then run it using a the command line  by navigating to the directory containing JAR file e.g.**“CD C:\JARFolder”**. The application will start using command **“java -jar Hekmat.jar”**, student's work samples  can be downloaded from this Github repository and saved in the folder **"C:\assignments\works"** then follow the **following instructions:**
#### **/help** - View instructions </n>
#### **/all** - View all students
#### **/getstudent** - Get student info by student id
#### **/update** - Checking student work
#### **/addstudent** - Add new student
#### **/delstudent** - Delete student
#### **/clear** - Clear database
The tool can then automatically grade the assignments and generate feedback and scores for each student, which can be accessed by the instructor using this application.
## Contributions
This project is open for contributions from the community. If you have any ideas for improving the tool or would like to report a bug, feel free to submit an issue or a pull request.
## License
This project is licensed under the PCC License. Feel free to use it and modify it as you wish, as long as you comply with the terms of the license.

