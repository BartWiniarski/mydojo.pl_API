package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.utils.AgeUtil;

import java.util.function.Function;

@Service
public class UserProfileAdminDTOMapper implements Function<User, UserProfileAdminDTO> {

    @Override
    public UserProfileAdminDTO apply(User user) {

        int age = AgeUtil.calculateAge(user);

        return new UserProfileAdminDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDob(),
                age,
                user.getEmail(),
                user.getRoles(),
                user.getEnabled()
        );
    }
}
