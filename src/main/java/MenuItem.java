public class MenuItem {
    private String itemId;
    private String itemName;
    private double price;
    private String description;
    private boolean available;

    // Constructor
    public MenuItem(String itemId, String itemName, double price, String description) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.available = true;
    }

    // Getters
    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ID: " + itemId + " | " + itemName + " | ₹" + price + 
               " | " + description + " | Available: " + available;
    }
}
