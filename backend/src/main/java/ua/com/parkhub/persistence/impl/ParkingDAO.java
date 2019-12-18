package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.Parking;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ParkingDAO extends ElementDAO<Parking> {

    public ParkingDAO() {
        super(Parking.class);
    }

    public Long  checkUniquenessofParking(String parkingName) {
        CriteriaBuilder cb = emp.getCriteriaBuilder();
        CriteriaQuery<Long> cr = cb.createQuery(Long.class);
        Root<Parking> root = cr.from(Parking.class);
        cr.select(cb.count(root));
        cr.where(cb.equal(root.get("parkingName"), parkingName));
        TypedQuery<Long> count = emp.createQuery(cr);
        return count.getSingleResult();
    }
    //method to check unique of address
    // SELECT * From Parking where city = ... and street ==... and building ==...
    // SELECT Count(*) FROM Parking where city ==........
}

