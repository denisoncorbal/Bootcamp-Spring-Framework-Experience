package one.digitalinnovation.parking.controller.Local;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.VehicleCreateDTO;
import org.hamcrest.Matchers;
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
public class VehicleControllerTest {

  @LocalServerPort
  private int randomPort;

  @BeforeEach
  public void setUpTest() {
    RestAssured.port = randomPort;
  }

  @Test
  void whenFindAllThenCheckResult() {
    RestAssured.given()
        .auth().basic("user", "12345")
        .when()
        .get("/vehicle")
        .then()
        .statusCode(HttpStatus.OK.value());
  }

  @Test
  void whenCreateThenCheckIsCreated() {
    var createDTO = new VehicleCreateDTO();
    createDTO.setColor("AMARELO");
    createDTO.setLicense("WRT-5555");
    createDTO.setModel("BRASILIA");
    createDTO.setState("SP");

    RestAssured.given()
        .when()
        .auth().basic("user", "12345")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(createDTO)
        .post("/vehicle")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("license", Matchers.equalTo("WRT-5555"))
        .body("color", Matchers.equalTo("AMARELO"));
  }

}
