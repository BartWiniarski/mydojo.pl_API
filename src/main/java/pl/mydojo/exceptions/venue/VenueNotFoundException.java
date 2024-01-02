package pl.mydojo.exceptions.venue;

public class VenueNotFoundException extends RuntimeException {

    public VenueNotFoundException(Long id){
        super("Venue with provided ID: " + id + " does not exist.");
    }

}
