package ua.com.parkhub.model;

import java.util.Objects;

public class Payment extends AbstractModel {

    private Long id;
    private int price;
    private boolean isPaid;
    private Booking booking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return price == payment.price &&
                Objects.equals(id, payment.id) &&
                Objects.equals(booking, payment.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, booking);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Payment" + ", id: ").append(id);
        sb.append(", price: ").append(price);
        sb.append(", isPaid: ").append(isPaid);
        sb.append(", booking: ").append(booking);
        return sb.toString();
    }
}
