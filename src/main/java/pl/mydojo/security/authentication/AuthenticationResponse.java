package pl.mydojo.security.authentication;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String message;
    private String accessToken;
    private String refreshToken;
    private List<String> roles;
    private String firstName;
}
