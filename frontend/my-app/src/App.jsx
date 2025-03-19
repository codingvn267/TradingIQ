import React, { useState } from "react";
import StockChart from "./components/stockChart.jsx";

function App() {
  const [symbol, setSymbol] = useState("TSLA"); // Only TSLA is available for now

  return (
    <div className="app-container">
      <label>Select Stock: </label>
      <select value={symbol} onChange={(e) => setSymbol(e.target.value)}>
        <option value="TSLA">Tesla (TSLA)</option>
        <option value="AAPL" disabled>Apple (AAPL) - Coming Soon</option>
        <option value="NVDA" disabled>Nvidia (NVDA) - Coming Soon</option>
      </select>

      <StockChart symbol={symbol} />
    </div>
  );
}

export default App;

