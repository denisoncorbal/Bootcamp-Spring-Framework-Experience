package one.digitalinnovation.parking.repository;

import one.digitalinnovation.parking.model.Parked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkedRepository extends JpaRepository<Parked, Long> {

  @Query("SELECT COUNT(*) > 0 FROM Parked p WHERE p.vehicle.id=:vehicleId AND p.exitDate IS NULL")
  public boolean checkParkedPending(@Param("vehicleId") String vehicleId);

  //TODO Consertar QUERY retornando null porque não há registro em parked. construir case if null ou semelhante.
  @Query("SELECT pk.capacity <= (SELECT COUNT(*) FROM Parked p WHERE p.parking.id =:parkingId AND p.exitDate IS NULL) FROM Parked pd RIGHT JOIN pd.parking pk WHERE pk.id =:parkingId")
  public boolean checkParkingCapacity(@Param("parkingId") Long parkingId);
}
