package ua.com.parkhub.persistence.impl;

import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.persistence.IElementDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ElementDAO<E, M> implements IElementDAO<M> {

    @PersistenceContext(unitName = "default")
    EntityManager emp;

    Class<E> elementClass;
    Mapper<M, E> modelToEntity;
    Mapper<E, M> entityToModel;

    public ElementDAO(Class<E> elementClass, Mapper<M, E> modelToEntity, Mapper<E, M> entityToModel) {
        this.elementClass = elementClass;
        this.modelToEntity = modelToEntity;
        this.entityToModel = entityToModel;
    }

    @Transactional
    @Override
    public Optional<M> addElement(M element) {
        E e = emp.merge(modelToEntity.transform(element));
        emp.flush();
        return Optional.of(entityToModel.transform(e));
    }

    @Transactional
    @Override
    public void updateElement(M element) {
        emp.merge(modelToEntity.transform(element));
    }

    @Transactional
    @Override
    public List<M> findAll() {
        CriteriaBuilder cb = emp.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(elementClass);
        Root<E> rootEntry = cq.from(elementClass);
        CriteriaQuery<E> all = cq.select(rootEntry);
        TypedQuery<E> allQuery = emp.createQuery(all);
        List<M> list = allQuery.getResultList().stream().map(entityToModel::transform).collect(Collectors.toList());
        return allQuery.getResultList().stream().map(entityToModel::transform).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<M> findElementByFieldsEqual(long slotId, LocalDateTime checkIn, LocalDateTime checkOut, String fieldNameSlotId, String fieldNameCheckIn, String fieldNameCheckOut) {
        CriteriaBuilder criteriaBuilder = emp.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(elementClass);
        Root<E> elementRoot = criteriaQuery.from(elementClass);
        Predicate[] predicates = new Predicate[3];
        predicates[0] = criteriaBuilder.equal(elementRoot.get(fieldNameSlotId), slotId);
        predicates[1] = criteriaBuilder.lessThanOrEqualTo(elementRoot.get(fieldNameCheckIn), checkIn);
        predicates[2] = criteriaBuilder.greaterThanOrEqualTo(elementRoot.get(fieldNameCheckOut), checkOut);
        criteriaQuery.select(elementRoot).where(predicates);
        TypedQuery<E> query = emp.createQuery(criteriaQuery).setMaxResults(1);
        E element;
        try {
            element = query.getSingleResult();
        } catch (PersistenceException e) {
            element = null;
        }
        return Optional.ofNullable(element).map(entityToModel::transform);
    }

    @Transactional
    @Override
    public Optional<M> findElementById(long id) {
        E element;
        try {
            element = emp.find(elementClass, id);
        } catch (PersistenceException e) {
            element = null;
        }
        return Optional.ofNullable(element).map(entityToModel::transform);
    }

    @Transactional
    @Override
    public void deleteElement(M element) {
        emp.remove(emp.merge(modelToEntity.transform(element)));
    }

    @Transactional
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
        return Optional.ofNullable(element).map(entityToModel::transform);
    }

    @Transactional
    @Override
    public <F> List<M> findManyByFieldEqual(String fieldName, F fieldValue) {
        CriteriaBuilder criteriaBuilder = emp.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(elementClass);
        Root<E> elementRoot = criteriaQuery.from(elementClass);
        criteriaQuery.select(elementRoot).where(criteriaBuilder.equal(elementRoot.get(fieldName), fieldValue));

        List<M> elements;
        try {
            elements = emp.createQuery(criteriaQuery).getResultList().stream().map(entityToModel::transform).collect(Collectors.toList());
        } catch (PersistenceException e) {
            elements = new ArrayList<>();
        }
        return elements;
    }
}
