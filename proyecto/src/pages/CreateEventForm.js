// src/components/CreateEventForm.js
import React, { useState } from 'react';

function CreateEventForm({ onCreateEvent }) {
  const [name, setName] = useState('');
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');
  const [location, setLocation] = useState('');
  const [localities, setLocalities] = useState([]);
  const [localityName, setLocalityName] = useState('');
  const [localityCapacity, setLocalityCapacity] = useState('');

  const handleAddLocality = () => {
    if (!localityName || !localityCapacity) {
      alert('Por favor, completa todos los campos de la localidad.');
      return;
    }
    setLocalities([...localities, { name: localityName, capacity: parseInt(localityCapacity) }]);
    setLocalityName('');
    setLocalityCapacity('');
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!name || !date || !time || !location || localities.length === 0) {
      alert('Por favor, completa todos los campos y añade al menos una localidad.');
      return;
    }
    onCreateEvent({ name, date, time, location, localities });
    setName('');
    setDate('');
    setTime('');
    setLocation('');
    setLocalities([]);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Nombre del Evento:</label>
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
      </div>
      <div>
        <label>Fecha del Evento:</label>
        <input type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
      </div>
      <div>
        <label>Hora del Evento:</label>
        <input type="time" value={time} onChange={(e) => setTime(e.target.value)} required />
      </div>
      <div>
        <label>Ubicación del Evento:</label>
        <input type="text" value={location} onChange={(e) => setLocation(e.target.value)} required />
      </div>
      <div>
        <label>Nombre de la Localidad:</label>
        <input type="text" value={localityName} onChange={(e) => setLocalityName(e.target.value)} />
      </div>
      <div>
        <label>Capacidad de la Localidad:</label>
        <input type="number" value={localityCapacity} onChange={(e) => setLocalityCapacity(e.target.value)} />
      </div>
      <button type="button" onClick={handleAddLocality}>Añadir Localidad</button>
      <ul>
        {localities.map((locality, index) => (
          <li key={index}>{locality.name} - {locality.capacity} personas</li>
        ))}
      </ul>
      <button type="submit">Crear Evento</button>
    </form>
  );
}

export default CreateEventForm;

