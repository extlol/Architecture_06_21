package arch.service;

import arch.entity.customer.Customer;
import arch.entity.customer.CustomerOrder;
import arch.entity.customer.CustomerRequirements;
import arch.entity.customer.Role;
import arch.entity.database.CustomerEntity;
import arch.entity.database.OrderEntity;
import arch.entity.database.RequirementsEntity;
import arch.entity.factory.FactoryOrder;
import arch.entity.factory.OrderStatus;
import arch.reposetory.CustomerJPA;
import arch.reposetory.OrderJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DatabaseService {
    private final CustomerJPA customerJPA;
    private final OrderJPA orderJPA;

    @Autowired
    public DatabaseService(CustomerJPA customerJPA, OrderJPA orderJPA) {
        this.customerJPA = customerJPA;
        this.orderJPA = orderJPA;
    }

    public CustomerEntity getCustomer(String login) {
        return customerJPA.findByLogin(login);
    }

    public Set<CustomerOrder> getOrderByCustomer(int customerId) {
        return orderJPA.findAllByCuAndCustomerId(customerId).stream().map(x ->
                new CustomerOrder(
                        x.getId(),
                        x.getStatus(),
                        x.getRequirementsEntities().stream()
                                .map(y -> new CustomerRequirements(
                                        y.getId(),
                                        y.getStatus()
                                )).collect(Collectors.toSet()
                        ),
                        x.getManagerOrder(),
                        x.getLogistOrder(),
                        x.getDescription()
                )
        ).collect(Collectors.toSet());
    }

    public void createOrder(Customer customer, FactoryOrder order) {
        orderJPA.save(new OrderEntity(
                OrderStatus.PAID.name(),
                customer.getId(),
                order.getDescription(),
                order.getMoney()
        ));
    }
}
