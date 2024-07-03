// src/App.js
import React, { Fragment } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import IndexPagina from './pages/IndexPagina';
import './App.css'; // Importa el archivo CSS

function App() {
  return(
    <Fragment>
      <Router>
        <Routes>
          <Route path='/' exact element={<IndexPagina/>}/>
        </Routes>
      </Router>
    </Fragment>
  );
}

export default App;
