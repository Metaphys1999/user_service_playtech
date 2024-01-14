package com.user.service.entity;

import com.user.service.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;

import java.sql.Date;

@Entity
@Builder
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "users_phone_ukey", columnNames = {"mobile_phone"})})
public class User {

    @Id
    @Column(name = "id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotNull(message = "The first name cannot be null")
    @NotBlank(message = "The first name cannot be blank")
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR")
    private String firstName;

    @NotNull(message = "The last name cannot be null")
    @NotBlank(message = "The last name cannot be blank")
    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR")
    private String lastName;

    @NotNull(message = "The date of birth cannot be null")
    @Past(message = "The date of birth must be prior to the current date")
    @Column(name = "date_birth", nullable = false)
    private Date dateBirth;

    @NotNull(message = "The address cannot be null")
    @NotBlank(message = "The address cannot be blank")
    @Column(name = "address", nullable = false)
    private String address;

    // private String token;

    @NotNull(message = "The password cannot be null")
    @NotBlank(message = "The password cannot be blank")
    @Column(name = "password", length = 120, nullable = false)
    private String password;

    @Basic
    @NotNull(message = "The mobile phone cannot be null")
    @NotBlank(message = "The mobile phone cannot be blank")
    @Column(name = "mobile_phone", nullable = false, columnDefinition = "VARCHAR")
    private String phone;

    @Email(message = "The Email should be valid")
    @NotNull(message = "The Email cannot be null")
    @NotBlank(message = "The Email cannot be blank")
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
