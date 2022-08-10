package one.digitalinnovation.parking.controller.dto;

public class ParkedCheckinDTO {
  private Long id;

  private String vehicleId;

  private Long parkingId;

  public ParkedCheckinDTO() {
  }

  public ParkedCheckinDTO(Long id, String vehicleId, Long parkingId) {
    this.id = id;
    this.vehicleId = vehicleId;
    this.parkingId = parkingId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  public Long getParkingId() {
    return parkingId;
  }

  public void setParkingId(Long parkingId) {
    this.parkingId = parkingId;
  }
}
