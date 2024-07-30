package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion_mysql {
    private static final String URL = "jdbc:mysql://181.36.177.174:3306/database_itlanetwork";
    private static final String USER = "prueba_remot";
    private static final String PASSWORD = "Duranaracena01";

    public Connection getConnection() {
        Connection conexion = null;
        try {
            // Propiedades para la conexión
            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);
           
            // 1. Crear conexión
            conexion = DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conexion;
    }
}
