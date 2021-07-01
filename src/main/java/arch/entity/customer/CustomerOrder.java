package arch.entity.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class CustomerOrder implements WorkList {
    private final Integer id;
    private final String status;
    private final Set<CustomerRequirements> requirements;
    private final Integer managerOrder;
    private final Integer logistOrder;
    private final String description;
}
