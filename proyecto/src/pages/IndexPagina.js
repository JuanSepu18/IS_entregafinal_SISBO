// src/pages/IndexPagina.js
import React, { useState } from 'react';
import ClubDashboard from './ClubDashboard';
import SeguidorDashboard from './SeguidorDashboard';
import LoginForm from './LoginForm';

function IndexPagina() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userType, setUserType] = useState('');
  const [events, setEvents] = useState([]);
  const [followers, setFollowers] = useState([]);

  const handleLogin = (type) => {
    setIsLoggedIn(true);
    setUserType(type);
  };

  const handleCreateEvent = (event) => {
    setEvents([...events, event]);
  };

  const handleLinkFollower = (email) => {
    setFollowers([...followers, email]);
    alert('Seguidor vinculado correctamente.');
  };

  const handleNotifyFollowers = (message) => {
    if (followers.length === 0) {
      alert('No se puede enviar el mensaje, no hay usuarios vinculados.');
    } else {
      console.log(`Mensaje enviado a los seguidores: ${message}`);
      alert('Los usuarios han sido notificados correctamente.');
    }
  };

  if (!isLoggedIn) {
    return <LoginForm onLogin={handleLogin} />;
  }

  return (
    <div className="App">
      {userType === 'club' && (
        <ClubDashboard
          events={events}
          onCreateEvent={handleCreateEvent}
          onLinkFollower={handleLinkFollower}
          onNotifyFollowers={handleNotifyFollowers}
        />
      )}
      {userType === 'seguidor' && (
        <SeguidorDashboard
          events={events}
        />
      )}
    </div>
  );
}

export default IndexPagina;
