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

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<SupportTicket> getTickets() {
        return tickets;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserRole() {
        return userRole;
    }

    public class Builder {
        private Builder(){
        }
        public Builder setUserId(long id){
            AdminUserModel.this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName){
            AdminUserModel.this.firstName = firstName;
            return this;
        }
    }
}
