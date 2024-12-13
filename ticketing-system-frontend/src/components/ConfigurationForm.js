import React from 'react';
import '../styles/ConfigurationForm.css';  // Ensure the correct path to the styles folder

const ConfigurationForm = ({ onStart, onStop, onReset, isRunning, formData, setFormData }) => {

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Check if any required field is empty or invalid
    for (let key in formData) {
      if (formData[key] === '' || formData[key] <= 0) {
        alert(`Please enter valid values for ${key.replace(/([A-Z])/g, ' $1')}`);
        return;
      }
    }

    // Parse the values to numbers before passing them to onStart
    const parsedData = {
      initialTickets: parseInt(formData.initialTickets, 10),
      maxTicketCapacity: parseInt(formData.maxTicketCapacity, 10),
      ticketReleaseRate: parseInt(formData.ticketReleaseRate, 10),
      customerRetrievalRate: parseInt(formData.customerRetrievalRate, 10)
    };

    onStart(parsedData);  // Pass parsed data to parent (App.js)
  };

  return (
    <div className="configuration-form-container">
      <form onSubmit={handleSubmit} className="configuration-form">
        <label>
          Enter Total Amount of Tickets In The System:
          <input type="number" name="initialTickets" value={formData.initialTickets} onChange={handleChange} disabled={isRunning} />
        </label>
        <label>
          Enter Max Ticket Capacity:
          <input type="number" name="maxTicketCapacity" value={formData.maxTicketCapacity} onChange={handleChange} disabled={isRunning} />
        </label>
        <label>
          Enter Ticket Release Rate:
          <input type="number" name="ticketReleaseRate" value={formData.ticketReleaseRate} onChange={handleChange} disabled={isRunning} />
        </label>
        <label>
          Enter Customer Retrieval Rate:
          <input type="number" name="customerRetrievalRate" value={formData.customerRetrievalRate} onChange={handleChange} disabled={isRunning} />
        </label>
        <button type="submit" disabled={isRunning}>Start Ticketing</button>
        <button type="button" onClick={onStop} disabled={!isRunning}>Stop Ticketing</button>
        <button type="button" onClick={onReset} disabled={isRunning}>Reset Ticketing</button> {/* Reset via parent's onReset */}
      </form>
    </div>
  );
};

export default ConfigurationForm;
