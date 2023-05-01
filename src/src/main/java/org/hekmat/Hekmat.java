package org.hekmat;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Hekmat {
    public static void main(String[] args) {
        System.out.println("******************************");
        System.out.println("*** Automatic Grading tool ***");
        System.out.println("******************************");
        String wdir = System.getProperty("user.dir") + File.separator + "works";
        String cmd = "";
        try {
            cmd = args[0];
        } catch (Exception e) {
            System.out.println("The first argument must be present.");
            getHelp();
            System.exit(1);
        }
        // View all students
        if(cmd.equalsIgnoreCase("/all")) {
            System.out.println("All students:");
            Student student = new Student();
            student.GetStudents();

        } else if(cmd.equalsIgnoreCase("/update")){ // Update scores
            if (!Files.exists(Paths.get(wdir))){
                System.out.println("Directory: \"" + wdir + "\" not found :(");
                System.out.println("Can't update!");
            } else {
                System.out.println("Check for update from directory: \"" + wdir + "\"");
                System.out.println("Start...");
                Test test = new Test();
                test.TestJavaFiles(wdir);
                System.out.println("End update.");
            }
        } else if(cmd.equalsIgnoreCase("/getstudent")){ // view student info by student name
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student id: ");
            int student_id = sc.nextInt();
            while(student_id <= 0){
                System.out.print("student id cannot be a negative number or zero! Please enter student id: ");
                student_id = sc.nextInt();
            }
            Student student = new Student();
            student.GetStudent(student_id);
        } else if(cmd.equalsIgnoreCase("/addstudent")){ // add new student
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter first name: ");
            String FirstName = sc.nextLine();
            while(FirstName.length() < 3){
                System.out.print("First name is too short! Please enter first neme: ");
                FirstName = sc.nextLine();
            }
            System.out.print("Enter last name: ");
            String LastName = sc.nextLine();
            while(LastName.length() < 3){
                System.out.print("Last name is too short! Please enter last neme: ");
                LastName = sc.nextLine();
            }
            Student student = new Student();
            int student_id = student.AddStudent(FirstName, LastName);
            if(student_id < 0){
                System.out.println("Such a student is already in the database.");
            } else if(student_id > 0) {
                System.out.print("The student has been added to the database.\nStudent id: ");
                System.out.println(student_id);
            } else System.out.println("Something went wrong please try again later.");
        }  else if(cmd.equalsIgnoreCase("/delstudent")){ // delete student by student name
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student id: ");
            int student_id = sc.nextInt();
            while(student_id <= 0){
                System.out.print("student id cannot be a negative number or zero! Please enter student id: ");
                student_id = sc.nextInt();
            }
            Student student = new Student();
            if(student.DelStudent(student_id)) System.out.println("Student successfully deleted from database");
            else System.out.println("There is no student with this id.");
        }  else if(cmd.equalsIgnoreCase("/help")){ // help
            getHelp();
        } else {
            System.out.println("Error command!");
            getHelp();
        }
    }

    public static void getHelp() { // View help information
        System.out.println("Help:");
        System.out.println("/help - View this list");
        System.out.println("/all - View all students");
        System.out.println("/getstudent - Get student info by student id");
        System.out.println("/update - Checking student work");
        System.out.println("/addstudent - Add new student");
        System.out.println("/delstudent - Delete student");
        System.out.println("/clear - Clear scores");
    }
}