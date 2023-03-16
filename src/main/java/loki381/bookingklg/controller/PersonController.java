package loki381.bookingklg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import loki381.bookingklg.exceptions.NoSuchElementException;
import loki381.bookingklg.model.ApartmentReport;
import loki381.bookingklg.model.LandlordReport;
import loki381.bookingklg.model.Person;
import loki381.bookingklg.service.PersonService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("persons")
@Tag(name = "Persons", description = "Operacje związane z osobami (najemcy/wynajmujący)")
public class PersonController {

    private final PersonService personService;

    PersonController(PersonService personService) {
        this.personService = personService;
    }


    @Operation(description = "Pobieranie osoby przez podanie id")
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = this.personService.getPersonById(id);

        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        } else {
            throw new NoSuchElementException("Nie znaleziono osoby o id = " + id);
        }
    }

    @Operation(description = "Pobieranie wszystkich osób")
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = this.personService.getAllPersons();

        if (persons.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(persons);
        }
    }

    @Operation(description = "Dodawanie nowej osoby")
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person newPerson) {
        Person person = this.personService.createPerson(newPerson);

        if (person == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(CREATED).body(person);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> createPerson(@PathVariable("id") Long id, @RequestBody Person person) {
        Optional<Person> personData = this.personService.getPersonById(id);

        if (personData.isPresent()) {
            Person _person = personData.get().toBuilder()
                    .name(person.getName())
                    .build();
            return ResponseEntity.ok(this.personService.updatePerson(_person));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/report")
    @Operation(description = "Raport z listą wynajmujących")
    public ResponseEntity<List<LandlordReport>> getPersonsReport(@RequestParam(name = "dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFrom,
                                                            @RequestParam(name = "dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateTo) {
        List<LandlordReport> landlordReports = this.personService.getLandlordsReport(dateFrom, dateTo);

        return ResponseEntity.ok(landlordReports);
    }
}
