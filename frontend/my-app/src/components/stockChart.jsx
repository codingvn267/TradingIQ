import { useEffect, useState } from "react";
import Chart from "react-apexcharts";
import { fetchStockData } from "../services/stockService";
import './stockChart.css';

const StockChart = ({ symbol }) => {
  const [candlestickSeries, setCandlestickSeries] = useState([]);
  const [volumeSeries, setVolumeSeries] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getStockData = async () => {
      setLoading(true);
      try {
        const stockData = await fetchStockData(symbol);
        console.log(`Fetched Stock Data for ${symbol}:`, stockData);

        if (stockData.length > 0) {
          // ✅ Format Candlestick Data
          const formattedCandlestickData = stockData.map((stock) => ({
            x: new Date(stock.timestamp),
            y: [stock.openPrice, stock.highPrice, stock.lowPrice, stock.closePrice],
          }));

          // ✅ Format Volume Data
          const formattedVolumeData = stockData.map((stock) => ({
            x: new Date(stock.timestamp),
            y: stock.volume,
            color: stock.closePrice >= stock.openPrice ? "#00E396" : "#FF4560", // Green for up, red for down
          }));

          setCandlestickSeries([{ name: "Candlestick", type: "candlestick", data: formattedCandlestickData }]);
          setVolumeSeries([{ name: "Volume", type: "bar", data: formattedVolumeData }]);
        } else {
          setCandlestickSeries([]);
          setVolumeSeries([]);
        }
      } catch (error) {
        console.error("Error fetching stock data:", error);
      }
      setLoading(false);
    };

    getStockData();
  }, [symbol]);

  // ✅ Shared x-axis settings for perfect alignment
  const sharedXaxis = {
    type: "datetime",
    labels: { style: { colors: "#fff" } },
    tickPlacement: "on",
    axisBorder: { show: true, color: "#444" }, // ✅ Ensures both x-axes have the same border
    axisTicks: { show: true, color: "#444" }, // ✅ Makes sure ticks are aligned
  };

  // ✅ Candlestick Chart Options
  const candlestickOptions = {
    chart: { type: "candlestick", height: "400px", background: "#222" },
    title: { text: `${symbol} Candlestick Chart`, align: "left", style: { color: "#fff" } },
    xaxis: sharedXaxis, // ✅ Shared x-axis
    yaxis: {
      labels: { style: { colors: "#fff" } },
      tooltip: { enabled: true },
    },
    grid: { borderColor: "#444" },
  };

  // ✅ Volume Chart Options (Aligned with Candlestick)
  const volumeOptions = {
    chart: { type: "bar", height: "150px", background: "#222" },
    xaxis: sharedXaxis, // ✅ Shared x-axis for perfect alignment
    yaxis: {
      labels: { style: { colors: "#fff" }, formatter: (value) => `${Math.round(value / 1000)}K` }, // ✅ Show volume in 'K'
      opposite: false, // ✅ Place Y-axis numbers on the left
    },
    grid: { borderColor: "#444", show: true }, // ✅ Keep light grid for better readability
    plotOptions: { bar: { columnWidth: "50%", borderRadius: 2 } }, // ✅ Adjust bar width & style
    dataLabels: { enabled: false }, // ✅ Hide volume labels on bars
    stroke: { width: 0 }, // ✅ No stroke for volume bars
  };

  return (
    <div className="stock-chart-container">
      {loading ? (
        <p className="loading">Loading...</p>
      ) : (
        <>
          {/* Candlestick Chart */}
          <Chart options={candlestickOptions} series={candlestickSeries} type="candlestick" width="100%" height="400px" />
          
          {/* Volume Chart (Aligned & with Left Y-axis) */}
          <Chart options={volumeOptions} series={volumeSeries} type="bar" width="100%" height="150px" />
        </>
      )}
    </div>
  );
};

export default StockChart;







