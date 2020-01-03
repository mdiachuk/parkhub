package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.persistence.entities.Booking;
import ua.com.parkhub.persistence.entities.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class BookingDAO extends ElementDAO<Booking, BookingModel> {

    public BookingDAO(Mapper<Booking, BookingModel> entityToModel,
                      Mapper<BookingModel, Booking> modelToEntity) {
        super(Booking.class, modelToEntity, entityToModel);
    }

    public Optional<BookingModel> findActiveBookingByCustomer(Customer customer) {
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
        return Optional.ofNullable(entityToModel.transform(booking));
    }
}

