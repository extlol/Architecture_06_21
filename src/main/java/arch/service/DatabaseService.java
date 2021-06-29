package arch.service;

import arch.entity.database.CustomerEntity;
import arch.reposetory.CustomerJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    private final CustomerJPA customerJPA;

    @Autowired
    public DatabaseService(CustomerJPA customerJPA) {
        this.customerJPA = customerJPA;
    }

    public CustomerEntity getCustomer(String login) {
        return customerJPA.findByLogin(login);
    }
}
