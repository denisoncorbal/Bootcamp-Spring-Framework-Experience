package one.digitalinnovation.parking.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import one.digitalinnovation.parking.model.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import one.digitalinnovation.parking.controller.dto.VehicleCreateDTO;
import one.digitalinnovation.parking.controller.dto.VehicleDTO;

@Component
public class VehicleMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public VehicleDTO toVehicleDTO(Vehicle vehicle) {
        return MODEL_MAPPER.map(vehicle, VehicleDTO.class);
    }

    public List<VehicleDTO> toVehicleDTOList(List<Vehicle> vehicleList) {
        return vehicleList.stream().map(this::toVehicleDTO).collect(Collectors.toList());
    }

    public Vehicle toVehicle(VehicleDTO dto) {
        return MODEL_MAPPER.map(dto, Vehicle.class);
    }

    public Vehicle toVehicleCreate(VehicleCreateDTO dto) {
        return MODEL_MAPPER.map(dto, Vehicle.class);
    }
}
