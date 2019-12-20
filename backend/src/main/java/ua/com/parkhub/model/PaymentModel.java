package ua.com.parkhub.model;

public class PaymentModel {

    private BookingModel booking;
    private int price;
    private boolean isPaid;

    public PaymentModel(BookingModel booking, int price, boolean isPaid) {
        this.booking = booking;
        this.price = price;
        this.isPaid = isPaid;
    }

    public BookingModel getBooking() {
        return booking;
    }

    public void setBooking(BookingModel booking) {
        this.booking = booking;
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
}
