import React from 'react';
import '../styles/TicketStatus.css';  // Adjust to point to the styles folder


const TicketStatus = ({ status }) => {
  return (
    <div className="ticket-status">
      <h3>Status:</h3>
      <p>{status}</p>
    </div>
  );
};

export default TicketStatus;
