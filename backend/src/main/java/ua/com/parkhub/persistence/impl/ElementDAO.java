package ua.com.parkhub.persistence.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.persistence.IElementDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class ElementDAO<E> implements IElementDAO<E> {

    @PersistenceContext
    EntityManager emp;

    Class<E> elementClass;

    public ElementDAO(Class<E> elementClass) {
        this.elementClass = elementClass;
    }

    @Override
    @Transactional
    public void addElement(E element) {
        emp.persist(element);
    }

    @Override
    @Transactional
    public void updateElement(E element) {
        this.emp.persist(this.emp.merge(element));
    }

    @Override
    @Transactional
    public Optional<E> findElementById(long id) {
        return Optional.ofNullable(emp.find( elementClass, id));
    }

    @Override
    @Transactional
    public List<E> findAll() {
        CriteriaBuilder cb = emp.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(elementClass);
        Root<E> rootEntry = cq.from(elementClass);
        CriteriaQuery<E> all = cq.select(rootEntry);
        TypedQuery<E> allQuery = emp.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    @Transactional
    public void deleteElement(E element) {
        emp.remove(element);
    }
}
