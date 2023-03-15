package loki381.bookingklg.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import loki381.bookingklg.model.Booking;
import org.springframework.stereotype.Repository;

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
}
