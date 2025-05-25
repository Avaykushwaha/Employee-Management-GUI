import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EmployeeManagementGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField, idField, departmentField, genderField, salaryField, ratingField, extraField;
    private JTextArea employeeListTextArea, searchResultsTextArea;
    private JComboBox<String> roleComboBox;
    private JLabel extraLabel;
    private EmployeeManager employeeManager;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagementGUI().createAndShowGUI());
    }

    public EmployeeManagementGUI() {
        employeeManager = new EmployeeManager(); // Initialize EmployeeManager
    }

    public void createAndShowGUI() {
        frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create input fields for employee details
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Employee Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1; panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Employee ID:"), gbc);
        idField = new JTextField(20);
        gbc.gridx = 1; panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Department:"), gbc);
        departmentField = new JTextField(20);
        gbc.gridx = 1; panel.add(departmentField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Gender:"), gbc);
        genderField = new JTextField(20);
        gbc.gridx = 1; panel.add(genderField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Salary:"), gbc);
        salaryField = new JTextField(20);
        gbc.gridx = 1; panel.add(salaryField, gbc);

        gbc.gridx = 0; gbc.gridy = 5; panel.add(new JLabel("Performance Rating:"), gbc);
        ratingField = new JTextField(20);
        gbc.gridx = 1; panel.add(ratingField, gbc);

        // Role selection dropdown
        gbc.gridx = 0; gbc.gridy = 6; panel.add(new JLabel("Role:"), gbc);
        roleComboBox = new JComboBox<>(new String[]{"Select Role", "Manager", "Regular", "Intern"});
        roleComboBox.addActionListener(this::roleChanged);
        gbc.gridx = 1; panel.add(roleComboBox, gbc);

        // Extra field for dynamic role-specific input (University, Job Title, Project)
        extraLabel = new JLabel("University/Job Title/Project:");
        gbc.gridx = 0; gbc.gridy = 7; panel.add(extraLabel, gbc);
        extraField = new JTextField(20);
        gbc.gridx = 1; panel.add(extraField, gbc);

        // Create action buttons
        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(e -> addEmployee());
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2; panel.add(addButton, gbc);

        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(e -> deleteEmployee());
        gbc.gridx = 0; gbc.gridy = 9; panel.add(deleteButton, gbc);

        JButton viewButton = new JButton("View All Employees");
        viewButton.addActionListener(e -> viewEmployees());
        gbc.gridx = 0; gbc.gridy = 10; panel.add(viewButton, gbc);

        JButton searchButton = new JButton("Search Employee");
        searchButton.addActionListener(e -> searchEmployee());
        gbc.gridx = 0; gbc.gridy = 11; panel.add(searchButton, gbc);

        JButton sortButton = new JButton("Sort Employees By");
        sortButton.addActionListener(e -> sortEmployees());
        gbc.gridx = 0; gbc.gridy = 12; panel.add(sortButton, gbc);

        // Display employee list
        employeeListTextArea = new JTextArea(10, 40);
        employeeListTextArea.setEditable(false);
        JScrollPane employeeListScrollPane = new JScrollPane(employeeListTextArea);
        gbc.gridx = 0; gbc.gridy = 13; gbc.gridwidth = 2; panel.add(employeeListScrollPane, gbc);

        // Display search results
        searchResultsTextArea = new JTextArea(5, 40);
        searchResultsTextArea.setEditable(false);
        JScrollPane searchResultsScrollPane = new JScrollPane(searchResultsTextArea);
        gbc.gridx = 0; gbc.gridy = 14; gbc.gridwidth = 2; panel.add(searchResultsScrollPane, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    // Dynamic field visibility based on role selected
    private void roleChanged(ActionEvent e) {
        String selectedRole = (String) roleComboBox.getSelectedItem();
        if (selectedRole != null) {
            if (selectedRole.equals("Manager")) {
                extraLabel.setText("Project Name:");
            } else if (selectedRole.equals("Regular")) {
                extraLabel.setText("Job Title:");
            } else if (selectedRole.equals("Intern")) {
                extraLabel.setText("University Name:");
            } else {
                extraLabel.setText("University/Job Title/Project:");
            }
        }
    }

    private void addEmployee() {
        String name = nameField.getText();
        String id = idField.getText();
        String department = departmentField.getText();
        String gender = genderField.getText();
        String role = (String) roleComboBox.getSelectedItem();
        String salary = salaryField.getText();
        String rating = ratingField.getText();
        String extraInfo = extraField.getText();

        if (name.isEmpty() || id.isEmpty() || department.isEmpty() || gender.isEmpty() || role.equals("Select Role") || salary.isEmpty() || rating.isEmpty() || extraInfo.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                double baseSalary = Double.parseDouble(salary);
                int performanceRating = Integer.parseInt(rating);

                Employee employee = null;

                // Determine which type of employee to create
                switch (role.toLowerCase()) {
                    case "manager":
                        employee = new Manager(id, name, department, gender, role, baseSalary, performanceRating, 5, extraInfo);
                        break;
                    case "regular":
                        employee = new RegularEmployee(id, name, department, gender, role, baseSalary, performanceRating, extraInfo, 3);
                        break;
                    case "intern":
                        employee = new Intern(id, name, department, gender, role, baseSalary, performanceRating, extraInfo, 6);
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "Invalid role entered.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                }

                employeeManager.addEmployee(employee);
                updateEmployeeList();
                searchResultsTextArea.setText("Employee Added:\n" + formatEmployeeDetails(employee)); // Show added employee in search results
                JOptionPane.showMessageDialog(frame, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid salary or performance rating.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteEmployee() {
        String id = idField.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter an employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            employeeManager.deleteEmployee(id);
            updateEmployeeList();
            JOptionPane.showMessageDialog(frame, "Employee deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void viewEmployees() {
        employeeManager.viewAllEmployees();
        updateEmployeeList();
    }

    private void searchEmployee() {
        String searchType = JOptionPane.showInputDialog(frame, "Select search type:\n1. ID\n2. Name\n3. Performance Rating");

        if (searchType != null) {
            switch (searchType.trim()) {
                case "1": // Search by ID
                    String id = JOptionPane.showInputDialog(frame, "Enter Employee ID:");
                    Employee employee = employeeManager.searchById(id);
                    if (employee != null) {
                        searchResultsTextArea.setText("Employee found: \n" + formatEmployeeDetails(employee));
                    } else {
                        searchResultsTextArea.setText("Employee not found.");
                    }
                    break;

                case "2": // Search by Name
                    String name = JOptionPane.showInputDialog(frame, "Enter Employee Name:");
                    List<Employee> employeesByName = employeeManager.searchByName(name);
                    if (!employeesByName.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        for (Employee emp : employeesByName) {
                            sb.append(formatEmployeeDetails(emp)).append("\n");
                        }
                        searchResultsTextArea.setText(sb.toString());
                    } else {
                        searchResultsTextArea.setText("No employees found.");
                    }
                    break;

                case "3": // Search by Performance Rating
                    String ratingStr = JOptionPane.showInputDialog(frame, "Enter Performance Rating:");
                    try {
                        int rating = Integer.parseInt(ratingStr);
                        List<Employee> employeesByRating = employeeManager.searchByPerformanceRating(rating);
                        if (!employeesByRating.isEmpty()) {
                            StringBuilder sb = new StringBuilder();
                            for (Employee emp : employeesByRating) {
                                sb.append(formatEmployeeDetails(emp)).append("\n");
                            }
                            searchResultsTextArea.setText(sb.toString());
                        } else {
                            searchResultsTextArea.setText("No employees found.");
                        }
                    } catch (NumberFormatException e) {
                        searchResultsTextArea.setText("Invalid rating.");
                    }
                    break;

                default:
                    searchResultsTextArea.setText("Invalid search type selected.");
            }
        }
    }

    private void sortEmployees() {
        String sortType = JOptionPane.showInputDialog(frame, "Select sort type:\n1. Name\n2. Salary\n3. Performance Rating");

        if (sortType != null) {
            switch (sortType.trim()) {
                case "1": // Sort by Name
                    employeeManager.quickSortEmployeesByName();
                    updateEmployeeList();
                    JOptionPane.showMessageDialog(frame, "Employees sorted by name!", "Sorting Success", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case "2": // Sort by Salary
                    employeeManager.sortEmployeesBySalary();
                    updateEmployeeList();
                    JOptionPane.showMessageDialog(frame, "Employees sorted by salary!", "Sorting Success", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case "3": // Sort by Performance Rating
                    employeeManager.sortEmployeesByPerformanceRating();
                    updateEmployeeList();
                    JOptionPane.showMessageDialog(frame, "Employees sorted by performance rating!", "Sorting Success", JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    JOptionPane.showMessageDialog(frame, "Invalid sort type selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateEmployeeList() {
        List<Employee> employees = employeeManager.getAllEmployees();
        StringBuilder sb = new StringBuilder();
        for (Employee e : employees) {
            sb.append(formatEmployeeDetails(e)).append("\n");
        }
        employeeListTextArea.setText(sb.toString());
    }

    private String formatEmployeeDetails(Employee e) {
        return "ID: " + e.getEmployeeID() + " | Name: " + e.getEmployeeName() +
                " | Department: " + e.getDepartment() + " | Gender: " + e.getGender() +
                " | Role: " + e.getRole() + " | Salary: $" + e.getBaseSalary() +
                " | Performance Rating: " + e.getPerformanceRating() + " ⭐️ | Team Size: " +
                (e instanceof Manager ? ((Manager) e).getTeamSize() : "N/A") +
                " | Project: " + (e instanceof Manager ? ((Manager) e).getProjectName() : "N/A");
    }
}
