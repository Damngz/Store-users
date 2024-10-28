package com.example.store_users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store_users.model.User;
import com.example.store_users.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long userId) {
    return userRepository.findById(userId);
  }

  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User updateUser(Long userId, User updatedUser) {
    return userRepository
      .findById(userId)
      .map(user -> {
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole());
        return userRepository.save(user);
      })
      .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
  }

  public void deleteUser(Long userId) {
    if (userRepository.existsById(userId)) {
      userRepository.deleteById(userId);
    } else {
      throw new RuntimeException("User not found with ID: " + userId);
    }
  }

  public User authenticateUser(String email, String password) {
    return userRepository.findByEmailAndPassword(email, password)
      .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
}
}
