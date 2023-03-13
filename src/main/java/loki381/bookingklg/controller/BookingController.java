package loki381.bookingklg.controller;

import loki381.bookingklg.model.Apartment;
import loki381.bookingklg.model.Booking;
import loki381.bookingklg.service.ApartmentService;
import loki381.bookingklg.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bookings")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable Long id) {
        Optional<Booking> booking = this.bookingService.findById(id);

        return ResponseEntity.ok(booking.get());
    }

    @GetMapping
    public ResponseEntity<List<Booking>> findAll() {
        List<Booking> bookings = this.bookingService.findAll();

        return ResponseEntity.ok(bookings);
    }
}
