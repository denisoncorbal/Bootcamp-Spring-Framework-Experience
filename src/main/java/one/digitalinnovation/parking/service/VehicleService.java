package one.digitalinnovation.parking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import one.digitalinnovation.parking.exception.VehicleNotFoundException;
import one.digitalinnovation.parking.model.Vehicle;
import one.digitalinnovation.parking.repository.VehicleRepository;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElseThrow(
                () -> new VehicleNotFoundException(id));
    }

    @Transactional
    public Vehicle create(Vehicle vehicleCreate) {
        String uuid = getUUID();
        vehicleCreate.setId(uuid);
        vehicleCreate.setEntryDate(LocalDateTime.now());
        vehicleRepository.save(vehicleCreate);
        return vehicleCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        vehicleRepository.deleteById(id);
    }

    @Transactional
    public Vehicle update(String id, Vehicle vehicleCreate) {
        Vehicle vehicle = findById(id);
        vehicle.setColor(vehicleCreate.getColor());
        vehicle.setState(vehicleCreate.getState());
        vehicle.setModel(vehicleCreate.getModel());
        vehicle.setLicense(vehicleCreate.getLicense());
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Transactional
    public Vehicle checkOut(String id) {
        Vehicle vehicle = findById(id);
        vehicle.setExitDate(LocalDateTime.now());
        vehicle.setBill(VehicleCheckOut.getBill(vehicle));
        return vehicleRepository.save(vehicle);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
