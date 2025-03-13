package com.example.tradingiq.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "stocksData")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private LocalDate date; // New field for stock date

  @Column(nullable = false)
  private String symbol;

  @Column(nullable = false)
  private double open;

  @Column(nullable = false)
  private double high;

  @Column(nullable = false)
  private double low;

  @Column(nullable = false)
  private double close;
}