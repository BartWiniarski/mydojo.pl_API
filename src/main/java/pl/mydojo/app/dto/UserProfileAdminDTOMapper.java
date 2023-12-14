package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.User;

import java.util.function.Function;

@Service
public class UserProfileAdminDTOMapper implements Function<User, UserProfileAdminDTO> {

    @Override
    public UserProfileAdminDTO apply(User user) {

        return new UserProfileAdminDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDob(),
                user.getAge(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
