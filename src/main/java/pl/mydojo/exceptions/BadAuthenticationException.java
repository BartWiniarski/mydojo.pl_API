package pl.mydojo.exceptions;

public class BadAuthenticationException extends RuntimeException{

    public BadAuthenticationException(){
        super("User authentication failed - please check credentials.");
    }
}
