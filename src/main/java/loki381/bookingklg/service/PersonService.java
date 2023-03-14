package loki381.bookingklg.service;

import loki381.bookingklg.model.Apartment;
import loki381.bookingklg.model.Person;
import loki381.bookingklg.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Optional<Person> getPersonById(Long id) {
        return this.personRepository.findById(id);
    }

    public List<Person> getAllPersons() {
        return this.personRepository.findAll();
    }

    public Person createPerson(Person person) {
        return this.personRepository.save(person);
    }

    public Person updatePerson(Person person) {
        return this.personRepository.save(person);
    }
}
