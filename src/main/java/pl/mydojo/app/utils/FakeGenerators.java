package pl.mydojo.app.utils;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import pl.mydojo.app.entities.Role;
import pl.mydojo.app.entities.RoleType;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.services.UserService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class FakeGenerators {
    private final UserService userService;

    @Autowired
    public FakeGenerators(UserService userService) {
        this.userService = userService;
    }

    public void usersGenerator(String type, int number, int minAge, int maxAge) {

        Faker faker = new Faker(new Locale("pl-PL"));

        for (int i = 0; i < number; i++) {

            final String userFirstName = faker.name().firstName();
            final String userLastName = faker.name().lastName();

            final Date date = faker.date().birthday(minAge, maxAge);
            final LocalDate userDob = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            final String userEmail = userFirstName + "." + userLastName + "@" + type;

            Role role;
            switch (type) {
                case "admin":
                    role = new Role(1L, RoleType.ADMIN);
                    break;
                case "trener":
                    role = new Role(2L, RoleType.TRAINER);
                    break;
                case "student":
                    role = new Role(3L, RoleType.STUDENT);
                    break;
                default:
                    throw new IllegalArgumentException("Nieobsługiwany typ: " + type);
            }

            List<Role> assignedRoles = new ArrayList<>() {{
                add(role);
            }};

            User user = User.builder()
                    .firstName(userFirstName)
                    .lastName(userLastName)
                    .dob(userDob)
                    .email(userEmail)
                    .password(BCrypt.hashpw(type, BCrypt.gensalt(10)))
                    .roles(assignedRoles)
                    .build();

            userService.addNewUser(user);
        }
    }
}
