package pl.mydojo.app.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DojoStatusDTO {
    private long numberOfStudents;
    private long numberOfTrainers;
    private long numberOfTrainingGroups;
    private long numberOfEnabledUsers;
    private long numberOfDisabledUsers;
}
