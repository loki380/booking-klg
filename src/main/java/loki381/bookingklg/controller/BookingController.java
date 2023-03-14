package loki381.bookingklg.controller;

import loki381.bookingklg.model.Apartment;
import loki381.bookingklg.model.Booking;
import loki381.bookingklg.service.ApartmentService;
import loki381.bookingklg.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("bookings")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = this.bookingService.findById(id);

        if (booking.isPresent()){
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> searchBookings() {
        List<Booking> bookings = this.bookingService.findAll();

        return ResponseEntity.ok(bookings);
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking newBooking) {
        Booking booking = this.bookingService.createBooking(newBooking);

        if (booking == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(CREATED).body(booking);
        }
    }
}
