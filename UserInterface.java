public class UserInterface{

    public void displayLoginMenu() {
        System.out.println("LOGIN MENU\n");
        System.out.println("0 - Exit\n");
        System.out.println("1 - Login: \n");
    }

    public void displayAdministratorMenu() {
        System.out.println("ADMINISTRATOR MENU\n");
        System.out.println("0 - Exit\n");
        System.out.println("1 - Create New User\n");
        System.out.println("2 - Create New Product\n");
        System.out.println("3 - Report Most Expensive Order\n");
        System.out.println("4 - Report Product With Lowest Inventory\n");
    }

    public void displayCustomerMenu() {
        System.out.println("CUSTOMER MENU\n");
        System.out.println("0 - Exit\n");
        System.out.println("1 - Start New Order\n");
    }

    public void displayStartNewOrder() {
        System.out.println("START NEW ORDER\n");
        System.out.println("0 - Exit\n");
        System.out.println("1 - Add product\n");
        System.out.println("2 - View shopping cart\n");
        System.out.println("3 - Finish order\n");

    }
}