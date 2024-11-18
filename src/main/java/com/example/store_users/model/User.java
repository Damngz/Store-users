package com.example.store_users.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="session_user")
public class User {
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "nombre", nullable = false)
  private String name;

  @Column(name = "apellidos", nullable = false)
  private String last_names;

  @Column(name = "telefono", nullable = false)
  private String phone;

  @Column(name = "ciudad", nullable = false)
  private String city;

  @Column(name = "direccion", nullable = false)
  private String address;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(name = "rol", nullable = false)
  private String role;

  public User() {}

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
  return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastNames() {
    return last_names;
  }

  public void setLastNames(String last_names) {
    this.last_names = last_names;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}