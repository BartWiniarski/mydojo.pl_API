package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.dto.VenueDTO;
import pl.mydojo.app.dto.VenueDTOMapper;
import pl.mydojo.app.entities.Schedule;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.entities.Venue;
import pl.mydojo.app.repositories.ScheduleRepository;
import pl.mydojo.app.repositories.VenueRepository;
import pl.mydojo.exceptions.schedule.ScheduleNotFoundException;
import pl.mydojo.exceptions.trainingGroup.TrainingGroupNotFoundException;
import pl.mydojo.exceptions.user.UserNotFoundException;
import pl.mydojo.exceptions.venue.VenueNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenueService {

    private final VenueRepository venueRepository;
    private final VenueDTOMapper venueDTOMapper;
    private final ScheduleRepository scheduleRepository;

    public VenueService(VenueRepository venueRepository,
                        VenueDTOMapper venueDTOMapper,
                        ScheduleRepository scheduleRepository) {
        this.venueRepository = venueRepository;
        this.venueDTOMapper = venueDTOMapper;
        this.scheduleRepository = scheduleRepository;
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
        return venueDTOMapper.apply(venueRepository.findVenueById(id));
    }

    public Venue addNewVenue(VenueDTO venueDTO) {

        Venue newVenue = Venue.builder()
                .name(venueDTO.getName())
                .address(venueDTO.getAddress())
                .build();

        return venueRepository.save(newVenue);
    }

    public void updateVenueById(Long id, VenueDTO venueDTO) {

        if (!venueRepository.existsById(id)) {
            throw new VenueNotFoundException(id);
        }

        Venue venue = venueRepository.findVenueById(id);

        if (venueDTO.getName() != null) {
            venue.setName(venueDTO.getName());
        }
        if (venueDTO.getAddress() != null) {
            venue.setAddress(venueDTO.getAddress());
        }
        venueRepository.save(venue);

    }

    public void deleteVenueById(Long id) {

        if (!venueRepository.existsById(id)) {
            throw new VenueNotFoundException(id);
        }

        venueRepository.deleteById(id);
    }
}
