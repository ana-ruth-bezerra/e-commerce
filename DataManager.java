import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DataManager {
    private static final String FILE_PATH = "ecommerce.dat";

    public static DataContainer loadData() {
        System.out.println("Loading data...");
        DataContainer loadedData = SerializationUtil.loadData(FILE_PATH);
        if (loadedData != null) {
            System.out.println("Data loaded successfully from " + FILE_PATH);
            return loadedData;
        } else {
            System.out.println("No saved data found. Starting fresh.");
            return new DataContainer(new ArrayList<>(), new HashMap<>(), new ArrayList<>(), new HashMap<>());
        }
    }

    public static void saveData(List<User> users, Map<Integer, Product> products, List<Order> orders, Map<Integer,Integer> productsInCart) {
        System.out.println("Saving data...");
        DataContainer data = new DataContainer(users, products, orders, productsInCart);
        SerializationUtil.saveData(data, FILE_PATH);
        System.out.println("Data saved successfully to " + FILE_PATH);
    }
}