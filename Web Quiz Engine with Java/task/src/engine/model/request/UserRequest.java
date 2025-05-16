package engine.model.request;

import engine.model.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    @Pattern(regexp = "\\w+@\\w+\\.\\w+")
    private String email;
    @NotBlank
    @Size(min = 5)
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        User user = new User();

        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return user;
    }
}
