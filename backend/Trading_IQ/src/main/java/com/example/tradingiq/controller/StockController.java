package com.example.tradingiq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.tradingiq.model.Stock;
import com.example.tradingiq.service.StockService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

  @Autowired
  private StockService stockService;

  @PostMapping("/save-all")
  public ResponseEntity<List<Stock>> saveAllStocks(@RequestBody List<Stock> stocks) {
    List<Stock> savedStocks = stockService.saveAllStocks(stocks);
    return ResponseEntity.ok(savedStocks);
  }

  @GetMapping
  public List<Stock> getAllStocks() {
    return stockService.getAllStocks();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getStockById(@PathVariable Integer id) {
    Optional<Stock> stock = stockService.getStockById(id);

    if (stock.isPresent()) {
      return ResponseEntity.ok(stock.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found");
    }
  }

  @GetMapping("/symbol/{symbol}")
  public ResponseEntity<List<Stock>> getStockBySymbol(
      @PathVariable String symbol,
      @RequestParam(defaultValue = "daily") String timeframe) {

    List<Stock> stocks = stockService.getStockBySymbolAndTimeframe(symbol, timeframe);
    return stocks.isEmpty()
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        : ResponseEntity.ok(stocks);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStock(@PathVariable Integer id) {
    Optional<Stock> stock = stockService.getStockById(id);
    if (stock.isPresent()) {
      stockService.deleteStock(id);
      return ResponseEntity.ok("Stock with ID " + id + " has been deleted.");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
  }

  @GetMapping("/history/{symbol}")
  public ResponseEntity<List<Stock>> getStockHistoryBySymbol(@PathVariable String symbol) {
    List<Stock> stocks = stockService.getStockHistoryBySymbol(symbol);
    if (stocks.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(stocks);
  }

  @GetMapping("/history/{symbol}/range")
  public ResponseEntity<List<Stock>> getStockHistoryByDateRange(
      @PathVariable String symbol,
      @RequestParam("start") String start,
      @RequestParam("end") String end) {

    List<Stock> stocks = stockService.getStockHistoryByDateRange(symbol, start, end);
    if (stocks.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(stocks);
  }

  @GetMapping("/history/{symbol}/paged")
  public ResponseEntity<Page<Stock>> getStockHistoryPaged(
      @PathVariable String symbol,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "date,asc") String sort) {

    String[] sortParams = sort.split(",");
    Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")
        ? Sort.Direction.DESC
        : Sort.Direction.ASC;
    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));

    Page<Stock> stocks = stockService.getStockHistoryPaged(symbol, pageable);

    if (stocks.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Page.empty());
    }

    return ResponseEntity.ok(stocks);
  }

  @DeleteMapping("/delete-all")
  public ResponseEntity<String> deleteAllStocks() {
    stockService.deleteAllStocks();
    return ResponseEntity.ok("All stock records have been deleted.");
  }

}
