package loki381.bookingklg.repository.custom;

import loki381.bookingklg.model.ApartmentReport;
import loki381.bookingklg.model.Booking;
import loki381.bookingklg.model.LandlordReport;

import java.util.Date;
import java.util.List;

public interface BookingRepositoryCustom {
    List<Booking> searchBooking(Long apartmentId, String apartmentName, String tenant);

    boolean checkIsOtherBookingInRange(Long apartmentId, Date dateFrom, Date dateTo);

    ApartmentReport getApartmentReport(Long apartmentId, Date dateFrom, Date dateTo);

    List<LandlordReport> getLandlordsReport(Date dateFrom, Date dateTo);
}
