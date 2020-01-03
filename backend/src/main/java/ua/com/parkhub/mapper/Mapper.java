package ua.com.parkhub.mapper;

public interface Mapper<A, B> {
    B transform(A from);
}
