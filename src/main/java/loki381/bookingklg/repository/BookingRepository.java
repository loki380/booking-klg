package loki381.bookingklg.repository;

import loki381.bookingklg.model.Booking;
import loki381.bookingklg.repository.custom.BookingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long>, BookingRepositoryCustom {
}