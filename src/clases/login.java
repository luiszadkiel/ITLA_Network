package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import itla_network.ventana_principal;

 class login {//prueba para el login 
	
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection cone =DriverManager.getConnection("jdbc:mysql://localhost:3306/database_itlanetwork", "root","adrian123");
	Statement state = cone.createStatement();
	ResultSet n =state.executeQuery("Select Correo, contraseña from Usuarios");
	while(n.next()) {
		
		if(n.getString("Correo").equals(" a lo introcido por el login") && n.getString("contraseña").equals("a lo introducido por el login")) {}
		ventana_principal nuew = new ventana_principal ();
	}
}catch(ClassNotFoundException e1) {
	
	
}catch (SQLException e12) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}

 };
 
