import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurantId;
    private String restaurantName;
    private String address;
    private double deliveryFee;
    private List<MenuItem> menu;

    // Constructor
    public Restaurant(String restaurantId, String restaurantName, String address, double deliveryFee) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.address = address;
        this.deliveryFee = deliveryFee;
        this.menu = new ArrayList<>();
    }

    // Add menu item to restaurant
    public void addMenuItem(MenuItem item) {
        menu.add(item);
        System.out.println("Added: " + item.getItemName() + " to " + restaurantName);
    }

    // Remove menu item
    public void removeMenuItem(String itemId) {
        menu.removeIf(item -> item.getItemId().equals(itemId));
    }

    // Get menu item by ID
    public MenuItem getMenuItem(String itemId) {
        for (MenuItem item : menu) {
            if (item.getItemId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }

    // Display menu
    public void displayMenu() {
        System.out.println("\n====== MENU: " + restaurantName + " ======");
        System.out.println("Address: " + address);
        System.out.println("Delivery Fee: ₹" + deliveryFee);
        System.out.println("----------------------------------------");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
        System.out.println("----------------------------------------\n");
    }

    // Getters
    public String getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return "ID: " + restaurantId + " | " + restaurantName + " | " + address + 
               " | Delivery: ₹" + deliveryFee;
    }
}
