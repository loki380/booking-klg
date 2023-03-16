package loki381.bookingklg.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandlordReport {

    private Long landlordId;
    private String landlordName;
    private Integer bookings;
    private BigDecimal profits;
}
