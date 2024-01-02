package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.dto.DojoStatusDTO;
import pl.mydojo.app.entities.RoleType;
import pl.mydojo.app.repositories.TrainingGroupRepository;
import pl.mydojo.app.repositories.UserRepository;

@Service
public class DojoStatusService {

    private final UserRepository userRepository;
    private final TrainingGroupRepository trainingGroupRepository;

    public DojoStatusService(UserRepository userRepository,
                             TrainingGroupRepository trainingGroupRepository) {
        this.userRepository = userRepository;
        this.trainingGroupRepository = trainingGroupRepository;
    }

    public DojoStatusDTO getDojoStatus() {
        long numberOfStudents = userRepository.countByRole(RoleType.STUDENT);
        long numberOfTrainers = userRepository.countByRole(RoleType.TRAINER);
        long numberOfTrainingGroups = trainingGroupRepository.count();
        long numberOfEnabledUsers = userRepository.countByEnabled(true);
        long numberOfDisabledUsers = userRepository.countByEnabled(false);

        return new DojoStatusDTO(
                numberOfStudents,
                numberOfTrainers,
                numberOfTrainingGroups,
                numberOfEnabledUsers,
                numberOfDisabledUsers);
    }

}
