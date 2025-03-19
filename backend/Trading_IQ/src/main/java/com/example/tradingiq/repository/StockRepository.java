package com.example.tradingiq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tradingiq.model.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // ✅ Fetch all stored stock data for a given symbol, ordered by timestamp ASC (oldest first)
    List<Stock> findBySymbolOrderByTimestampAsc(String symbol);

    // ✅ Fetch the most recent stock data for a symbol
    @Query("SELECT s FROM Stock s WHERE s.symbol = :symbol ORDER BY s.timestamp DESC LIMIT 1")
    Stock findLatestStockData(@Param("symbol") String symbol);
}


