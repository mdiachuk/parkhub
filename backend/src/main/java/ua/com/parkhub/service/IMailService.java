package ua.com.parkhub.service;

public interface IMailService {

    void sendEmail(String[] to, String subject, String body);
}
