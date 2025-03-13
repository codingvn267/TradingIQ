import { useEffect, useState } from "react";
import Chart from "react-apexcharts";
import { fetchStockData } from "../services/stockService";
import './stockChart.css';

const StockChart = ({ symbol }) => {
  const [series, setSeries] = useState([{ data: [] }]);
  const [loading, setLoading] = useState(true);
  const [timeframe, setTimeframe] = useState("daily"); // Default: Daily Chart

  useEffect(() => {
    const getStockData = async () => {
      setLoading(true);
      try {
        const stockData = await fetchStockData(symbol, timeframe);
        console.log(`Fetched ${timeframe} Stock Data:`, stockData);

        // Format data for ApexCharts
        const formattedData = stockData.map((stock) => ({
          x: new Date(stock.date),
          y: [stock.open, stock.high, stock.low, stock.close],
        }));

        setSeries([{ data: formattedData }]);
      } catch (error) {
        console.error("Error fetching stock data:", error);
      }
      setLoading(false);
    };

    getStockData();
  }, [symbol, timeframe]);

  const chartOptions = {
    chart: {
      type: "candlestick",
      height: "600px",
      width: "100%",
      background: "#222",
    },
    title: {
      text: `${symbol} Candlestick Chart (${timeframe.toUpperCase()})`,
      align: "left",
      style: { color: "#fff" },
    },
    xaxis: {
      type: "datetime",
      labels: { style: { colors: "#fff" } },
    },
    yaxis: {
      tooltip: { enabled: true },
      labels: { style: { colors: "#fff" } },
    },
    grid: {
      borderColor: "#444",
    },
  };

  return (
    <div className="stock-chart-container">
      <div className="timeframe-selector">
        <label>Select Timeframe: </label>
        <select value={timeframe} onChange={(e) => setTimeframe(e.target.value)}>
          <option value="1h">1 Hour</option>
          <option value="4h">4 Hours</option>
          <option value="daily">Daily</option>
          <option value="weekly">Weekly</option>
          <option value="monthly">Monthly</option>
        </select>
      </div>

      {loading ? (
        <p className="loading">Loading...</p>
      ) : (
        <Chart
          options={chartOptions}
          series={series}
          type="candlestick"
          width="100%"
          height="400px"
        />
      )}
    </div>
  );
};

export default StockChart;



