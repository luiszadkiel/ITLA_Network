package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion_mysql {

    private static final String URL = "jdbc:mysql://181.36.177.174:3306/database_itlanetwork";
    private static final String USER = "prueba_remot";
    private static final String PASSWORD = "Duranaracena01"; // **Recuerda reemplazarlo con tu contraseña real**

    public Connection getConnection() {
        Connection conexion = null;
        try {
            // Properties for secure connection (avoid hardcoding credentials)
            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);

            // Create connection
            conexion = DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        Conexion_mysql conexion = new Conexion_mysql();
        Connection conn = conexion.getConnection();


        // Close the connection when done
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public static void closeConnection(Connection conn) {
		// TODO Auto-generated method stub
		if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
	}
}}
