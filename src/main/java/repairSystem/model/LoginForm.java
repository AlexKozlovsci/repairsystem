package repairSystem.model;

/**
 * Created by Алексей on 29.04.2017.
 */

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class LoginForm {
    @Size(min=2, max=30, message = "Username size should be in the range [2...30]")
    private String login;

    @NotNull
    @Size(min=1, max=50)
    private String password;


}