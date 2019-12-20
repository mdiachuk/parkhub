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
        emp.refresh(element);
    }

    @Override
    public E findElementById(long id) {
        return emp.find( elementClass, id);
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
        CriteriaBuilder criteriaBuilder = emp.getCriteriaBuilder();                      //Create an EntityManager, open a transaction, and create a CriteriaBuilder that will build query objects.
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(elementClass);      //Using CriteriaBuilder we create CriteriaQuery, which is parameterized by the type that this query returns.
        Root<E> elementRoot = criteriaQuery.from(elementClass);                          //Then a root object is created, from which the tree of properties is traversed when imposing restrictions or specifying what to choose.
        criteriaQuery.select(elementRoot).where(criteriaBuilder.equal(elementRoot.get(fieldName), fieldValue));
                                                                                         //The equal() method creates an instance of Predicate that compares the values of the received link to the fieldName and the fieldValue.
                                                                                         //This Predicate is given to the CriteriaQuery.where() method and now the query will return only those objects for which the predicate is true.
        try {
            E element = emp.createQuery(criteriaQuery).getSingleResult();
            return Optional.of(element);
        } catch (NoResultException e1) {
//            logger.debug(e1.getMessage());
            return Optional.empty();
        } catch (Exception e2) {
//            logger.error(e2.getMessage(), e2);
            throw new UnsupportedOperationException();
        }
    }
}
