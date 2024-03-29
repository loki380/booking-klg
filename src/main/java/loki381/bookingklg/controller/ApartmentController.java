package loki381.bookingklg.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import loki381.bookingklg.exceptions.NoSuchElementException;
import loki381.bookingklg.model.Apartment;
import loki381.bookingklg.model.ApartmentReport;
import loki381.bookingklg.model.Booking;
import loki381.bookingklg.service.ApartmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("apartments")
@Tag(name = "Apartments", description = "Operacje związane z obiektami do wynajęcia")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Operation(description = "Pobieranie obiektu do wynajęcia przez id")
    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable Long id) {
        Optional<Apartment> apartment = this.apartmentService.findById(id);

        if (apartment.isPresent()) {
            return ResponseEntity.ok(apartment.get());
        } else {
            throw new NoSuchElementException("Nie znaleziono apartamentu o id = " + id);
        }
    }

    @Operation(description = "Pobieranie wszystkich obiektów do wynajęcia")
    @GetMapping
    public ResponseEntity<List<Apartment>> searchApartments() {
        List<Apartment> apartments = this.apartmentService.findAll();

        return ResponseEntity.ok(apartments);
    }

    @PostMapping
    @Operation(description = "Dodanie nowego obiektu do wynajęcia")
    public ResponseEntity<Apartment> createApartment(@RequestBody Apartment newApartment) {
        Apartment apartment = this.apartmentService.save(newApartment);

        if (apartment == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(CREATED).body(apartment);
        }
    }

    @GetMapping("/{id}/bookings")
    @Operation(description = "Rezerwacje dla danego obiektu")
    public ResponseEntity<List<Booking>> getBookingsByApartmentId(@PathVariable Long id) {
        List<Booking> bookings = this.apartmentService.getBookingsByApartmentId(id);

        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}/report")
    @Operation(description = "Raport dla danego apartmentu")
    public ResponseEntity<ApartmentReport> getApartmentReport(@PathVariable Long id,
                                                              @RequestParam(required = true, name = "dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFrom,
                                                              @RequestParam(required = true, name = "dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateTo) {
        ApartmentReport apartmentReport = this.apartmentService.getApartmentReport(id, dateFrom, dateTo);

        return ResponseEntity.ok(apartmentReport);
    }
}
