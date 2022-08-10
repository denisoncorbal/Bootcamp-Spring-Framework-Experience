package one.digitalinnovation.parking.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Parked {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  private Vehicle vehicle;

  @ManyToOne
  private Parking parking;

  private LocalDateTime entryDate;
  private LocalDateTime exitDate;

  private Double bill;

  public Parked() {
  }

  public Parked(Long id, Vehicle vehicle, Parking parking, LocalDateTime entryDate,
      LocalDateTime exitDate, Double bill) {
    this.id = id;
    this.vehicle = vehicle;
    this.parking = parking;
    this.entryDate = entryDate;
    this.exitDate = exitDate;
    this.bill = bill;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
