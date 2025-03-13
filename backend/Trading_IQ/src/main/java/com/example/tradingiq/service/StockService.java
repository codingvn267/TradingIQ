package com.example.tradingiq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.example.tradingiq.model.Stock;
import com.example.tradingiq.repository.StockRepository;

@Service
public class StockService {

  @Autowired
  private StockRepository stockRepository;

  public List<Stock> saveAllStocks(List<Stock> stocks) {
    return stockRepository.saveAll(stocks);
  }

  public List<Stock> getAllStocks() {
    return stockRepository.findAll();
  }

  public Optional<Stock> getStockById(int id) {
    return stockRepository.findById(id);
  }

  public List<Stock> getStockBySymbol(String symbol) {
    return stockRepository.findBySymbol(symbol);
  }

  public void deleteStock(int id) {
    stockRepository.deleteById(id);
  }

  public List<Stock> getStockHistoryBySymbol(String symbol) {
    return stockRepository.findBySymbolOrderByDateAsc(symbol);
  }

  public List<Stock> getStockHistoryByDateRange(String symbol, String start, String end) {
    return stockRepository.findBySymbolAndDateBetween(symbol, LocalDate.parse(start), LocalDate.parse(end));
  }

  public Page<Stock> getStockHistoryPaged(String symbol, Pageable pageable) {
    return stockRepository.findBySymbol(symbol, pageable);
  }

  public void deleteAllStocks() {
    stockRepository.deleteAll();
  }

  public List<Stock> getStockBySymbolAndTimeframe(String symbol, String timeframe) {
    switch (timeframe) {
        case "1h":
            return stockRepository.findHourlyStockData(symbol);
        case "4h":
            return stockRepository.findFourHourlyStockData(symbol);
        case "daily":
            return stockRepository.findBySymbolOrderByDateAsc(symbol);
        case "weekly":
            return stockRepository.findWeeklyStockData(symbol);
        case "monthly":
            return stockRepository.findMonthlyStockData(symbol);
        default:
            throw new IllegalArgumentException("Invalid timeframe: " + timeframe);
    }
}

}
