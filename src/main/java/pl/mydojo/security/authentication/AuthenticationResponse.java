package pl.mydojo.security.authentication;

import lombok.*;
import pl.mydojo.app.entities.Role;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String message;
    private String token;
    private List<String> roles;
    private String firstName;
}
