package pl.mydojo.exceptions.schedule;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(Long id) {
        super("Schedule with provided ID: " + id + " does not exist.");
    }

}
