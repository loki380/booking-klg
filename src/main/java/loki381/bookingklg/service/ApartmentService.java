package loki381.bookingklg.service;

import loki381.bookingklg.model.Apartment;
import loki381.bookingklg.model.Booking;
import loki381.bookingklg.repository.ApartmentRepository;
import loki381.bookingklg.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final BookingRepository bookingRepository;
    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository, BookingRepository bookingRepository) {
        this.apartmentRepository = apartmentRepository;
        this.bookingRepository = bookingRepository;
    }

    public Optional<Apartment> findById(Long id) {
        return this.apartmentRepository.findById(id);
    }

    public List<Apartment> findAll() {
        return this.apartmentRepository.findAll();
    }

    public Apartment save(Apartment apartment) {
        return this.apartmentRepository.save(apartment);
    }

    public List<Booking> getBookingsByApartmentId(Long id) {
        return this.bookingRepository.searchBooking(id,null,null);
    }
}
