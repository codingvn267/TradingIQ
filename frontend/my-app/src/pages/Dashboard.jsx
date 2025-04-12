import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import TradingViewWidget from '../components/TradingViewWidget';
import AnalysisPanel from '../components/AnalysisPanel';
import News from '../components/News';
import TickerBar from '../components/TickerBar';

const Dashboard = () => {
  const [balance, setBalance] = useState(10000);
  const [shares, setShares] = useState(0);
  const [message, setMessage] = useState('');
  const [selectedStock, setSelectedStock] = useState('TSLA');

  const priceMap = {
    TSLA: 221.86,
    AAPL: 170.12,
    NVDA: 881.86,
  };

  const currentPrice = priceMap[selectedStock];

  const handleBuy = (e) => {
    e.preventDefault();
    const amount = Number(e.target.elements.amount.value);
    const cost = amount * currentPrice;

    if (cost > balance) {
      setMessage('❌ Over Limit: Insufficient balance.');
    } else {
      setBalance(balance - cost);
      setShares(shares + amount);
      setMessage(`✅ Bought ${amount} shares of ${selectedStock} for $${cost.toFixed(2)}`);
    }

    e.target.reset();
  };

  const handleSell = (e) => {
    e.preventDefault();
    const amount = Number(e.target.elements.amount.value);

    if (amount > shares) {
      setMessage('❌ Over Limit: Not enough shares.');
    } else {
      const gain = amount * currentPrice;
      setBalance(balance + gain);
      setShares(shares - amount);
      setMessage(`✅ Sold ${amount} shares of ${selectedStock} for $${gain.toFixed(2)}`);
    }

    e.target.reset();
  };

  return (
    <>
      <Navbar />
      <div className="dashboard-container">
        {/* === TOP SECTION: Chart & Account Side-by-Side === */}
        <div className="top-section">
          {/* Chart Panel */}
          <div className="panel-box chart-panel">
            <h2>📈 Live Stock Chart</h2>
            <TradingViewWidget />
          </div>

          {/* Account Panel */}
          <div className="panel-box account-panel">
            <h2>💰 Account Summary</h2>

            <label htmlFor="stock">Select Stock:</label>
            <select
              id="stock"
              value={selectedStock}
              onChange={(e) => setSelectedStock(e.target.value)}
            >
              <option value="TSLA">Tesla (TSLA)</option>
              <option value="AAPL">Apple (AAPL)</option>
              <option value="NVDA">NVIDIA (NVDA)</option>
            </select>

            <p><strong>Balance:</strong> ${balance.toFixed(2)}</p>
            <p><strong>Shares Owned:</strong> {shares}</p>
            <p><strong>Current Price per Share:</strong> ${currentPrice}</p>

            <form onSubmit={handleBuy}>
              <input name="amount" type="number" min="1" placeholder="Buy shares" required />
              <button type="submit" className="btn-primary">Buy</button>
            </form>

            <form onSubmit={handleSell}>
              <input name="amount" type="number" min="1" placeholder="Sell shares" required />
              <button type="submit" className="btn-primary">Sell</button>
            </form>

            {message && (
              <p className={message.startsWith('✅') ? 'message-success' : 'message-error'}>
                {message}
              </p>
            )}
          </div>
        </div>

        {/* === BOTTOM SECTION: Analysis + News === */}
        <div className="bottom-section">
          <div className="panel-box">
            <h2>📊 Technical Analysis</h2>
            <AnalysisPanel />
          </div>

          <div className="panel-box">
            <h2>📰 Market Headlines</h2>
            <News />
          </div>
        </div>
      </div>
      <TickerBar />
    </>
  );
};

export default Dashboard;

