package loki381.bookingklg.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import loki381.bookingklg.exceptions.NoSuchElementException;
import loki381.bookingklg.model.Booking;
import loki381.bookingklg.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("bookings")
@Tag(name = "Booking", description = "Operacje związane z rezerwacjami obiektów")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(description = "Pobieranie rezerwacji przez id")
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = this.bookingService.getBookingById(id);

        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            throw new NoSuchElementException("Nie znaleziono rezerwacji o id = " + id);
        }
    }

    @Operation(description = "Wyszukiwanie rezerwacji z filtrami")
    @Parameter
    @GetMapping
    public ResponseEntity<List<Booking>> searchBookings(
            @Parameter(description = "Nazwa apartamentu") @RequestParam(required = false, name = "apartment") String apartment,
            @Parameter(description = "Nazwa najemcy") @RequestParam(required = false, name = "tenant") String tenant) {
        List<Booking> bookings = this.bookingService.searchBookings(apartment, tenant);

        return ResponseEntity.ok(bookings);
    }

    @Operation(description = "Dodawanie nowej rezerwacji")
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking newBooking) {
        Booking booking = this.bookingService.createBooking(newBooking);

        if (booking == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(CREATED).body(booking);
        }
    }

    @Operation(description = "Edycja istniejącej rezerwacji")
    @PutMapping("/{id}")
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

    @Operation(description = "Usuwanie istniejącej rezerwacji")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable("id") Long id) {
        this.bookingService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }
}
