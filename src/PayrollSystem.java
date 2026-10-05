import java.util.ArrayList;
import java.util.List;

public class PayrollSystem {
    private List<Employee> employees = new ArrayList<>();

    public int getEmployeeCount() { return employees.size(); }

    // REQ-1
    public boolean addEmployee(Employee e) {
        if (e == null) return false;                 // Branch A
        if (e.getBasicSalary() < 0) return false;    // Branch B
        employees.add(e);
        return true;                                 // Branch C
    }

    // REQ-2
    public double calculateNetSalary(Employee e) {
        double gross = e.getBasicSalary() + e.getAllowances();
        double net = gross - e.getDeductions();
        if (net < 0) {
            net = 0;                                 // Branch D
        }
        return net;                                  // Branch E
    }

    // REQ-3
    public String generateReport() {
        if (employees.isEmpty()) {
            return "No employees found.";           // Branch F
        }
        StringBuilder sb = new StringBuilder();
        for (Employee e : employees) {              // Branch G / H
            double net = calculateNetSalary(e);
            sb.append(e.getId()).append(" | ")
                    .append(e.getName()).append(" | Net Salary: ")
                    .append(net).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PayrollSystem system = new PayrollSystem();
        system.addEmployee(new Employee("E01", "Alice Uwase", 500000, 50000, 20000));
        system.addEmployee(new Employee("E02", "Jean Mugisha", 400000, 30000, 450000));
        System.out.println(system.generateReport());
    }
}