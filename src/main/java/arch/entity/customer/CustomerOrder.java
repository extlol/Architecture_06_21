package arch.entity.customer;

import lombok.Getter;

import java.util.Set;

@Getter
public class CustomerOrder extends WorkList {
    private final Set<CustomerRequirements> requirements;
    private final Integer managerOrder;
    private final Integer logistOrder;
    private final String description;

    public CustomerOrder(Integer id, String status, Set<CustomerRequirements> requirements,
                         Integer managerOrder, Integer logistOrder, String description) {
        super(id, status);
        this.requirements = requirements;
        this.managerOrder = managerOrder;
        this.logistOrder = logistOrder;
        this.description = description;
    }
}
