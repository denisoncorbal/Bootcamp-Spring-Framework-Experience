package one.digitalinnovation.parking.controller.Local;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkedCreateDTO;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import one.digitalinnovation.parking.controller.dto.VehicleCreateDTO;
import one.digitalinnovation.parking.controller.dto.VehicleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;


@DisabledOnOs(OS.LINUX)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkedControllerTest {

  @LocalServerPort
  private int randomPort;

  @BeforeEach
  public void setUpTest() {
    RestAssured.port = randomPort;
  }

  @Test
  void whenTryingParkWithParkedPendingThenThrowVehicleAlredyParkedException() {
    var vehicleCreateDTO = new VehicleCreateDTO();
    vehicleCreateDTO.setColor("AMARELO");
    vehicleCreateDTO.setLicense("WRT-5555");
    vehicleCreateDTO.setModel("BRASILIA");
    vehicleCreateDTO.setState("SP");

    var parkingCreateDTO = new ParkingCreateDTO();
    parkingCreateDTO.setCapacity(10);
    parkingCreateDTO.setName("Estacionamento Teste");

    System.out.println("Post to Vehicle");

    VehicleDTO vehicleDTO = RestAssured.given()
        .when()
        .auth().basic("user", "12345")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(vehicleCreateDTO)
        .post("/vehicle")
        .getBody().as(VehicleDTO.class);

    System.out.println("Vehicle id: " + vehicleDTO.getId());

    System.out.println("Post to Parking");

    ParkingDTO parkingDTO = RestAssured.given()
        .when()
        .auth().basic("user", "12345")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(parkingCreateDTO)
        .post("/parking")
        .getBody().as(ParkingDTO.class);

    System.out.println("Parking id: " + parkingDTO.getId());

    ParkedCreateDTO parkedCreateDTO = new ParkedCreateDTO();

    parkedCreateDTO.setVehicleId(vehicleDTO.getId());
    parkedCreateDTO.setParkingId(parkingDTO.getId());

    System.out.println("Primeiro POST to Parked Checkin");

    RestAssured.given()
        .when()
        .auth().basic("user", "12345")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(parkedCreateDTO)
        .post("/parked/checkin")
        .then()
        .statusCode(HttpStatus.CREATED.value());

    System.out.println("Segundo POST to Parked Checkin");

    RestAssured.given()
        .when()
        .auth().basic("user", "12345")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(parkedCreateDTO)
        .post("/parked/checkin")
        .then()
        .assertThat()
        .statusCode(HttpStatus.FORBIDDEN.value());
  }

  @Test
  void whenTryingParkWithMaximumCapacityReachedThenThrowParkingCapacityExceeded() {

    var vehicleCreateDTO = new VehicleCreateDTO();
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

    parkedCreateDTO.setVehicleId(vehicleDTO.getId());
    parkedCreateDTO.setParkingId(parkingDTO.getId());

    RestAssured.given()
        .when()
        .auth().basic("user", "12345")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(parkedCreateDTO)
        .post("/parked/checkin")
        .then()
        .assertThat()
        .statusCode(HttpStatus.FORBIDDEN.value());
  }

}
