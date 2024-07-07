import React, { useState, useEffect } from 'react';
import '../assets/SeguidorDashboard.css'; // Importa el archivo CSS personalizado
import Navbar from '../components/Navbar';
import APIInvoke from '../utils/APIInvoke';
import swal from 'sweetalert';

function SeguidorDashboard({ events }) {
  const [showTickets, setShowTickets] = useState(false);
  const [showServices, setShowServices] = useState(false);

  const handleToggleTickets = () => {
    setShowTickets(!showTickets);
    setShowServices(false);
  };

  const handleToggleServices = () => {
    setShowServices(!showServices);
    setShowTickets(false);
  };

  const transferirBoleta = (idBoleta) => {

    swal({
      title: "¿Para quien es la boleta?",
      text: "Por favor, ingrese el numero de documento de la persona a la que desea transferir su boleta",
      content: {
        element: "input",
        value: "documentoATransferir",
        attributes: {
          type: "text",
        },
      },
      buttons: {
        cancel: "Cancelar",
        confirm: {
          text: "Confirmar",
          closeModal: false,
        },
      },
    })
      .then(async (documentoATransferir) => {
        try {

          const boleta = await APIInvoke.invokeGET(`api/Boleta/list/${idBoleta}`);

          const seguidor = await APIInvoke.invokeGET(`api/Seguidor/list/${documentoATransferir}`);

          const data = {
            "precio": (boleta.precio),
            "localidad": {
              "id_localidad": boleta.localidad.id_localidad,

              "eventoDeportivo": {
                "id_evento": boleta.localidad.eventoDeportivo.id_evento,

                "id_club": {
                  "idClub": boleta.localidad.eventoDeportivo.id_club.idClub
                }
              }
            },
            "seguidor": seguidor,
            "mercadoSecundario": false,
            "idBoleta": idBoleta
          }

          APIInvoke.invokePUT(`api/Boleta/`, data)

          swal({
            title: "Boleta transferida",
            text: "Ha transferido su boleta satisfactoriamente",
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

        } catch (error) {
          console.error("Error al transferir su boleta:", error);
          swal("Error", "No se pudo transferir la boleta, el documento que ingresó no es correcto", "error");
        }
      })
      .catch((error) => {
        if (error) {
          console.error("Unknown promise rejection reason:", error);
        }
      });

  };

  const venderEnMercadoSecundario = (idBoleta) => {
    swal({
      title: "Venta de boleta",
      text: "¿Está seguro que quiere vender su boleta en el mercado secundario? Se le devolverá el 90% del valor de la boleta",
      buttons: {
        cancel: {
          text: "Cancelar",
          value: "cancelar",
          visible: true,
          className: "",
          closeModal: true,
        },
        confirm: {
          text: "Confirmar",
          value: "confirmar",
          closeModal: false,
        },
      },
    }).then(async (value) => {
      if (value === "confirmar") {

        const boletaVendida = await APIInvoke.invokeGET(`api/Boleta/list/${idBoleta}`)

        console.log('boleta vendida:', boletaVendida);

        confirmarVentaMercadoSecundario(boletaVendida);

      } else if (value === "cancelar") {
        window.location.reload(); // Recarga la página si se presiona cancelar
      }
    }).catch(swal.noop);
  };

  const confirmarVentaMercadoSecundario = (boletaAVender) => {

    const data = {
      "precio": (boletaAVender.precio * 0.9),
      "localidad": {
        "id_localidad": boletaAVender.localidad.id_localidad,

        "eventoDeportivo": {
          "id_evento": boletaAVender.localidad.eventoDeportivo.id_evento,

          "id_club": {
            "idClub": boletaAVender.localidad.eventoDeportivo.id_club.idClub
          }
        }
      },
      "seguidor": null,
      "mercadoSecundario": true,
      "idBoleta": boletaAVender.idBoleta
    }

    APIInvoke.invokePUT(`api/Boleta/`, data)

    swal({
      title: "Boleta vendida",
      text: "Ha vendido su boleta satisfactoriamente",
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
  }

  const handleComprarBoleta = async (eventId) => {

    try {
      const response = await APIInvoke.invokeGET("api/Localidad/list/");

      const localidades = response.filter(
        (localidad) => localidad.eventoDeportivo.id_evento === eventId
      );


      if (localidades.length > 0) {
        // Crear las opciones para el select
        const localidadesOptions = localidades.map((localidad) => {
          return `<option value="${localidad.id_localidad}">${localidad.nombre}, valor: ${localidad.precio}</option>`;
        }).join('');

        // Mostrar swal con el select de localidades
        swal({
          title: "Escoja una localidad",
          text: "Por favor, escoja la localidad de su preferencia.",
          content: {
            element: "select",
            attributes: {
              innerHTML: localidadesOptions,
              id: "localidadSelect",
            },
          },
          buttons: {
            cancel: {
              text: "Cancelar",
              value: "cancelar",
              visible: true,
              className: "",
              closeModal: true,
            },
            confirm: {
              text: "Confirmar",
              value: "confirmar",
              closeModal: false,
            },
          },
        }).then(async (value) => {
          if (value === "confirmar") {
            const id_localidad = document.getElementById('localidadSelect').value;

            const dataLocalidad = await APIInvoke.invokeGET(`api/Localidad/list/${id_localidad}`)

            console.log('Localidad seleccionada:', dataLocalidad);

            confirmarCompra(dataLocalidad); // Llama a la función confirmarCompra con la localidad seleccionada
          } else if (value === "cancelar") {
            window.location.reload(); // Recarga la página si se presiona cancelar
          }
        }).catch(swal.noop);
      } else {
        swal({
          title: "Error",
          text: "No hay localidades disponibles para este evento.",
          icon: "error",
        }).then(() => {
          window.location.reload(); // Recarga la página al presionar "OK"
        });
      }
    } catch (error) {
      console.error("Error al obtener las localidades:", error);
      swal("Error", "No se pudo obtener la información de las localidades.", "error");
    }

  };

  const confirmarCompra = async (dataLocalidad) => {

    const data = {
      "precio": dataLocalidad.precio,
      "localidad": dataLocalidad,
      "seguidor": {
        "documento_de_identidad": sessionStorage.getItem("id_user")
      }
    }

    const boletaNueva = await APIInvoke.invokePOST(`api/Boleta/`, data)

    // sumar 1 boleta a la localidad

    const dataPutLocalidad = {
      "id_localidad": boletaNueva.localidad.id_localidad,
      "nombre": boletaNueva.localidad.nombre,
      "precio": boletaNueva.localidad.precio,
      "cantidad_puestos_total": boletaNueva.localidad.cantidad_puestos_total,
      "cantidad_puestos_vendidos": boletaNueva.localidad.cantidad_puestos_vendidos + 1,
      "eventoDeportivo": {
        "id_evento": boletaNueva.localidad.eventoDeportivo.id_evento,

        "id_club": {
          "idClub": boletaNueva.localidad.eventoDeportivo.id_club.idClub
        }
      }

    }

    APIInvoke.invokePUT("api/Localidad/", dataPutLocalidad)

    swal({
      title: "Boleta comprada",
      text: "Felicidades, ha comprado la boleta",
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

  // Eventos
  const [eventosDisponibles, setEventosDisponibles] = useState([]);
  const [boletasCompradas, setBoletasCompradas] = useState([]);
  const [serviciosAdicionales, setServiciosAdicionales] = useState([]);

  useEffect(() => {
    const fetchDatosUsuario = async () => {
      try {
        const response = await APIInvoke.invokeGET("api/Evento/list/");
        setEventosDisponibles(response);
      } catch (error) {
        console.error("Error al cargar los eventos", error);
        alert("Ha ocurrido un error, intente más tarde");
      }

      try {
        const response = await fetch("http://localhost:8080/api/Boleta/list/");
        const data = await response.json();

        setBoletasCompradas(data);
      } catch (error) {
        console.error("Error al cargar las boletas", error);
        alert("Ha ocurrido un error, intente más tarde");
      }

      try {
        const response = await fetch("http://localhost:8080/api/ServicioClub/list/");
        const data = await response.json();

        setServiciosAdicionales(data);
      } catch (error) {
        console.error("Error al cargar las boletas", error);
        alert("Ha ocurrido un error, intente más tarde");
      }

    };

    fetchDatosUsuario();
  }, []);

  const vincularse = () => {
    swal({
      title: "Funcionalidad limitada",
      text: "Esta funcionalidad está limitada en este momento.",
      icon: "info",
      buttons: {
        confirm: {
          text: "Ok",
          value: true,
          visible: true,
          className: "btn btn-primary",
          closeModal: true,
        },
      },
    });
  }

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
        <h1 className="mb-4">{sessionStorage.getItem("name_user")} Dashboard</h1>

        {/* Botones para ver boletas compradas y servicios adicionales */}
        <div className="mb-4">
          <button className="btn btn-primary mr-2 btn-separate" onClick={handleToggleTickets}>Ver Boletas Compradas</button>
          <button className="btn btn-primary btn-separate" onClick={handleToggleServices}>Ver Servicios Adicionales</button>
          <button className="btn btn-separate" onClick={vincularse}
            style={{ backgroundColor: "grey", color: "white" }}>Vincularme a un club</button>
        </div>

        {/* Mostrar eventos disponibles */}
        {!showTickets && !showServices && (
          <div className="mb-4">
            <h2>Eventos Disponibles</h2>
            <div className="row">
              {eventosDisponibles.map((event) => (
                <div key={event.id_evento} className="col-md-4 mb-4">
                  <div className="card">
                    <div className="card-body" style={{ width: "100%", display: 'block' }}>
                      <h6><strong>{event.id_club.nombre} vs {event.oponente}</strong></h6> <br />
                      <strong>Ubicación:</strong> {event.estadio} <br />
                      <strong>Fecha:</strong> {event.fecha.substring(0, 10)} <br />
                      <strong>Hora de inicio:</strong> {event.hora_ingreso}, <strong>cierre:</strong> {event.hora_cierre} <br />

                      <button className="btn btn-primary mt-2" onClick={() => handleComprarBoleta(event.id_evento)}>Comprar Boleta</button>

                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        )}

        {/* Sección de boletas compradas */}
        {showTickets && (
          <div className="mt-4">
            <h2>Boletas Compradas</h2>

            {boletasCompradas.length > 0 ? (

              <ul className="list-group">
                {boletasCompradas
                  // Filtrar solo las boletas que tienen un seguidor definido
                  .filter((boleta) => boleta.seguidor !== null && boleta.seguidor.documento_de_identidad === sessionStorage.getItem("id_user"))
                  .map((boleta) => (
                    <li key={boleta.idBoleta} className="list-group-item">
                      <strong>Evento:</strong> {boleta.localidad.eventoDeportivo.id_club.nombre} vs {boleta.localidad.eventoDeportivo.oponente}<br />
                      <strong>Localidad:</strong> {boleta.localidad.nombre} <br />
                      <button className="btn btn-danger mr-2" onClick={() => venderEnMercadoSecundario(boleta.idBoleta)}>Vender en mercado secundario</button>
                      <button className="btn btn-warning" onClick={() => transferirBoleta(boleta.idBoleta)}>Transferir Boleta</button>
                    </li>
                  ))}
              </ul>

            ) : (
              <p>No tienes boletas compradas.</p>
            )}
          </div>
        )}

        {/* Sección de servicios adicionales */}
        {showServices && (
          <div className="mt-4">
            <h2>Servicios Adicionales</h2>
            <ul className="list-group">
              {serviciosAdicionales.map((servicio) => (
                <li key={servicio.id_servicio_club} className="list-group-item">
                  <strong>Equipo:</strong> {servicio.club.nombre} <br /><br />
                  <strong>Servicio:</strong> {servicio.nombre} <br />
                  <strong>Descripcion:</strong> {servicio.descripcion} <br />
                  <strong>Precio:</strong> {servicio.precio} <br />
                </li>
              ))}

            </ul>


          </div>
        )}
      </div>
    </div>
  );
}

export default SeguidorDashboard;