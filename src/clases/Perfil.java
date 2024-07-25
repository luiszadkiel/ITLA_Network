package clases;

import java.awt.Image;
import java.util.List;

public class Perfil extends Usuario {
	    
	   private Image foto_Perfil;
	    private String Nombre_Perfil;
	    private String Biografía;
	    private int Seguidores;
	    private int Seguidos;
	    private List<?> publicaciones;
	    private String Descripción;

	    
	    
	    // debe tener constructores
	    
	    
	    
	    
	    // Getter y Setter para foto_Perfil
	    public Image getFoto_Perfil() {
	        return foto_Perfil;
	    }

	    public void setFoto_Perfil(Image foto_Perfil) {
	        this.foto_Perfil = foto_Perfil;
	    }

	    // Getter y Setter para Nombre_Perfil
	    public String getNombre_Perfil() {
	        return Nombre_Perfil;
	    }

	    public void setNombre_Perfil(String Nombre_Perfil) {
	        this.Nombre_Perfil = Nombre_Perfil;
	    }

	    // Getter y Setter para Biografía
	    public String getBiografía() {
	        return Biografía;
	    }

	    public void setBiografía(String Biografía) {
	        this.Biografía = Biografía;
	    }

	    // Getter y Setter para Seguidores
	    public int getSeguidores() {
	        return Seguidores;
	    }

	    public void setSeguidores(int Seguidores) {
	        this.Seguidores = Seguidores;
	    }

	    // Getter y Setter para Seguidos
	    public int getSeguidos() {
	        return Seguidos;
	    }

	    public void setSeguidos(int Seguidos) {
	        this.Seguidos = Seguidos;
	    }

	    // Getter y Setter para publicaciones
	    public List getPublicaciones() {
	        return publicaciones;
	    }

	    public void setPublicaciones(List publicaciones) {
	        this.publicaciones = publicaciones;
	    }

	    // Getter y Setter para Descripción
	    public String getDescripción() {
	        return Descripción;
	    }

	    public void setDescripción(String Descripción) {
	        this.Descripción = Descripción;
	    }

	    public void editarPerfil(String Usuario, String Correo, String Contraseña) {
	        // implementación
	    }
}
