package com.example.tradingiq.service;

import com.example.tradingiq.model.Stock;
import com.example.tradingiq.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import java.time.Instant;
import java.time.ZoneId;

@Service
public class StockDataService {

    private final RestTemplate restTemplate;
    private final StockRepository stockRepository;

    @Value("${stockdata.api.url}")
    private String stockDataApiUrl;

    @Value("${stockdata.api.key}")
    private String stockDataApiKey;

    public StockDataService(StockRepository stockRepository) {
        this.restTemplate = new RestTemplate();
        this.stockRepository = stockRepository;
    }

    @SuppressWarnings({ "null", "unchecked" })
    public void fetchAndSaveHistoricalStockData() {
        String symbols = "TSLA"; 
        String dateFrom = "2024-12-01"; 
        String url = stockDataApiUrl + "/eod?symbols=" + symbols + "&api_token=" + stockDataApiKey + "&date_from="
                + dateFrom;

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() {
                });

        if (response.getBody() != null && response.getBody().containsKey("data")) {
            List<Map<String, Object>> stockList = (List<Map<String, Object>>) response.getBody().get("data");

            if (stockList != null && !stockList.isEmpty()) {
                for (Map<String, Object> stockData : stockList) {
                    String stockSymbol = symbols;
                    double openPrice = ((Number) stockData.get("open")).doubleValue();
                    double highPrice = ((Number) stockData.get("high")).doubleValue();
                    double lowPrice = ((Number) stockData.get("low")).doubleValue();
                    double closePrice = ((Number) stockData.get("close")).doubleValue();
                    int volume = ((Number) stockData.get("volume")).intValue();

                    // ✅ Convert API timestamp correctly
                    String apiTimestamp = (String) stockData.get("date"); // Example: "2023-09-12T00:00:00.000Z"
                    LocalDateTime timestamp = Instant.parse(apiTimestamp).atZone(ZoneId.of("UTC")).toLocalDateTime();

                    // ✅ Save multiple historical data points per stock
                    Stock stock = new Stock(stockSymbol, openPrice, highPrice, lowPrice, closePrice, volume, timestamp);
                    stockRepository.save(stock);
                }
            }
        }
    }

}
