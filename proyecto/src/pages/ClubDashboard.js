import React, { useState, useEffect } from 'react';
import CreateEventForm from '../components/CreateEventForm';
import '../assets/ClubDashboard.css'; // Archivo CSS personalizado para estilos adicionales
import Navbar from '../components/Navbar';
import APIInvoke from '../utils/APIInvoke';
import swal from 'sweetalert';


function ClubDashboard() {
  const [isCreateEventOpen, setCreateEventOpen] = useState(false);
  const [isNotifyFollowersOpen, setNotifyFollowersOpen] = useState(false);
  const [isConsultarBoletasOpen, setConsultarBoletasOpen] = useState(false);
  const [isCrearServicioOpen, setCrearServicioOpen] = useState(false);
  const [servicioFormData, setServicioFormData] = useState({
    nombre: '',
    precio: '',
    descripcion: '',
    unidades_totales: ''
  });
  const [events, setEvents] = useState([]);
  const [boletasFiltradas, setBoletasFiltradas] = useState([]);

  const onCreateEvent = (event) => {
    setEvents([...events, event]);
  };

  const handleNotifyFollowers = (e, message) => {
    e.preventDefault();

    alert('No se puede enviar el mensaje, no hay usuarios vinculados.');

  };

  const handleToggleCreateEvent = () => {
    setCreateEventOpen(prevState => !prevState);
    setNotifyFollowersOpen(false);
    setConsultarBoletasOpen(false);
    setCrearServicioOpen(false);
  };

  const handleToggleNotifyFollowers = (e) => {
    e.preventDefault();
    setNotifyFollowersOpen(prevState => !prevState);
    setCreateEventOpen(false);
    setConsultarBoletasOpen(false);
    setCrearServicioOpen(false);
  };

  const handleConsultarBoletas = async (event) => {

    const response = await APIInvoke.invokeGET(`api/Boleta/list/`);
    const boletaParaEvento = response.filter(
      (boletaEvento) => boletaEvento.localidad.eventoDeportivo.id_evento === event)

    setBoletasFiltradas(boletaParaEvento);
    setSelectedEventId(event);
    setConsultarBoletasOpen(prevState => !prevState);
    setCrearServicioOpen(false);
  };

  const handleCrearServicio = () => {
    setCrearServicioOpen(prevState => !prevState);
    setConsultarBoletasOpen(false);
  };

  const handleServicioInputChange = (e) => {
    const { name, value } = e.target;
    setServicioFormData({ ...servicioFormData, [name]: value });
  };

  const handleCrearServicioSubmit = (e) => {
    e.preventDefault();

    const data = {
      "nombre": servicioFormData.nombre,
      "descripcion": servicioFormData.descripcion,
      "precio": servicioFormData.precio,
      "unidades_totales": servicioFormData.unidades_totales,
      "unidades_vendidas": 0,
      "club": {
        "idClub": sessionStorage.getItem("id_user")
      }
    }

    console.log(data)

    APIInvoke.invokePOST("api/ServicioClub/", data)

    swal({
      title: "Servicio creado",
      text: "El servicio adicional ha sido creado satisfactoriamente",
      icon: "success",
      buttons: {
        confirm: {
          text: "Ok",
          value: true,
          visible: true,
          className: "btn btn-success",
          closeModal: true,
        },
      },
    })
      .then((value) => {
        if (value) {
          window.location.reload();
        }
      });

  };

  const handleCreateEventSubmit = (formData) => {
    onCreateEvent(formData);
    setCreateEventOpen(true);
    setConsultarBoletasOpen(false);
    setCrearServicioOpen(false);
  };

  const [eventosDisponibles, setEventosDisponibles] = useState([]);

  useEffect(() => {
    const fetchDatosUsuario = async () => {
      try {
        const response = await APIInvoke.invokeGET("api/Evento/list/");
        setEventosDisponibles(response);
      } catch (error) {
        console.error("Error al cargar los eventos", error);
        alert("Ha ocurrido un error, intente más tarde");
      }
    };

    fetchDatosUsuario();
  }, []);

  const [selectedEventId, setSelectedEventId] = useState(null);



  return (
    <div style={{
      position: "absolute",
      top: "50px",
      left: "0%",
      width: "100%"

    }}>
      <Navbar />
      <div></div>
      <div className="container mt-4">
        <h1>{sessionStorage.getItem("name_user")} Dashboard</h1>

        <div>
          <div className="d-flex flex-wrap gap-2 justify-content-center align-items-center">
            <button className="btn btn-primary custom-btn" onClick={handleToggleCreateEvent}
              style={{ "marginRight": "5px" }}>Crear Evento Deportivo</button>

            <button
              className="btn btn-primary custom-btn" onClick={() => handleCrearServicio()}
              style={{ "marginRight": "5px" }}>Crear Servicio Adicional</button>

            <button className="btn custom-btn" onClick={handleToggleNotifyFollowers}
              style={{ "marginRight": "5px", backgroundColor: "grey", color: "white" }}>Notificar Seguidores</button>

          </div>

          {isCreateEventOpen && (
            <>
              <h2>Crear Evento Deportivo</h2>
              <div className="form mt-3 d-flex justify-content-center align-items-center">
                <CreateEventForm onCreateEvent={handleCreateEventSubmit} />
              </div>
            </>
          )}
        </div>

        <div>
          {isNotifyFollowersOpen && (
            <>
              <h2>Notificar Seguidores</h2>
              <div className="form mt-3 d-flex justify-content-center align-items-center">
                <form onSubmit={(e) => handleNotifyFollowers(e, 'Mensaje predeterminado')}>
                  <textarea className="form-control mb-2 custom-textarea" placeholder="Mensaje a enviar" required></textarea>
                  <button type="submit" className="btn btn-success custom-btn">Enviar Mensaje</button>
                </form>
              </div>
            </>
          )}
        </div>
        {isCrearServicioOpen && (
          <>
            <h3>Crear Servicio Adicional</h3>
            <div className="form mt-3 d-flex justify-content-center align-items-center">

              <form onSubmit={(e) => handleCrearServicioSubmit(e)}>
                <input
                  type="text"
                  className="form-control mb-2 custom-input"
                  name="nombre"
                  placeholder="Nombre del servicio"
                  value={servicioFormData.nombre}
                  onChange={handleServicioInputChange} required />

                <input
                  type="number"
                  className="form-control mb-2 custom-input"
                  name="precio"
                  placeholder="Precio"
                  value={servicioFormData.precio}
                  onChange={handleServicioInputChange} required />
                <input
                  type="number"
                  className="form-control mb-2 custom-input"
                  name="unidades_totales"
                  placeholder="unidades totales"
                  value={servicioFormData.unidades_totales}
                  onChange={handleServicioInputChange} required />
                <textarea
                  className="form-control mb-2 custom-textarea"
                  name="descripcion"
                  placeholder="Descripción"
                  value={servicioFormData.descripcion}
                  onChange={handleServicioInputChange}></textarea>
                <button type="submit" className="btn btn-success custom-btn">Crear Servicio</button>
              </form>
            </div>
          </>
        )}

        <div className="mt-4">
          <h2>Eventos pendientes</h2>
          {!isCreateEventOpen && !isNotifyFollowersOpen && (
            <ul className="list-group">
              {eventosDisponibles
                .filter((event) => event.id_club.idClub === parseInt(sessionStorage.getItem("id_user"), 10))

                .map((event) => (
                  <li key={event.id_evento} className="list-group-item">
                    <h6><strong>{event.id_club.nombre} vs {event.oponente}</strong></h6> <br />
                    <strong>Ubicación:</strong> {event.estadio} <br />
                    <strong>Fecha:</strong> {event.fecha.substring(0, 10)} <br />
                    <strong>Hora de inicio:</strong> {event.hora_ingreso}, <strong>cierre:</strong> {event.hora_cierre} <br />


                    <button
                      className="btn btn-info mr-2 custom-btn"
                      onClick={() => handleConsultarBoletas(event.id_evento)}>Consultar Boletas Vendidas</button>
                    {isConsultarBoletasOpen && selectedEventId === event.id_evento && (
                      <div className="mt-3">
                      <h3>Localidades y Boletas Vendidas:</h3>
                      <ul className="list-group">

                        {Array.from(
                          boletasFiltradas.reduce((map, boleta) => {
                            const nombreLocalidad = boleta.localidad.nombre;
                            // Si aún no existe la localidad en el Map, la agregamos
                            if (!map.has(nombreLocalidad)) {
                              map.set(nombreLocalidad, boleta);
                            }
                            return map;
                          }, new Map()).values()
                        ).map((boleta, index) => (
                          <li key={index} className="list-group-item">
                            <strong>Localidad:</strong> {boleta.localidad.nombre} <br />
                            <strong>Cantidad de boletas:</strong> {boleta.localidad.cantidad_puestos_vendidos} <br />
                          </li>
                        ))}
                      </ul>
                    </div>
                    )}
                  </li>
                ))}
            </ul>
          )}

        </div>
      </div>
    </div>
  );
}

export default ClubDashboard;
