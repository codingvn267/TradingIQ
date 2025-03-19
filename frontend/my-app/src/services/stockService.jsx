import axios from "axios";

export const fetchStockData = async (symbol) => {
  try {
    const response = await axios.get(`http://localhost:8080/api/stocks/history?symbol=${symbol}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching stock data:", error);
    return [];
  }
};
