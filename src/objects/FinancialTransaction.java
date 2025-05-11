package objects;

public class FinancialTransaction {
    private String type; // "Revenue" or "Expense"
    private double amount;
    private String category;
    private String description;

    public FinancialTransaction(String type, double amount, String category, String description) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

} 