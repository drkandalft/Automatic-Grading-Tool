package org.hekmat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Test {
    public String TestFile(File file, String folderPath){
        StringBuilder output = new StringBuilder();
        try {
            // Create a ProcessBuilder for the javac command
            ProcessBuilder pb = new ProcessBuilder("javac", file.getAbsolutePath());
            // Redirect the error stream to the standard output
            pb.redirectErrorStream(true);
            // Start the process
            Process process = pb.start();
            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Check if the exit code is 0 (indicating success)
            if (exitCode == 0) { // If the compilation was successful
                // Run the compiled Java file
                String className = file.getName().substring(0, file.getName().lastIndexOf("."));
                ProcessBuilder runPb = new ProcessBuilder("java", "-cp", folderPath, className);
                Process runProcess = runPb.start();
                BufferedReader runReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                String runLine;
                while ((runLine = runReader.readLine()) != null) output.append(runLine);
            } else {
                output.append("Error");
            }
        }  catch (InterruptedException e) {
            //e.printStackTrace();
            output.append("Error");
        } catch (IOException e) {
            output.append("Error");
            //throw new RuntimeException(e);
        }
        //System.out.print(output.toString());
        return output.toString();
    }

    public void TestJavaFiles(String wdir){
        List<File> javaFiles = this.getJavaFiles(wdir);
        Student student = new Student();

        if(javaFiles.isEmpty()) {
            System.out.println("Workdir is empty");
        } else {
            student.ClearHistory();

            String[] FirstNameArr = new String[javaFiles.size()];
            String[] LastNameArr = new String[javaFiles.size()];
            String[] PhoneArr = new String[javaFiles.size()];
            String[] FeedBackArr = new String[javaFiles.size()];
            String[] ScoreArr = new String[javaFiles.size()];

            int i = 0;
            for (File file : javaFiles) {
                String curFileName = file.getName();
                int pos = curFileName.indexOf('_');
                String FirstName = curFileName.substring(0,1).toUpperCase() + curFileName.substring(1, pos).toLowerCase();
                String LastName = curFileName.substring(pos + 1, pos + 2).toUpperCase() + curFileName.substring(pos + 2, curFileName.lastIndexOf('.')).toLowerCase();
                FirstNameArr[i] = FirstName;
                LastNameArr[i] = LastName;
                String phone = this.TestFile(file, wdir);
                if(phone.equals("Error")) {
                    FeedBackArr[i] = "Code error";
                    ScoreArr[i] = "0";
                    phone = "";
                } else if (Pattern.compile("(?:\\d{3}-){2}\\d{4}").matcher(phone).matches()) {
                    int index = -1;
                    for (int j = 0; j < i; j++){
                        if(PhoneArr[j].equals(phone)) {
                            index = j;
                            FeedBackArr[j] = "Same number used by another student. Plagiarism detected";
                            ScoreArr[j] = "0";
                            break;
                        }
                    }
                    if(index < 0) {
                        FeedBackArr[i] = "Good work";
                        ScoreArr[i] = "1";
                    } else {
                        FeedBackArr[i] = "Same number used by another student. Plagiarism detected";
                        ScoreArr[i] = "0";
                    }
                } else {
                    FeedBackArr[i] = "Incorrect format";
                    ScoreArr[i] = "0";
                    phone = "";
                }

                PhoneArr[i] = phone;
                i++;
            }
            for (int j = 0; j < i; j++){
                System.out.print("[" + FirstNameArr[j] + " " + LastNameArr[j] + "] Phone: " + PhoneArr[j] + "; Score: ");
                System.out.println(ScoreArr[j] + "; FeedBack: " + FeedBackArr[j]);
                student.AddTestResult(FirstNameArr[j], LastNameArr[j], ScoreArr[j], PhoneArr[j], FeedBackArr[j]);
            }
        }
    }

    // Get all java files from directory
    public List<File> getJavaFiles(String folderPath) {
        List<File> javaFiles = new ArrayList<>();

        // Get a list of all the files in the directory
        File folder = new File(folderPath);

        File[] files = folder.listFiles();
        // Loop through the files

        for (File file : files) {
            // Check if the file is a Java file
            if (file.isFile() && file.getName().endsWith(".java")) {
                javaFiles.add(file);
            } else if (file.isDirectory()) {
                javaFiles.addAll(getJavaFiles(file.getAbsolutePath()));
            }
        }

        return javaFiles;
    }
}