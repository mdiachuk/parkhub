package ua.com.parkhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private CustomerDTO customerDTO;
    private RoleDTO role;
    private String token;

    private int numberOfFailedPassEntering;
    private List<SupportTicketDTO> tickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
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

    public List<SupportTicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<SupportTicketDTO> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return numberOfFailedPassEntering == userDTO.numberOfFailedPassEntering &&
                Objects.equals(id, userDTO.id) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(password, userDTO.password) &&
                Objects.equals(firstName, userDTO.firstName) &&
                Objects.equals(lastName, userDTO.lastName) &&
                Objects.equals(customerDTO, userDTO.customerDTO) &&
                role == userDTO.role &&
                Objects.equals(token, userDTO.token) &&
                Objects.equals(tickets, userDTO.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, firstName, lastName, customerDTO, role, token, numberOfFailedPassEntering, tickets);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerDTO=" + customerDTO +
                ", role=" + role +
                ", token='" + token + '\'' +
                ", numberOfFailedPassEntering=" + numberOfFailedPassEntering +
                ", tickets=" + tickets +
                '}';
    }
}
