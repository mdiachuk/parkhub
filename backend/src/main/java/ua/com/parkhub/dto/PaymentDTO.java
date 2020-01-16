package ua.com.parkhub.dto;

public class PaymentDTO {

    private Long id;
    private Integer price;
    private BookingDTO booking;

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

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", price=" + price +
                ", booking=" + booking +
                '}';
    }
}
