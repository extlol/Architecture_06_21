package arch.service;

import arch.entity.customer.*;
import arch.entity.database.CustomerEntity;
import arch.entity.database.OrderEntity;
import arch.entity.database.RequirementsEntity;
import arch.entity.factory.FactoryOrder;
import arch.entity.factory.OrderStatus;
import arch.reposetory.CustomerJPA;
import arch.reposetory.OrderJPA;
import arch.reposetory.RequirementJPA;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static arch.entity.factory.OrderStatus.ORDER_SEND;

@Service
public class DatabaseService {
    private final CustomerJPA customerJPA;
    private final OrderJPA orderJPA;
    private final RequirementJPA requirementJPA;

    @Autowired
    public DatabaseService(CustomerJPA customerJPA, OrderJPA orderJPA, RequirementJPA requirementJPA) {
        this.customerJPA = customerJPA;
        this.orderJPA = orderJPA;
        this.requirementJPA = requirementJPA;
    }

    public CustomerEntity getCustomer(String login) {
        return customerJPA.findByLogin(login);
    }

    public void createOrder(Customer customer, FactoryOrder order) {
        orderJPA.save(new OrderEntity(
                OrderStatus.PAID.name(),
                customer.getId(),
                order.getDescription(),
                order.getMoney()
        ));
    }

    public Set<WorkList> getWorkList(int customerId, Role role) {
        return switch (role) {
            case MANAGER -> workList(orderJPA.findAllByStatusNotIn(new String[]{ORDER_SEND.name()}));
            case ENGINEER -> engineerList(requirementJPA.findByWorkingCustomerOrWorkingCustomerIsNull(customerId));
            case CLIENT -> workList(orderJPA.findAllByCustomerId(customerId));
            case LOGIST -> workList(orderJPA.findAllByStatusNotIn(
                    new String[]{OrderStatus.PAID.name(), ORDER_SEND.name(), OrderStatus.NONE.name()}));
        };
    }

    private Set<WorkList> engineerList(Set<RequirementsEntity> workList) {
        return workList.stream().map(x -> new CustomerRequirements(
                x.getId(),
                x.getStatus(),
                x.getWorkingCustomer(),
                x.getSpecification()
                )).collect(Collectors.toSet()
        );
    }

    private Set<WorkList> workList(Set<OrderEntity> orders) {
        return orders.stream().map(x -> new CustomerOrder(
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
}
