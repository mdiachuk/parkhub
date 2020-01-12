package ua.com.parkhub.model;

import ua.com.parkhub.model.enums.RoleModel;

import java.util.Objects;

public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleModel role;
    private CustomerModel customer;
    private String token;
    private int numberOfFailedPassEntering;

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

    public int getNumberOfFailedPassEntering() {
        return numberOfFailedPassEntering;
    }

    public void setNumberOfFailedPassEntering(int numberOfFailedPassEntering) {
        this.numberOfFailedPassEntering = numberOfFailedPassEntering;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel user = (UserModel) o;
        return numberOfFailedPassEntering == user.numberOfFailedPassEntering &&
                Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(customer, user.customer) &&
                Objects.equals(token, user.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, role, customer, token, numberOfFailedPassEntering);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", customer=" + customer +
                ", token='" + token + '\'' +
                ", numberOfFaildPassEntering=" + numberOfFailedPassEntering +
                '}';
    }
}
