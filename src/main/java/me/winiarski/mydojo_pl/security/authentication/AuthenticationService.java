package me.winiarski.mydojo_pl.security.authentication;

import me.winiarski.mydojo_pl.app.entities.Role;
import me.winiarski.mydojo_pl.app.entities.RoleType;
import me.winiarski.mydojo_pl.app.entities.User;
import me.winiarski.mydojo_pl.app.repositories.RoleRepository;
import me.winiarski.mydojo_pl.app.services.UserService;
import me.winiarski.mydojo_pl.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager){
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {

        Role adminRole = roleRepository.findByType(RoleType.ADMIN)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        List<Role> assignedRoles = new ArrayList<>(){{
            add(adminRole);
        }};

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(assignedRoles)
                .build();
        userService.addNewUser(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userService.getUserByEmail(request.getEmail());

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
