package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.repositories.VenueRepository;

@Service
public class VenueService {

    private VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository){
        this.venueRepository = venueRepository;
    }


}
