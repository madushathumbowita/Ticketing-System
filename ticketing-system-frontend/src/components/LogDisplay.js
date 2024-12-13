import React from 'react';
import '../styles/LogDisplay.css';  


const LogDisplay = ({ log }) => {
  return (
    <div className="log-display">
      <h3>Transaction Log</h3>
      <pre>{log}</pre>
    </div>
  );
};

export default LogDisplay;