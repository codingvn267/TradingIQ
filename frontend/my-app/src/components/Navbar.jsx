import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Navbar.css';

const Navbar = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Just remove the user token (same as you did in LoginPage.jsx)
    localStorage.removeItem("user");
    navigate('/login');
  };

  return (
    <div className="navbar">
      <h2 onClick={() => navigate('/dashboard')} className="nav-title">
        Trading_IQ
      </h2>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default Navbar;

