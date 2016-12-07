package es.uji.agdc.videoclub.models;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * User entity, from the business domain
 */
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    public enum Role{
        ADMIN, MEMBER
    }

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int phone;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDate lastPayment;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public String getDni() {
        return dni;
    }

    public User setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getPhone() {
        return phone;
    }

    public User setPhone(int phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getLastPayment() {
        return lastPayment;
    }

    public User setLastPayment(LocalDate lastPayment) {
        this.lastPayment = lastPayment;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public boolean isMember() {
        return role == Role.MEMBER;
    }

    public boolean isAdmin() {
        return !isMember();
    }

    @Override
    public String toString() {
        return "User{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", lastPayment=" + lastPayment +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                "} " + super.toString();
    }
}
