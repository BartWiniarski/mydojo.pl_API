package pl.mydojo.security.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mydojo.security.registration.RegisterRequest;
import pl.mydojo.security.registration.RegisterResponse;

import java.io.IOException;

@RestController
@RequestMapping("v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String token = authenticationService.register(request).getAccessToken();
        RegisterResponse response = new RegisterResponse("User registered successfully", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refreshToken(
            @RequestHeader("Authorization") String token) throws IOException {
        return ResponseEntity.ok(authenticationService.refreshToken(token));
    }
}
