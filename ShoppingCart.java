import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.io.Serializable;

public class ShoppingCart implements Serializable {
    private DataContainer dataContainer;

    public ShoppingCart(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public boolean isEmpty() {
        return dataContainer.getProductsInCart().isEmpty();
    }

    public void clear() {
        dataContainer.getProductsInCart().clear();
    }

    public void addProduct(Product product, int amount) {
        int productId = product.getId();
        if (dataContainer.getProductsInCart() == null) {
            dataContainer.setProductsInCart(new HashMap<>());
        }      
        if (product.canFulfillOrder(amount)) {
            int newAmount = dataContainer.getProductsInCart().getOrDefault(productId, 0) + amount;
            dataContainer.getProductsInCart().put(productId, newAmount);
            product.decrementStock(amount);
        } else {
            System.out.println("Insufficient stock for product: " + product.getName());
        }
    }    
    
    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Integer,Integer> entry : dataContainer.getProductsInCart().entrySet()) {
            int productId = entry.getKey();
            int amount = entry.getValue();
            Product product = dataContainer.getProducts().get(productId);
            if (product != null) {
                total += product.getPrice() * amount;
            }
        }
        return total;
    }    

    public void display() {
        if (isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("Items in your shopping cart:");
        for (Map.Entry<Integer,Integer> entry : dataContainer.getProductsInCart().entrySet()) {
            int productId = entry.getKey();
            int amount = entry.getValue();
            Product product = dataContainer.getProducts().get(productId);
            if (product != null) {
                System.out.printf("Product: %s | ID: %d | Price: $%.2f | Quantity: %d | Subtotal: $%.2f%n",
                    product.getName(), product.getId(), product.getPrice(), amount, product.getPrice() * amount);
            }
        }
        System.out.printf("Total: $%.2f%n", calculateTotal());
    }    

    public Order finishOrder() {
        if (isEmpty()) {
            System.out.println("Your cart is empty. Cannot finish order.\n");
            return null;
        }
        LocalDate currentDate = LocalDate.now();
        Order order = new Order(currentDate);
        for (Map.Entry<Integer,Integer> entry : dataContainer.getProductsInCart().entrySet()) {
            int productId = entry.getKey();
            int amount = entry.getValue();
            Product product = dataContainer.getProducts().get(productId);
            if (product != null) {
                order.addProductToOrder(product, amount);
            }
        }
        return order;
    }    
}