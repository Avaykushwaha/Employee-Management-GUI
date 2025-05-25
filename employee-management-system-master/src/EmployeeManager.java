import java.io.*;
import java.util.*;

public class EmployeeManager {
    private final LinkedList<Employee> employees = new LinkedList<>();
    private final Queue<String> recentActions = new LinkedList<>();
    private boolean isModified = false;

    // Method to get all employees
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees); // Return a copy of the employees list
    }

    // Method to view all employees
    public void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee employee : employees) {
                employee.displayEmployeeDetails(); // Calling the display method of each employee
            }
            System.out.println("-----------------------------------------------------");
            System.out.println("Total " + employees.size() + " result(s) found.");
            System.out.println("-----------------------------------------------------");
        }
    }

    // Add employee
    public void addEmployee(Employee employee) {
        employees.add(employee);
        isModified = true;
        recentActions.add("New employee added: " + employee.getEmployeeID());
    }

    // Delete employee
    public void deleteEmployee(String id) {
        employees.removeIf(employee -> employee.getEmployeeID().equalsIgnoreCase(id));
        isModified = true;
        recentActions.add("Deleted employee: " + id);
    }

    // Load employees from a file
    public void loadFromFile(String filePath) {
        employees.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String readLine;
            while ((readLine = br.readLine()) != null) {
                if (readLine.toLowerCase().contains("id")) {
                    continue;
                }
                String[] parts = readLine.split("\\|");
                if (parts.length >= 9) {
                    String id = parts[0];
                    String name = parts[1];
                    String dept = parts[2];
                    String gender = parts[3];
                    String role = parts[4];
                    double baseSalary = Double.parseDouble(parts[5]);
                    int performanceRating = Integer.parseInt(parts[6]);
                    String roleBased1 = parts[7];
                    String roleBased2 = parts[8];

                    Employee employee = null;
                    switch (role.toLowerCase()) {
                        case "manager":
                            employee = new Manager(id, name, dept, gender, role, baseSalary, performanceRating,
                                    Integer.parseInt(roleBased1), roleBased2);
                            break;
                        case "regular":
                            employee = new RegularEmployee(id, name, dept, gender, role, baseSalary, performanceRating,
                                    roleBased1, Integer.parseInt(roleBased2));
                            break;
                        case "intern":
                            employee = new Intern(id, name, dept, gender, role, baseSalary, performanceRating,
                                    roleBased1, Integer.parseInt(roleBased2));
                            break;
                    }
                    if (employee != null) employees.add(employee);
                }
            }
            System.out.println("Loaded from " + filePath);
            recentActions.add("File Loaded from " + filePath);
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
            recentActions.add("Error loading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Data format error: " + e.getMessage());
            recentActions.add("Data format error: " + e.getMessage());
        }
    }

    // Save data to file
    public void saveToFile(String filePath) {
        if (!isModified) {
            System.out.println("No changes detected. File not updated.");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employees) {
                StringBuilder sb = new StringBuilder();
                sb.append(employee.getEmployeeID()).append("|")
                        .append(employee.getEmployeeName()).append("|")
                        .append(employee.getDepartment()).append("|")
                        .append(employee.getGender()).append("|")
                        .append(employee.getRole()).append("|")
                        .append(employee.getBaseSalary()).append("|")
                        .append(employee.getPerformanceRating()).append("|");

                if (employee instanceof Manager manager) {
                    sb.append(manager.getTeamSize()).append("|").append(manager.getProjectName());
                } else if (employee instanceof RegularEmployee regularEmployee) {
                    sb.append(regularEmployee.getJobTitle()).append("|").append(regularEmployee.getYearsOfExperience());
                } else if (employee instanceof Intern intern) {
                    sb.append(intern.getUniversityName()).append("|").append(intern.getInternshipDuration());
                }

                bw.write(sb.toString());
                bw.newLine();
            }
            System.out.println("File saved to " + filePath);
            recentActions.add("File saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Search employee by ID
    public Employee searchById(String id) {
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equalsIgnoreCase(id)) {
                return employee;
            }
        }
        return null;
    }

    // Search employee by Name
    public List<Employee> searchByName(String name) {
        List<Employee> foundEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getEmployeeName().toLowerCase().contains(name.toLowerCase())) {
                foundEmployees.add(employee);
            }
        }
        return foundEmployees;
    }

    // Search employee by Performance Rating
    public List<Employee> searchByPerformanceRating(int rating) {
        List<Employee> foundEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getPerformanceRating() == rating) {
                foundEmployees.add(employee);
            }
        }
        return foundEmployees;
    }

    // Sorting by name (QuickSort)
    public void quickSortEmployeesByName() {
        quickSort(employees, 0, employees.size() - 1);
    }

    private void quickSort(List<Employee> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private int partition(List<Employee> list, int low, int high) {
        Employee pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getEmployeeName().compareToIgnoreCase(pivot.getEmployeeName()) < 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    // Sorting by Salary (ascending)
    public void sortEmployeesBySalary() {
        employees.sort(Comparator.comparingDouble(Employee::getBaseSalary));
    }

    // Sorting by Performance Rating (descending)
    public void sortEmployeesByPerformanceRating() {
        employees.sort(Comparator.comparingInt(Employee::getPerformanceRating).reversed());
    }
}
