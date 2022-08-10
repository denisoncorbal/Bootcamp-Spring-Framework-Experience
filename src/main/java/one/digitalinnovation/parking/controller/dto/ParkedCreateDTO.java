package one.digitalinnovation.parking.controller.dto;

public class ParkedCreateDTO {

  private String vehicleId;

  private Long parkingId;

  public ParkedCreateDTO() {
  }

  public ParkedCreateDTO(String vehicleId, Long parkingId) {
    this.vehicleId = vehicleId;
    this.parkingId = parkingId;
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
