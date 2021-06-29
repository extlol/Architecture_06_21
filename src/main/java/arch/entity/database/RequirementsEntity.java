package arch.entity.database;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "REQUIREMENTS")
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

    @Column(name = "StATUS")
    private String status;




}
