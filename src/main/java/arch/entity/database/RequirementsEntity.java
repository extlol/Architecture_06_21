package arch.entity.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "F_REQUIREMENTS")
public class RequirementsEntity {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "WORKING_CUSTOMER")
    private Integer workingCustomer;

    @Column(name = "SPECIFICATION")
    private String specification;

    @Column(name = "STATUS")
    private String status;

    public RequirementsEntity(Integer orderId, String status, String specification) {
        this.orderId = orderId;
        this.status = status;
        this.specification = specification;
    }
}
