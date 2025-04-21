import java.io.Serializable;

public class Customer extends User implements Serializable {
    private String address;
    private DataContainer dataContainer;

    public Customer(String name, String email, String password, String address, DataContainer dataContainer) {
        super(name, email, password);
        this.address = address;
        this.dataContainer = dataContainer;
    }

    public String getAddress() {
        return address;
    }

    public void addOrderToHistory(Order order) {
        dataContainer.getOrders().add(order);
    }
}