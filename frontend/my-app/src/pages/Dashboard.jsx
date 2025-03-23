import React from 'react';
import TradingViewWidget from '../components/TradingViewWidget';
import AnalysisPanel from '../components/AnalysisPanel';
import Navbar from '../components/Navbar';

const Dashboard = () => {
  return (
    <>
      <Navbar />
      <div className="dashboard">
        <div className="chart-section">
          <TradingViewWidget />
        </div>
        <div className="analysis-section">
          <AnalysisPanel />
        </div>
      </div>
    </>
  );
};

export default Dashboard;


