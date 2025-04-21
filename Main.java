import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        DataContainer dataContainer = DataManager.loadData();
        UserInterface userInterface = new UserInterface();
        ShoppingCart shoppingCart = new ShoppingCart(dataContainer);
        Administrator administrator = new Administrator("Admin", "admin", "admin", dataContainer);
        dataContainer.getUsers().add(administrator);
        LoginMenuHandler loginMenu = new LoginMenuHandler(administrator, userInterface, dataContainer, shoppingCart);
        loginMenu.handleLoginMenu();
        DataManager.saveData(dataContainer.getUsers(), dataContainer.getProducts(), dataContainer.getOrders(), dataContainer.getProductsInCart());
    }
}