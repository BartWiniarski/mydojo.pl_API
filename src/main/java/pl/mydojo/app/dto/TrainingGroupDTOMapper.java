package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.TrainingGroup;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TrainingGroupDTOMapper implements Function<TrainingGroup, TrainingGroupDTO> {


    @Override
    public TrainingGroupDTO apply(TrainingGroup trainingGroup) {
        List<Long> schedulesId = trainingGroup.getSchedules()
                .stream()
                .map(s-> s.getId())
                .collect(Collectors.toList());

        List<Long> studentsId = trainingGroup.getStudents()
                .stream()
                .map(s-> s.getId())
                .collect(Collectors.toList());

        List<Long> trainersId = trainingGroup.getTrainers()
                .stream()
                .map(t -> t.getId())
                .collect(Collectors.toList());


        return new TrainingGroupDTO(
                trainingGroup.getId(),
                trainingGroup.getName(),
                trainingGroup.getDescription(),
                schedulesId,
                studentsId,
                trainersId
        );
    }
}
