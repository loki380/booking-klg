package loki381.bookingklg.service;

import loki381.bookingklg.model.Apartment;
import loki381.bookingklg.model.Booking;
import loki381.bookingklg.model.Person;
import loki381.bookingklg.repository.ApartmentRepository;
import loki381.bookingklg.repository.BookingRepository;
import loki381.bookingklg.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ApartmentRepository apartmentRepository;
    private final PersonRepository personRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          ApartmentRepository apartmentRepository,
                          PersonRepository personRepository) {
        this.bookingRepository = bookingRepository;
        this.apartmentRepository = apartmentRepository;
        this.personRepository = personRepository;
    }

    public Optional<Booking> getBookingById(Long id) {
        return this.bookingRepository.findById(id);
    }

    public List<Booking> searchBookings(String apartment, String tenant) {
        if (apartment == null && tenant == null) {
            return this.bookingRepository.findAll();
        } else {
            return this.bookingRepository.searchBooking(apartment, tenant);
        }
    }

    public Booking createBooking(Booking newBooking) {
        Optional<Apartment> apartment = this.apartmentRepository.findById(newBooking.getApartment().getId());
        Optional<Person> tenant = this.personRepository.findById(newBooking.getTenant().getId());
        Optional<Person> landlord = this.personRepository.findById(newBooking.getLandlord().getId());

        long duration = getDurationBetweenDates(newBooking.getDateFrom(),newBooking.getDateTo());

        BigDecimal cost = apartment.get().getPrice().multiply(BigDecimal.valueOf(duration));

        Booking booking = Booking.builder()
                .dateFrom(newBooking.getDateFrom())
                .dateTo(newBooking.getDateTo())
                .landlord(landlord.get())
                .tenant(tenant.get())
                .apartment(apartment.get())
                .cost(cost)
                .build();

        return this.bookingRepository.save(booking);
    }

    public Booking updateBooking(Booking oldBooking, Booking newBooking) {
        Optional<Apartment> apartment = this.apartmentRepository.findById(newBooking.getApartment().getId());
        Optional<Person> tenant = this.personRepository.findById(newBooking.getTenant().getId());
        Optional<Person> landlord = this.personRepository.findById(newBooking.getLandlord().getId());

        long duration = getDurationBetweenDates(newBooking.getDateFrom(),newBooking.getDateTo());

        BigDecimal cost = apartment.get().getPrice().multiply(BigDecimal.valueOf(duration));

        Booking booking = oldBooking.toBuilder()
                .dateFrom(newBooking.getDateFrom())
                .dateTo(newBooking.getDateTo())
                .landlord(landlord.get())
                .tenant(tenant.get())
                .apartment(apartment.get())
                .cost(cost)
                .build();

        return this.bookingRepository.save(booking);
    }

    private long getDurationBetweenDates(Date dateFrom, Date dateTo) {
        LocalDate dateFromAsLocalDate = LocalDate.ofInstant(dateFrom.toInstant(), ZoneId.systemDefault());
        LocalDate dateToAsLocalDate = LocalDate.ofInstant(dateTo.toInstant(), ZoneId.systemDefault());
        return Duration.between(dateFromAsLocalDate.atStartOfDay(), dateToAsLocalDate.atStartOfDay()).toDays();
    }
}
