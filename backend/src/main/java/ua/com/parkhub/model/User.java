//package ua.com.parkhub.model;
//
//import java.util.List;
//import java.util.Objects;
//
//public class User extends AbstractModel {
//
//    private Long id;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;
//    private UserRole role;
//    private Customer customer;
//    private List<SupportTicket> tickets;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public UserRole getRole() {
//        return role;
//    }
//
//    public void setRole(UserRole role) {
//        this.role = role;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public List<SupportTicket> getTickets() {
//        return tickets;
//    }
//
//    public void setTickets(List<SupportTicket> tickets) {
//        this.tickets = tickets;
//    }
//
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(id, user.id) &&
//                Objects.equals(firstName, user.firstName) &&
//                Objects.equals(lastName, user.lastName) &&
//                Objects.equals(email, user.email) &&
//                Objects.equals(password, user.password) &&
//                Objects.equals(role, user.role) &&
//                Objects.equals(customer, user.customer);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, firstName, lastName, email, password, role, customer);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("User" + ", id: ").append(id);
//        sb.append(", firstName: ").append(firstName);
//        sb.append(", lastName: ").append(lastName);
//        sb.append(", email: ").append(email);
//        sb.append(", password: ").append(password);
//        sb.append(", role: ").append(role);
//        sb.append(", customer: ").append(customer);
//        sb.append(", tickets: ").append(tickets);
//        return sb.toString();
//    }
//
//}
