package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.dto.VenueDTO;
import pl.mydojo.app.dto.VenueDTOMapper;
import pl.mydojo.app.entities.Venue;
import pl.mydojo.app.repositories.VenueRepository;
import pl.mydojo.exceptions.user.UserNotFoundException;
import pl.mydojo.exceptions.venue.VenueNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenueService {

    private final VenueRepository venueRepository;
    private final VenueDTOMapper venueDTOMapper;

    public VenueService(VenueRepository venueRepository,
                        VenueDTOMapper venueDTOMapper){
        this.venueRepository = venueRepository;
        this.venueDTOMapper = venueDTOMapper;
    }

    public List<VenueDTO> getVenues() {
        return venueRepository.findAll()
                .stream()
                .map(v -> venueDTOMapper.apply(v))
                .collect(Collectors.toList());
    }

    public VenueDTO getVenue(long id) {
        if (!venueRepository.existsById(id)) {
            throw new VenueNotFoundException(id);
        }
        return venueDTOMapper.apply(venueRepository.getById(id));
    }
}
