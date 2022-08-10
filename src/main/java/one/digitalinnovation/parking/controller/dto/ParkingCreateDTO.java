package one.digitalinnovation.parking.controller.dto;

public class ParkingCreateDTO {

  private String name;

  private Integer capacity;

  public ParkingCreateDTO() {
  }

  public ParkingCreateDTO(String name, Integer capacity) {
    this.name = name;
    this.capacity = capacity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }
}
