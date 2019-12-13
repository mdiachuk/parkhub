package ua.com.parkhub.persistence;

import java.util.List;

public interface IElementDAO<E> {

    void addElement(E element);
    void updateElement(E element);
    E getElementById(long id);
    List<E> getAll();
    void deleteElement(E element);
}
