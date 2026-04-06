import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String orderId;
    private Restaurant restaurant;
    private List<MenuItem> items;
    private List<Integer> quantities;
    private double deliveryFee;
    private String status;
    private LocalDateTime orderTime;

    // Constructor
    public Order(String orderId, Restaurant restaurant, double deliveryFee) {
        this.orderId = orderId;
        this.restaurant = restaurant;
        this.deliveryFee = deliveryFee;
        this.items = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.status = "PENDING";
        this.orderTime = LocalDateTime.now();
    }

    // Add item to order
    public void addItem(MenuItem item, int quantity) {
        if (!item.isAvailable()) {
            System.out.println("Item " + item.getItemName() + " is not available!");
            return;
        }
        items.add(item);
        quantities.add(quantity);
        System.out.println("Added " + quantity + " x " + item.getItemName() + " to order");
    }

    // Remove item from order
    public void removeItem(String itemId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemId().equals(itemId)) {
                items.remove(i);
                quantities.remove(i);
                System.out.println("Item removed from order");
                return;
            }
        }
        System.out.println("Item not found in order!");
    }

    // Calculate subtotal
    public double calculateSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < items.size(); i++) {
            subtotal += items.get(i).getPrice() * quantities.get(i);
        }
        return subtotal;
    }

    // Calculate total with delivery fee and tax
    public double calculateTotal() {
        double subtotal = calculateSubtotal();
        double tax = subtotal * 0.05; // 5% tax
        return subtotal + tax + deliveryFee;
    }

    // Display order summary
    public void displayOrderSummary() {
        System.out.println("\n====== ORDER SUMMARY ======");
        System.out.println("Order ID: " + orderId);
        System.out.println("Restaurant: " + restaurant.getRestaurantName());
        System.out.println("Order Time: " + orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Status: " + status);
        System.out.println("----------------------------------------");
        System.out.println("Items Ordered:");
        
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            int qty = quantities.get(i);
            double itemTotal = item.getPrice() * qty;
            System.out.println("  " + qty + " x " + item.getItemName() + " @ ₹" + item.getPrice() + " = ₹" + itemTotal);
        }
        
        double subtotal = calculateSubtotal();
        double tax = subtotal * 0.05;
        
        System.out.println("----------------------------------------");
        System.out.println("Subtotal: ₹" + String.format("%.2f", subtotal));
        System.out.println("Tax (5%): ₹" + String.format("%.2f", tax));
        System.out.println("Delivery Fee: ₹" + String.format("%.2f", deliveryFee));
        System.out.println("Total: ₹" + String.format("%.2f", calculateTotal()));
        System.out.println("=============================\n");
    }

    // Place order
    public void placeOrder() {
        if (items.isEmpty()) {
            System.out.println("Cannot place order! Cart is empty.");
            return;
        }
        this.status = "CONFIRMED";
        System.out.println("Order placed successfully! Order ID: " + orderId);
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }
}
