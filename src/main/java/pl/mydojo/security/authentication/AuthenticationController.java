package pl.mydojo.security.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mydojo.security.registration.RegisterRequest;
import pl.mydojo.security.registration.RegisterResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String token = authenticationService.register(request).getAccessToken();
        RegisterResponse response = new RegisterResponse("User registered successfully",token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    // @RequestHeader("Authorization") String token może lepiej to? sprawdź
    @PostMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.refreshToken(request,response);
    }
}
