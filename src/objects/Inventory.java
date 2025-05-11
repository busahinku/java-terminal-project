package objects;

import java.time.LocalDateTime;

public class Inventory {
    private String itemId;
    private String itemName;
    private String category;
    private int quantity;
    private int minimumQuantity;
    private double unitPrice;
    private String supplier;
    private LocalDateTime lastRestocked;
    private String location;
    private String notes;

    public Inventory(String itemId, String itemName, String category, int quantity,
                    int minimumQuantity, double unitPrice, String supplier, String location) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.minimumQuantity = minimumQuantity;
        this.unitPrice = unitPrice;
        this.supplier = supplier;
        this.lastRestocked = LocalDateTime.now();
        this.location = location;
        this.notes = "";
    }

    // Getters
    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public LocalDateTime getLastRestocked() {
        return lastRestocked;
    }

    public String getLocation() {
        return location;
    }

    public String getNotes() {
        return notes;
    }

    // Methods
    public void addStock(int amount) {
        this.quantity += amount;
        this.lastRestocked = LocalDateTime.now();
    }

    public void removeStock(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
        } else {
            System.out.println("Insufficient stock available");
        }
    }

    public String GeneralInfo() {
        return "Inventory ID: " + itemId +
                ", Name: " + itemName +
                ", Category: " + category +
                ", Quantity: " + quantity +
                ", Price: $" + unitPrice +
                ", Location: " + location;
    }
} 