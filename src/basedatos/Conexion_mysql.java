package basedatos;

import java.sql.*;
import java.util.Properties;

public class Conexion_mysql {
    private static final String URL = "jdbc:mysql://181.36.177.174:3306/database_itlanetwork";
    private static final String USER = "prueba_remot";
    private static final String PASSWORD = "Duranaracena01";

    public static void main(String[] args) {
        Connection conexion = null;
        Statement statement = null;

        try {
            // Propiedades para la conexión
            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);
           
            // 1. Crear conexión
            conexion = DriverManager.getConnection(URL, properties);

            // 2. Crear objeto de tipo Statement
            statement = conexion.createStatement();
          

        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        } finally {
            // 5. Cerrar recursos
            try {
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
    
    
    
    
}
