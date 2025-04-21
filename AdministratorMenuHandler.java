import java.util.Scanner;
import java.util.Locale;

public class AdministratorMenuHandler {
    private Scanner scanner;
    private Administrator administrator;
    private UserInterface userInterface;
    private DataContainer dataContainer;

    public AdministratorMenuHandler(Administrator administrator, UserInterface userInterface, DataContainer dataContainer) {
        this.scanner = new Scanner(System.in);
        this.userInterface = userInterface;
        this.administrator = administrator;
        this.dataContainer = dataContainer;
    }

    public void handleAdministratorMenu() {
        while (true) {
            userInterface.displayAdministratorMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 0 -> {
                    System.out.println("Exiting Administrator Menu.\n");
                    return;
                }
                case 1 -> handleCreateUser();
                case 2 -> handleCreateProduct();
                case 3 -> handleMostExpensiveOrderReport();
                case 4 -> handleLowestInventoryProductReport();     
                default -> System.out.println("Invalid Option.\n");
            }   
        }
    }

    public void handleCreateUser() {
        System.out.println("CREATE NEW USER");
        System.out.println("Enter User Type: \n");
        System.out.println("1 - Administrator\n2 - Customer\n");
        int userType = scanner.nextInt(); 
        scanner.nextLine();      
        System.out.println("Name: \n");
        String name = scanner.nextLine();
        System.out.println("Email: \n");
        String email = scanner.nextLine();
        if (emailIsAvailable(email)) {
            System.out.println("Password: \n");
            String password = scanner.nextLine();
            System.out.println("Confirm Password: \n");
            String confirmPassword = scanner.nextLine();
            if (checkPassword(password, confirmPassword)) {
                if (userType == 2) {
                    System.out.println("Address: \n");
                    String address = scanner.nextLine();
                    administrator.create(name, email, password, confirmPassword, address, false, dataContainer.getUsers());
                    System.out.println("Customer created successfully.\n");
                } else if (userType == 1) {
                    administrator.create(name, email, password, confirmPassword, null, true, dataContainer.getUsers());
                    System.out.println("Administrator created successfully.\n");
                } else {
                    System.out.println("Invalid user type\n");
                    return;
                }
            } else {
                System.out.println("User creation failed: Passwords do not match.\n");
                return;
            }
        } else {
            System.out.println("User creation failed: Email is already registered.\n");
            return;
        }
        
    }

    public void handleCreateProduct() {
        scanner.useLocale(Locale.US);
        System.out.println("CREATE NEW PRODUCT");
        System.out.println("Enter product name: \n");
        String productName = scanner.nextLine();
        System.out.println("Enter product description: \n");
        String description = scanner.nextLine();
        System.out.println("Enter product price: \n");
        double price = getValidDoubleInput();
        System.out.println("Enter product category: \n");
        String category = scanner.nextLine();
        System.out.println("Enter initial stock amount: \n");
        int stockAmount = getValidIntInput();
        administrator.create(productName, description, price, category, stockAmount, dataContainer.getProducts());
        System.out.println("Product created successfully.");
    }

    public void handleMostExpensiveOrderReport() {
        System.out.println("REPORT MOST EXPENSIVE ORDER");
        Order mostExpensive = administrator.report(dataContainer.getOrders());
        if (mostExpensive != null) {
            System.out.println("Order #" + mostExpensive.getNumber() + "\nTotal: $" + mostExpensive.calculateTotal() + "\nDate: " + mostExpensive.getDate());
        } else {
            System.out.println("No orders available to generate a report.\n");
        }
    }

    public void handleLowestInventoryProductReport() {
        System.out.println("REPORT LOWEST INVENTORY PRODUCT");
        Product lowestInventory = administrator.report(dataContainer.getProducts());
        if (lowestInventory != null) {
            System.out.println("Name: " + lowestInventory.getName() + "\nID: " + lowestInventory.getId() + "\nStock: " + lowestInventory.getStockAmount());
        } else {
            System.out.println("No products available to generate a report.\n");
        }
    }

    private double getValidDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
    }
    
    private int getValidIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer:");
            }
        }
    }  
    
    public boolean checkPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean emailIsAvailable(String email) {
        for (User user : dataContainer.getUsers()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
}