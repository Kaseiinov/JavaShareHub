package kg.attractor.javasharehub.exceptions;

public class SuchEmailAlreadyExistsException extends Exception {
    public SuchEmailAlreadyExistsException() {
        super("Such email already exists");
    }
}
