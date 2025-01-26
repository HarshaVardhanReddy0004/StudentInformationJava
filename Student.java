package studentinformationharsha;

import java.io.*;
import java.util.*;

public class Student {
    private final String FILE_NAME = "studentdata.txt";
    public String studentNo;
    public String studentName;

    // Method to accept student information (No and Name)
    public boolean acceptStudentInformation() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Validate student number to ensure it's numeric
            while (true) {
                System.out.println("Enter the student no (must be a number):");
                studentNo = br.readLine();
                if (studentNo.isEmpty()) {
                    System.out.println("Please enter student no");
                    continue;
                }

                // Check if studentNo is a valid number
                try {
                    Integer.parseInt(studentNo);  // Try to parse the studentNo as an integer
                    break;  // If successful, exit the loop
                } catch (NumberFormatException e) {
                    System.out.println("Error: Student number must be a numeric value. Please try again.");
                }
            }

            // Student name input
            System.out.println("Enter the student name:");
            studentName = br.readLine();
            if (studentName.isEmpty()) {
                System.out.println("Please enter student name.");
                return false;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // Method to save student information into a file
    public boolean saveStudentInformation() {
        if (!acceptStudentInformation()) {
            return false;
        }
        
        // Check if student already exists
        if (isStudentExists(studentNo)) {
            System.out.println("Error: Student with this number already exists.");
            return false;
        }

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File(FILE_NAME), true));
            pw.println(studentNo + "," + studentName);
            pw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // Method to print all saved student information from the file
    public boolean printStudentInformation() {
        List<String> students = getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return false;
        }
        
        System.out.println(String.format("%-15s %-40s", "Student No", "Student Name"));
        for (String student : students) {
            String[] data = student.split(",");
            System.out.println(String.format("%-15s %-40s", data[0], data[1]));
        }
        return true;
    }

    // Method to get all students from the file
    private List<String> getAllStudents() {
        List<String> students = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String dataLine;
            while ((dataLine = br.readLine()) != null) {
                students.add(dataLine);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    // Method to check if a student exists based on student number
    private boolean isStudentExists(String studentNo) {
        List<String> students = getAllStudents();
        for (String student : students) {
            if (student.split(",")[0].equals(studentNo)) {
                return true;
            }
        }
        return false;
    }

    // Method to edit a student's information
    public boolean editStudentInformation() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the student no to edit:");
        try {
            String studentNoToEdit = br.readLine();
            List<String> students = getAllStudents();
            boolean studentFound = false;

            for (int i = 0; i < students.size(); i++) {
                String[] data = students.get(i).split(",");
                if (data[0].equals(studentNoToEdit)) {
                    studentFound = true;
                    System.out.println("Current Name: " + data[1]);
                    System.out.println("Enter new name for student:");
                    String newName = br.readLine();
                    students.set(i, studentNoToEdit + "," + newName);
                    updateFile(students);
                    System.out.println("Student information updated successfully.");
                    break;
                }
            }

            if (!studentFound) {
                System.out.println("Student with this number not found.");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // Method to delete a student by student number
    public boolean deleteStudentInformation() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the student no to delete:");
        try {
            String studentNoToDelete = br.readLine();
            List<String> students = getAllStudents();
            boolean studentFound = false;

            Iterator<String> iterator = students.iterator();
            while (iterator.hasNext()) {
                String[] data = iterator.next().split(",");
                if (data[0].equals(studentNoToDelete)) {
                    studentFound = true;
                    iterator.remove();
                    updateFile(students);
                    System.out.println("Student deleted successfully.");
                    break;
                }
            }

            if (!studentFound) {
                System.out.println("Student with this number not found.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // Method to update the file after editing or deleting
    private void updateFile(List<String> students) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File(FILE_NAME), false)); // Overwrite the file
            for (String student : students) {
                pw.println(student);
            }
            pw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
