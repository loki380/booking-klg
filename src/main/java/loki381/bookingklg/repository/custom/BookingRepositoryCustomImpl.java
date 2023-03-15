package loki381.bookingklg.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import loki381.bookingklg.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class BookingRepositoryCustomImpl implements BookingRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Booking> searchBooking(String apartment, String tenant) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT b ");
        sql.append(" FROM Booking b ");
        sql.append(" WHERE 1=1 ");
        if(apartment != null){
            sql.append(" AND b.apartment.name LIKE '%' + :apartmentName + '%' ");
        }
        if(tenant != null){
            sql.append(" AND b.tenant.name LIKE '%' + :tenantName + '%' ");
        }

        Query query = this.entityManager.createQuery(sql.toString(), Booking.class);
        if(apartment != null){
            query.setParameter("apartmentName", apartment);
        }
        if(tenant != null){
            query.setParameter("tenantName", tenant);
        }

        return query.getResultList();
    }

    @Override
    public List<Booking> getBookingsByApartmentId(Long apartmentId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT b ");
        sql.append(" FROM Booking b ");
        sql.append(" WHERE b.apartment_id = :apartmentId ");

        Query query = this.entityManager.createQuery(sql.toString(), Booking.class);
        query.setParameter("apartmentId", apartmentId);

        return query.getResultList();
    }

    @Override
    public boolean checkIsOtherBookingInRange(Long apartmentId, Date dateFrom, Date dateTo) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COUNT(*) > 0 ");
        sql.append(" FROM Booking b ");
        sql.append(" WHERE b.apartment.id = :apartmentId ");
        sql.append(" AND b.dateFrom <= :dateTo ");
        sql.append(" AND b.dateTo >= :dateFrom ");

        Query query = this.entityManager.createQuery(sql.toString(), Booking.class);
        query.setParameter("apartmentId", apartmentId);
        query.setParameter("dateFrom", dateFrom);
        query.setParameter("dateTo", dateTo);

        return (boolean) query.getSingleResult();
    }
}
