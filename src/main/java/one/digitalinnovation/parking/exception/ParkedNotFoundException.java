package one.digitalinnovation.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkedNotFoundException extends RuntimeException {

  public ParkedNotFoundException(Long id) {
    super("Parked not found with id: " + id);
  }
}
