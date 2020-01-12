package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Booking;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookingDAO extends ElementDAO<Booking, BookingModel> {

    public BookingDAO(Mapper<Booking, BookingModel> entityToModel, Mapper<BookingModel, Booking> modelToEntity) {
        super(Booking.class, modelToEntity, entityToModel);
    }

    public BookingModel addWithResponse(BookingModel bookingModel) {
        Booking entity = modelToEntity.transform(bookingModel);
        emp.persist(entity);
        return entityToModel.transform(entity);
    }

    public List<BookingModel> findBookingsByCustomer(CustomerModel customer) {
        CriteriaBuilder criteriaBuilder = this.emp.getCriteriaBuilder();
        CriteriaQuery<Booking> criteriaQuery = criteriaBuilder.createQuery(Booking.class);
        Root<Booking> itemRoot = criteriaQuery.from(Booking.class);
        Predicate predicateForCustomer
                = criteriaBuilder.equal(itemRoot.get("customer"), customer.getId());
        criteriaQuery.where(predicateForCustomer);
        return this.emp.createQuery(criteriaQuery).getResultList().stream().map(x -> entityToModel.transform(x)).collect(Collectors.toList());
    }
}

