import React, { useEffect, useRef, useState } from "react";

const TradingViewWidget = () => {
  const containerRef = useRef();
  const [symbol, setSymbol] = useState("NASDAQ:TSLA");

  useEffect(() => {
    containerRef.current.innerHTML = "";

    const script = document.createElement("script");
    script.src = "https://s3.tradingview.com/external-embedding/embed-widget-advanced-chart.js";
    script.type = "text/javascript";
    script.async = true;
    script.innerHTML = `
      {
        "autosize": true,
        "symbol": "${symbol}",
        "interval": "D",
        "timezone": "Etc/UTC",
        "theme": "dark",
        "style": "1",
        "locale": "en",
        "withdateranges": true,
        "allow_symbol_change": false,
        "studies": ["STD;Smoothed%1Moving%1Average"],
        "support_host": "https://www.tradingview.com"
      }`;

    containerRef.current.appendChild(script);
  }, [symbol]);

  return (
    <div>
      <label style={{ color: 'white' }}>Select Stock:</label>
      <select onChange={(e) => setSymbol(e.target.value)} value={symbol}>
        <option value="NASDAQ:TSLA">Tesla</option>
        <option value="NASDAQ:NVDA">NVIDIA</option>
        <option value="NASDAQ:AAPL">Apple</option>
      </select>
      <div ref={containerRef} style={{ height: "600px", width: "100%" }} />
    </div>
  );
};

export default TradingViewWidget;

