import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for interface selection
        System.out.println("Welcome to Employee Management System");
        System.out.println("Please select your preferred interface:");
        System.out.println("1. Text-Based Interface (CLI)");
        System.out.println("2. Graphical User Interface (GUI)");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // Run the selected interface
        switch (choice) {
            case 1:
                // Run the Text-Based Interface (CLI)
                TextBasedCLI cli = new TextBasedCLI();
                cli.userMenu();
                break;
            case 2:
                // Run the Graphical User Interface (GUI)
                EmployeeManagementGUI gui = new EmployeeManagementGUI();
                gui.createAndShowGUI();
                break;
            default:
                System.out.println("Invalid choice. Please restart the program and select 1 or 2.");
                break;
        }
    }
}
