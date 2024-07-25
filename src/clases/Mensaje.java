package clases;

import java.sql.Time;

public interface Mensaje {

	 String getTexto();
	    Time getTiempo();

	    void setTexto(String texto);
	    void Enviar();
	    void Eliminar();
	
}
