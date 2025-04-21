import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class DataContainer implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<User> users;
    private Map<Integer,Product> products;
    private Map<Integer,Integer> productsInCart;
    private List<Order> orders;

    public DataContainer(List<User> users, Map<Integer,Product> products, List<Order> orders, Map<Integer,Integer> productsInCart) {
        this.users = users;
        this.products = products;
        this.orders = orders;
        this.productsInCart = productsInCart != null ? productsInCart : new HashMap<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public Map<Integer,Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Map<Integer,Integer> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(Map<Integer, Integer> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public void displayAvailableProducts() {
        if (getProducts().isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("Available Products:");
        for (Product product : getProducts().values()) {
            System.out.println(String.format("ID: %d - Product: %s (Price: $%.2f, Stock: %d)", product.getId(), product.getName(), product.getPrice(), product.getStockAmount()));
        }
    }
    
    public Product getProductById(int id) {
        return getProducts().get(id);
    }
}