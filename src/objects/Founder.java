package objects;

import java.util.ArrayList;
import java.util.List;

public class Founder extends Person {
    private List<Department> departments;
    private List<Doctor> doctors;
    private List<Room> rooms;
    private double totalRevenue;
    private double totalExpenses;
    private double salary;
    private List<FinancialTransaction> transactions;
    private List<HospitalReport> reports;

    public Founder(String id, String firstName, String lastName, int age, char gender,
                  String phoneNumber, String username, String password, double salary) {
        super(id, firstName, lastName, age, gender, phoneNumber, username, password);
        this.departments = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.totalRevenue = 0.0;
        this.totalExpenses = 0.0;
        this.salary = salary;
        this.transactions = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    // Getters
    public List<Department> getDepartments() {
        return new ArrayList<>(departments);
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getNetIncome() {
        return totalRevenue - totalExpenses;
    }

    public double getSalary() {
        return salary;
    }

    public List<FinancialTransaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public List<HospitalReport> getReports() {
        return new ArrayList<>(reports);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    // Methods
    public void createDepartment(String name, Doctor head, String location) {
        Department department = new Department(name, head, location);
        departments.add(department);
        addExpense("Department Setup", 50000.0, "Initial setup cost for " + name);
    }

    public void hireDoctor(Doctor doctor, Department department) {
        doctors.add(doctor);
        if (department != null && !doctor.isPrivate()) {
            department.addDoctor(doctor);
            addExpense("Doctor Salary", doctor.getSalary(), "Monthly salary for Dr. " + doctor.getFullName());
        }
    }

    public void fireDoctor(Doctor doctor) {
        doctors.remove(doctor);
        if (!doctor.isPrivate()) {
            for (Department dept : departments) {
                dept.removeDoctor(doctor);
            }
        }
    }

    public void addRevenue(double amount, String source, String description) {
        this.totalRevenue += amount;
        transactions.add(new FinancialTransaction("Revenue", amount, source, description));
    }

    public void addExpense(String category, double amount, String description) {
        this.totalExpenses += amount;
        transactions.add(new FinancialTransaction("Expense", -amount, category, description));
    }

    public void generateMonthlyReport() {
        HospitalReport report = new HospitalReport(
            "Monthly Report -  MAY",
            departments.size(),
            doctors.size(),
            totalRevenue,
            totalExpenses,
            getNetIncome(),
            new ArrayList<>(transactions)
        );
        reports.add(report);
    }

    public String getFinancialSummary() {
        String summary = "Financial Summary, Revenue: " + totalRevenue +
                ", Expenses: $" + totalExpenses +
                ", Net Income: $" + getNetIncome();
        System.out.println(summary);
        return "";
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public String GeneralInfo() {
        return "Founder [Name: " + getFullName() +
                ", Departments: " + departments.size() +
                ", Doctors: " + doctors.size() +
                ", Rooms: " + rooms.size() +
                ", Salary: $" + salary +
                ", Net Income: $" + getNetIncome();
    }
}

