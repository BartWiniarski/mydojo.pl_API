package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.entities.TrainingGroup;
import pl.mydojo.app.repositories.TrainingGroupRepository;

import java.util.List;

@Service
public class TrainingGroupService {

    private final TrainingGroupRepository trainingGroupRepository;

    public TrainingGroupService(TrainingGroupRepository trainingGroupRepository) {
        this.trainingGroupRepository = trainingGroupRepository;
    }

    //------------------ CRUD ------------------\\

    public List<TrainingGroup> getTrainingGroups() {
        return trainingGroupRepository.findAll();
    }

    public void addNewTrainingGroup(TrainingGroup trainingGroup) {
        trainingGroupRepository.save(trainingGroup);
    }

    public void updateTrainingGroupById(Long id, TrainingGroup trainingGroupUpdated) {
        boolean exists = trainingGroupRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Training Group with provided ID: " + id + " does not exists.");
        }

        TrainingGroup trainingGroup = new TrainingGroup();

        if (trainingGroupUpdated.getName() != null) {
            trainingGroup.setName(trainingGroupUpdated.getName());
        }
        if (trainingGroupUpdated.getDescription() != null) {
            trainingGroup.setDescription(trainingGroupUpdated.getDescription());
        }

        trainingGroup.setId(id);

        trainingGroupRepository.save(trainingGroup);
    }

    public void deleteTrainingGroupById(Long id) {
        boolean exists = trainingGroupRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Training Group with provided ID: " + id + " does not exists.");
        }

        trainingGroupRepository.deleteById(id);
    }
}



