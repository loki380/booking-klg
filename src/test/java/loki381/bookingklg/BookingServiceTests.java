package loki381.bookingklg;

import loki381.bookingklg.model.Apartment;
import loki381.bookingklg.model.Booking;
import loki381.bookingklg.model.Person;
import loki381.bookingklg.repository.ApartmentRepository;
import loki381.bookingklg.repository.BookingRepository;
import loki381.bookingklg.repository.PersonRepository;
import loki381.bookingklg.service.BookingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookingServiceTests{

    ApartmentRepository apartmentRepository;
    PersonRepository personRepository;
    BookingRepository bookingRepository;
    BookingService bookingService;

    @Before
    public void setupService() {
        this.apartmentRepository = mock(ApartmentRepository.class);
        this.personRepository = mock(PersonRepository.class);
        this.bookingRepository = mock(BookingRepository.class);
        this.bookingService = new BookingService(this.bookingRepository,this.apartmentRepository, this.personRepository);
    }
    @Test
    public void addBooking(){
        Apartment apartment = Apartment.builder()
                .id(1L)
                .name("Testowy apartament")
                .price(BigDecimal.valueOf(100))
                .build();

        Person tenant = Person.builder()
                .id(1L)
                .name("Jan Testowy")
                .build();

        Person landlord = Person.builder()
                .id(1L)
                .name("Marek Testowy")
                .build();

        when(this.apartmentRepository.findById(1L)).thenReturn(Optional.of(apartment));
        when(this.personRepository.findById(1L)).thenReturn(Optional.of(tenant));
        when(this.personRepository.findById(2L)).thenReturn(Optional.of(landlord));
        when(this.bookingRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());

        Booking booking = Booking.builder()
                .dateFrom(new Date(2023, Calendar.JANUARY,1))
                .dateTo(new Date(2023,Calendar.JANUARY,5))
                .apartment(apartment)
                .tenant(tenant)
                .landlord(landlord)
                .build();

        Booking createdBooking = this.bookingService.createBooking(booking);

        Assert.assertEquals(createdBooking.getDateFrom(), new Date(2023, Calendar.JANUARY,1));
        Assert.assertEquals(createdBooking.getApartment().getName(), "Testowy apartament");
        Assert.assertEquals(createdBooking.getCost(), BigDecimal.valueOf(400));
    }
}
