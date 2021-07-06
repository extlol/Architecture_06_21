package arch.entity.customer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Authentication {
    @NotNull @NotEmpty
    private String login;
    @NotNull @NotEmpty
    private String password;
}
