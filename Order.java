import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.io.Serializable;

public class Order implements Serializable {
    private static int nextNumber = 1;
    private int number;
    private LocalDate date;
    private Map<Product,Integer> productAmount;

    public Order(LocalDate date) {
        this.number = nextNumber++;
        this.date = date;
        this.productAmount = new HashMap<>();
    }

    public LocalDate getDate() {
        return this.date;
    }

    public int getNumber() {
        return this.number;
    }

    public void addProductToOrder(Product product, int amount) {
        productAmount.put(product, amount);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Product,Integer> entry : productAmount.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}