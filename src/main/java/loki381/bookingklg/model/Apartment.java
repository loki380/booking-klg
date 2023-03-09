package loki381.bookingklg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Apartment {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal price;
    private String area;
    private String description;

}
