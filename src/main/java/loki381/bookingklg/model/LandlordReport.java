package loki381.bookingklg.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandlordReport {

    @Schema(description = "Id wynajmującego")
    private Long landlordId;
    @Schema(description = "Nazwa wynajmującego")
    private String landlordName;
    @Schema(description = "Liczba rezerwacji")
    private Integer bookings;
    @Schema(description = "Zyski")
    private BigDecimal profits;
}
