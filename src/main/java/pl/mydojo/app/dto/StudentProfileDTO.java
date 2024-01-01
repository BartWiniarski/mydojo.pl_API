package pl.mydojo.app.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
}
