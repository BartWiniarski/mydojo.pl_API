package pl.mydojo.app.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    //TODO: w przyszłości dodać guardian/wards
}
