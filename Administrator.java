import java.util.List;
import java.util.Map;
import java.io.Serializable;

public class Administrator extends User implements Serializable {
    private DataContainer dataContainer;

    public Administrator(String name, String email, String password, DataContainer dataContainer) {
        super(name, email, password);
        this.dataContainer = dataContainer;
    }

    public User create(String name, String email, String password, String confirmPassword, String address, boolean isAdministrator, List<User> userDatabase) {
        User newUser;
        if (isAdministrator) {
            newUser = new Administrator(name, email, password, dataContainer);
        } else {
            newUser = new Customer(name, email, password, address, dataContainer);
        }
        userDatabase.add(newUser);
        return newUser;
    }

    public Product create(String name, String description, double price, String category, int initialStock, Map<Integer, Product> productMap) {
        Product product = new Product(name, description, price, initialStock, category);
        productMap.put(product.getId(), product);
        return product;
    }

    public Order report(List<Order> orders) {
        if (orders.isEmpty()) {
            return null;
        }
        Order mostExpensive = orders.get(0);
        for (Order order : orders) {
            if (order.calculateTotal() > mostExpensive.calculateTotal()) {
                mostExpensive = order;
            }
        }
        return mostExpensive;
    }

    public Product report(Map<Integer, Product> productMap) {
        if (productMap.isEmpty()) {
            return null;
        }
        Product lowestStockProduct = null;
        for (Product product : productMap.values()) {
            if (lowestStockProduct == null || product.getStockAmount() < lowestStockProduct.getStockAmount()) {
                lowestStockProduct = product;
            }
        }
        return lowestStockProduct;
    }
}