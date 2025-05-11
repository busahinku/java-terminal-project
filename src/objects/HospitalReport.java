package objects;

import java.util.ArrayList;
import java.util.List;

public class HospitalReport {
    private String title;
    private int departmentCount;
    private int doctorCount;
    private double totalRevenue;
    private double totalExpenses;
    private double netIncome;
    private List<FinancialTransaction> transactions;

    public HospitalReport(String title, int departmentCount, int doctorCount,
                         double totalRevenue, double totalExpenses, double netIncome,
                         List<FinancialTransaction> transactions) {
        this.title = title;
        this.departmentCount = departmentCount;
        this.doctorCount = doctorCount;
        this.totalRevenue = totalRevenue;
        this.totalExpenses = totalExpenses;
        this.netIncome = netIncome;
        this.transactions = new ArrayList<>(transactions);
    }

    public String getTitle() {
        return title;
    }

    public int getDepartmentCount() {
        return departmentCount;
    }

    public int getDoctorCount() {
        return doctorCount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getNetIncome() {
        return netIncome;
    }

    public List<FinancialTransaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public String getReportSummary() {
        return "Report: " + title + "\n" +
                "Departments: " + departmentCount + "\n" +
                "Doctors: " + doctorCount + "\n" +
                "Revenue: $" + totalRevenue + "\n" +
                "Expenses: $" + totalExpenses + "\n" +
                "Net Income: $" + netIncome + "\n";
    }
} 