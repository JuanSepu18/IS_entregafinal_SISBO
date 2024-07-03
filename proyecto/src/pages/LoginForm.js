// src/components/LoginForm.js
import React, { useState } from 'react';

function LoginForm({ onLogin }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [userType, setUserType] = useState('club');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!username || !password) {
      alert('Por favor, complete todos los campos.');
      return;
    }
    onLogin(userType);
  };

  return (
    <form onSubmit={handleSubmit} className="form">
      <h2>Login</h2> {/* Título agregado */}
      <div>
        <label>Usuario:</label>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Contraseña:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Tipo de Usuario:</label>
        <select
          value={userType}
          onChange={(e) => setUserType(e.target.value)}
        >
          <option value="club">Club</option>
          <option value="seguidor">Seguidor</option>
        </select>
      </div>
      <button type="submit">Login</button>
    </form>
  );
}

export default LoginForm;
