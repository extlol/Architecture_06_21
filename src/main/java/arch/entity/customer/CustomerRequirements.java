package arch.entity.customer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerRequirements implements WorkList {
    private Integer id;
    private Integer workingCustomer;
    private String specification;
    private String status;

    public CustomerRequirements(Integer id, String status) {
        this.id = id;
        this.status = status;
    }
}
