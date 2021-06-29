package arch.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Customer {
    private int id;
    private String sessionId;
    private String login;
    private Role role;
    private String firstName;
    private String secondName;
    private long msisdn;
}
