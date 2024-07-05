import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'; // Importa Bootstrap CSS
import './SeguidorDashboard.css'; // Importa el archivo CSS personalizado

function SeguidorDashboard({ events }) {
  const [showTickets, setShowTickets] = useState(false);
  const [showServices, setShowServices] = useState(false);
  const [tickets, setTickets] = useState([
    { id: 1, eventName: 'Partido de Fútbol', locality: 'General', quantity: 2 },
  ]);
  const [documento, setDocumento] = useState('');
  const [transferMessage, setTransferMessage] = useState('');
  const [selectedEvent, setSelectedEvent] = useState(null);
  const [selectedLocality, setSelectedLocality] = useState('');
  const [quantity, setQuantity] = useState(1);

  const handleToggleTickets = () => {
    setShowTickets(!showTickets);
    setShowServices(false);
  };

  const handleToggleServices = () => {
    setShowServices(!showServices);
    setShowTickets(false);
  };

  const handleTransferTickets = () => {
    if (documento.trim() === '') {
      alert('Por favor ingresa un número de documento válido.');
      return;
    }

    // Simulando la transferencia de boletas
    const ticketToTransfer = tickets[0]; // Transferimos la primera boleta como ejemplo
    setTickets([]);
    setTransferMessage('Boleta transferida al número de documento ${documento}');
    setDocumento(''); // Limpiamos el campo de documento después de la transferencia
  };

  const handleSellTicketSecondaryMarket = () => {
    if (window.confirm('¿Realmente desea vender esta boleta en el mercado secundario?')) {
      // Aquí puedes agregar la lógica para eliminar la boleta de la lista
      setTickets([]);
      setTransferMessage('Boleta vendida en mercado secundario correctamente.');
      setDocumento(''); // Limpiamos el campo de documento después de vender en mercado secundario
    }
  };

  const handleDocumentoChange = (e) => {
    setDocumento(e.target.value);
  };

  const handleComprarBoleta = (eventId) => {
    const event = eventosEjemplo.find(e => e.id === eventId);
    setSelectedEvent(event);
  };

  const handleLocalityChange = (e) => {
    setSelectedLocality(e.target.value);
  };

  const handleQuantityChange = (e) => {
    setQuantity(e.target.value);
  };

  const handleConfirmPurchase = () => {
    if (!selectedLocality) {
      alert('Por favor selecciona una localidad.');
      return;
    }

    const newTicket = {
      id: tickets.length + 1,
      eventName: selectedEvent.name,
      locality: selectedLocality,
      quantity: Number(quantity),
    };

    setTickets([...tickets, newTicket]);
    setSelectedEvent(null); // Resetea la selección de evento después de la compra
    setSelectedLocality('');
    setQuantity(1);
  };

  // Eventos de ejemplo con localidades
  const eventosEjemplo = [
    {
      id: 1,
      name: 'Concierto de Rock',
      date: '2024-08-15',
      location: 'Estadio Nacional',
      localidades: [
        { id: 1, name: 'VIP', capacity: 100, boletasVendidas: 50 },
        { id: 2, name: 'General', capacity: 500, boletasVendidas: 300 },
      ],
    },
    {
      id: 2,
      name: 'Partido de Baloncesto',
      date: '2024-08-20',
      location: 'Polideportivo Municipal',
      localidades: [
        { id: 3, name: 'Tribuna', capacity: 200, boletasVendidas: 150 },
        { id: 4, name: 'Platea', capacity: 300, boletasVendidas: 200 },
      ],
    },
    {
      id: 3,
      name: 'Carrera de Autos',
      date: '2024-09-05',
      location: 'Circuito de Velocidad',
      localidades: [
        { id: 5, name: 'Paddock', capacity: 50, boletasVendidas: 20 },
        { id: 6, name: 'Gradas', capacity: 500, boletasVendidas: 300 },
      ],
    },
  ];

  return (
    <div className="container mt-4">
      <h1 className="mb-4">Seguidor Dashboard</h1>

      {/* Botones para ver boletas compradas y servicios adicionales */}
      <div className="mb-4">
        <button className="btn btn-primary mr-2 btn-separate" onClick={handleToggleTickets}>Ver Boletas Compradas</button>
        <button className="btn btn-primary btn-separate" onClick={handleToggleServices}>Ver Servicios Adicionales</button>
      </div>

      {/* Mostrar eventos disponibles */}
      <div className="mb-4">
        <h2>Eventos Disponibles</h2>
        <div className="row">
          {eventosEjemplo.map((event) => (
            <div key={event.id} className="col-md-4 mb-4">
              <div className="card">
                <div className="card-body">
                  <strong>Nombre:</strong> {event.name} <br />
                  <strong>Fecha:</strong> {event.date} <br />
                  <strong>Ubicación:</strong> {event.location} <br />
                  <strong>Localidades:</strong>
                  <ul>
                    {event.localidades.map((localidad) => (
                      <li key={localidad.id}>
                        {localidad.name} - Capacidad: {localidad.capacity} - Boletas Vendidas: {localidad.boletasVendidas}
                      </li>
                    ))}
                  </ul>
                  <button className="btn btn-primary mt-2" onClick={() => handleComprarBoleta(event.id)}>Comprar Boleta</button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>

      {/* Sección de compra de boletas */}
      {selectedEvent && (
        <div className="mt-4">
          <h2>Comprar Boleta para {selectedEvent.name}</h2>
          <div className="form-group">
            <label htmlFor="localitySelect">Selecciona Localidad</label>
            <select id="localitySelect" className="form-control" value={selectedLocality} onChange={handleLocalityChange}>
              <option value="">Selecciona una localidad</option>
              {selectedEvent.localidades.map((localidad) => (
                <option key={localidad.id} value={localidad.name}>{localidad.name}</option>
              ))}
            </select>
          </div>
          <div className="form-group">
            <label htmlFor="quantity">Cantidad de Boletas</label>
            <input
              type="number"
              id="quantity"
              className="form-control"
              value={quantity}
              onChange={handleQuantityChange}
              min="1"
              max="10"
            />
          </div>
          <button className="btn btn-success mt-2" onClick={handleConfirmPurchase}>Confirmar Compra</button>
        </div>
      )}

      {/* Sección de boletas compradas */}
      {showTickets && (
        <div className="mt-4">
          <h2>Boletas Compradas</h2>
          {tickets.length > 0 ? (
            <ul className="list-group">
              {tickets.map((ticket) => (
                <li key={ticket.id} className="list-group-item">
                  <strong>Evento:</strong> {ticket.eventName} <br />
                  <strong>Localidad:</strong> {ticket.locality} <br />
                  <strong>Cantidad:</strong> {ticket.quantity} <br />
                  <button className="btn btn-danger mr-2" onClick={handleSellTicketSecondaryMarket}>Vender en mercado secundario</button>
                  <button className="btn btn-warning" onClick={handleTransferTickets}>Transferir Boleta</button>
                </li>
              ))}
            </ul>
          ) : (
            <p>No tienes boletas compradas.</p>
          )}
          {transferMessage && <p>{transferMessage}</p>}
          {tickets.length > 0 && (
            <input
              type="text"
              value={documento}
              onChange={handleDocumentoChange}
              className="form-control mt-2"
              placeholder="Número de Documento"
              required
            />
          )}
        </div>
      )}

      {/* Sección de servicios adicionales */}
      {showServices && (
        <div className="mt-4">
          <h2>Servicios Adicionales</h2>
          <p>Lista de servicios adicionales disponibles para visualizar.</p>
        </div>
      )}
    </div>
  );
}

export default SeguidorDashboard;