package arch.entity.database;

import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "F_ORDERS")
public class OrderEntity {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "MONEY")
    private Float money;

    @Column(name = "CUSTOMER_ID")
    private Integer customerId;

    @Column(name = "MANAGER_ORDER")
    private Integer managerOrder;

    @Column(name = "LOGIST_ORDER")
    private Integer logistOrder;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany
    @JoinColumn(name = "ID", referencedColumnName = "ORDER_ID")
    private Set<RequirementsEntity> requirementsEntities;

    public OrderEntity(String status, int customerId, String description, Float money) {
        this.status = status;
        this.customerId = customerId;
        this.description = description;
        this.money = money;
    }
}
