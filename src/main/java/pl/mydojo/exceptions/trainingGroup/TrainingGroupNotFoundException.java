package pl.mydojo.exceptions.trainingGroup;

public class TrainingGroupNotFoundException extends RuntimeException {

    public TrainingGroupNotFoundException(Long id){
        super("Training Group with provided ID: " + id + " does not exist.");
    }

}
