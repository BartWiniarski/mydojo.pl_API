package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.Schedule;

import java.util.function.Function;

@Service
public class ScheduleDTOMapper implements Function<Schedule, ScheduleDTO> {


    @Override
    public ScheduleDTO apply(Schedule schedule) {

        return new ScheduleDTO(
                schedule.getId(),
                schedule.getDayOfWeek(),
                schedule.getTime(),
                schedule.getVenue().getId(),
                schedule.getTrainingGroup().getId()
        );
    }
}