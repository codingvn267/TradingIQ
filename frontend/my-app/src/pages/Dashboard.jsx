import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import TradingViewWidget from '../components/TradingViewWidget';
import AnalysisPanel from '../components/AnalysisPanel';
import News from '../components/News';

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
      <div style={styles.dashboardContainer}>
        {/* === TOP SECTION: Chart & Account Side-by-Side === */}
        <div style={styles.topSection}>
          {/* Chart Panel */}
          <div style={styles.panelBox}>
            <h2>📈 Live Stock Chart</h2>
            <TradingViewWidget />
          </div>

          {/* Account Panel */}
          <div style={styles.panelBox}>
            <h2>💰 Account Summary</h2>

            <label htmlFor="stock">Select Stock:</label>
            <select
              id="stock"
              value={selectedStock}
              onChange={(e) => setSelectedStock(e.target.value)}
              style={{ margin: '10px 0', padding: '5px' }}
            >
              <option value="TSLA">Tesla (TSLA)</option>
              <option value="AAPL">Apple (AAPL)</option>
              <option value="NVDA">NVIDIA (NVDA)</option>
            </select>

            <p><strong>Balance:</strong> ${balance.toFixed(2)}</p>
            <p><strong>Shares Owned:</strong> {shares}</p>
            <p><strong>Current Price per Share:</strong> ${currentPrice}</p>

            <form onSubmit={handleBuy} style={{ marginTop: '10px' }}>
              <input name="amount" type="number" min="1" placeholder="Buy shares" required />
              <button type="submit" style={styles.btn}>Buy</button>
            </form>

            <form onSubmit={handleSell} style={{ marginTop: '10px' }}>
              <input name="amount" type="number" min="1" placeholder="Sell shares" required />
              <button type="submit" style={styles.btn}>Sell</button>
            </form>

            {message && (
              <p style={{ marginTop: '10px', fontWeight: 'bold', color: message.startsWith('✅') ? 'lightgreen' : 'red' }}>
                {message}
              </p>
            )}
          </div>
        </div>

        {/* === BOTTOM SECTION: Analysis + News === */}
        <div style={styles.bottomSection}>
          <div style={styles.panelBox}>
            <h2>📊 Technical Analysis</h2>
            <AnalysisPanel />
          </div>

          <div style={styles.panelBox}>
            <h2>📰 Market Headlines</h2>
            <News />
          </div>
        </div>
      </div>
    </>
  );
};

const styles = {
  dashboardContainer: {
    padding: '20px',
    maxWidth: '1200px',
    margin: '0 auto',
  },
  topSection: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'flex-start',
    flexWrap: 'wrap',
    gap: '20px',
    marginBottom: '30px',
  },
  bottomSection: {
    display: 'flex',
    flexDirection: 'column',
    gap: '20px',
  },
  panelBox: {
    flex: '1 1 48%',
    minWidth: '300px',
    backgroundColor: '#1a1a1a',
    padding: '20px',
    borderRadius: '8px',
    border: '1px solid #444',
    color: '#fff',
  },
  btn: {
    padding: '6px 14px',
    marginLeft: '10px',
    backgroundColor: '#007bff',
    color: '#fff',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
  }
};

export default Dashboard;
