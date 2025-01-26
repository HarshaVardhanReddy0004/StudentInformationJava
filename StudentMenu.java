package studentinformationharsha;




import java.io.*;

public class StudentMenu {
    public static void main(String args[]) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Student s = new Student();
            String select = "";
            do {
                showMenu();
                select = br.readLine();
                switch (select) {
                    case "1":
                        if (s.saveStudentInformation())
                            System.out.println("Save successful...");
                        else
                            System.out.println("Error occurred while saving student information...");
                        break;
                    case "2":
                        if (s.printStudentInformation())
                            System.out.println("Printing successful...");
                        else
                            System.out.println("Error occurred while printing student information...");
                        break;
                    case "3":
                        if (s.editStudentInformation())
                            System.out.println("Edit successful...");
                        else
                            System.out.println("Error occurred while editing student information...");
                        break;
                    case "4":
                        if (s.deleteStudentInformation())
                            System.out.println("Delete successful...");
                        else
                            System.out.println("Error occurred while deleting student information...");
                        break;
                    case "5":
                        System.out.println("Exiting the program...");
                        return;
                }
            } while (!select.equals("5"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to display the menu options
    private static void showMenu() {
        System.out.println("1: Add student");
        System.out.println("2: Print all students");
        System.out.println("3: Edit student");
        System.out.println("4: Delete student");
        System.out.println("5: Exit");
        System.out.print("Select Number (1 or 2 or 3 or 4 or 5): ");
    }
}
