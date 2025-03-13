package com.example.tradingiq.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.tradingiq.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {


  List<Stock> findBySymbol(String symbol);

  @Query("SELECT s FROM Stock s WHERE s.symbol = :symbol ORDER BY s.date ASC")
  List<Stock> findBySymbolOrderByDateAsc(@Param("symbol") String symbol);

  @Query("SELECT s FROM Stock s WHERE s.symbol = :symbol AND s.date BETWEEN :start AND :end ORDER BY s.date ASC")
  List<Stock> findBySymbolAndDateBetween(
      @Param("symbol") String symbol,
      @Param("start") LocalDate start,
      @Param("end") LocalDate end);

  Page<Stock> findBySymbol(@Param("symbol") String symbol, Pageable pageable);

  
  @Query(value = "SELECT * FROM stocks WHERE symbol = :symbol AND MOD(HOUR(date), 1) = 0 ORDER BY date ASC", nativeQuery = true)
  List<Stock> findHourlyStockData(@Param("symbol") String symbol);

  
  @Query(value = "SELECT * FROM stocks WHERE symbol = :symbol AND MOD(HOUR(date), 4) = 0 ORDER BY date ASC", nativeQuery = true)
  List<Stock> findFourHourlyStockData(@Param("symbol") String symbol);

  @Query(value = "SELECT * FROM stocks WHERE symbol = :symbol AND DATE(date) = CURRENT_DATE ORDER BY date ASC", nativeQuery = true)
  List<Stock> findDailyStockData(@Param("symbol") String symbol);

  
  @Query(value = "SELECT * FROM stocks WHERE symbol = :symbol AND WEEK(date) = WEEK(CURRENT_DATE) AND YEAR(date) = YEAR(CURRENT_DATE) ORDER BY date ASC", nativeQuery = true)
  List<Stock> findWeeklyStockData(@Param("symbol") String symbol);

  
  @Query(value = "SELECT * FROM stocks WHERE symbol = :symbol AND MONTH(date) = MONTH(CURRENT_DATE) AND YEAR(date) = YEAR(CURRENT_DATE) ORDER BY date ASC", nativeQuery = true)
  List<Stock> findMonthlyStockData(@Param("symbol") String symbol);

}
