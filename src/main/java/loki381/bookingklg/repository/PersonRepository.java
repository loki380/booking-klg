package loki381.bookingklg.repository;

import loki381.bookingklg.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
