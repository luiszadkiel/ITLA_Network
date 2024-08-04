package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.mysql.cj.telemetry.TelemetryAttribute;

import basedatos.Conexion_mysql;
import itla_network.registro;

public class Registro {

	    private String NombreDeusuario;
	    private String Correo;
	    private String contraseña;
	    private String telefono;
	  
	    
	    public Registro(String NombreDeusuario,  String Correo, String contraseña, String telefono){
	    	
	 
	    	this.NombreDeusuario =  NombreDeusuario;
	    	this.Correo = Correo;
	    	this.contraseña = contraseña;
	    	this.telefono= telefono;
	    	
	    	 Conexion_mysql insertConexion_mysql = new Conexion_mysql();
	 	    Connection coneinsetConnection= insertConexion_mysql.getConnection();
	 	    
	 	   JOptionPane.showMessageDialog(null, "confirmacion de los datos, son enviados correctamente");
	 	    try {
				PreparedStatement inserrtPreparedStatement = coneinsetConnection.prepareStatement("insert into Usuarios(Nombre_USUARIO, Correo, contraseña, telefono) values(?,?,?,?)");
			inserrtPreparedStatement.setString(1, NombreDeusuario);
			inserrtPreparedStatement.setString(2, Correo);
			inserrtPreparedStatement.setString(3, contraseña);
			inserrtPreparedStatement.setString(4, telefono);
             inserrtPreparedStatement.executeUpdate();
            
	 	    
	 	    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	    
	    }

	    public String getNombreDeusuario() {
	        return NombreDeusuario;
	    }

	    public void setNombreDeusuario(String nombreDeusuario) {
	        this.NombreDeusuario = nombreDeusuario;
	    }

	 
	    public String getCorreo() {
	        return Correo;
	    }

	    public void setCorreo(String correo) {
	        this.Correo = correo;
	    }

	 
	    public String getContraseña() {
	        return contraseña;
	    }

	    public void setContraseña(String contraseña) {
	        this.contraseña = contraseña;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }
	    
	    
	   
	    
	    
	}




