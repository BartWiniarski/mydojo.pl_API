package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.dto.TrainingGroupDTO;
import pl.mydojo.app.dto.TrainingGroupDTOMapper;
import pl.mydojo.app.entities.TrainingGroup;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.repositories.TrainingGroupRepository;
import pl.mydojo.app.repositories.UserRepository;
import pl.mydojo.exceptions.trainingGroup.NotAssignedToTrainingGroupException;
import pl.mydojo.exceptions.trainingGroup.TrainingGroupNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    public TrainingGroupDTO getTrainingGroupById(Long id) {

        if (!trainingGroupRepository.existsById(id)) {
            throw new TrainingGroupNotFoundException(id);
        }

        return trainingGroupDTOMapper.apply(
                trainingGroupRepository.findTrainingGroupById(id));
    }

    public TrainingGroup addNewTrainingGroup(TrainingGroupDTO trainingGroupDTO) {

        List<Long> trainerIds = Optional.ofNullable(
                trainingGroupDTO.getTrainersId()).orElse(Collections.emptyList());
        List<Long> studentIds = Optional.ofNullable(
                trainingGroupDTO.getStudentsId()).orElse(Collections.emptyList());

        TrainingGroup trainingGroup = TrainingGroup.builder()
                .name(trainingGroupDTO.getName())
                .description(trainingGroupDTO.getDescription())
                .trainers(trainerIds
                        .stream()
                        .map(trainerId -> userRepository.findById(trainerId)
                                //TODO poprawić wyjątki, żeby rzucić Trainer not found
                                .orElseThrow(() -> new IllegalStateException("Trainer not found with ID: " + trainerId)))
                        .collect(Collectors.toList()))
                .students(studentIds
                        .stream()
                        .map(studentId -> userRepository.findById(studentId)
                                //TODO poprawić wyjątki, żeby rzucić Student not found
                                .orElseThrow(() -> new IllegalStateException("Student not found with ID: " + studentId)))
                        .collect(Collectors.toList()))
                .build();

        return trainingGroupRepository.save(trainingGroup);
    }

    public void updateTrainingGroupById(Long id, TrainingGroupDTO trainingGroupDTO) {

        if (!trainingGroupRepository.existsById(id)) {
            throw new TrainingGroupNotFoundException(id);
        }

        TrainingGroup trainingGroup = trainingGroupRepository.findTrainingGroupById(id);

        if (trainingGroupDTO.getName() != null) {
            trainingGroup.setName(trainingGroupDTO.getName());
        }
        if (trainingGroupDTO.getDescription() != null) {
            trainingGroup.setDescription(trainingGroupDTO.getDescription());
        }
        if (trainingGroupDTO.getTrainersId() != null) {
            List<User> trainers = trainingGroupDTO.getTrainersId()
                    .stream()
                    .map(trainerDTO -> userRepository.findById(trainerDTO)
                            //TODO poprawić wyjątki, żeby rzucić Trainer not found
                            .orElseThrow(() -> new IllegalStateException("Trainer not found with ID: " + trainerDTO)))
                    .collect(Collectors.toList());

            trainingGroup.setTrainers(trainers);
        }
        if (trainingGroupDTO.getStudentsId() != null) {
            List<User> students = trainingGroupDTO.getStudentsId()
                    .stream()
                    .map(studentDTO -> userRepository.findById(studentDTO)
                            //TODO poprawić wyjątki, żeby rzucić Student not found
                            .orElseThrow(() -> new IllegalStateException("Student not found with ID: " + studentDTO)))
                    .collect(Collectors.toList());

            trainingGroup.setStudents(students);
        }
        trainingGroupRepository.save(trainingGroup);
    }

    public void deleteTrainingGroupById(Long id) {

        if (!trainingGroupRepository.existsById(id)) {
            throw new TrainingGroupNotFoundException(id);
        }
        trainingGroupRepository.deleteById(id);
    }


    //------------------ STUDENTS AND TRAINERS ------------------\\
    public List<TrainingGroupDTO> getStudentTrainingGroups(String userEmailFromToken) {
        User user = userService.getUserByEmail(userEmailFromToken);

        List<TrainingGroup> trainingGroups = trainingGroupRepository
                .findAllByStudentsId(user.getId());

        if (trainingGroups.isEmpty()) {
            throw new NotAssignedToTrainingGroupException();
        }

        return trainingGroups.stream()
                .map(u -> trainingGroupDTOMapper.apply(u))
                .collect(Collectors.toList());
    }

    public List<TrainingGroupDTO> getTrainerTrainingGroups(String userEmailFromToken) {
        User user = userService.getUserByEmail(userEmailFromToken);

        List<TrainingGroup> trainingGroups = trainingGroupRepository
                .findAllByTrainersId(user.getId());

        if (trainingGroups.isEmpty()) {
            throw new NotAssignedToTrainingGroupException();
        }

        return trainingGroups.stream()
                .map(u -> trainingGroupDTOMapper.apply(u))
                .collect(Collectors.toList());
    }
}





