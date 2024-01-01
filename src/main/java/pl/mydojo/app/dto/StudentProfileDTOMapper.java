package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.utils.AgeUtil;

import java.util.function.Function;


@Service
public class StudentProfileDTOMapper implements Function<User, StudentProfileDTO> {

    @Override
    public StudentProfileDTO apply(User user) {

        int age = AgeUtil.calculateAge(user);

        return new StudentProfileDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                age
        );
    }
}
