package kg.attractor.javasharehub.exceptions;

import java.util.NoSuchElementException;

public class CategoryNotFoundException extends NoSuchElementException {
  public CategoryNotFoundException(String message) {
    super("Category not found: ");
  }
}
