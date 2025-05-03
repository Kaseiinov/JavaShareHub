package kg.attractor.javasharehub.exceptions;

import java.util.NoSuchElementException;

public class FileNotFoundException extends NoSuchElementException {
    public FileNotFoundException() {
        super("File not found");
    }
}
