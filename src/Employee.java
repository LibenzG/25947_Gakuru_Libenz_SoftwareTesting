public class Employee {
    private String id;
    private String name;
    private double basicSalary;
    private double allowances;
    private double deductions;

    public Employee(String id, String name, double basicSalary,
                    double allowances, double deductions) {
        this.id = id;
        this.name = name;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.deductions = deductions;
    }

    public String getId()          { return id; }
    public String getName()        { return name; }
    public double getBasicSalary() { return basicSalary; }
    public double getAllowances()  { return allowances; }
    public double getDeductions()  { return deductions; }
}