package ua.com.parkhub.dto;

public class UserResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private String token;

    private int numberOfFailedPassEntering;

    public Long getId() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String  role) {
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", token='" + token + '\'' +
                ", numberOfFailedPassEntering=" + numberOfFailedPassEntering +
                '}';
    }
}
