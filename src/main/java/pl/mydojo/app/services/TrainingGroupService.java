package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.dto.TrainingGroupDTO;
import pl.mydojo.app.dto.TrainingGroupDTOMapper;
import pl.mydojo.app.entities.TrainingGroup;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.repositories.TrainingGroupRepository;
import pl.mydojo.app.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingGroupService {

    private final TrainingGroupRepository trainingGroupRepository;
    private final TrainingGroupDTOMapper trainingGroupDTOMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    public TrainingGroupService(TrainingGroupRepository trainingGroupRepository,
                                TrainingGroupDTOMapper trainingGroupDTOMapper,
                                UserService userService,
                                UserRepository userRepository) {
        this.trainingGroupRepository = trainingGroupRepository;
        this.trainingGroupDTOMapper = trainingGroupDTOMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //------------------ CRUD ------------------\\

    public List<TrainingGroupDTO> getTrainingGroups() {
        List<TrainingGroup> trainingGroups = trainingGroupRepository.findAll();

        return trainingGroups.stream()
                .map(u -> trainingGroupDTOMapper.apply(u))
                .collect(Collectors.toList());
    }

    public void addNewTrainingGroup(TrainingGroupDTO trainingGroupDTO) {

        TrainingGroup trainingGroup = TrainingGroup.builder()
                .name(trainingGroupDTO.getName())
                .description(trainingGroupDTO.getDescription())
                .schedule(trainingGroupDTO.getSchedule())
                .build();

        trainingGroupRepository.save(trainingGroup);
    }

    public void updateTrainingGroupById(Long id, TrainingGroupDTO trainingGroupUpdated) {

        TrainingGroup trainingGroup = trainingGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Training Group with provided ID: " + id + " not found."));


        if (trainingGroupUpdated.getName() != null) {
            trainingGroup.setName(trainingGroupUpdated.getName());
        }
        if (trainingGroupUpdated.getDescription() != null) {
            trainingGroup.setDescription(trainingGroupUpdated.getDescription());
        }
        if (trainingGroupUpdated.getTrainers() != null) {
            List<User> trainers = trainingGroupUpdated.getTrainers()
                    .stream()
                    .map(trainerDTO -> userRepository.findById(trainerDTO)
                            .orElseThrow(() -> new IllegalStateException("Trainer not found with ID: " + trainerDTO)))
                    .collect(Collectors.toList());

            trainingGroup.setTrainers(trainers);
        }
        if (trainingGroupUpdated.getStudents() != null) {
            List<User> students = trainingGroupUpdated.getStudents()
                    .stream()
                    .map(studentDTO -> userRepository.findById(studentDTO)
                            .orElseThrow(() -> new IllegalStateException("Student not found with ID: " + studentDTO)))
                    .collect(Collectors.toList());

            trainingGroup.setStudents(students);
        }
        if (trainingGroupUpdated.getSchedule() != null) {
            trainingGroup.setSchedule(trainingGroupUpdated.getSchedule());
        }

        trainingGroupRepository.save(trainingGroup);
    }

    public void deleteTrainingGroupById(Long id) {
        boolean exists = trainingGroupRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Training Group with provided ID: " + id + " does not exists.");
        }

        trainingGroupRepository.deleteById(id);
    }

    public List<TrainingGroupDTO> getStudentTrainingGroups(String userEmailFromToken) {
        User user = userService.getUserByEmail(userEmailFromToken);

        List<TrainingGroup> trainingGroups = trainingGroupRepository.findAllByStudentsId(user.getId());

        return trainingGroups.stream()
                .map(u -> trainingGroupDTOMapper.apply(u))
                .collect(Collectors.toList());
    }

    public List<TrainingGroupDTO> getTrainerTrainingGroups(String userEmailFromToken) {
        User user = userService.getUserByEmail(userEmailFromToken);

        List<TrainingGroup> trainingGroups = trainingGroupRepository.findAllByTrainersId(user.getId());

        return trainingGroups.stream()
                .map(u -> trainingGroupDTOMapper.apply(u))
                .collect(Collectors.toList());
    }
}





