public class Intern extends Employee{
    String universityName;
    int internshipDuration;

    public Intern(String employeeID, String employeeName, String department, String gender, String role, double baseSalary, int performanceRating, String universityName, int internshipDuration) {
        super(employeeID, employeeName, department, gender, role, baseSalary, performanceRating);
        this.universityName = universityName;
        this.internshipDuration = internshipDuration;
    }

    //getterss
     String getUniversityName(){
        return universityName;
    }
    int getInternshipDuration(){
        return internshipDuration;
    }

    // setterss
    public void setUniversityName(String universityName){
        this.universityName =universityName;
    }
    public void setInternshipDuration(int internshipDuration) {
        this.internshipDuration = internshipDuration;
    }

    @Override
    public double calculateBonus() {
        return 0;
    }

    @Override
    public void displayEmployeeDetails() {
        System.out.println(String.format(
                "ID: %s | Name: %s | Department: %s | Gender: %s | Role: %s | Salary: $%.2f | Performance Rating: %d ⭐️ | University: %s | Duration: %d mth",
                getEmployeeID(), getEmployeeName(), getDepartment(), getGender(),
                getRole(), getBaseSalary(), getPerformanceRating(), universityName, internshipDuration
        ));

    }
}
