package ua.com.parkhub.dto;

import java.io.Serializable;

public class PaymentResponseDTO implements Serializable {
    private int price;
    private boolean status;

    public PaymentResponseDTO() {
    }

    public PaymentResponseDTO(int price, boolean status) {
        this.price = price;
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
