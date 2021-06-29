package arch;

import arch.entity.Customer;
import arch.entity.Role;
import arch.entity.database.CustomerEntity;
import arch.service.DatabaseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Component
public class Workspace {
    private final DatabaseService database;
    private final HashMap<String, Customer> users;

    @Autowired
    public Workspace(DatabaseService database) {
        this.database = database;
        users = new HashMap<>();
    }

    public void authUser(String sessionId, String login, String password) {
        CustomerEntity customer = database.getCustomer(login);
        if (Objects.equals(customer.getPassword().hashCode(), password.hashCode())) {
            addUser(sessionId, new Customer(
                    customer.getId(),
                    sessionId,
                    login,
                    Role.valueOf(customer.getRole()),
                    customer.getFirstName(),
                    customer.getSecondName(),
                    customer.getMsisdn()
            ));
        } else {
            throw new SecurityException("Password incorrect");
        }
    }

    public void addUser(String sessionId, Customer customer) {
        users.put(sessionId, customer);
    }

    public Customer getCustomer(String sessionId) {
        return users.get(sessionId);
    }

    public void removeUser(String sessionId) {
        users.remove(sessionId);
    }
}
