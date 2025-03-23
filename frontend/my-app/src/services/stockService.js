import axios from 'axios';

const API_URL = 'http://localhost:8080/api/stocks';

const analyzeStock = async (symbol) => {
  const token = localStorage.getItem('token');
  const response = await axios.get(`${API_URL}/analyze?symbol=${symbol}`, {
    headers: { Authorization: `Bearer ${token}` }
  });
  return response.data;
};

export default { analyzeStock };

