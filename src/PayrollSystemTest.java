import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PayrollSystemTest {
    private PayrollSystem system;

    @BeforeEach
    void setUp() { system = new PayrollSystem(); }

    // ---------- BLACK-BOX ----------
    @Test void BB01_addValidEmployee() {
        assertTrue(system.addEmployee(new Employee("E01","Alice Uwase",500000,50000,20000)));
    }
    @Test void BB02_negativeSalary() {
        assertFalse(system.addEmployee(new Employee("E03","Bob",-100000,0,0)));
        assertEquals(0, system.getEmployeeCount());
    }
    @Test void BB03_nullEmployee() {
        assertFalse(system.addEmployee(null));
    }
    @Test void BB04_zeroSalaryBoundary() {
        assertTrue(system.addEmployee(new Employee("E04","Zero",0,0,0)));
    }
    @Test void BB05_netSalaryNormal() {
        assertEquals(530000.0, system.calculateNetSalary(new Employee("E01","Alice",500000,50000,20000)));
    }
    @Test void BB06_deductionsExceedGross() {
        assertEquals(0.0, system.calculateNetSalary(new Employee("E02","Jean",400000,30000,450000)));
    }
    @Test void BB07_deductionsEqualGross() {
        assertEquals(0.0, system.calculateNetSalary(new Employee("E05","Eq",100000,0,100000)));
    }
    @Test void BB08_reportEmpty() {
        assertEquals("No employees found.", system.generateReport());
    }
    @Test void BB09_reportWithEmployees() {
        system.addEmployee(new Employee("E01","Alice Uwase",500000,50000,20000));
        system.addEmployee(new Employee("E02","Jean Mugisha",400000,30000,450000));
        String r = system.generateReport();
        assertTrue(r.contains("E01 | Alice Uwase | Net Salary: 530000.0"));
        assertTrue(r.contains("E02 | Jean Mugisha | Net Salary: 0.0"));
    }

    // ---------- WHITE-BOX ----------
    @Test void WB01_branchA_null() {
        assertFalse(system.addEmployee(null));
        assertEquals(0, system.getEmployeeCount());
    }
    @Test void WB02_branchB_negative() {
        assertFalse(system.addEmployee(new Employee("E06","Neg",-50000,0,0)));
        assertEquals(0, system.getEmployeeCount());
    }
    @Test void WB03_branchC_valid() {
        assertTrue(system.addEmployee(new Employee("E01","Alice",500000,50000,20000)));
        assertEquals(1, system.getEmployeeCount());
    }
    @Test void WB04_branchD_netNegative() {
        assertEquals(0.0, system.calculateNetSalary(new Employee("E02","Jean",400000,30000,450000)));
    }
    @Test void WB05_branchE_netPositive() {
        assertEquals(530000.0, system.calculateNetSalary(new Employee("E01","Alice",500000,50000,20000)));
    }
    @Test void WB06_branchF_emptyList() {
        assertEquals("No employees found.", system.generateReport());
    }
    @Test void WB07_loopOnce() {
        system.addEmployee(new Employee("E01","Alice",500000,50000,20000));
        assertEquals(1, system.generateReport().split("\n").length);
    }
    @Test void WB08_loopMultiple() {
        system.addEmployee(new Employee("E01","Alice",500000,50000,20000));
        system.addEmployee(new Employee("E02","Jean",400000,30000,450000));
        assertEquals(2, system.generateReport().split("\n").length);
    }
}