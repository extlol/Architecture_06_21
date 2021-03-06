package arch.entity.database;

import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "F_CUSTOMERS")
public class CustomerEntity {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @Column(name = "MSISDN")
    private long msisdn;

    @OneToMany
    @JoinColumn(name = "ID", referencedColumnName = "CUSTOMER_ID")
    private Set<OrderEntity> orderEntities;
}
