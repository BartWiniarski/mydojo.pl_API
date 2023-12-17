package pl.mydojo.app.dto;

import lombok.*;
import pl.mydojo.app.entities.DayOfWeek;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingGroupDTO {
    private Long id;
    private String name;
    private String description;
    private Map<DayOfWeek, LocalTime> schedule;
    private List<Long> students;
    private List<Long> trainers;
}
