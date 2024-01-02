package pl.mydojo.app.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueDTO {
    long id;
    private String name;
    private String address;
    private List<Long> schedules;
}
