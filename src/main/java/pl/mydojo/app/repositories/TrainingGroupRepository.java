package pl.mydojo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.mydojo.app.entities.TrainingGroup;

import java.util.List;

@Repository
public interface TrainingGroupRepository extends JpaRepository<TrainingGroup, Long> {

    TrainingGroup findTrainingGroupById(Long id);

    List<TrainingGroup> findAllByStudentsId(Long id);

    List<TrainingGroup> findAllByTrainersId(Long id);

    long count();
}
