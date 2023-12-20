package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.Venue;
import pl.mydojo.app.repositories.VenueRepository;

import java.util.List;

@Service
public class VenueService {

    private VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository){
        this.venueRepository = venueRepository;
    }


    public List<Venue> getVenues() {
        return venueRepository.findAll();
    }
}
