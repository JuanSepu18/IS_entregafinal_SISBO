import React, { useState } from 'react';
import CreateEventForm from '../pages/CreateEventForm';
import 'bootstrap/dist/css/bootstrap.min.css';
import './ClubDashboard.css'; // Archivo CSS personalizado para estilos adicionales

function ClubDashboard({ events, onCreateEvent, onLinkFollower, onNotifyFollowers }) {
  const [isCreateEventOpen, setCreateEventOpen] = useState(false);
  const [isLinkFollowerOpen, setLinkFollowerOpen] = useState(false);
  const [isNotifyFollowersOpen, setNotifyFollowersOpen] = useState(false);
  const [isConsultarBoletasOpen, setConsultarBoletasOpen] = useState(false);
  const [isCrearServicioOpen, setCrearServicioOpen] = useState(false);
  const [servicioFormData, setServicioFormData] = useState({ nombre: '', precio: '', descripcion: '' });

  const handleToggleCreateEvent = () => {
    setCreateEventOpen(!isCreateEventOpen);
    setLinkFollowerOpen(false);
    setNotifyFollowersOpen(false);
    setConsultarBoletasOpen(false);
    setCrearServicioOpen(false);
  };

  const handleToggleLinkFollower = (e) => {
    e.preventDefault();
    setLinkFollowerOpen(!isLinkFollowerOpen);
    setCreateEventOpen(false);
    setNotifyFollowersOpen(false);
    setConsultarBoletasOpen(false);
    setCrearServicioOpen(false);
  };

  const handleToggleNotifyFollowers = (e) => {
    e.preventDefault();
    setNotifyFollowersOpen(!isNotifyFollowersOpen);
    setCreateEventOpen(false);
    setLinkFollowerOpen(false);
    setConsultarBoletasOpen(false);
    setCrearServicioOpen(false);
  };

  const handleConsultarBoletas = (event) => {
    console.log(`Consultar boletas vendidas para el evento ${event.name}`);
    setConsultarBoletasOpen(prevState => !prevState);
    setCrearServicioOpen(false);
  };

  const handleCrearServicio = (event) => {
    console.log(`Crear servicio adicional para el evento ${event.name}`);
    setCrearServicioOpen(prevState => !prevState);
    setConsultarBoletasOpen(false);
  };

  const handleServicioInputChange = (e) => {
    const { name, value } = e.target;
    setServicioFormData({ ...servicioFormData, [name]: value });
  };

  const handleCrearServicioSubmit = (event) => {
    event.preventDefault();
    console.log('Datos del formulario de servicio adicional:', servicioFormData);
    setCrearServicioOpen(false);
  };

  const handleCreateEventSubmit = (formData) => {
    onCreateEvent(formData);
    setCreateEventOpen(false);
    setConsultarBoletasOpen(false);
    setCrearServicioOpen(false);
  };

  return (
    <div className="container mt-4">
      <h1>Club Dashboard</h1>

      <div>
        <button className="btn btn-primary mr-2 custom-btn" onClick={handleToggleCreateEvent}>Crear Evento Deportivo</button>
        {isCreateEventOpen && (
          <div className="form mt-3">
            <h2>Crear Evento Deportivo</h2>
            <CreateEventForm onCreateEvent={(formData) => {
              handleCreateEventSubmit(formData);
            }} />
          </div>
        )}
      </div>

      <div>
        <button className="btn btn-primary mr-2 custom-btn" onClick={handleToggleLinkFollower}>Vincular Seguidor</button>
        {isLinkFollowerOpen && (
          <div className="form mt-3">
            <h2>Vincular Seguidor</h2>
            <form onSubmit={handleToggleLinkFollower}>
              <input type="email" className="form-control mb-2 custom-input" placeholder="Correo electrónico" required />
              <button type="submit" className="btn btn-success custom-btn">Vincular Seguidor</button>
            </form>
          </div>
        )}
      </div>

      <div>
        <button className="btn btn-primary mr-2 custom-btn" onClick={handleToggleNotifyFollowers}>Notificar Seguidores</button>
        {isNotifyFollowersOpen && (
          <div className="form mt-3">
            <h2>Notificar Seguidores</h2>
            <form onSubmit={handleToggleNotifyFollowers}>
              <textarea className="form-control mb-2 custom-textarea" placeholder="Mensaje a enviar" required></textarea>
              <button type="submit" className="btn btn-success custom-btn">Enviar Mensaje</button>
            </form>
          </div>
        )}
      </div>

      <div className="mt-4">
        <h2>Eventos Creados</h2>
        <ul className="list-group">
          {events.map((event) => (
            <li key={event.id} className="list-group-item">
              <strong>Nombre:</strong> {event.name} <br />
              <strong>Fecha:</strong> {event.date} <br />
              <strong>Ubicación:</strong> {event.location} <br />
              <button className="btn btn-info mr-2 custom-btn" onClick={() => handleConsultarBoletas(event)}>Consultar Boletas Vendidas</button>
              <button className="btn btn-warning custom-btn" onClick={() => handleCrearServicio(event)}>Crear Servicio Adicional</button>
              {isCrearServicioOpen && (
                <div className="form mt-3">
                  <h3>Crear Servicio Adicional</h3>
                  <form onSubmit={handleCrearServicioSubmit}>
                    <input type="text" className="form-control mb-2 custom-input" name="nombre" placeholder="Nombre del servicio" value={servicioFormData.nombre} onChange={handleServicioInputChange} required />
                    <input type="number" className="form-control mb-2 custom-input" name="precio" placeholder="Precio" value={servicioFormData.precio} onChange={handleServicioInputChange} required />
                    <textarea className="form-control mb-2 custom-textarea" name="descripcion" placeholder="Descripción" value={servicioFormData.descripcion} onChange={handleServicioInputChange}></textarea>
                    <button type="submit" className="btn btn-success custom-btn">Crear Servicio</button>
                  </form>
                </div>
              )}
              {isConsultarBoletasOpen && event.localities && (
                <div className="mt-3">
                  <h3>Localidades y Boletas Vendidas:</h3>
                  <ul className="list-group">
                    {event.localities.map(localidad => (
                      <li key={localidad.id} className="list-group-item">
                        <strong>Nombre:</strong> {localidad.name} <br />
                        <strong>Capacidad:</strong> {localidad.capacity} <br />
                        <strong>Boletas Vendidas:</strong> {localidad.boletasVendidas} <br />
                      </li>
                    ))}
                  </ul>
                </div>
              )}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default ClubDashboard;
