package loki381.bookingklg.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateFrom;
    private Date dateTo;
    private BigDecimal cost;
    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private Person landlord; // Wynajmujący
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Person tenant; // Najemca
    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment; // Najemca
}
