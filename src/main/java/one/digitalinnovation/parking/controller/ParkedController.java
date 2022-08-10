package one.digitalinnovation.parking.controller;

import io.swagger.annotations.Api;
import one.digitalinnovation.parking.controller.dto.ParkedCheckinDTO;
import one.digitalinnovation.parking.controller.dto.ParkedCreateDTO;
import one.digitalinnovation.parking.controller.dto.ParkedCheckoutDTO;
import one.digitalinnovation.parking.controller.mapper.ParkedMapper;
import one.digitalinnovation.parking.model.Parked;
import one.digitalinnovation.parking.service.ParkedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parked")
@Api(tags = "Parked Controller")
public class ParkedController {

  private final ParkedService parkedService;

  private final ParkedMapper parkedMapper;

  public ParkedController(ParkedService parkedService, ParkedMapper parkedMapper) {
    this.parkedMapper = parkedMapper;
    this.parkedService = parkedService;
  }

  @PostMapping("/checkin")
  public ResponseEntity<ParkedCheckinDTO> checkin(@RequestBody ParkedCreateDTO parkedCreateDTO) {
    var parkedCreate = parkedMapper.toParkedCreate(parkedCreateDTO);
    var parked = parkedService.create(parkedCreate);
    var result = parkedMapper.toParkedCheckinDTO(parked);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @PutMapping("/checkout/{id}")
  public ResponseEntity<ParkedCheckoutDTO> checkout(@PathVariable Long id,
      @RequestBody ParkedCreateDTO parkedCreateDTO) {
    Parked parkedUpdate = parkedMapper.toParkedCreate(parkedCreateDTO);
    Parked parked = parkedService.update(id, parkedUpdate);
    return ResponseEntity.ok(parkedMapper.toParkedCheckoutDTO(parked));
  }

}
