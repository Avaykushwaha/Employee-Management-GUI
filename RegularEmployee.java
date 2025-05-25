public class RegularEmployee extends Employee{
    private String jobTitle;
    private int yearsOfExperience;

    public RegularEmployee(String employeeID, String employeeName, String department, String gender, String role,
                           double baseSalary, int performanceRating, String jobTitle, int yearsOfExperience) {
        super(employeeID, employeeName, department, gender, role, baseSalary, performanceRating);
        this.jobTitle = jobTitle;
        this.yearsOfExperience = yearsOfExperience;
    }

    //getters
    String getJobTitle(){
        return jobTitle;
    }
    int getYearsOfExperience(){
        return yearsOfExperience;
    }

    //setters
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public double calculateBonus() {
        if (getPerformanceRating() >= 8) {
            evaluatePerformance();
            System.out.println("Congratulations! you have received 5% bonus.");
            return getBaseSalary() * 0.5; // 5% bonus
        }
        else if (getPerformanceRating() <= 3){
            evaluatePerformance();
            System.out.println("2% salary is deducted from your base salary.");
            return getBaseSalary() - (getBaseSalary() * 0.02);
        }
        else return getBaseSalary();
    }

    @Override
    public void displayEmployeeDetails() {
        System.out.format(
                "ID: %s | Name: %s | Department: %s | Gender: %s | Role: %s | Salary: $%.2f | Performance Rating: %d ⭐️ | Job Title: %s  | Years of Experience: %s yr%n",
                getEmployeeID(), getEmployeeName(), getDepartment(), getGender(),
                getRole(), getBaseSalary(), getPerformanceRating(), jobTitle, yearsOfExperience
        );

    }


}
