const sql = require('mssql');

/**/
const config = {
    user: 'SANCLEMENTE\\A23SergioGR', // Usuario de conexión
    password: '', // Si el usuario no tiene contraseña, deja esto vacío
    server: 'mssql\\DAM2', // Nombre del servidor y la instancia
    database: 'EscuelaMusica', // Nombre de la base de datos
    options: {
        encrypt: false, // Configura en true si usas Azure o SSL
        enableArithAbort: true // Mejora la seguridad de la conexión
    }
};

// Función para realizar una consulta
async function ejecutarConsulta(query) {
    try {
        const pool = await sql.connect(config); // Conexión al servidor
        const result = await pool.request().query(query); // Ejecución de la consulta
        console.log('Resultado:', result.recordset); // Mostrar resultados en consola
        return result.recordset; // Devuelve los datos obtenidos
    } catch (err) {
        console.error('Error al ejecutar la consulta:', err.message);
        throw err; // Lanza el error para manejo posterior
    } finally {
        await sql.close(); // Cierra la conexión
    }
}

// Exporta la función para usarla en otros archivos
module.exports = { ejecutarConsulta };

const { ejecutarConsulta } = require('./db');

(async () => {
    try {
        const query = 'SELECT TOP 10 * FROM TU_TABLA'; // Cambia "TU_TABLA" por una tabla de tu base de datos
        const datos = await ejecutarConsulta(query);
        console.log('Datos obtenidos:', datos);
    } catch (error) {
        console.error('Error:', error.message);
    }
})();


const express = require('express');
const { ejecutarConsulta } = require('./db');

const app = express();
const PORT = 3000;

app.get('/api/datos', async (req, res) => {
    try {
        const query = 'SELECT TOP 10 * FROM TU_TABLA'; // Cambia "TU_TABLA" por una tabla válida
        const datos = await ejecutarConsulta(query);
        res.json(datos); // Envía los datos como JSON
    } catch (error) {
        res.status(500).send('Error al obtener datos: ' + error.message);
    }
});

app.listen(PORT, () => {
    console.log(`Servidor escuchando en http://localhost:${PORT}`);
});
