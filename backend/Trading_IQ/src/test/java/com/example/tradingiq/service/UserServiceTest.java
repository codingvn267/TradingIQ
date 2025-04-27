package com.example.tradingiq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.tradingiq.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
  
  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Test
  void testServiceIsNotNull() {
    assertThat(userService).isNotNull();
  }

  @Test
  void testRepositoryIsNotNull() {
    assertThat(userRepository).isNotNull();
  }
}
