package pl.mydojo.app.dto;

import lombok.*;
import pl.mydojo.app.entities.DayOfWeek;

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
