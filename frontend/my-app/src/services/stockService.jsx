import axios from "axios";

export const fetchStockData = async (symbol, timeframe = "daily") => {
  try {
    const response = await axios.get(`http://localhost:8080/api/stocks/${symbol}?timeframe=${timeframe}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching stock data:", error);
    return [];
  }
};
