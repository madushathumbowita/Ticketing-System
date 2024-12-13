import React, { useState, useEffect } from 'react';
import './App.css';
import ConfigurationForm from './components/ConfigurationForm';
import LogDisplay from './components/LogDisplay';
import TicketStatus from './components/TicketStatus';
import { startTicketingSystem, stopTicketingSystem, resetTicketingSystem, fetchTransactionLog } from './api/ticketservice';

const App = () => {
  const [log, setLog] = useState('');
  const [ticketStatus, setTicketStatus] = useState('');
  const [isRunning, setIsRunning] = useState(false);

  const [formData, setFormData] = useState({
    initialTickets: '',
    maxTicketCapacity: '',
    ticketReleaseRate: '',
    customerRetrievalRate: ''
  });

  // Polling for transaction logs
  useEffect(() => {
    let pollingInterval;
    if (isRunning) {
      pollingInterval = setInterval(() => {
        fetchTransactionLog()
          .then((response) => {
            setLog(response);
          })
          .catch((error) => console.error('Error fetching logs:', error));
      }, 1000); // Poll every 2 seconds
    }
    return () => clearInterval(pollingInterval); // Cleanup interval on stop/reset
  }, [isRunning]);

  const handleStart = (config) => {
    startTicketingSystem(config)
      .then((response) => {
        setLog(response.log);
        setTicketStatus('Ticketing system started and running...');
        setIsRunning(true);
      })
      .catch((error) => {
        console.error(error);
        setTicketStatus('Error starting the system');
      });
  };

  const handleStop = () => {
    stopTicketingSystem()
      .then((response) => {
        setLog(response);
        setTicketStatus('Ticketing system stopped.');
        setIsRunning(false);
      })
      .catch((error) => {
        console.error(error);
        setTicketStatus('Error stopping the system');
      });
  };

  const handleReset = () => {
    resetTicketingSystem()
      .then(() => {
        setLog('');
        setTicketStatus('Ticketing system reset.');
        setIsRunning(false);
        setFormData({
          initialTickets: '',
          maxTicketCapacity: '',
          ticketReleaseRate: '',
          customerRetrievalRate: ''
        });
      })
      .catch((error) => {
        console.error(error);
        setTicketStatus('Error resetting the system');
      });
  };

  return (
    <div className="container">
      <div className="left-section">
        <h1>Ticketing System</h1>
        <ConfigurationForm
          onStart={handleStart}
          onStop={handleStop}
          onReset={handleReset}
          isRunning={isRunning}
          formData={formData}
          setFormData={setFormData}
        />
        <TicketStatus status={ticketStatus} />
      </div>
      <div className="right-section">
        <LogDisplay log={log} />
      </div>
    </div>
  );
};

export default App;
