package pl.mydojo.exceptions.trainingGroup;

public class NotAssignedToTrainingGroupException extends RuntimeException{

    public NotAssignedToTrainingGroupException(){
        super("User is not assigned to any Training Group");
    }
}
