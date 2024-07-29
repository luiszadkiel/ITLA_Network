package clases;

public class Registro {
	public class Usuario {
	    private String NombreDeusuario;
	    private String Correo;
	    private String contraseña;
	    private String telefono;

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



}
