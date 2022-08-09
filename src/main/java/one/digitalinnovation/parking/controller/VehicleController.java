package one.digitalinnovation.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import one.digitalinnovation.parking.controller.dto.VehicleCreateDTO;
import one.digitalinnovation.parking.controller.dto.VehicleDTO;
import one.digitalinnovation.parking.controller.mapper.VehicleMapper;
import one.digitalinnovation.parking.model.Vehicle;
import one.digitalinnovation.parking.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
@Api(tags = "Vehicle Controller")
public class VehicleController {

  private final VehicleService vehicleService;
  private final VehicleMapper vehicleMapper;

  public VehicleController(VehicleService vehicleService, VehicleMapper vehicleMapper) {
    this.vehicleService = vehicleService;
    this.vehicleMapper = vehicleMapper;
  }

  @GetMapping
  @ApiOperation("Find all vehicles")
  public ResponseEntity<List<VehicleDTO>> findAll() {
    List<Vehicle> vehicleList = vehicleService.findAll();
    List<VehicleDTO> result = vehicleMapper.toVehicleDTOList(vehicleList);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<VehicleDTO> findById(@PathVariable String id) {
    Vehicle vehicle = vehicleService.findById(id);
    VehicleDTO result = vehicleMapper.toVehicleDTO(vehicle);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable String id) {
    vehicleService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<VehicleDTO> create(@RequestBody VehicleCreateDTO dto) {
    var vehicleCreate = vehicleMapper.toVehicleCreate(dto);
    var vehicle = vehicleService.create(vehicleCreate);
    var result = vehicleMapper.toVehicleDTO(vehicle);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @PutMapping("/{id}")
  public ResponseEntity<VehicleDTO> update(@PathVariable String id,
      @RequestBody VehicleCreateDTO vehicleCreateDTO) {
    Vehicle vehicleUpdate = vehicleMapper.toVehicleCreate(vehicleCreateDTO);
    Vehicle vehicle = vehicleService.update(id, vehicleUpdate);
    return ResponseEntity.ok(vehicleMapper.toVehicleDTO(vehicle));
  }

  @Deprecated
  @PostMapping("/{id}/exit")
  public ResponseEntity<VehicleDTO> checkOut(@PathVariable String id) {
    //TODO verificar se já não esta fechado e lançar exceção
    Vehicle vehicle = vehicleService.checkOut(id);
    return ResponseEntity.ok(vehicleMapper.toVehicleDTO(vehicle));
  }

}
