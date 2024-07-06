import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import Navbar from '../components/Navbar';
import APIInvoke from '../utils/APIInvoke';
import swal from 'sweetalert';

const RegistroForm = () => {
    const navigate = useNavigate();
    const [userType, setUserType] = useState('cliente');

    const [seguidor, setSeguidor_] = useState({
        correo_electronicoS: "",
        contrasenaS: "",
        nombreS: "",
        documento_de_identidadS: "",
    });

    const { nombreS, correo_electronicoS, contrasenaS, documento_de_identidadS } = seguidor;

    const setSeguidor = (e) => {
        setSeguidor_({
            ...seguidor,
            [e.target.name]: e.target.value,
        });
    };

    const [club, setClub_] = useState({
        correo_electronicoC: "",
        contrasenaC: "",
        nombreC: "",
        estadioPropioC: "",
    });

    const { nombreC, correo_electronicoC, contrasenaC, estadioPropioC } = club;

    const setClub = (e) => {
        setClub_({
            ...club,
            [e.target.name]: e.target.value,
        });
    };

    const register = async () => {

        if (userType === "club") {
            const data = {
                nombre: club.nombreC,
                correoElectronico: club.correo_electronicoC,
                estadioPropio: club.estadioPropioC,
                contrasena: club.contrasenaC,
            };

            try {
                const response = await APIInvoke.invokePOST(`api/Club/`, data);

                if (response.idClub) {
                    console.log("Datos ingresados correctamente en la base de datos");
                    sessionStorage.setItem('id_user', response.idClub);
                    sessionStorage.setItem('name_user', response.nombre);

                    navigate("/club")
                } else {
                    const msg =
                        "No fue posible registrar el usuario, verifique los datos ingresados.";
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
            } catch (error) {
                console.error("Error al intentar registrar el usuario:", error);
                const msg = "Ocurrió un error al intentar registrar el usuario. Intente de nuevo más tarde.";
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
            const data = {
                correo_electronico: seguidor.correo_electronicoS,
                contrasena: seguidor.contrasenaS,
                documento_de_identidad: seguidor.documento_de_identidadS,
                nombre: seguidor.nombreS
            };

            try {
                const response = await APIInvoke.invokePOST(`api/Seguidor/`, data);

                if (response.documento_de_identidad) {
                    console.log("Datos ingresados correctamente en la base de datos");
                    sessionStorage.setItem('id_user', response.documento_de_identidad);
                    sessionStorage.setItem('name_user', response.nombre);

                    navigate("/seguidor")
                } else {
                    const msg =
                        "No fue posible registrar el usuario, verifique los datos ingresados.";
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
            } catch (error) {
                console.error("Error al intentar registrar el usuario:", error);
                const msg = "Ocurrió un error al intentar registrar el usuario. Intente de nuevo más tarde.";
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


        // navigate(`/${userType}`)
    };

    const onSubmit = (e) => {
        e.preventDefault();
        register();
    };

    return (
        <div>
            <Navbar />
            <form onSubmit={onSubmit} className="form">
                <h2>Registro</h2>
                <div>
                    <label>Tipo de Usuario:</label>
                    <select
                        value={userType}
                        onChange={(e) => setUserType(e.target.value)}
                    >
                        <option value="seguidor">Seguidor</option>
                        <option value="club">Club</option>
                    </select>
                </div>
                {userType === 'club' ? (
                    <>
                        <div>
                            <label>Nombre del Club:</label>
                            <input
                                type="text"
                                name="nombreC"
                                value={nombreC}
                                onChange={setClub}
                                required
                            />
                        </div>
                        <div>
                            <label>Correo electronico:</label>
                            <input
                                type="email"
                                name='correo_electronicoC'
                                value={correo_electronicoC}
                                onChange={setClub}
                                required
                            />
                        </div>
                        <div>
                            <label>Contraseña:</label>
                            <input
                                type="password"
                                name='contrasenaC'
                                value={contrasenaC}
                                onChange={setClub}
                                required
                            />
                        </div>
                        <div>
                            <label>Estadio propio:</label>
                            <input
                                type="text"
                                name='estadioPropioC'
                                value={estadioPropioC}
                                onChange={setClub}
                                required
                            />
                        </div>
                    </>
                ) : (
                    <>
                        <div>
                            <label>Nombre del Seguidor:</label>
                            <input
                                type="text"
                                name="nombreS"
                                value={nombreS}
                                onChange={setSeguidor}
                                required
                            />
                        </div>
                        <div>
                            <label>Documento de identidad:</label>
                            <input
                                type="number"
                                name="documento_de_identidadS"
                                value={documento_de_identidadS}
                                onChange={setSeguidor}
                                required
                            />
                        </div>
                        <div>
                            <label>Correo electronico:</label>
                            <input
                                type="email"
                                name='correo_electronicoS'
                                value={correo_electronicoS}
                                onChange={setSeguidor}
                                required
                            />
                        </div>
                        <div>
                            <label>Contraseña:</label>
                            <input
                                type="password"
                                name='contrasenaS'
                                value={contrasenaS}
                                onChange={setSeguidor}
                                required
                            />
                        </div>

                    </>
                )}
                <button type="submit">Registro</button>
            </form>
        </div>
    );
};

export default RegistroForm;

