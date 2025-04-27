package com.example.tradingiq.service;

import com.example.tradingiq.model.Stock;
import com.example.tradingiq.util.TA4JUtil;
import org.springframework.stereotype.Service;
import org.ta4j.core.*;
import org.ta4j.core.backtest.BarSeriesManager;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;

import java.util.List;

/**
 * Service for analyzing stock data using a simple moving average (SMA) strategy.
 * It uses the TA4J library to implement the trading strategy and generate trade signals.
 */

@Service 
public class StrategyService {

  public String analyzeStock(List<Stock> stockData, String symbol) {
    if (stockData == null || stockData.size() < 50) {
      return "⚠️ Feed more data for me, please.";
    }

    BarSeries series = TA4JUtil.convertToBarSeries(stockData, symbol);

    ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
    SMAIndicator smaShort = new SMAIndicator(closePrice, 10);
    SMAIndicator smaLong = new SMAIndicator(closePrice, 50);

    Rule entryRule = new CrossedUpIndicatorRule(smaShort, smaLong);
    Rule exitRule = new CrossedDownIndicatorRule(smaShort, smaLong);

    Strategy strategy = new BaseStrategy(entryRule, exitRule);
    BarSeriesManager manager = new BarSeriesManager(series);
    TradingRecord record = manager.run(strategy);

    StringBuilder report = new StringBuilder();

    if (!record.getPositions().isEmpty()) {
      for (Position position : record.getPositions()) {

        if (position.isOpened()) {
          int entryIndex = position.getEntry().getIndex();

          report.append("⚠️ Trade still open - entry at index ")
              .append(entryIndex)
              .append("\n");

        } else {
          int entryIndex = position.getEntry().getIndex();
          int exitIndex = position.getExit().getIndex();

          Bar entryBar = series.getBar(entryIndex);
          Bar exitBar = series.getBar(exitIndex);

          report.append("🟢 BUY at $")
              .append(entryBar.getClosePrice())
              .append(" on ")
              .append(entryBar.getEndTime())
              .append("\n");

          report.append("🔴 SELL at $")
              .append(exitBar.getClosePrice())
              .append(" on ")
              .append(exitBar.getEndTime())
              .append("\n");
        }
      }
    } else {
      report.append("⚠️ No trade signals generated.");
    }

    return report.toString();

  }
}
