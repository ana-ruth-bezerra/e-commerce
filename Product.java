import java.io.Serializable;

public class Product implements Serializable {
    private static int nextId = 1;
    private int id;
    private String name;
    private String description;
    private double price;
    private int stockAmount;
    private String category;

    public Product(String name, String description, double price, int stockAmount, String category) {
        this.id = nextId++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public String getCategory() {
        return category;
    }

    public boolean decrementStock(int amount) {
        if (amount > 0 && stockAmount >= amount) {
            this.stockAmount -= amount;
            System.out.println("Stock decremented by " + amount);
            System.out.println("Current stock: " + stockAmount);
            return true;
        } else {
            System.out.println("Insufficient stock or invalid amount.");
            return false;
        }
    }

    public boolean hasStock() {
        return stockAmount > 0;
    }

    public boolean canFulfillOrder(int amount) {
        return stockAmount >= amount;
    }
}