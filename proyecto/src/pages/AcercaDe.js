import Navbar from '../components/Navbar';


function AcercaDe() {

    return (
        <div>
            <Navbar />

            <div className='card' style={{ padding: "20px" }}>
                <h2>Acerca De:</h2>
                <h5>
                    SISBO es una aplicación web cuyo cliente principal son los clubes deportivos, <br /> teniendo como usuarios finales a ellos mismos y a sus seguidores, <br /> donde estos crean una cuenta que les permite tener varias funcionalidades relacionadas a la boletería, <br /> las funciones disponibles para los equipos son las siguientes:<br />

                    <br /> - Vender las entradas para sus eventos deportivos (incluyendo venta de abonos al inicio de temporada) y el precio. <br />
                    - Consultar en tiempo real la cantidad de entradas vendidas por localidad. <br />
                    - Mostrar servicios adicionales que se vayan a ofrecer el día del evento deportivo. <br />

                    <br /> Por otro lado, las funcionalidades que tendrán disponibles los usuarios son las siguientes: <br />

                    <br /> - Crear una cuenta haciendo uso tan solo de su documento de identidad, <br/> una contraseña y su correo electrónico, para facilitar el proceso. <br />
                    - Comprar directamente desde la plataforma, cuando se realiza una compra. <br />
                    - Vender la boleta que tenga en su poder en un mercado secundario de venta de boletas. <br />
                    - Transferir la boleta que tenga en su poder. <br />

                    <br />Con todo esto, se permite tener una plataforma que brinde confianza a los usuarios a la hora de comprar las boletas, <br /> además, con una interfaz amigable, permite que aficionados de todas las edades <br/> no tengan muchos inconvenientes a la hora de realizar compras.<br />
                    <br />

                </h5>

            </div>
            <br></br>
            <div className='card' style={{ padding: "20px" }}>
                <h3>Desarrollado el 07 de Julio de 2024 por:</h3>
                <h5>Carlos Esteban Garcia</h5>
                <h5>Juan Esteban Sepulveda</h5>
            </div>
        </div>
    );
}

export default AcercaDe;
