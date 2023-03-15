package loki381.bookingklg.controller;

import loki381.bookingklg.exceptions.NoSuchElementException;
import loki381.bookingklg.model.Apartment;
import loki381.bookingklg.service.ApartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable Long id) {
        Optional<Apartment> apartment = this.apartmentService.findById(id);

        if (apartment.isPresent()) {
            return ResponseEntity.ok(apartment.get());
        } else {
            throw new NoSuchElementException("Nie znaleziono apartamentu o id = " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Apartment>> searchApartments() {
        List<Apartment> apartments = this.apartmentService.findAll();

        return ResponseEntity.ok(apartments);
    }

    @PostMapping
    public ResponseEntity<Apartment> createApartment(@RequestBody Apartment newApartment) {
        Apartment apartment = this.apartmentService.save(newApartment);

        if (apartment == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(CREATED).body(apartment);
        }
    }
}
