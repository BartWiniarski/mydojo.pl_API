package pl.mydojo.exceptions.trainer;

public class TrainerNotFoundException extends RuntimeException {

    public TrainerNotFoundException(Long id){
        super("Trainer with provided ID: " + id + " does not exist.");
    }

}
