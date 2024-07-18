package basedatos;
import java.sql.*;

public class Conexion_mysql {
	 public static void main(String[] args) {
	   Connection conexion = null;
       String url = "jdbc:mysql://localhost:3306/database_itlanetwork";
       String usuario = "root";
       String password = "admin";
	
       
	// entramos la conexion dentro de un try para que nos informe si see produjo algun error en la conexion
	try {
		
// forma 1 de hacer la conexion	   //	Connection miConexion=DriverManager.getConection();
		
/* fomar 2*/
		
		// 1. crear conexion 
		conexion = DriverManager.getConnection(url, usuario, password);
		// 2. crear objecto de tipo statement
		Statement miStatement = conexion.createStatement();
		// 3. ejecutar sql
		ResultSet miResultset =miStatement.executeQuery("select * from usuarios"); 
		
		while(miResultset.next()) {
			System.out.print(miResultset.getString("Nombre_USUARIO") + "  ");
		//	System.out.print(miResultset.getString("Correo") + "  ");
			System.out.println(miResultset.getString("contraseña"));
		}
		
		
	}catch(SQLException  e){
		 System.out.println("Error en la conexión: " + e.getMessage());
	}
	finally {
        try {
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
	
	
	
	}
}}
