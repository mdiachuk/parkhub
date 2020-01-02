package ua.com.parkhub.persistence.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.persistence.IElementDAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


import ua.com.parkhub.persistence.IElementDAO;

import static javax.persistence.PersistenceContextType.EXTENDED;

public class ElementDAO<E>  implements IElementDAO<E> {

    @PersistenceContext(unitName = "default", type = EXTENDED)
    EntityManager emp;

    Class<E> elementClass;

    public ElementDAO(Class<E> elementClass) {
        this.elementClass = elementClass;
    }

    @Transactional
    @Override
    public void addElement(E element) {
        emp.persist(element);
    }


    @Transactional
    public void updateElement(E element) {
        emp.persist(element);
    }



    @Override
    public E findElementByIdSimple(long id) {
        return emp.find(elementClass, id);
    }

    @Override
    public Optional<E> findElementById(long id) {
//<<<<<<< HEAD
//            E element = emp.find( elementClass, id);
//            return Optional.ofNullable(element);
//=======
        E element;
        try {
            element = emp.find(elementClass, id);
        } catch (PersistenceException e) {
            element = null;
        }
        return Optional.ofNullable(element);
//=======
//
//    @Transactional
//    public Optional<E> findElementById(long id) {
//        return Optional.ofNullable(emp.find( elementClass, id));
//>>>>>>> 414ab46fb119952f8a5186146f76d7060f173f40
    }



    @Transactional
    public List<E> findAll() {
        CriteriaBuilder cb = emp.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(elementClass);
        Root<E> rootEntry = cq.from(elementClass);
        CriteriaQuery<E> all = cq.select(rootEntry);
        TypedQuery<E> allQuery = emp.createQuery(all);
        return allQuery.getResultList();
    }


    @Transactional
    public void deleteElement(E element) {
        emp.remove(element);
    }


    @Transactional
    public <F> Optional<E> findOneByFieldEqual(String fieldName, F fieldValue) {
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
        return Optional.ofNullable(element);
    }

//        try {
//            E element = emp.createQuery(criteriaQuery).getSingleResult();
//            return Optional.of(element);
//        } catch (NoResultException e1) {
//            return Optional.empty();
//        } catch (Exception e2) {
//            throw new UnsupportedOperationException();
//        }
    }


