package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.TrainingGroup;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TrainingGroupDTOMapper implements Function<TrainingGroup, TrainingGroupDTO> {

    private final StudentProfileDTOMapper studentProfileDTOMapper;
    private final TrainerProfileDTOMapper trainerProfileDTOMapper;

    public TrainingGroupDTOMapper(
            StudentProfileDTOMapper studentProfileDTOMapper,
            TrainerProfileDTOMapper trainerProfileDTOMapper) {
        this.studentProfileDTOMapper = studentProfileDTOMapper;
        this.trainerProfileDTOMapper = trainerProfileDTOMapper;
    }

    @Override
    public TrainingGroupDTO apply(TrainingGroup trainingGroup) {
        List<Long> studentsId = trainingGroup.getStudents()
                .stream()
                .map(s-> s.getId())
                .collect(Collectors.toList());

        List<Long> trainersId = trainingGroup.getTrainers()
                .stream()
                .map(t -> t.getId())
                .collect(Collectors.toList());

//        long venueId = trainingGroup.getVenue().getId();

        return new TrainingGroupDTO(
                trainingGroup.getId(),
                trainingGroup.getName(),
                trainingGroup.getDescription(),
//                trainingGroup.getSchedule(),
                studentsId,
                trainersId
//                venueId
        );
    }
}
