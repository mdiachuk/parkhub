package ua.com.parkhub.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

import ua.com.parkhub.persistence.IElementDAO;

public class ElementDAO<E> implements IElementDAO<E> {

    @PersistenceContext
    EntityManager emp;

    Class<E> elementClass;

    public ElementDAO(Class<E> elementClass) {
        this.elementClass = elementClass;
    }

    @Override
    public void addElement(E element) {
        emp.persist(element);
    }

    @Override
    public void updateElement(E element) {
        emp.persist(element);
    }

    @Override
    public Optional<E> findElementById(long id) {
        try {
            E element = emp.find( elementClass, id);
            return Optional.of(element);
        } catch (NoResultException e1) {
            return Optional.empty();
        } catch (Exception e2) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public List<E> findAll() {
        CriteriaBuilder cb = emp.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(elementClass);
        Root<E> rootEntry = cq.from(elementClass);
        CriteriaQuery<E> all = cq.select(rootEntry);
        TypedQuery<E> allQuery = emp.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void deleteElement(E element) {
        emp.remove(element);
    }

    @Override
    public <F> Optional<E> findOneByFieldEqual(String fieldName, F fieldValue) {
        CriteriaBuilder criteriaBuilder = emp.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(elementClass);
        Root<E> elementRoot = criteriaQuery.from(elementClass);
        criteriaQuery.select(elementRoot).where(criteriaBuilder.equal(elementRoot.get(fieldName), fieldValue));

        try {
            E element = emp.createQuery(criteriaQuery).getSingleResult();
            return Optional.of(element);
        } catch (NoResultException e1) {
            return Optional.empty();
        } catch (Exception e2) {
            throw new UnsupportedOperationException();
        }
    }
}
