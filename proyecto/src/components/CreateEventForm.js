// src/components/CreateEventForm.js
import React, { useState } from 'react';

const CreateEventForm = ({ onCreateEvent }) => {
  const [eventName, setEventName] = useState('');
  const [eventDate, setEventDate] = useState('');
  const [eventLocation, setEventLocation] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Validación de campos (puedes implementarla según tus necesidades)

    // Crear objeto con los datos del evento
    const newEvent = {
      name: eventName,
      date: eventDate,
      location: eventLocation,
      // Otros campos según tus necesidades
    };

    // Llamar a la función onCreateEvent del padre con el nuevo evento
    onCreateEvent(newEvent);

    // Limpiar los campos después de crear el evento (opcional)
    setEventName('');
    setEventDate('');
    setEventLocation('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Crear Evento Deportivo</h2>
      <label>
        Nombre del Evento:
        <input
          type="text"
          value={eventName}
          onChange={(e) => setEventName(e.target.value)}
          required
        />
      </label>
      <br />
      <label>
        Fecha del Evento:
        <input
          type="date"
          value={eventDate}
          onChange={(e) => setEventDate(e.target.value)}
          required
        />
      </label>
      <br />
      <label>
        Ubicación del Evento:
        <input
          type="text"
          value={eventLocation}
          onChange={(e) => setEventLocation(e.target.value)}
          required
        />
      </label>
      <br />
      <button type="submit">Guardar Evento</button>
    </form>
  );
};

export default CreateEventForm;
