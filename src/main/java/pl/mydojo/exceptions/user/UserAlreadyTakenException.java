package pl.mydojo.exceptions.user;

public class UserAlreadyTakenException extends RuntimeException {

    public UserAlreadyTakenException(String email) {
        super("User with provided e-mail: " + email + " already exists.");
    }
}
