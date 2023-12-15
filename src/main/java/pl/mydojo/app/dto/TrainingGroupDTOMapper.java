package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.TrainingGroup;
import pl.mydojo.app.entities.User;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TrainingGroupDTOMapper implements Function<TrainingGroup, TrainingGroupDTO> {

    @Override
    public TrainingGroupDTO apply(TrainingGroup trainingGroup) {

        int numberOfStudents = 0;
        if (trainingGroup.getStudents() != null) {
            numberOfStudents = trainingGroup.getStudents().size();
        }

        String trainers = null;
        if (!trainingGroup.getTrainers().isEmpty()) {
            trainers = trainingGroup.getTrainers()
                    .stream()
                    .map(trainer -> trainer.getFirstName() + " " + trainer.getLastName())
                    .collect(Collectors.joining(", "));
        }

        return new TrainingGroupDTO(
                trainingGroup.getId(),
                trainingGroup.getName(),
                trainingGroup.getDescription(),
                numberOfStudents,
                trainers
        );
    }
}
