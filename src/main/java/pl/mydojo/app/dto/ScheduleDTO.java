package pl.mydojo.app.dto;

import jakarta.persistence.*;
import lombok.*;
import pl.mydojo.app.entities.DayOfWeek;
import pl.mydojo.app.entities.TrainingGroup;
import pl.mydojo.app.entities.Venue;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime time;
    private long venueId;
    private long trainingGroupId;
}
