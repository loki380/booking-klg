package loki381.bookingklg.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import loki381.bookingklg.model.Booking;
import org.springframework.stereotype.Repository;

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
}
