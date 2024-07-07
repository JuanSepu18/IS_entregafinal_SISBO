// src/components/CreateEventForm.js
import React, { useState } from 'react';
import APIInvoke from '../utils/APIInvoke';
import swal from 'sweetalert';

function CreateEventForm({ onCreateEvent }) {

  const [localidades, setLocalidades] = useState([]);
  const [nombreLocalidad, setNombreLocalidad] = useState('');
  const [capacidadLocalidad, setCapacidadLocalidad] = useState('');
  const [precioLocalidad, setPrecioLocalidad] = useState('');


  const handleAddLocality = () => {
    if (!precioLocalidad || !nombreLocalidad || !capacidadLocalidad) {
      alert('Por favor, completa todos los campos de la localidad.');
      return;
    }
    setLocalidades([...localidades, {
      nombre: nombreLocalidad,
      precio: parseInt(precioLocalidad),
      cantidad_puestos_total: parseInt(capacidadLocalidad)

    }]);
    setNombreLocalidad('');
    setCapacidadLocalidad('');
    setPrecioLocalidad('');
  };

  const [eventoNuevo, setEventoNuevo] = useState({
    oponente: "",
    estadio: "",
    fecha: "",
    hora_ingreso: "",
    hora_cierre: "",
  });

  const { oponente, estadio, fecha, hora_ingreso, hora_cierre } = eventoNuevo;

  const setEvento = (e) => {
    setEventoNuevo({
      ...eventoNuevo,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (localidades.length === 0) {
      swal({
        title: "Error",
        text: "Debe ingresar al menos una localidad",
        icon: "error",
        buttons: {
          confirm: {
            text: "Ok",
            value: true,
            visible: true,
            className: "btn btn-danger",
            closeModal: true,
          },
        },
      });
    } else {
      try {
        const data = {
          "oponente": eventoNuevo.oponente,
          "estadio": eventoNuevo.estadio,
          "fecha": eventoNuevo.fecha,
          "hora_ingreso": eventoNuevo.hora_ingreso,
          "hora_cierre": eventoNuevo.hora_cierre,
          "id_club": {
            "idClub": parseInt(sessionStorage.getItem("id_user"), 10)
          }
        };

        const eventoCreado = await APIInvoke.invokePOST("api/Evento/", data);

        swal({
          title: "Evento creado",
          text: "El evento ha sido creado satisfactoriamente",
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
              // Lógica para las localidades
              localidades.forEach((localidad) => {
                const data = {
                  "nombre": localidad.nombre,
                  "precio": localidad.precio,
                  "cantidad_puestos_total": localidad.cantidad_puestos_total,
                  "cantidad_puestos_vendidos": 0,
                  "eventoDeportivo": {
                    "id_evento": eventoCreado.id_evento

                  }
                };
                
                APIInvoke.invokePOST("api/Localidad/", data);
              });
              window.location.reload();
            }
          });

      } catch (error) {
        console.error("Error al publicar el evento", error);
        alert("Ha ocurrido un error, intente más tarde");
      }
    }

  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Oponente del partido:</label>
        <input
          type="text"
          value={oponente}
          name='oponente'
          onChange={setEvento}
          required />
      </div>
      <div>
        <label>Fecha del Evento:</label>
        <input
          type="date"
          value={fecha}
          name='fecha'
          onChange={setEvento}
          required />
      </div>
      <div>
        <label>Hora de Ingreso:</label>
        <input
          type="time"
          value={hora_ingreso}
          name='hora_ingreso'
          onChange={setEvento}
          required />
      </div>
      <div>
        <label>Hora de Salida:</label>
        <input
          type="time"
          value={hora_cierre}
          name='hora_cierre'
          onChange={setEvento}
          required />
      </div>
      <div>
        <label>Ubicación del Evento - Estadio:</label>
        <input
          type="text"
          value={estadio}
          name='estadio'
          onChange={setEvento}
          required />
      </div>
      <div className="card" style={{ "backgroundColor": "#e9e9e9" }}>
        <div>
          <label>Nombre de la Localidad:</label>
          <input
            type="text"
            value={nombreLocalidad}
            onChange={(e) => setNombreLocalidad(e.target.value)}
            style={{ "marginBottom": "5px" }} />
        </div>
        <div>
          <label>Capacidad de la Localidad:</label>
          <input
            type="number"
            value={capacidadLocalidad}
            onChange={(e) => setCapacidadLocalidad(e.target.value)}
            style={{ "marginBottom": "5px" }} />
        </div>
        <div>
          <label>Precio de la boleta en la Localidad:</label>
          <input
            type="number"
            value={precioLocalidad}
            onChange={(e) => setPrecioLocalidad(e.target.value)}
            style={{ "marginBottom": "5px" }} />
        </div>
      </div>
      <button type="button" className='btn btn-info' onClick={handleAddLocality}>Añadir Localidad</button>
      <ul>
        {localidades.map((localidad, index) => (
          <li key={index}>{localidad.nombre} - {localidad.cantidad_puestos_total} personas - {localidad.precio} COP</li>
        ))}
      </ul>
      <button type="submit" className='btn btn-success'>Crear Evento</button>
    </form>
  );
}

export default CreateEventForm;

