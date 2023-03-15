package loki381.bookingklg.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import loki381.bookingklg.exceptions.NoSuchElementException;
import loki381.bookingklg.model.ApartmentReport;
import loki381.bookingklg.model.Person;
import loki381.bookingklg.service.PersonService;
import loki381.bookingklg.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("reports")
@Tag(name = "Reports", description = "Raporty")
public class ReportController {

    private final ReportService reportService;

    ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @Operation(description = "Raport 4A")
    @GetMapping("/apartments")
    public ResponseEntity<List<ApartmentReport>> getReportForApartments(@RequestParam(required = false, name = "dateFrom") Date dateFrom, @RequestParam(required = false, name = "dateTo") Date dateTo) {
        List<ApartmentReport> apartmentReports = this.reportService.getApartmentReports(dateFrom, dateTo);
        return ResponseEntity.ok(apartmentReports);
    }

    @Operation(description = "Raport 4B")
    @GetMapping("/landlord")
    public ResponseEntity<Person> getReportB(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
