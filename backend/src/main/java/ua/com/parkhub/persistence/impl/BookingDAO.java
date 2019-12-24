package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.Booking;
import ua.com.parkhub.persistence.entities.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class BookingDAO extends ElementDAO<Booking> {

    public BookingDAO() {
        super(Booking.class);
    }

    public Optional<Booking> findActiveBookingByCustomer(Customer customer) {
        CriteriaBuilder criteriaBuilder = this.emp.getCriteriaBuilder();
        CriteriaQuery<Booking> criteriaQuery = criteriaBuilder.createQuery(Booking.class);
        Root<Booking> itemRoot = criteriaQuery.from(Booking.class);
        Predicate predicateForCustomer
                = criteriaBuilder.equal(itemRoot.get("customer"), customer);
        Predicate predicateForActiveStatus
                = criteriaBuilder.equal(itemRoot.get("isActive"), true);
        Predicate predicateForActiveBookingByCustomer =
                criteriaBuilder.and(predicateForCustomer, predicateForActiveStatus);
        criteriaQuery.where(predicateForActiveBookingByCustomer);
        Booking booking = this.emp.createQuery(criteriaQuery).getSingleResult();
        return Optional.ofNullable(booking);
    }
}

