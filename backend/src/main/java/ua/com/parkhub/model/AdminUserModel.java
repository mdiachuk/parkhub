package ua.com.parkhub.model;

import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.entities.SupportTicket;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.List;

public class AdminUserModel {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
    private Customer customer;
    private String userRole;
    private List<SupportTicket> tickets;

    private AdminUserModel() {
    }

    public AdminUserModel(long id, String firstName, UserRole role) {
        this.id = id;
        this.firstName = firstName;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<SupportTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<SupportTicket> tickets) {
        this.tickets = tickets;
    }
}

