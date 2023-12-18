package pl.mydojo.exceptions.authentication;

public class BadAuthenticationException extends RuntimeException{

    public BadAuthenticationException(){
        super("User authentication failed - please check credentials.");
    }
}
