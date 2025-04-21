import java.util.Scanner;

public class CustomerMenuHandler {
    private Scanner scanner;
    private UserInterface userInterface;
    private ShoppingCart shoppingCart;
    private DataContainer dataContainer;

    public CustomerMenuHandler(UserInterface userInterface, ShoppingCart shoppingCart, DataContainer dataContainer) {
        this.scanner = new Scanner(System.in);
        this.userInterface = userInterface;
        this.shoppingCart = shoppingCart;
        this.dataContainer = dataContainer;
    }

    public void handleCustomerMenu() {
        while (true) {
            userInterface.displayCustomerMenu();
            int option = getValidatedInput("Enter a valid option: ");
            switch (option) {
                case 0 -> {
                    System.out.println("Exiting Customer Menu.\n");
                    return;
                }
                case 1 -> handleStartNewOrder();
                default -> System.out.println("Invalid Option. Try again.\n");
            }
        }
    }

    private void handleStartNewOrder() {
        while (true) {
            userInterface.displayStartNewOrder();
            int option = getValidatedInput("Enter a valid option: ");
            switch (option) {
                case 0 -> {
                    System.out.println("Exiting Start New Order.\n");
                    return;
                }
                case 1 -> handleAddProduct();
                case 2 -> handleViewShoppingCart();
                case 3 -> handleFinishOrder();
                default -> System.out.println("Invalid Option. Try again.\n");
            }
        }
    }

    private void handleAddProduct() {
        System.out.println("ADD PRODUCT TO CART\n");
        dataContainer.displayAvailableProducts();
        System.out.println("Enter the product ID (or 0 to cancel): ");
        int productId = getValidatedInput("Product ID: ");
        if (productId == 0) {
            System.out.println("Canceled adding product.\n");
            return;
        }
        Product product = dataContainer.getProductById(productId);
        if (product == null || !product.hasStock()) {
            System.out.println("Invalid product ID or product out of stock.\n");
            return;
        }
        System.out.println("Enter the amount to add: ");
        int amount = getValidatedInput("Amount: ");
        if (amount <= 0 || !product.canFulfillOrder(amount)) {
            System.out.println("Invalid amount or insufficient stock.\n");
            return;
        }
        shoppingCart.addProduct(product, amount);
        System.out.printf("Added %d of %s to the cart.\n", amount, product.getName());
    }

    private void handleViewShoppingCart() {
        System.out.println("VIEW SHOPPING CART\n");
        if (shoppingCart.isEmpty()) {
            System.out.println("Your cart is empty.\n");
            return;
        }
        shoppingCart.display();
    }

    private void handleFinishOrder() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your cart is empty. Cannot finish order.\n");
            return;
        }
        Order order = shoppingCart.finishOrder();
        dataContainer.getOrders().add(order);
        System.out.println("Order finished successfully!\nOrder #" + order.getNumber() + " Date: " + order.getDate() + " Total: $" + shoppingCart.calculateTotal());
        shoppingCart.clear();
    }

    private int getValidatedInput(String prompt) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                if (input >= 0) {
                    return input;
                } else {
                    System.out.println("Please enter a valid non-negative number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}