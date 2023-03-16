package loki381.bookingklg.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import loki381.bookingklg.model.ApartmentReport;
import loki381.bookingklg.model.Booking;
import loki381.bookingklg.model.LandlordReport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BookingRepositoryCustomImpl implements BookingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Booking> searchBooking(Long apartmentId, String apartmentName, String tenant) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT b ");
        sql.append(" FROM Booking b ");
        sql.append(" WHERE 1=1 ");
        if (apartmentId != null) {
            sql.append(" AND b.apartment.id = :apartmentId ");
        }
        if (apartmentName != null) {
            sql.append(" AND b.apartment.name LIKE '%' + :apartmentName + '%' ");
        }
        if (tenant != null) {
            sql.append(" AND b.tenant.name LIKE '%' + :tenantName + '%' ");
        }

        Query query = this.entityManager.createQuery(sql.toString(), Booking.class);
        if (apartmentId != null) {
            query.setParameter("apartmentId", apartmentId);
        }
        if (apartmentName != null) {
            query.setParameter("apartmentName", apartmentName);
        }
        if (tenant != null) {
            query.setParameter("tenantName", tenant);
        }

        return query.getResultList();
    }

    @Override
    public boolean checkIsOtherBookingInRange(Long apartmentId, Date dateFrom, Date dateTo) {
        String sql = " SELECT COUNT(*) > 0 " +
                " FROM Booking b " +
                " WHERE b.apartment.id = :apartmentId " +
                " AND b.dateFrom <= :dateTo " +
                " AND b.dateTo >= :dateFrom ";

        Query query = this.entityManager.createQuery(sql, Booking.class);
        query.setParameter("apartmentId", apartmentId);
        query.setParameter("dateFrom", dateFrom);
        query.setParameter("dateTo", dateTo);

        return (boolean) query.getSingleResult();
    }

    @Override
    public ApartmentReport getApartmentReport(Long apartmentId, Date dateFrom, Date dateTo) {
        String sql = "WITH bookingDays (id, days) as (SELECT b.id, DATEDIFF(day,b.date_from, b.date_to) FROM Booking b) "
                + "SELECT COUNT(*), SUM(bd.days) FROM Booking bk "
                + "JOIN bookingDays bd ON bd.id = bk.id "
                + "WHERE bk.apartment_id = :apartmentId "
                + " AND bk.date_from <= :dateTo "
                + " AND bk.date_to >= :dateFrom ";

        Query query = this.entityManager.createNativeQuery(sql)
                .setParameter("apartmentId", apartmentId)
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo);

        Object[] apartmentReport = (Object[]) query.getSingleResult();

        return ApartmentReport.builder()
                .bookingCount(((Long) apartmentReport[0]).intValue())
                .days(((BigDecimal) apartmentReport[1]).intValue())
                .build();
    }

    @Override
    public List<LandlordReport> getLandlordsReport(Date dateFrom, Date dateTo) {
        String sql = "SELECT p.id, p.name, COUNT(b.id), SUM(b.cost)"
                + "FROM Booking b "
                + "JOIN Person p ON b.landlord_id = p.id "
                + "WHERE b.date_from <= :dateTo "
                + " AND b.date_to >= :dateFrom "
                + "GROUP BY p ";

        Query query = this.entityManager.createNativeQuery(sql)
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo);

        List<Object[]> landlordReports = query.getResultList();

        List<LandlordReport> result = new ArrayList<>();
        landlordReports.forEach(report -> {
            result.add(LandlordReport.builder()
                    .landlordId((Long) report[0])
                    .landlordName((String) report[1])
                    .bookings(((Long) report[2]).intValue())
                    .profits((BigDecimal) report[3])
                    .build());
        });

        return result;
    }
}
