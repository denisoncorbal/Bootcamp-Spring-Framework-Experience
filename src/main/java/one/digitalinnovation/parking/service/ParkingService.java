package one.digitalinnovation.parking.service;

import java.util.List;
import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkingService {

  private final ParkingRepository parkingRepository;

  public ParkingService(ParkingRepository parkingRepository) {
    this.parkingRepository = parkingRepository;
  }

  @Transactional(readOnly = true)
  public List<Parking> findAll() {
    return parkingRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Parking findById(Long id) {
    return parkingRepository.findById(id).orElseThrow(
        () -> new ParkingNotFoundException(id));
  }

  @Transactional
  public Parking create(Parking parkingCreate) {
    parkingRepository.save(parkingCreate);
    return parkingCreate;
  }

  @Transactional
  public void delete(Long id) {
    findById(id);
    parkingRepository.deleteById(id);
  }

  @Transactional
  public Parking update(Long id, Parking parkingCreate) {
    Parking parking = findById(id);
    parking.setName(parkingCreate.getName());
    parking.setCapacity(parkingCreate.getCapacity());
    parkingRepository.save(parking);
    return parking;
  }

}
