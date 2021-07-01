package arch.entity.customer;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Customer {
    private int id;
    private String sessionId;
    private String login;
    private Role role;
    private String firstName;
    private String secondName;
    private long msisdn;
    private Set<WorkList> workList;
}
