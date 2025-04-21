import java.io.Serializable;

public class User implements Serializable {
    private static int nextId = 1;
    private int id;
    private String email;
    private String name;
    private String password;

    protected User(String name, String email, String password) {
        this.id = nextId++;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    protected String getPassword() {
        return password;
    }
}