package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Booking;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.util.formatter.DateFormatter;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.Metamodel;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    /**
     * SELECT * FROM park_hub.slot WHERE park_hub.slot.id NOT IN (SELECT park_hub.booking.slot_id FROM park_hub.booking WHERE
     * (park_hub.booking.check_in <= '2020-01-12 17:59:11.000000' AND park_hub.booking.check_out >= '2020-01-12 17:59:11.000000') OR
     * (park_hub.booking.check_in <= '2020-01-12 19:30:00.000000' AND park_hub.booking.check_out >= '2020-01-12 19:30:00.000000') OR
     * (park_hub.booking.check_in >= '2020-01-12 17:59:11.000000' AND park_hub.booking.check_out <= '2020-01-12 19:30:00.000000'));
     **/

    public List<Slot> findElementsByFieldsEqual(long checkIn, long checkOut) {
        CriteriaBuilder criteriaBuilder = emp.getCriteriaBuilder();
        CriteriaQuery<Slot> criteriaQuery = criteriaBuilder.createQuery(Slot.class);
        Metamodel metamodel = emp.getMetamodel();
        Root<Slot> root = criteriaQuery.from(metamodel.entity(Slot.class));
        Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        Root<Booking> subRoot = subquery.from(metamodel.entity(Booking.class));
        subquery.select(subRoot.get("slot").get("id"));

        List<Predicate> predicates = new ArrayList<Predicate>();

        ParameterExpression<LocalDateTime> fromDate1 = criteriaBuilder.parameter(LocalDateTime.class);
        Predicate exp1 = criteriaBuilder.lessThanOrEqualTo(subRoot.get("checkIn"), fromDate1);
        ParameterExpression<LocalDateTime> toDate1 = criteriaBuilder.parameter(LocalDateTime.class);
        Predicate exp2 = criteriaBuilder.greaterThanOrEqualTo(subRoot.get("checkOut"), toDate1);
        Predicate and1 = criteriaBuilder.and(exp1, exp2);

        ParameterExpression<LocalDateTime> fromDate2 = criteriaBuilder.parameter(LocalDateTime.class);
        Predicate exp3 = criteriaBuilder.lessThanOrEqualTo(subRoot.get("checkIn"), fromDate2);
        ParameterExpression<LocalDateTime> toDate2 = criteriaBuilder.parameter(LocalDateTime.class);
        Predicate exp4 = criteriaBuilder.greaterThanOrEqualTo(subRoot.get("checkOut"), toDate2);
        Predicate and2 = criteriaBuilder.and(exp3, exp4);

        ParameterExpression<LocalDateTime> fromDate3 = criteriaBuilder.parameter(LocalDateTime.class);
        Predicate exp5 = criteriaBuilder.greaterThanOrEqualTo(subRoot.get("checkIn"), fromDate3);
        ParameterExpression<LocalDateTime> toDate3 = criteriaBuilder.parameter(LocalDateTime.class);
        Predicate exp6 = criteriaBuilder.lessThanOrEqualTo(subRoot.get("checkOut"), toDate3);
        Predicate and3 = criteriaBuilder.and(exp5, exp6);

        Predicate or = criteriaBuilder.or(and1, and2, and3);
        predicates.add(or);
        subquery.where(predicates.toArray(new Predicate[0]));

        criteriaQuery.where(criteriaBuilder.in(root.get("id")).value(subquery).not());
        return emp.createQuery(criteriaQuery)
                .setParameter(fromDate1, DateFormatter.convertTimeStampToLocalDateTime(new Timestamp(checkIn)))
                .setParameter(toDate1, DateFormatter.convertTimeStampToLocalDateTime(new Timestamp(checkIn)))
                .setParameter(fromDate2, DateFormatter.convertTimeStampToLocalDateTime(new Timestamp(checkOut)))
                .setParameter(toDate2, DateFormatter.convertTimeStampToLocalDateTime(new Timestamp(checkOut)))
                .setParameter(fromDate3, DateFormatter.convertTimeStampToLocalDateTime(new Timestamp(checkIn)))
                .setParameter(toDate3, DateFormatter.convertTimeStampToLocalDateTime(new Timestamp(checkOut)))
                .getResultList();
    }
}

