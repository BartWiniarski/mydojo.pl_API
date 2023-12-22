package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.User;

import java.util.function.Function;

@Service
public class TrainerProfileDTOMapper implements Function<User, TrainerProfileDTO> {

    @Override
    public TrainerProfileDTO apply(User user) {

        return new TrainerProfileDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
