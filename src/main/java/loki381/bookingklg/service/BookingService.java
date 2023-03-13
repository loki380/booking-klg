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
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Optional<Booking> findById(Long id) {
        return this.bookingRepository.findById(id);
    }

    public List<Booking> findAll() {
        return this.bookingRepository.findAll();
    }
}
