// src/components/LoginForm.js
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import Navbar from '../components/Navbar';
import swal from "sweetalert";
import APIInvoke from '../utils/APIInvoke';

function LoginForm() {
  sessionStorage.removeItem("id_user");

  const navigate = useNavigate();

  const [userType, setUserType] = useState('club');

  const [usuario, setUsuario] = useState({
    correo_electronico: "",
    contrasena: "",
  });

  const { correo_electronico, contrasena } = usuario;

  const setUser = (e) => {
    setUsuario({
      ...usuario,
      [e.target.name]: e.target.value,
    });
  };

  const Login = async (e) => {
    e.preventDefault();
    if (!correo_electronico || !contrasena) {
      alert('Por favor, complete todos los campos.');
      return;
    }

    const data = {
      correo_electronico: usuario.correo_electronico,
      contrasena: usuario.contrasena,
    };

    if (userType === "club") {
      console.log("esto es un club")
      
      const response = await APIInvoke.invokePOST(`api/Club/login`, data);
      const mensaje = response.Mensaje;

      if (mensaje === "Datos correctos") {
        //guardamos en el localstorage
        sessionStorage.setItem('id_user', response.Club.idClub)
        sessionStorage.setItem('name_user', response.Club.nombre)

        //redireccionamos al home la pagina principal
        navigate("/club");
      } else {
        const msg =
          "No fue posible iniciar la sesión verifique los datos ingresados.";
        swal({
          title: "Error",
          text: msg,
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
      }
      
    } else {
      console.log("esto es un seguidor")

      const response = await APIInvoke.invokePOST(`api/Seguidor/login`, data);
      const mensaje = response.Mensaje;

      if (mensaje === "Datos correctos") {
        //guardamos en el localstorage
        sessionStorage.setItem('id_user', response.Seguidor.documento_de_identidad)
        sessionStorage.setItem('name_user', response.Seguidor.nombre)

        //redireccionamos al home la pagina principal
        navigate("/seguidor");
      } else {
        const msg =
          "No fue posible iniciar la sesión verifique los datos ingresados.";
        swal({
          title: "Error",
          text: msg,
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
      }
    }
  };

  return (
    <div>
      <Navbar />
      <form onSubmit={Login} className="form">
        <h2>Login</h2> {/* Título agregado */}
        <div>
          <label>Correo electronico:</label>
          <input
            type="email"
            name='correo_electronico'
            value={correo_electronico}
            onChange={setUser}
            required
          />
        </div>
        <div>
          <label>Contraseña:</label>
          <input
            type="password"
            name='contrasena'
            value={contrasena}
            onChange={setUser}
            required
          />
        </div>
        <div>
          <label>Tipo de Usuario:</label>
          <select
            value={userType}
            onChange={(e) => setUserType(e.target.value)}
          >
            <option value="club">Club</option>
            <option value="seguidor">Seguidor</option>
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Login</button>
      </form>
    </div>
  );
}

export default LoginForm;
