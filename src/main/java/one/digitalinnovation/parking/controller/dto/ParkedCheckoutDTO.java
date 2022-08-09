package one.digitalinnovation.parking.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.model.Vehicle;

public class ParkedCheckoutDTO {

  private Long id;

  private Vehicle vehicle;

  private Parking parking;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime entryDate;
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime exitDate;
  private Double bill;

  public ParkedCheckoutDTO() {
  }

  public ParkedCheckoutDTO(Long id, Vehicle vehicle, Parking parking, LocalDateTime entryDate,
      LocalDateTime exitDate,
      Double bill) {
    this.id = id;
    this.vehicle = vehicle;
    this.parking = parking;
    this.entryDate = entryDate;
    this.exitDate = exitDate;
    this.bill = bill;
  }

  public Long getId(){
    return id;
  }

  public void setId(Long id){
    this.id = id;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public Parking getParking() {
    return parking;
  }

  public void setParking(Parking parking) {
    this.parking = parking;
  }

  public LocalDateTime getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(LocalDateTime entryDate) {
    this.entryDate = entryDate;
  }

  public LocalDateTime getExitDate() {
    return exitDate;
  }

  public void setExitDate(LocalDateTime exitDate) {
    this.exitDate = exitDate;
  }

  public Double getBill() {
    return bill;
  }

  public void setBill(Double bill) {
    this.bill = bill;
  }
}
