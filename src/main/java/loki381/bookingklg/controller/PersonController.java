package loki381.bookingklg.controller;

import loki381.bookingklg.model.Booking;
import loki381.bookingklg.model.Person;
import loki381.bookingklg.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("persons")
public class PersonController {

    private final PersonService personService;

    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = this.personService.getPersonById(id);

        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = this.personService.getAllPersons();

        if (persons.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(persons);
        }
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person newPerson){
        Person person = this.personService.createPerson(newPerson);

        if (person == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(CREATED).body(person);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> createPerson(@PathVariable("id") Long id, @RequestBody Person person){
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
}
