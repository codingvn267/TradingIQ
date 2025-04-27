package com.example.tradingiq.util;

import com.example.tradingiq.model.Stock;
import org.ta4j.core.*;
import org.ta4j.core.num.DecimalNum;

import java.time.Duration;
import java.time.ZoneId;
import java.util.List;
/**
 * Utility class for converting stock data to TA4J BarSeries.
 * This class provides methods to convert a list of Stock objects into a BarSeries object.
 */

public class TA4JUtil {
  public static BarSeries convertToBarSeries(List<Stock> stockData, String symbol) {
    BarSeries series = new BaseBarSeriesBuilder().withName(symbol).build();

    for (Stock stock : stockData) {
      Bar bar = BaseBar.builder(DecimalNum::valueOf, Number.class)
          .timePeriod(Duration.ofDays(1))
          .endTime(stock.getTimestamp().atZone(ZoneId.systemDefault()))
          .openPrice(stock.getOpenPrice())
          .highPrice(stock.getHighPrice())
          .lowPrice(stock.getLowPrice())
          .closePrice(stock.getClosePrice())
          .volume(stock.getVolume())
          .build();

      series.addBar(bar);
    }
    return series;
  }
}
