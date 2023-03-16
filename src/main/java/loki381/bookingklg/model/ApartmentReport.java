package loki381.bookingklg.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentReport {

    @Schema(description = "Liczba rezerwacji")
    private Integer bookingCount;
    @Schema(description = "Liczba zarezerwowanych dni")
    private Integer days;
}
