package com.example.tradingiq.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stockData")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Auto-generated, should not be included in constructor manually

    private String symbol;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double closePrice;
    private int volume;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // ✅ Add a constructor without `id`
    public Stock(String symbol, double openPrice, double highPrice, double lowPrice, double closePrice, int volume, LocalDateTime timestamp) {
        this.symbol = symbol;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.timestamp = timestamp;
    }
}

