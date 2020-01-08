package ua.com.parkhub.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.persistence.IElementDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ElementDAO<E, M> implements IElementDAO<M> {

    @PersistenceContext
    EntityManager emp;

    Class<E> elementClass;
    Mapper<M, E> modelToEntity;
    Mapper<E, M> entityToModel;

    public ElementDAO(Class<E> elementClass, Mapper<M, E> modelToEntity, Mapper<E, M> entityToModel) {
        this.elementClass = elementClass;
        this.modelToEntity = modelToEntity;
        this.entityToModel = entityToModel;
    }

    @Override
    public void addElement(M element) {
        System.out.println(modelToEntity.transform(element));
        emp.persist(modelToEntity.transform(element));
    }

    @Override
    public void updateElement(M element) {
        emp.merge(modelToEntity.transform(element));
    }

    @Override
    public List<M> findAll() {
        CriteriaBuilder cb = emp.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(elementClass);
        Root<E> rootEntry = cq.from(elementClass);
        CriteriaQuery<E> all = cq.select(rootEntry);
        TypedQuery<E> allQuery = emp.createQuery(all);
        //TODO NPE!!!
        return allQuery.getResultList().stream().map(entityToModel::transform).collect(Collectors.toList());
    }

    @Override
    public Optional<M> findElementByFieldsEqual(long slotId, LocalDateTime checkIn, LocalDateTime checkOut, String fieldNameSlotId, String fieldNameCheckIn, String fieldNameCheckOut) {
        CriteriaBuilder criteriaBuilder = emp.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(elementClass);
        Root<E> elementRoot = criteriaQuery.from(elementClass);
        Predicate[] predicates = new Predicate[3];
        predicates[0] = criteriaBuilder.equal(elementRoot.get(fieldNameSlotId), slotId);
        predicates[1] = criteriaBuilder.greaterThanOrEqualTo(elementRoot.get(fieldNameCheckIn), checkIn);
        predicates[2] = criteriaBuilder.lessThanOrEqualTo(elementRoot.get(fieldNameCheckOut), checkOut);
        criteriaQuery.select(elementRoot).where(predicates);
        TypedQuery<E> query = emp.createQuery(criteriaQuery).setMaxResults(1);
        E element;
        try {
            element = query.getSingleResult();
        } catch (PersistenceException e) {
            element = null;
        }
        return (element != null ? Optional.ofNullable(entityToModel.transform(element)) : Optional.empty());
    }

    @Override
    public Optional<M> findElementById(long id) {
        E element;
        try {
            element = emp.find(elementClass, id);
        } catch (PersistenceException e) {
            element = null;
        }
        return Optional.ofNullable(entityToModel.transform(element));
    }

    @Override
    public void deleteElement(M element) {

    }

    @Override
    public <F> Optional<M> findOneByFieldEqual(String fieldName, F fieldValue) {
        CriteriaBuilder criteriaBuilder = emp.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(elementClass);
        Root<E> elementRoot = criteriaQuery.from(elementClass);
        criteriaQuery.select(elementRoot).where(criteriaBuilder.equal(elementRoot.get(fieldName), fieldValue));
        E element;
        try {
            element = emp.createQuery(criteriaQuery).getSingleResult();
        } catch (PersistenceException e) {
            element = null;
        }
        return (element != null ? Optional.ofNullable(entityToModel.transform(element)) : Optional.empty());
    }
}
