package pl.mydojo.app.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingGroupDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> schedulesId;
    private List<Long> studentsId;
    private List<Long> trainersId;
}
