package loki381.bookingklg.service;

import loki381.bookingklg.model.Apartment;
import loki381.bookingklg.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public Optional<Apartment> findById(Long id) {
        return this.apartmentRepository.findById(id);
    }
}
