package loki381.bookingklg.controller;

import loki381.bookingklg.model.Apartment;
import loki381.bookingklg.service.ApartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apartments")
public class ApartmentController {

    private ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService){
        this.apartmentService = apartmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> findById(@PathVariable Long id) {
        Optional<Apartment> apartment = this.apartmentService.findById(id);

        return ResponseEntity.ok(apartment.get());
    }

    @GetMapping
    public ResponseEntity<List<Apartment>> findAll() {
        List<Apartment> apartments = this.apartmentService.findAll();

        return ResponseEntity.ok(apartments);
    }
}
