package one.digitalinnovation.parking.repository;

import one.digitalinnovation.parking.model.Parked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkedRepository extends JpaRepository<Parked, Long> {

}
