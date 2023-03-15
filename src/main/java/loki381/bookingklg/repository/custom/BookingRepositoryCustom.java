package loki381.bookingklg.repository.custom;

import loki381.bookingklg.model.Booking;

import java.util.Date;
import java.util.List;

public interface BookingRepositoryCustom {
    List<Booking> searchBooking(String apartment, String tenant);
    List<Booking> getBookingsByApartmentId(Long apartmentId);
    boolean checkIsOtherBookingInRange(Long apartmentId, Date dateFrom, Date dateTo);
}
