
abstract class Employee {
    private String employeeID;
    private String employeeName;
    private String department;
    private String gender;
    private String role;
    private double baseSalary;
    private int performanceRating;
    
    //constructor
    public Employee(String employeeID, String employeeName, String department, String gender, String role, double baseSalary, int performanceRating) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.department = department;
        this.gender = gender;
        this.role = role;
        this.baseSalary = baseSalary;
        this.performanceRating = performanceRating;
    }

    //getters
    public String getEmployeeID() {
        return employeeID; }

    public String getEmployeeName() {
        return employeeName; }

    public String getGender(){
        return gender;}

    public String getDepartment() {
        return department; }

    public String getRole(){ return  role;}

    public double getBaseSalary() {
        return baseSalary; }

    public int getPerformanceRating() {
        return performanceRating; }

    //setters
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName; }

    public void setDepartment(String department) {
        this.department = department; }

    public void setRole(String role) {
        this.role = role; }

    public void setGender(String gender){
        this.gender = gender; }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary; }

    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating; }


    //abstract method to calculate bonus
    public abstract double calculateBonus();

    // Display default info of employee
    public void displayEmployeeDetails() {
        System.out.println(employeeID + " | " + employeeName + " | " + department + " | " + gender + " | " + role + " | $" + baseSalary + " | " + performanceRating + " ⭐️");
    }

    // evaluate the performance
    public void evaluatePerformance() {
        if (performanceRating <= 3) {
            System.out.println( UIEnhancement.YELLOW +"Warning Letter Issued." + UIEnhancement.RESET);
        } else if (performanceRating >= 8) {
            System.out.println(UIEnhancement.GREEN + "Appreciation Letter Issued."+ UIEnhancement.RESET);
        } else {
            System.out.println(UIEnhancement.GREEN +"Performance is Satisfactory." + UIEnhancement.RESET);
        }
    }
}
