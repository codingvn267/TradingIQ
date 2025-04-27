package com.example.tradingiq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for StrategyService.
 * This class contains tests to verify the functionality of the StrategyService.
 */

@SpringBootTest
public class StrategyServiceTest {
  
  @Autowired
  private StrategyService strategyService;

  @Test
  void testServiceIsNotNull() {
    assertThat(strategyService).isNotNull();
  }
}
