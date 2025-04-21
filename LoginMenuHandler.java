import java.util.Scanner;

public class LoginMenuHandler {
    private Scanner scanner;
    private Administrator administrator;
    private UserInterface userInterface;
    private DataContainer dataContainer;
    private ShoppingCart shoppingCart;

    public LoginMenuHandler(Administrator administrator, UserInterface userInterface, DataContainer dataContainer, ShoppingCart shoppingCart) {
        this.scanner = new Scanner(System.in);
        this.administrator = administrator;
        this.userInterface = userInterface;
        this.dataContainer = dataContainer;
        this.shoppingCart = shoppingCart;
    }

    public void handleLoginMenu() {
        while (true) {
            userInterface.displayLoginMenu();
            int option = getValidatedInput("Choose an option: ");
            switch (option) {
                case 0 -> {
                    System.out.println("Exiting Login Menu.\n");
                    return;
                }
                case 1 -> handleLogin();
                default -> System.out.println("Invalid Option. Please try again.\n");
            }
        }
    }

    public void handleLogin() {
        System.out.println("LOGIN\n");
        System.out.print("email: ");
        String email = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();
        User user = authenticate(email, password);
        if (user != null) {
            System.out.println("Login successful!\n");
            redirectToUserMenu(user);
        } else {
            System.out.println("Invalid email or password. Please try again.\n");
        }
    }

    public void redirectToUserMenu(User user) {
        if (user instanceof Administrator) {
            System.out.println("Redirecting to Administrator Menu");
            AdministratorMenuHandler administratorMenu = new AdministratorMenuHandler(administrator, userInterface, dataContainer);
            administratorMenu.handleAdministratorMenu();
        } else if (user instanceof Customer) {
            System.out.println("Redirecting to Customer Menu");
            CustomerMenuHandler customerMenu = new CustomerMenuHandler(userInterface, shoppingCart, dataContainer);
            customerMenu.handleCustomerMenu();
        } else {
            System.out.println("Unknown user type. Access denied.\n");
        }
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
    
    private User authenticate(String email, String password) {
        for (User user : dataContainer.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}