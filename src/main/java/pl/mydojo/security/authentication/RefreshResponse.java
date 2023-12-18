package pl.mydojo.security.authentication;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshResponse {
    private String message;
    private String accessToken;
}
