package ua.com.parkhub.persistence.impl;

import ua.com.parkhub.persistence.IElementDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
        return emp.find(elementClass, id);
    }

    @Override
    public boolean haveEmail(String email) {
        Query query =
                emp.createQuery("SELECT email FROM User WHERE email=:email")
                        .setParameter("email", email).setMaxResults(1);

        return query.getResultList().size()>0?true:false;
    }

    @Override
    public boolean havePhoneNumber(String phoneNumber) {

        Query query =
                emp.createQuery("SELECT phoneNumber FROM Customer WHERE phone_number=:phoneNumber")
                        .setParameter("phoneNumber", phoneNumber).setMaxResults(1);

        return query.getResultList().size()>0?true:false;
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
}