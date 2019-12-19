package ua.com.parkhub.persistence;

import java.util.List;

public interface IElementDAO<E> {

    void addElement(E element);
    void updateElement(E element);
    E findElementById(long id);
    boolean haveEmail (String email);
    boolean havePhoneNumber (String phoneNumber);
    List<E> findAll();
    void deleteElement(E element);
}
