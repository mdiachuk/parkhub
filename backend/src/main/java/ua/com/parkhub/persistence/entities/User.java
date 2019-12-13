package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user", schema = "park_hub")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "park_hub.user_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    @Size(min = 6, max = 50)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(mappedBy = "solvers")
    private Set<SupportTicket> tickets;

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

    public Set<SupportTicket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<SupportTicket> tickets) {
        this.tickets = tickets;
    }
}
