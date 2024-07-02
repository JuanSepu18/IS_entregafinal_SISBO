// src/App.js
import React, { useState } from 'react';
import LoginForm from './components/LoginForm';
import CreateEventForm from './components/CreateEventForm';
import './App.css';

function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  const [userType, setUserType] = useState('');
  const [events, setEvents] = useState([]);
  const [showCreateEventForm, setShowCreateEventForm] = useState(false); // Estado para controlar la visibilidad del formulario

  const handleLogin = (username, password, type) => {
    // Simulación de autenticación
    setLoggedIn(true);
    setUserType(type);
  };

  const handleCreateEvent = (newEvent) => {
    // Agregar el nuevo evento al estado de eventos
    setEvents([...events, newEvent]);
    // Puedes agregar aquí la lógica para guardar el evento en tu backend si lo tienes
    console.log('Nuevo evento creado:', newEvent);
    // Ocultar el formulario después de crear el evento
    setShowCreateEventForm(false);
  };

  const showCreateEventFormHandler = () => {
    setShowCreateEventForm(true);
  };

  return (
    <div className="App">
      {!loggedIn ? (
        <LoginForm handleLogin={handleLogin} />
      ) : (
        <div>
          <h1>Bienvenido {userType === 'club' ? 'Club' : 'Seguidor'}</h1>
          {userType === 'club' && (
            <div>
              {!showCreateEventForm ? (
                <button onClick={showCreateEventFormHandler}>Crear Evento Deportivo</button>
              ) : (
                <CreateEventForm onCreateEvent={handleCreateEvent} />
              )}
              <h2>Eventos Creados</h2>
              <ul>
                {events.length > 0 ? (
                  events.map((event, index) => (
                    <li key={index}>{event.name} - {event.date}</li>
                  ))
                ) : (
                  <li>No hay eventos creados</li>
                )}
              </ul>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default App;




