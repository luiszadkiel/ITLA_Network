package clases;

import java.sql.Time;

public abstract class Usuario {
	private String nombre;
	private String correo;
	private String contraseña;
	private String telefono;
	private Time fechaRegistro;

	public Usuario() {}
	
	 // Constructor
    public Usuario(String nombre, String correo, String contraseña, String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }
	
	
	
	
	// Métodos getter y setter
	public String getNombre() {
	    return nombre;
	}

	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}

	public String getCorreo() {
	    return correo;
	}

	public void setCorreo(String correo) {
	    this.correo = correo;
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

	public Time getFechaRegistro() {
	    return fechaRegistro;
	}

	public void setFechaRegistro(Time fechaRegistro) {
	    this.fechaRegistro = fechaRegistro;
	}

}
