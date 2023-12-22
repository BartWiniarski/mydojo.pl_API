package pl.mydojo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.mydojo.exceptions.trainingGroup.NotAssignedToTrainingGroupException;
import pl.mydojo.exceptions.trainingGroup.TrainingGroupNotFoundException;
import pl.mydojo.exceptions.venue.VenueNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class VenueExceptionHandler {

    @ExceptionHandler(VenueNotFoundException.class)
    public ResponseEntity<?> handleVenueNotFoundException(VenueNotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "venue-001");
        response.put("message", e.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}