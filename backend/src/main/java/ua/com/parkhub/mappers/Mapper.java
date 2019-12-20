package ua.com.parkhub.mappers;

public interface Mapper<M, E> {
    E transformEtoM(M from);
    M transformMtoE(E from);
}
