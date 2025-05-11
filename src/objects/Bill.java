package objects;

import java.time.LocalDateTime;


public class Bill {
    private String billId;
    private Patient patient;
    private LocalDateTime issueDate;
    private LocalDateTime dueDate;
    private double totalAmount;
    private double paidAmount;
    private boolean isPaid;
    private String notes;

    public Bill(String billId, Patient patient, LocalDateTime dueDate) {
        this.billId = billId;
        this.patient = patient;
        this.issueDate = LocalDateTime.now();
        this.dueDate = dueDate;
        this.totalAmount = 0.0;
        this.paidAmount = 0.0;
        this.isPaid = false;
        this.notes = "";
    }

    // Getters
    public String getBillId() {
        return billId;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public boolean isPaid() {
        return isPaid;
    }



    public String getNotes() {
        return notes;
    }

    // Methods
    public void makePayment(double amount) {
        if (!isPaid) {
            this.paidAmount += amount;
            if (paidAmount >= totalAmount) {
                this.isPaid = true;
                System.out.println("Bill " + billId + " has been fully paid");
            } else {
                System.out.println("Payment of $" + amount + " received. Remaining balance: $" + (totalAmount - paidAmount));
            }
        } else {
            System.out.println("There is no bill which patient has to be paid.");
        }
    }

    public String GeneralInfo() {
        String paid;
        if (isPaid) {
            paid = "Paid";
        } else {
            paid = "Unpaid";
        }
        return "Bill ID: " + billId +
               ", Patient: " + patient.getFullName() +
               ", Total: $" + totalAmount +
               ", Paid: $" + paidAmount +
               ", Status: " + paid +
               ", Due: " + dueDate.toLocalDate();
    }
} 