package loki381.bookingklg.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateFrom;
    private Date dateTo;
    private BigDecimal cost;
    @ManyToOne
    private Person landlord; // Wynajmujący
    @ManyToOne
    private Person tenant; // Najemca
}
