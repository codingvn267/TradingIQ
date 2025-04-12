import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AnalysisPanel = () => {
  const [symbol, setSymbol] = useState('TSLA');
  const [analysisMode, setAnalysisMode] = useState('backend');
  const [analysis, setAnalysis] = useState('');
  const [loading, setLoading] = useState(false);

  const fetchBackendAnalysis = async (selectedSymbol) => {
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

  const fetchAIAnalysis = async (selectedSymbol) => {
    if (loading) return; // prevent spam
    setLoading(true);
    try {
      const response = await axios.post(
        'http://localhost:8080/api/openai',
        { symbol: selectedSymbol }
      );
      const result = JSON.parse(response.data);
      setAnalysis(result.choices?.[0]?.message?.content || '⚠️ No response from AI.');
    } catch (error) {
      console.error('Error:', error);
      setAnalysis('⚠️ Failed to fetch AI analysis.');
    }
    setLoading(false);
  };

  useEffect(() => {
    if (analysisMode === 'backend') {
      fetchBackendAnalysis(symbol);
    } else {
      setAnalysis('');
    }
  }, [symbol, analysisMode]);

  return (
    <div className="panel-box">
      <h2>📊 Technical Analysis</h2>

      <div style={{ marginBottom: '1rem' }}>
        <label>
          Select Stock:&nbsp;
          <select value={symbol} onChange={(e) => setSymbol(e.target.value)}>
            <option value="TSLA">Tesla</option>
            <option value="NVDA">NVIDIA</option>
            <option value="AAPL">Apple</option>
          </select>
        </label>

        &nbsp;&nbsp;

        <label>
          Analysis Type:&nbsp;
          <select value={analysisMode} onChange={(e) => setAnalysisMode(e.target.value)}>
            <option value="backend">🚀 Backend Analysis</option>
            <option value="ai">🤖 AI Assistant</option>
          </select>
        </label>
      </div>

      {analysisMode === 'ai' && (
        <button className="btn-primary" onClick={() => fetchAIAnalysis(symbol)}>
          💬 Ask ChatGPT
        </button>
      )}

      <div style={{ marginTop: '1rem' }}>
        {loading ? <p>⏳ Loading...</p> : <pre>{analysis}</pre>}
      </div>
    </div>
  );
};

export default AnalysisPanel;


