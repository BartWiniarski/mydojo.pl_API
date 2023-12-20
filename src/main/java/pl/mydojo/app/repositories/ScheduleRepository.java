package pl.mydojo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mydojo.app.entities.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
