// import { useState, useEffect } from "react";
// import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
// import StockChart from "./components/stockChart.jsx";
// import LoginPage from "./components/LoginPage.jsx";
import "./index.css";

function App() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-900 text-white">
      <h1 className="text-4xl font-bold">Tailwind is working! 🚀</h1>
    </div>
  );}
//   const [symbol, setSymbol] = useState("TSLA"); // Only TSLA is available for now
//   const [user, setUser] = useState(null);

//   // Check if user is logged in
//   useEffect(() => {
//     const storedUser = localStorage.getItem("user");
//     if (storedUser) {
//       setUser(JSON.parse(storedUser));
//     }
//   }, []);

//   return (
//     <Router>
//       <div className="app-container">
//         <Routes>
//           {/* Login Route */}
//           <Route path="/login" element={user ? <Navigate to="/" /> : <LoginPage />} />

//           {/* Stock Chart Route (Only if Logged In) */}
//           <Route
//             path="/"
//             element={
//               user ? (
//                 <>
//                   <label>Select Stock: </label>
//                   <select value={symbol} onChange={(e) => setSymbol(e.target.value)}>
//                     <option value="TSLA">Tesla (TSLA)</option>
//                     <option value="AAPL" disabled>Apple (AAPL) - Coming Soon</option>
//                     <option value="NVDA" disabled>Nvidia (NVDA) - Coming Soon</option>
//                   </select>
//                   <StockChart symbol={symbol} />
//                 </>
//               ) : (
//                 <Navigate to="/login" />
//               )
//             }
//           />
//         </Routes>
//       </div>
//     </Router>
//   );
// }

export default App;

