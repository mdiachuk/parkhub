package ua.com.parkhub.model;

import java.util.List;

public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleModel role;
    private CustomerModel customer;
//    private List<SupportTicketModel> tickets;
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

//    public List<SupportTicketModel> getTickets() {
//        return tickets;
//    }
//
//    public void setTickets(List<SupportTicketModel> tickets) {
//        this.tickets = tickets;
//    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
