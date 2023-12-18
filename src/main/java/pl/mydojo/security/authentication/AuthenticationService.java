package pl.mydojo.security.authentication;

import org.springframework.security.core.AuthenticationException;
import pl.mydojo.app.entities.Role;
import pl.mydojo.app.entities.RoleType;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.repositories.RoleRepository;
import pl.mydojo.app.repositories.UserRepository;
import pl.mydojo.app.services.UserService;
import pl.mydojo.exceptions.BadAuthenticationException;
import pl.mydojo.exceptions.UserAlreadyTakenException;
import pl.mydojo.exceptions.UserNotFoundException;
import pl.mydojo.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService,
                                 RoleRepository roleRepository,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager){
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {

        String email = request.getEmail();

        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new UserAlreadyTakenException(email);
        }

        Role roleStudent = roleRepository.findByType(RoleType.STUDENT)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        List<Role> assignedRoles = new ArrayList<>(){{
            add(roleStudent);
        }};

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dob(request.getDob())
                .email(email)
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(assignedRoles)
                .build();

        userService.addNewUser(user);
        String jwtToken = jwtService.generateToken( user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new BadAuthenticationException();
        }
        User user = userService.getUserByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);

        List<Role> assignedRoles = userService.getUserRoles(user.getId());
        List<String> roles = assignedRoles
                .stream()
                .map(role -> role.getType().name())
                .collect(Collectors.toList());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstName(user.getFirstName())
                .roles(roles)
                .build();
    }
}
