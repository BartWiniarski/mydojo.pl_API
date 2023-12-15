package pl.mydojo.app.services;

import org.springframework.stereotype.Service;
import pl.mydojo.app.dto.TrainingGroupDTO;
import pl.mydojo.app.dto.TrainingGroupDTOMapper;
import pl.mydojo.app.entities.TrainingGroup;
import pl.mydojo.app.repositories.TrainingGroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingGroupService {

    private final TrainingGroupRepository trainingGroupRepository;
    private final TrainingGroupDTOMapper trainingGroupDTOMapper;

    public TrainingGroupService(TrainingGroupRepository trainingGroupRepository,
                                TrainingGroupDTOMapper trainingGroupDTOMapper) {
        this.trainingGroupRepository = trainingGroupRepository;
        this.trainingGroupDTOMapper = trainingGroupDTOMapper;
    }

    //------------------ CRUD ------------------\\

    public List<TrainingGroupDTO> getTrainingGroups() {
        List<TrainingGroup> trainingGroups = trainingGroupRepository.findAll();

        return trainingGroups.stream()
                .map(u -> trainingGroupDTOMapper.apply(u))
                .collect(Collectors.toList());
    }

    //TODO zmienić reszte metod na DTO mapper
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



