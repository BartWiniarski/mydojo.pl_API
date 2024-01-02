package pl.mydojo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.mydojo.exceptions.authentication.BadAuthenticationException;
import pl.mydojo.exceptions.authentication.UserDisabledException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(BadAuthenticationException.class)
    public ResponseEntity<?> handleBadAuthenticationException(BadAuthenticationException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "authentication-001");
        response.put("message", e.getMessage());
        response.put("status", HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    //authentication-002 in JwtAuthenticationFilter
    //authentication-003 in CustomAccessDeniedHandler
    //authentication-004 in CustomAuthenticationEntryPoint

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<?> handleUserDisabledException(UserDisabledException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "authentication-005");
        response.put("message", e.getMessage());
        response.put("status", HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}