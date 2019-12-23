package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface Mapper<E, M> {
    Optional<E> toEntity(M model);

    Optional<M> toModel(E entity);
}
