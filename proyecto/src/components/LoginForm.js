// src/components/LoginForm.js
import React, { useState } from 'react';

const LoginForm = ({ handleLogin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [userType, setUserType] = useState('club'); // Valor por defecto para el tipo de usuario

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aquí podrías realizar validaciones adicionales si lo deseas

    // Llamar a la función handleLogin del padre con los datos del formulario
    handleLogin(username, password, userType);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Login</h2>
      <label>
        Usuario:
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
      </label>
      <br />
      <label>
        Contraseña:
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </label>
      <br />
      <label>
        Tipo de Usuario:
        <select value={userType} onChange={(e) => setUserType(e.target.value)}>
          <option value="club">Club</option>
          <option value="seguidor">Seguidor</option>
        </select>
      </label>
      <br />
      <button type="submit">Iniciar Sesión</button>
    </form>
  );
};

export default LoginForm;

