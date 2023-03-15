package loki381.bookingklg.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "apartment")
@Table(name = "apartment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private Double area;
    private String description;

}
