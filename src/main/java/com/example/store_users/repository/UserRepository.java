package com.example.store_users.repository;

import com.example.store_users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
  Optional<User> findByEmail(String email);
  Optional<User> findByEmailAndPassword(String email, String password);
}
