package ua.com.parkhub.persistence.impl;

import ua.com.parkhub.mapper.Mapper;
import ua.com.parkhub.persistence.IElementDAO;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    @Transient
    public void addElement(M element) {
        emp.persist(modelToEntity.transform(element));
    }

    @Override
    public void updateElement(M element) {
        emp.persist(modelToEntity.transform(element));
    }

    @Override
    public List<M> findAll() {
        CriteriaBuilder cb = emp.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(elementClass);
        Root<E> rootEntry = cq.from(elementClass);
        CriteriaQuery<E> all = cq.select(rootEntry);
        TypedQuery<E> allQuery = emp.createQuery(all);
        return allQuery.getResultList().stream().map(entityToModel::transform).collect(Collectors.toList());
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
        return Optional.ofNullable(entityToModel.transform(element));
    }
}
