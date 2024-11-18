package com.example.store_users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store_users.model.ApiResponse;
import com.example.store_users.model.Login;
import com.example.store_users.model.User;
import com.example.store_users.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
    return ResponseEntity.ok(new ApiResponse<>(200,"OK",userService.getAllUsers()));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long userId) {
    Optional<User> user = userService.getUserById(userId);
    return user
      .map(value -> ResponseEntity.ok(new ApiResponse<>(200, "User found", value)))
      .orElseGet(() -> ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ApiResponse<>(404, "User not found", null)));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
    User createdUser = userService.createUser(user);
    ApiResponse<User> response = new ApiResponse<>(201, "User created successfully", createdUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long userId, @RequestBody User user) {
    try {
      User updatedUser = userService.updateUser(userId, user);
      return ResponseEntity.ok(new ApiResponse<>(200, "User updated successfully", updatedUser));
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, "User not found", null));
    }
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
    try {
      userService.deleteUser(userId);
      return ResponseEntity.ok(new ApiResponse<>(200, "User deleted", null));
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, "User not found", null));
    }
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<Optional<User>>> login(@RequestBody Login loginRequest) {
    try {
      userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
      ApiResponse<Optional<User>> response = new ApiResponse<>(200, "Login successful", userService.getUserByEmail(loginRequest.getEmail()));
      return ResponseEntity.ok(response);
    } catch (RuntimeException e) {
      return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ApiResponse<>(401, "Invalid credentials", null));
    }
  }
}
