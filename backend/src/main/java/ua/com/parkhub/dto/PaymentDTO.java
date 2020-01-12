package ua.com.parkhub.dto;

public class PaymentDTO {
    private int price;
    private BookingDTO booking;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "price=" + price +
                ", booking=" + booking +
                '}';
    }
}
