package nl.yacht.lakesideresort.repository;

import nl.yacht.lakesideresort.domain.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

}


