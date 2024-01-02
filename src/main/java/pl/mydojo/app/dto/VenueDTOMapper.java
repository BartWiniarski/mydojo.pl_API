package pl.mydojo.app.dto;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.Venue;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class VenueDTOMapper implements Function<Venue, VenueDTO> {

    @Override
    public VenueDTO apply(Venue venue) {

        List<Long> schedulesId = venue.getSchedules()
                .stream()
                .map(s -> s.getId())
                .collect(Collectors.toList());

        return new VenueDTO(
                venue.getId(),
                venue.getName(),
                venue.getAddress(),
                schedulesId
        );
    }
}
