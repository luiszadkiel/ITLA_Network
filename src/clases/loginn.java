package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import basedatos.Conexion_mysql;
import itla_network.ventana_principal;

 public class loginn {
	 
     // Variables para almacenar los datos del perfil

   
     
	 Conexion_mysql cone = new Conexion_mysql();
	 public  boolean verificador(String usuario, String pass) {
		 Connection conexionConnection = cone.getConnection(); 
		 try {
			PreparedStatement consultaPreparedStatement = conexionConnection.prepareStatement("Select contraseña , Nombre_USUARIO from Usuarios ");
			/*consultaPreparedStatement.setString(1, "contraseña");
			consultaPreparedStatement.setString(2, "Nombre_USUARIO");*/

			ResultSet resultSet = consultaPreparedStatement.executeQuery();
			
			while (resultSet.next()) {
				if (resultSet.getString("contraseña").equals(pass) && resultSet.getString("Nombre_USUARIO").equals(usuario)){
					return true;
				}else if(resultSet.getString("Nombre_USUARIO").equals(usuario)){
	
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return false;

		 
		 
	 };
	 
	 
	 Connection conexionConnection = cone.getConnection(); 
	 
	 
 
	
	 
	 
 }
 
