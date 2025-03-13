import React, { useState } from "react";
import StockChart from "./components/stockChart.jsx";

function App() {
  const [symbol, setSymbol] = useState("AAPL");

  return (
    <div className="app-container">
      {/* Dropdown for selecting stocks */}
      <label>Select Stock: </label>
      <select value={symbol} onChange={(e) => setSymbol(e.target.value)}>
        <option value="AAPL">Apple (AAPL)</option>
        <option value="NVDA">Nvidia (NVDA)</option>
        <option value="TSLA">Tesla (TSLA)</option>
      </select>

      {/* Display StockChart */}
      <StockChart symbol={symbol} />
    </div>
  );
}

export default App;
