package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;

import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.*;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ParkingDAO extends ElementDAO<Parking, ParkingModel> {

    public ParkingDAO(Mapper<Parking, ParkingModel> entityToModel, Mapper<ParkingModel, Parking> modelToEntity) {
        super(Parking.class, modelToEntity, entityToModel);
    }

    public Long countOfParkingsByName(String parkingName) {
        CriteriaBuilder cb = emp.getCriteriaBuilder();
        CriteriaQuery<Long> cr = cb.createQuery(Long.class);
        Root<Parking> root = cr.from(Parking.class);
        cr.select(cb.count(root));
        cr.where(cb.equal(root.get("parkingName"), parkingName));
        TypedQuery<Long> count = emp.createQuery(cr);
        return count.getSingleResult();
    }

    public Long countOfParkingsByAddress(AddressModel addressModel) {
        CriteriaBuilder cb = emp.getCriteriaBuilder();
        CriteriaQuery<Long> cr = cb.createQuery(Long.class);
        Root<Address> root = cr.from(Address.class);
        cr.select(cb.count(root));
        cr.where
                ((cb.equal(root.get("city"),addressModel.getCity())),
                        (cb.equal(root.get("street"), addressModel.getStreet())),
                        (cb.equal(root.get("building"), addressModel.getBuilding())));
        TypedQuery<Long> count = emp.createQuery(cr);
        return count.getSingleResult();
    }

    public List<ParkingModel> findAllParkingByOwnerId(Long id) {
        CriteriaBuilder cb = emp.getCriteriaBuilder();
        CriteriaQuery<Parking> cr = cb.createQuery(elementClass);
        Root<Parking> root = cr.from(elementClass);
        cr.select(root);
        cr.where(cb.equal(root.get("owner").get("id"), id));
        return emp.createQuery(cr).getResultList().stream().map(entityToModel::transform).collect(Collectors.toList());
    }
}

