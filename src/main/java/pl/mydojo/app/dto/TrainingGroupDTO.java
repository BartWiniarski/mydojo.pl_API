package pl.mydojo.app.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingGroupDTO {
    private Long id;
    private String name;
    private String description;
    private int numberOfStudents;
    private String trainers;

}
