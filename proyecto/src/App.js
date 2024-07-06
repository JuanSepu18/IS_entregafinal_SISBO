// src/App.js
import React, { Fragment } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css'; // Importa el archivo CSS
import SeguidorDashboard from './pages/SeguidorDashboard';
import ClubDashboard from './pages/ClubDashboard';
import LoginForm from './pages/LoginForm';
import RegistroForm from './pages/RegistroForm';

function App() {
  return(
    <Fragment>
      <Router>
        <Routes>
          <Route path='/' exact element={<LoginForm/>}/>
          <Route path='/seguidor' exact element={<SeguidorDashboard/>}/>
          <Route path='/club' exact element={<ClubDashboard/>}/>
          <Route path='/registro' exact element={<RegistroForm/>}/>
        </Routes>
      </Router>
    </Fragment>
  );
}

export default App;
