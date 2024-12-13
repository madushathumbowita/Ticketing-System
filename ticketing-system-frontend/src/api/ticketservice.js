import axios from 'axios';

const BASE_URL = 'http://localhost:8080/ticketing';

// Start the Ticketing System
export const startTicketingSystem = async (config) => {
  try {
    const response = await axios.post(`${BASE_URL}/start`, {}, {
      params: {
        initialTickets: config.initialTickets,
        maxTicketCapacity: config.maxTicketCapacity,
        ticketReleaseRate: config.ticketReleaseRate,
        customerRetrievalRate: config.customerRetrievalRate
      },
    });
    console.log("Response from backend:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error in starting ticketing system", error);
    throw error;
  }
};

// Stop the Ticketing System
export const stopTicketingSystem = async () => {
  try {
    const response = await axios.post(`${BASE_URL}/stop`);
    console.log("Response from backend:", response.data);
    return response.data; // Return the transaction log from the backend
  } catch (error) {
    console.error("Error in stopping ticketing system", error);
    throw error;
  }
};

// Reset Ticketing System
export const resetTicketingSystem = async () => {
  try {
    const response = await axios.post(`${BASE_URL}/reset`);
    console.log("Response from backend:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error in resetting ticketing system", error);
    throw error;
  }
};

// Fetch the Transaction Log
export const fetchTransactionLog = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/log`);
    console.log("Transaction log:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error in fetching transaction log", error);
    throw error;
  }
};
