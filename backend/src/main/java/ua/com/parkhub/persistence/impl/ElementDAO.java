package ua.com.parkhub.persistence.impl;

import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.persistence.IElementDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    public Optional<M> addElement(M element) {
        E entity = modelToEntity.transform(element);
        emp.merge(entity);
        emp.flush();
        return Optional.of(entityToModel.transform(entity));
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
        return Optional.ofNullable(element).map(entityToModel::transform);
    }

    @Override
    public void deleteElement(M element) {
        emp.remove(emp.merge(modelToEntity.transform(element)));
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
        return Optional.ofNullable(element).map(entityToModel::transform);
    }

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
