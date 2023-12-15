package pl.mydojo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.mydojo.app.entities.TrainingGroup;

@Repository
public interface TrainingGroupRepository extends JpaRepository<TrainingGroup, Long> {

}
