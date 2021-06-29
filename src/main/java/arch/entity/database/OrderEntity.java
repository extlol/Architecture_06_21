package arch.entity.database;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "Orders")
public class OrderEntity {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "MONEY")
    private Float money;

    @Column(name = "CUSTOMER_ID")
    private String description;

    @Column(name = "MANAGER_ORDER")
    private Integer managerOrder;

    @Column(name = "LOGIST_ORDER")
    private Integer logistOrder;

}
