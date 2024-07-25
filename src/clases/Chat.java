package clases;

import java.sql.Time;

public class Chat extends Perfil implements Mensaje {

    private String Nombre_Chat;

    public Chat() {
        // manda un mensaje de hola
    }

    // Getter y Setter para Nombre_Chat
    public String getNombre_Chat() {
        return Nombre_Chat;
    }

    public void setNombre_Chat(String Nombre_Chat) {
        this.Nombre_Chat = Nombre_Chat;
    }

    public void setChat(String texto) {
        // implementación
    }

    public String GetChat() {
        // implementación
        return null;
    }

    public static Void Notificar(String Nombre_Perfil, String TipoNotificaciones) {
        // implementación
        return null;
    }

    @Override
    public String getTexto() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Time getTiempo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTexto(String texto) {
        // TODO Auto-generated method stub
    }

    @Override
    public void Enviar() {
        // TODO Auto-generated method stub
    }

    @Override
    public void Eliminar() {
        // TODO Auto-generated method stub
    }
}
