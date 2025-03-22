package com.example.tradingiq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.tradingiq.config.CorsConfig;
import com.example.tradingiq.model.User;
import com.example.tradingiq.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final WebMvcConfigurer corsConfigurer;

  private final CorsConfig corsConfig;
  @Autowired
  private UserService userService;

  UserController(CorsConfig corsConfig, WebMvcConfigurer corsConfigurer) {
    this.corsConfig = corsConfig;
    this.corsConfigurer = corsConfigurer;
  }

  @PostMapping("/save")
  public User saveUser(@RequestBody User user) {
    return userService.registerUser(user);
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public Optional<User> getUserById(@PathVariable Integer id) {
    return userService.getUserById(id);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
    Optional<User> user = userService.getUserByEmail(email);

    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
    Optional<User> user = userService.getUserById(id);
    if (user.isPresent()) {
      userService.deleteUser(id);
      return ResponseEntity.ok("User with ID " + id + " has been deleted.");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
    Optional<User> user = userService.getUserByEmail(loginRequest.getEmail());

    if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
      return ResponseEntity.ok(user.get()); // Return user data on successful login
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
  }

}
