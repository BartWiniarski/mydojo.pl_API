package pl.mydojo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mydojo.app.entities.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue,Long> {

}
