package loki381.bookingklg.service;

import loki381.bookingklg.model.LandlordReport;
import loki381.bookingklg.model.Person;
import loki381.bookingklg.repository.BookingRepository;
import loki381.bookingklg.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    PersonService(PersonRepository personRepository, BookingRepository bookingRepository) {
        this.personRepository = personRepository;
        this.bookingRepository = bookingRepository;
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

    public List<LandlordReport> getLandlordsReport(Date dateFrom, Date dateTo) {
        return this.bookingRepository.getLandlordsReport(dateFrom, dateTo);
    }
}
