package ua.com.parkhub.persistence.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Objects;

@Component
@javax.persistence.Entity
@Table(name = "user", schema = "park_hub")
public class User implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String secondname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password")
    private String pass;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private UserRole role;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(secondname, user.secondname) &&
                Objects.equals(customer, user.customer) &&
                Objects.equals(email, user.email) &&
                Objects.equals(pass, user.pass) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondname, customer, email, pass, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondname='" + secondname + '\'' +
                ", customer=" + customer +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", role=" + role +
                '}';
    }
}
