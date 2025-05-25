import java.util.Scanner;
import java.util.List;  // Import the List interface
import java.util.ArrayList;  // Import the ArrayList class for list implementation

public class TextBasedCLI {
    private static EmployeeManager employeeManager = new EmployeeManager();

    public static void main(String[] args) {
        TextBasedCLI cli = new TextBasedCLI();
        cli.userMenu(); // Calling the userMenu method to display the menu
    }

    // Method to display the user menu and handle user input
    public void userMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Search Employee");
            System.out.println("5. Sort Employees By Name");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    deleteEmployee(scanner);
                    break;
                case 3:
                    employeeManager.viewAllEmployees(); // Calling the method to display all employees
                    break;
                case 4:
                    searchEmployee(scanner); // Adding the search option
                    break;
                case 5:
                    employeeManager.quickSortEmployeesByName(); // Sort employees by name
                    System.out.println("Employees sorted by name.");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return; // Exit the loop and program
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to add an employee
    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();
        System.out.print("Enter Base Salary: ");
        double salary = scanner.nextDouble();
        System.out.print("Enter Performance Rating: ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Create the employee (for simplicity, let's assume it's a manager)
        Employee employee = new Manager(id, name, department, gender, role, salary, rating, 5, "Project A");

        employeeManager.addEmployee(employee); // Add to the EmployeeManager
        System.out.println("Employee added.");
    }

    // Method to delete an employee by ID
    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        String id = scanner.nextLine();
        employeeManager.deleteEmployee(id);
        System.out.println("Employee deleted.");
    }

    // Method to search for an employee
    private static void searchEmployee(Scanner scanner) {
        System.out.println("Search by:");
        System.out.println("1. Employee ID");
        System.out.println("2. Employee Name");
        System.out.println("3. Performance Rating");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                System.out.print("Enter Employee ID: ");
                String id = scanner.nextLine();
                Employee employee = employeeManager.searchById(id);
                if (employee != null) {
                    employee.displayEmployeeDetails();
                } else {
                    System.out.println("Employee not found.");
                }
                break;
            case 2:
                System.out.print("Enter Employee Name: ");
                String name = scanner.nextLine();
                List<Employee> employeesByName = employeeManager.searchByName(name);
                if (!employeesByName.isEmpty()) {
                    for (Employee emp : employeesByName) {
                        emp.displayEmployeeDetails();
                    }
                } else {
                    System.out.println("No employees found with that name.");
                }
                break;
            case 3:
                System.out.print("Enter Performance Rating: ");
                int rating = scanner.nextInt();
                List<Employee> employeesByRating = employeeManager.searchByPerformanceRating(rating);
                if (!employeesByRating.isEmpty()) {
                    for (Employee emp : employeesByRating) {
                        emp.displayEmployeeDetails();
                    }
                } else {
                    System.out.println("No employees found with that performance rating.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
