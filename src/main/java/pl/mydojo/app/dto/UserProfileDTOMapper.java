package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.User;

import java.util.function.Function;

@Service
public class UserProfileDTOMapper implements Function<User, UserProfileDTO> {

    @Override
    public UserProfileDTO apply(User user) {

        return new UserProfileDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getDob(),
                user.getEmail()
        );
    }
}
