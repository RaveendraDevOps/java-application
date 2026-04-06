import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodDeliveryApp {
    private List<Restaurant> restaurants;
    private List<Order> orderHistory;
    private Scanner scanner;
    private int orderCounter;

    // Constructor
    public FoodDeliveryApp() {
        this.restaurants = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.orderCounter = 1000;
        initializeRestaurants();
    }

    // Initialize sample restaurants and menu items
    private void initializeRestaurants() {
        // Restaurant 1: Pizza Palace
        Restaurant pizzaPlace = new Restaurant("R001", "Pizza Palace", "123 Main Street", 50);
        pizzaPlace.addMenuItem(new MenuItem("P001", "Margherita Pizza", 250, "Classic cheese pizza"));
        pizzaPlace.addMenuItem(new MenuItem("P002", "Pepperoni Pizza", 350, "Pizza with pepperoni"));
        pizzaPlace.addMenuItem(new MenuItem("P003", "Garlic Bread", 100, "Crispy garlic bread"));
        pizzaPlace.addMenuItem(new MenuItem("P004", "Coke", 50, "Cold carbonated drink"));
        restaurants.add(pizzaPlace);

        // Restaurant 2: Burger Hub
        Restaurant burgerHub = new Restaurant("R002", "Burger Hub", "456 Oak Avenue", 40);
        burgerHub.addMenuItem(new MenuItem("B001", "Veggie Burger", 150, "Fresh vegetable burger"));
        burgerHub.addMenuItem(new MenuItem("B002", "Chicken Burger", 200, "Grilled chicken burger"));
        burgerHub.addMenuItem(new MenuItem("B003", "Fries", 80, "Crispy french fries"));
        burgerHub.addMenuItem(new MenuItem("B004", "Milkshake", 120, "Vanilla milkshake"));
        restaurants.add(burgerHub);

        // Restaurant 3: Curry House
        Restaurant curryHouse = new Restaurant("R003", "Curry House", "789 Spice Road", 60);
        curryHouse.addMenuItem(new MenuItem("C001", "Butter Chicken", 300, "Creamy butter chicken"));
        curryHouse.addMenuItem(new MenuItem("C002", "Biryani", 250, "Fragrant rice biryani"));
        curryHouse.addMenuItem(new MenuItem("C003", "Naan", 40, "Indian flatbread"));
        curryHouse.addMenuItem(new MenuItem("C004", "Lassi", 60, "Traditional yogurt drink"));
        restaurants.add(curryHouse);
    }

    // Display all restaurants
    public void displayRestaurants() {
        System.out.println("\n========== AVAILABLE RESTAURANTS ==========");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i));
        }
        System.out.println("==========================================\n");
    }

    // Browse restaurant and place order
    public void browseAndOrder(int restaurantIndex) {
        if (restaurantIndex < 0 || restaurantIndex >= restaurants.size()) {
            System.out.println("Invalid restaurant selection!");
            return;
        }

        Restaurant selected = restaurants.get(restaurantIndex);
        selected.displayMenu();

        // Create new order
        Order currentOrder = new Order("ORD" + (orderCounter++), selected, selected.getDeliveryFee());

        boolean addingItems = true;
        while (addingItems) {
            System.out.print("Enter item ID to add (or 'done' to finish): ");
            String itemId = scanner.nextLine().trim();

            if (itemId.equalsIgnoreCase("done")) {
                addingItems = false;
            } else {
                MenuItem item = selected.getMenuItem(itemId);
                if (item == null) {
                    System.out.println("Item not found!");
                    continue;
                }

                System.out.print("Enter quantity: ");
                try {
                    int quantity = Integer.parseInt(scanner.nextLine().trim());
                    if (quantity <= 0) {
                        System.out.println("Quantity must be greater than 0!");
                        continue;
                    }
                    currentOrder.addItem(item, quantity);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity!");
                }
            }
        }

        if (!currentOrder.getItems().isEmpty()) {
            currentOrder.displayOrderSummary();
            System.out.print("Confirm order? (yes/no): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                currentOrder.placeOrder();
                orderHistory.add(currentOrder);
            } else {
                System.out.println("Order cancelled.");
            }
        } else {
            System.out.println("No items added. Order cancelled.");
        }
    }

    // View order history
    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("\nNo orders yet!");
            return;
        }

        System.out.println("\n========== ORDER HISTORY ==========");
        for (Order order : orderHistory) {
            System.out.println("Order ID: " + order.getOrderId() + 
                             " | Status: " + order.getStatus() + 
                             " | Total: ₹" + String.format("%.2f", order.calculateTotal()));
        }
        System.out.println("===================================\n");
    }

    // Display main menu
    public void displayMainMenu() {
        System.out.println("\n===== FOOD DELIVERY APP =====");
        System.out.println("1. Browse Restaurants");
        System.out.println("2. View Order History");
        System.out.println("3. Exit");
        System.out.println("=============================\n");
    }

    // Main application loop
    public void run() {
        System.out.println("Welcome to Food Delivery App!");

        boolean running = true;
        while (running) {
            displayMainMenu();
            System.out.print("Enter your choice (1-3): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayRestaurants();
                    System.out.print("Select restaurant number: ");
                    try {
                        int restaurantNum = Integer.parseInt(scanner.nextLine().trim());
                        browseAndOrder(restaurantNum - 1);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input!");
                    }
                    break;

                case "2":
                    viewOrderHistory();
                    break;

                case "3":
                    System.out.println("Thank you for using Food Delivery App! Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    // Main method
    public static void main(String[] args) {
        FoodDeliveryApp app = new FoodDeliveryApp();
        app.run();
    }
}
