package loki381.bookingklg.service;

import jakarta.persistence.EntityNotFoundException;
import loki381.bookingklg.exceptions.ApartamentIsAlreadyBusyException;
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
        Apartment apartment = this.apartmentRepository.findById(newBooking.getApartment().getId()).orElseThrow(EntityNotFoundException::new);
        Person tenant = this.personRepository.findById(newBooking.getTenant().getId()).orElseThrow(EntityNotFoundException::new);
        Person landlord = this.personRepository.findById(newBooking.getLandlord().getId()).orElseThrow(EntityNotFoundException::new);

        boolean otherBookingExist = this.bookingRepository.checkIsOtherBookingInRange(apartment.getId(), newBooking.getDateFrom(), newBooking.getDateTo());

        if (otherBookingExist) {
            throw new ApartamentIsAlreadyBusyException("Apartament o id = " + apartment.getId() + " jest już zajęty w tym terminie");
        }

        long duration = getDurationBetweenDates(newBooking.getDateFrom(), newBooking.getDateTo());

        BigDecimal cost = apartment.getPrice().multiply(BigDecimal.valueOf(duration));

        Booking booking = Booking.builder()
                .dateFrom(newBooking.getDateFrom())
                .dateTo(newBooking.getDateTo())
                .landlord(landlord)
                .tenant(tenant)
                .apartment(apartment)
                .cost(cost)
                .build();

        return this.bookingRepository.save(booking);
    }

    public Booking updateBooking(Booking newBooking) {
        Booking oldBooking = this.bookingRepository.findById(newBooking.getId()).orElseThrow(EntityNotFoundException::new);
        Apartment apartment = this.apartmentRepository.findById(newBooking.getApartment().getId()).orElseThrow(EntityNotFoundException::new);
        Person tenant = this.personRepository.findById(newBooking.getTenant().getId()).orElseThrow(EntityNotFoundException::new);
        Person landlord = this.personRepository.findById(newBooking.getLandlord().getId()).orElseThrow(EntityNotFoundException::new);

        long duration = getDurationBetweenDates(newBooking.getDateFrom(), newBooking.getDateTo());

        BigDecimal cost = apartment.getPrice().multiply(BigDecimal.valueOf(duration));

        Booking booking = oldBooking.toBuilder()
                .dateFrom(newBooking.getDateFrom())
                .dateTo(newBooking.getDateTo())
                .landlord(landlord)
                .tenant(tenant)
                .apartment(apartment)
                .cost(cost)
                .build();

        return this.bookingRepository.save(booking);
    }

    private long getDurationBetweenDates(Date dateFrom, Date dateTo) {
        LocalDate dateFromAsLocalDate = LocalDate.ofInstant(dateFrom.toInstant(), ZoneId.systemDefault());
        LocalDate dateToAsLocalDate = LocalDate.ofInstant(dateTo.toInstant(), ZoneId.systemDefault());
        return Duration.between(dateFromAsLocalDate.atStartOfDay(), dateToAsLocalDate.atStartOfDay()).toDays();
    }

    public void deleteBooking(Long id) {
        this.bookingRepository.deleteById(id);
    }
}
