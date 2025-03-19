package com.example.tradingiq.controller;

import com.example.tradingiq.model.Stock;
import com.example.tradingiq.service.StockDataService;
import com.example.tradingiq.repository.StockRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

  private final StockDataService stockDataService;
  private final StockRepository stockRepository;

  public StockController(StockDataService stockDataService, StockRepository stockRepository) {
    this.stockDataService = stockDataService;
    this.stockRepository = stockRepository;
  }

  // ✅ Fetch & store multiple stocks (TSLA, AAPL, NVDA) - batch process
  @GetMapping("/fetch-historical")
  public String fetchAndSaveHistoricalStockData() {
    stockDataService.fetchAndSaveHistoricalStockData();
    return "Historical stock data fetched and saved!";
  }

  // ✅ Retrieve all stored stock data for a specific symbol
  @GetMapping("/history")
  public List<Stock> getStockHistory(@RequestParam String symbol) {
    return stockRepository.findBySymbolOrderByTimestampAsc(symbol);
  }

  // ✅ Retrieve the most recent stock data for a symbol
  @GetMapping("/latest")
  public Stock getLatestStockData(@RequestParam String symbol) {
    return stockRepository.findLatestStockData(symbol);
  }
}
