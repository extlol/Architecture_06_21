package arch.entity.customer;

public class CustomerRequirements extends WorkList {
    private Integer workingCustomer;
    private String specification;

    public CustomerRequirements(Integer id, String status, Integer workingCustomer, String specification) {
        super(id, status);
        this.workingCustomer = workingCustomer;
        this.specification = specification;
    }

    public CustomerRequirements(Integer id, String status) {
        super(id, status);
    }
}
