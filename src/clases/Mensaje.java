package clases;

import java.sql.Time;
import java.util.Date;

public interface Mensaje {

	 String getTexto();
	    Date getTiempo();

	    void setTexto(String texto);
	    void Enviar();
	    void Eliminar();
	
}
