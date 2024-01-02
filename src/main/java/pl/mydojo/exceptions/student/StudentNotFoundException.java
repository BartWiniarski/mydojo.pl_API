package pl.mydojo.exceptions.student;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id){
        super("Student with provided ID: " + id + " does not exist.");
    }

}
