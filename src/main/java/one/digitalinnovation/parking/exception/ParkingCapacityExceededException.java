package one.digitalinnovation.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ParkingCapacityExceededException extends RuntimeException {

  public ParkingCapacityExceededException(Long id) {
    super("The parking with id: " + id + " is full.");
  }
}
