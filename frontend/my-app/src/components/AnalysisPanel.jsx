import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AnalysisPanel = () => {
  const [symbol, setSymbol] = useState('TSLA');
  const [analysis, setAnalysis] = useState('');
  const [loading, setLoading] = useState(false);

  const fetchAnalysis = async (selectedSymbol) => {
    setLoading(true);
    try {
      const response = await axios.get(`http://localhost:8080/api/stocks/analyze?symbol=${selectedSymbol}`);
      setAnalysis(response.data);
    } catch (error) {
      setAnalysis("⚠️ Failed to fetch analysis.");
      console.error(error);
    }
    setLoading(false);
  };

  useEffect(() => {
    fetchAnalysis(symbol);
  }, [symbol]);

  return (
    <div>
      <h3>📊 Stock Analysis</h3>
      <select value={symbol} onChange={(e) => setSymbol(e.target.value)}>
        <option value="TSLA">Tesla</option>
        <option value="NVDA">NVIDIA</option>
        <option value="AAPL">Apple</option>
      </select>
      {loading ? <p>Loading...</p> : <pre>{analysis}</pre>}
    </div>
  );
};

export default AnalysisPanel;
