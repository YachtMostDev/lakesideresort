package nl.yacht.lakesideresort.repository;

import nl.yacht.lakesideresort.domain.Booking;
import org.springframework.cglib.core.Local;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

}


