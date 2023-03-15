package loki381.bookingklg.controller;

import loki381.bookingklg.model.Booking;
import loki381.bookingklg.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = this.bookingService.getBookingById(id);

        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Booking>> searchBookings(
            @RequestParam(required = false, name = "apartment") String apartment,
            @RequestParam(required = false, name = "tenant") String tenant) {
        List<Booking> bookings = this.bookingService.searchBookings(apartment, tenant);

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

    @PutMapping
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") Long id, @RequestBody Booking newBooking) {
        Optional<Booking> oldBooking = this.bookingService.getBookingById(id);

        if (oldBooking.isPresent()) {
            Booking booking = this.bookingService.updateBooking(newBooking);
            if (booking == null) {
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.ok(booking);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
