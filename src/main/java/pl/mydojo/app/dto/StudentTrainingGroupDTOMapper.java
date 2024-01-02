package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.TrainingGroup;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentTrainingGroupDTOMapper implements Function<TrainingGroup, StudentTrainingGroupDTO> {

    @Override
    public StudentTrainingGroupDTO apply(TrainingGroup trainingGroup) {
        List<String> schedules = trainingGroup.getSchedules()
                .stream()
                .map(s ->
                        s.getDayOfWeek().toString() + " " +
                                s.getTime() + " " +
                                s.getVenue().getName() + " "
                )
                .collect(Collectors.toList());

        List<String> trainers = trainingGroup.getTrainers()
                .stream()
                .map(t -> t.getFirstName() + " " + t.getLastName())
                .collect(Collectors.toList());


        return new StudentTrainingGroupDTO(
                trainingGroup.getId(),
                trainingGroup.getName(),
                trainingGroup.getDescription(),
                schedules,
                trainers
        );
    }
}
