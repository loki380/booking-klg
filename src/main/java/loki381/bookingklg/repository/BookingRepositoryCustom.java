package loki381.bookingklg.repository;

import loki381.bookingklg.model.Booking;

import java.util.List;

public interface BookingRepositoryCustom {
    List<Booking> searchBooking(String apartment, String tenant);
}
