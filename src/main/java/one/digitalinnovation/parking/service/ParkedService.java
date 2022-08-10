package one.digitalinnovation.parking.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import one.digitalinnovation.parking.exception.ParkedNotFoundException;
import one.digitalinnovation.parking.exception.ParkingCapacityExceededException;
import one.digitalinnovation.parking.exception.VehicleAlreadyParkedException;
import one.digitalinnovation.parking.model.Parked;
import one.digitalinnovation.parking.repository.ParkedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkedService {

  public static final int ONE_HOUR = 60;
  public static final int TWENTY_FOUR_HOUR = 24 * ONE_HOUR;
  public static final double ONE_HOUR_VALUE = 5.00;
  public static final double ADDITIONAL_PER_HOUR_VALUE = 2.00;
  public static final double DAY_VALUE = 20.00;


  private final ParkedRepository parkedRepository;

  public ParkedService(ParkedRepository parkedRepository) {
    this.parkedRepository = parkedRepository;
  }

  @Transactional
  public Parked create(Parked parkedCreate) {
    parkedCreate.setEntryDate(LocalDateTime.now());
    if(checkParkedPending(parkedCreate).isPresent())
        if(checkParkingCapacity(parkedCreate).isPresent())
          return parkedRepository.save(parkedCreate);
      else
        throw new ParkingCapacityExceededException(parkedCreate.getParking().getId());
    else
      throw new VehicleAlreadyParkedException(parkedCreate.getVehicle().getId());
  }

  @Transactional
  public Parked update(Long id, Parked parkedCreate) {
    Parked parked = findById(id);
    parked.setExitDate(LocalDateTime.now());
    parked.setBill(getBill(parked));
    parkedRepository.save(parked);
    return parked;
  }

  @Transactional(readOnly = true)
  public Parked findById(Long id) {
    return parkedRepository.findById(id).orElseThrow(
        () -> new ParkedNotFoundException(id));
  }

  private static Double getBill(Parked parked) {
    long minutes = parked.getEntryDate().until(parked.getExitDate(), ChronoUnit.MINUTES);
    Double bill = 0.0;
    if (minutes <= ONE_HOUR) {
      return ONE_HOUR_VALUE;
    }
    if (minutes <= TWENTY_FOUR_HOUR) {
      bill = ONE_HOUR_VALUE;
      int hours = (int) (minutes / ONE_HOUR);
      System.out.println(hours);
      for (int i = 0; i < hours; i++) {
        bill += ADDITIONAL_PER_HOUR_VALUE;
      }
      return bill;
    }
    int days = (int) (minutes / TWENTY_FOUR_HOUR);
    System.out.println(days);
    for (int i = 0; i < days; i++) {
      bill += DAY_VALUE;
    }
    return bill;
  }

  private Optional<Parked> checkParkedPending(Parked parked){
    if(parkedRepository.checkParkedPending(parked.getVehicle().getId())){
      parked = null;
    }
    return Optional.ofNullable(parked);
  }

  private Optional<Parked> checkParkingCapacity(Parked parked){
    if(parkedRepository.checkParkingCapacity(parked.getParking().getId())){
      parked = null;
    }
    return Optional.ofNullable(parked);
  }

}
