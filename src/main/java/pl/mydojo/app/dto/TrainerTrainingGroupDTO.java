package pl.mydojo.app.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerTrainingGroupDTO {
    private Long id;
    private String name;
    private String description;
    private List<String> schedules;
    private List<String> students;
    private List<String> trainers;
}
