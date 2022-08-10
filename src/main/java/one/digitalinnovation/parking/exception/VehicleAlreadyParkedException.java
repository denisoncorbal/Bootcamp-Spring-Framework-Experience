package one.digitalinnovation.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class VehicleAlreadyParkedException extends RuntimeException {

  public VehicleAlreadyParkedException(String id) {
    super("The vehicle with id: " + id + " is already parked.");
  }
}
