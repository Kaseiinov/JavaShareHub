package kg.attractor.javasharehub.service;

import kg.attractor.javasharehub.exceptions.ErrorResponseBody;
import org.springframework.validation.BindingResult;

import java.util.NoSuchElementException;

public interface ErrorService {
    ErrorResponseBody makeResponse(NoSuchElementException e);

    ErrorResponseBody makeResponse(Exception e);

    ErrorResponseBody makeResponse(BindingResult bindingResult);
}
