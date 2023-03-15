package loki381.bookingklg.service;

import loki381.bookingklg.model.ApartmentReport;
import loki381.bookingklg.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    private final BookingRepository bookingRepository;

    @Autowired
    ReportService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<ApartmentReport> getApartmentReports(Date dateFrom, Date dateTo) {
        List<ApartmentReport> result = new ArrayList<>();
        return result;
    }
}
