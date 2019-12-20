package ua.com.parkhub.model;

public interface Mapper<A, B> {
    B transform(A from);
}