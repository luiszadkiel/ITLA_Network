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

        // Use the connection for database operations here
        // For example, you can create a Statement or PreparedStatement and execute queries

        // Example of how you might use the connection (this code is commented out as it requires actual database operations):
        /*
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM some_table")) {
            while (rs.next()) {
                System.out.println("Column 1: " + rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error durante la consulta: " + e.getMessage());
        }
        */

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
