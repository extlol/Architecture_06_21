package arch;

import arch.entity.customer.Authentication;
import arch.entity.customer.Customer;
import arch.entity.customer.Role;
import arch.entity.database.CustomerEntity;
import arch.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;

@Component
public class Workspace {
    private final DatabaseService database;
    private final HashMap<String, Customer> users;

    @Autowired
    public Workspace(DatabaseService database) {
        this.database = database;
        users = new HashMap<>();
    }

    public void authUser(String sessionId, Authentication authentication) {
        CustomerEntity customer = database.getCustomer(authentication.getLogin());
        if (Objects.equals(customer.getPassword().hashCode(), authentication.getPassword().hashCode())) {
            addUser(sessionId, new Customer(
                    customer.getId(),
                    sessionId,
                    authentication.getLogin(),
                    Role.valueOf(customer.getRole()),
                    customer.getFirstName(),
                    customer.getSecondName(),
                    customer.getMsisdn(),
                    database.getWorkList(customer.getId(), Role.valueOf(customer.getRole()))
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
