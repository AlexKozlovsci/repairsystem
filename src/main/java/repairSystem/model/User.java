package repairSystem.model;

/**
 * Created by Алексей on 23.04.2017.
 */
import org.springframework.stereotype.Component;


@Component
public class User {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}