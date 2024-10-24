package net.littlemad.socialapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    @NotNull(message = "Username is required")
    @Size(min = 1, message = "Username has to be at least 1 char")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password has to be at least 6 chars")
    private String password;

    @NotNull(message = "Username is required")
    @Size(min = 1, message = "Display name has to be at least 1 char")
    private String displayName;
}
