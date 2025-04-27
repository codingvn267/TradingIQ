package com.example.tradingiq.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.tradingiq.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for StockDataService.
 * This class contains tests to verify the functionality of the StockDataService.
 */

@SpringBootTest
class StockDataServiceTest {

    @Autowired
    private StockDataService stockDataService;

    @Autowired
    private StockRepository stockRepository;

    @Test
    void testServiceIsNotNull() {
        assertThat(stockDataService).isNotNull();
    }

    @Test
    void testRepositoryIsNotNull() {
        assertThat(stockRepository).isNotNull();
    }
}

