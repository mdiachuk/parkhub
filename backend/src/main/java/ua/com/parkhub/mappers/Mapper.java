package ua.com.parkhub.mappers;

public interface Mapper<E, M> {
    E toEntity(M model);
    M totoModel(E entity);
}
