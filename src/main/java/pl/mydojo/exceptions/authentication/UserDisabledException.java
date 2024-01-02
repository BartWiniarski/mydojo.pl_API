package pl.mydojo.exceptions.authentication;

public class UserDisabledException extends RuntimeException {

    public UserDisabledException() {
        super("User account has been disabled - please contact administrator.");
    }
}
