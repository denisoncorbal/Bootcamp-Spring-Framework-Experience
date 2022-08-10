package one.digitalinnovation.parking.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;
import one.digitalinnovation.parking.controller.dto.ParkedCheckinDTO;
import one.digitalinnovation.parking.controller.dto.ParkedCreateDTO;
import one.digitalinnovation.parking.controller.dto.ParkedCheckoutDTO;
import one.digitalinnovation.parking.model.Parked;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.model.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ParkedMapper {

  private static final ModelMapper MODEL_MAPPER = new ModelMapper();

  public ParkedCheckoutDTO toParkedCheckoutDTO(Parked parked) {
    return MODEL_MAPPER.map(parked, ParkedCheckoutDTO.class);
  }

  public ParkedCheckinDTO toParkedCheckinDTO(Parked parked){
    //return MODEL_MAPPER.map(parked, ParkedCheckinDTO.class);
    ParkedCheckinDTO parkedCheckinDTO = new ParkedCheckinDTO();
    parkedCheckinDTO.setId(parked.getId());
    parkedCheckinDTO.setParkingId(parked.getParking().getId());
    parkedCheckinDTO.setVehicleId(parked.getVehicle().getId());
    return parkedCheckinDTO;
  }

  public List<ParkedCheckoutDTO> toParkedCheckoutDTOList(List<Parked> parkedList) {
    return parkedList.stream().map(this::toParkedCheckoutDTO).collect(Collectors.toList());
  }

  public Parked toParked(ParkedCheckoutDTO dto) {
    return MODEL_MAPPER.map(dto, Parked.class);
  }

  public Parked toParkedCreate(ParkedCreateDTO dto) {
    // return MODEL_MAPPER.map(dto, Parked.class);
    Vehicle vehicle = new Vehicle();
    vehicle.setId(dto.getVehicleId());
    Parking parking = new Parking();
    parking.setId(dto.getParkingId());
    Parked parked = new Parked();
    parked.setVehicle(vehicle);
    parked.setParking(parking);
    return parked;
  }
}
