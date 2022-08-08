package one.digitalinnovation.parking.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkedControllerTest extends AbstractContainerBase{

  @LocalServerPort
  private int randomPort;

  @BeforeEach
  public void setUpTest() {
    RestAssured.port = randomPort;
  }

  @Test
  void whenTryingParkWithParkedPendingThenThrowVehicleAlredyParkedException(){
    assertThrows(VehicleAlreadyParkedException.class, () -> {
      var vehicleCreateDTO = new ParkingCreateDTO();
      vehicleCreateDTO.setColor("AMARELO");
      vehicleCreateDTO.setLicense("WRT-5555");
      vehicleCreateDTO.setModel("BRASILIA");
      vehicleCreateDTO.setState("SP");

      var parkingCreateDTO = new ParkingCreateDTO();
      parkingCreateDTO.setCapacity(10);
      parkingCreateDTO.setName("Estacionamento Teste");

      VehicleDTO vehicleDTO = RestAssured.given()
          .when()
          .auth().basic("user", "12345")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(vehicleCreateDTO)
          .post("/vehicle")
              .getBody().as(VehicleDTO.class);

      ParkingDTO parkingDTO = RestAssured.given()
          .when()
          .auth().basic("user", "12345")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(parkingCreateDTO)
          .post("/parking")
              .getBody().as(ParkingDTO.class);

      ParkedCreateDTO parkedCreateDTO = new ParkedCreateDTO();

      parkedCreateDTO.vehicleId = vehicleDTO.getId();
      parkedCreateDTO.parkingId = parkingDTO.getId();

      RestAssured.given()
          .when()
          .auth().basic("user", "12345")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(parkedCreateDTO)
          .post("/parked/checkin")
          .then()
          .statusCode(HttpStatus.CREATED.value())
          .body("vehicleId", Matchers.equalTo(vehicleDTO.getId()))
          .body("parkingId", Matchers.equalTo(parkingDTO.getId()));

      RestAssured.given()
          .when()
          .auth().basic("user", "12345")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(parkedCreateDTO)
          .post("/parked/checkin")
          .then()
          .statusCode(HttpStatus.FORBIDDEN.value());
    });
  }

  @Test
  void whenTryingParkWithMaximumCapacityReachedThenThrowParkingCapacityExceeded(){
    assertThrows(ParkingCapacityExceededException.class, () -> {
      var vehicleCreateDTO = new ParkingCreateDTO();
      vehicleCreateDTO.setColor("AMARELO");
      vehicleCreateDTO.setLicense("WRT-5555");
      vehicleCreateDTO.setModel("BRASILIA");
      vehicleCreateDTO.setState("SP");

      var parkingCreateDTO = new ParkingCreateDTO();
      parkingCreateDTO.setCapacity(0);
      parkingCreateDTO.setName("Estacionamento Teste");

      VehicleDTO vehicleDTO = RestAssured.given()
          .when()
          .auth().basic("user", "12345")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(vehicleCreateDTO)
          .post("/vehicle")
          .getBody().as(VehicleDTO.class);

      ParkingDTO parkingDTO = RestAssured.given()
          .when()
          .auth().basic("user", "12345")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(parkingCreateDTO)
          .post("/parking")
          .getBody().as(ParkingDTO.class);

      ParkedCreateDTO parkedCreateDTO = new ParkedCreateDTO();

      parkedCreateDTO.vehicleId = vehicleDTO.getId();
      parkedCreateDTO.parkingId = parkingDTO.getId();

      RestAssured.given()
          .when()
          .auth().basic("user", "12345")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(parkedCreateDTO)
          .post("/parked/checkin")
          .then()
          .statusCode(HttpStatus.FORBIDDEN.value());
    });
  }


}
