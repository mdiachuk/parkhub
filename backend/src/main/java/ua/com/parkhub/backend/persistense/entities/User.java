package ua.com.parkhub.backend.persistense.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Table(name = "users")
public class User implements ua.com.parkhub.backend.persistense.entities.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "second_name")
    private String secondname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String pass;

    @Column(name = "role_id")
    private int role;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                role == user.role &&
                Objects.equals(name, user.name) &&
                Objects.equals(secondname, user.secondname) &&
//                Objects.equals(customer, user.customer) &&
                Objects.equals(email, user.email) &&
                Objects.equals(pass, user.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondname,
//                customer,
                email, pass, role);
    }


}
