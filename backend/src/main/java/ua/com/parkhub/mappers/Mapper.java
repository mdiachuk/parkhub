package ua.com.parkhub.mappers;

public interface Mapper<A, B> {
    B transform(A from);
}

