import React from 'react';
import './TickerBar.css';

const TickerBar = () => {
  return (
    <div className="ticker-bar">
      <marquee behavior="scroll" direction="left">
        📈 NASDAQ: 15,100.21 ▲ 0.65% &nbsp;&nbsp;|&nbsp;&nbsp;
        S&P 500: 4,250.88 ▲ 0.42% &nbsp;&nbsp;|&nbsp;&nbsp;
        DOW JONES: 33,450.77 ▼ 0.12%
      </marquee>
    </div>
  );
};

export default TickerBar;
