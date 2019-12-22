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
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        if (price != payment.price) return false;
        if (isPaid != payment.isPaid) return false;
        if (!Objects.equals(id, payment.id)) return false;
        return Objects.equals(booking, payment.booking);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + (isPaid ? 1 : 0);
        result = 31 * result + (booking != null ? booking.hashCode() : 0);
        return result;
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
