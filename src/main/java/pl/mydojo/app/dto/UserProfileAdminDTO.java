package pl.mydojo.app.dto;

import lombok.*;
import pl.mydojo.app.entities.Role;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileAdminDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int age;
    private String email;
    private List<Role> roles;

}
