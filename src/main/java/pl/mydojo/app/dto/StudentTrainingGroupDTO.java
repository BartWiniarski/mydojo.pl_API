package pl.mydojo.app.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentTrainingGroupDTO {
    private Long id;
    private String name;
    private String description;
    private List<String> schedules;
    private List<String> trainers;
}
