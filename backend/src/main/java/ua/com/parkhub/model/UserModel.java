package ua.com.parkhub.model;

import java.util.List;
import java.util.Objects;

public class UserModel extends AbstractModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleModel role;
    private CustomerModel customer;
    private String token;
    private int numberOfFaildPassEntering;
    private List<SupportTicketModel> tickets;


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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getNumberOfFaildPassEntering() {
        return numberOfFaildPassEntering;
    }

    public void setNumberOfFaildPassEntering(int numberOfFaildPassEntering) {
        this.numberOfFaildPassEntering = numberOfFaildPassEntering;
    }

    public List<SupportTicketModel> getTickets() {
        return tickets;
    }

    public void setTickets(List<SupportTicketModel> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return numberOfFaildPassEntering == userModel.numberOfFaildPassEntering &&
                Objects.equals(id, userModel.id) &&
                Objects.equals(firstName, userModel.firstName) &&
                Objects.equals(lastName, userModel.lastName) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(password, userModel.password) &&
                role == userModel.role &&
                Objects.equals(customer, userModel.customer) &&
                Objects.equals(token, userModel.token) &&
                Objects.equals(tickets, userModel.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, role, customer, token, numberOfFaildPassEntering, tickets);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", customer=" + customer +
                ", token='" + token + '\'' +
                ", numberOfFaildPassEntering=" + numberOfFaildPassEntering +
                ", tickets=" + tickets +
                '}';
    }
}
