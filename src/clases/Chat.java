package clases;

import basedatos.Conexion_mysql;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Chat extends Perfil implements Mensaje {
	 List<String> mensajes = new ArrayList<>();
    private String Nombre_Chat;
    private int id_chat;
    private Timestamp tiempo;
    private Connection connection;
    private int Id_user_chat;

    public Chat() {
        // manda un mensaje de hola
        Conexion_mysql conexion = new Conexion_mysql();
        this.connection = conexion.getConnection();
    }

    // Getter y Setter para Nombre_Chat
    public String getNombre_Chat() {
        return Nombre_Chat;
    }

    public void setid_chat(int id_chat) {
        this.id_chat = id_chat;
    }

    public int getid_chat() {
        return id_chat;
    }

    public void setNombre_Chat(String Nombre_Chat) {
        this.Nombre_Chat = Nombre_Chat;
    }

    public void setChat(String texto) {
 
       
        String query = "SELECT texto_mensaje FROM Mensaje WHERE chat_id = ? AND user_id = ? ORDER BY hora_envio ASC";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, this.id_chat);
            stmt.setInt(2, this.Id_user_chat);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                mensajes.add(rs.getString("texto_mensaje"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // implementación para manejar la lista de mensajes obtenidos
    }

    public List<String> GetChat() {
        // implementación
        return mensajes;
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
        // Obtener el tiempo de envío del mensaje desde la base de datos
        String query = "SELECT hora_envio FROM Mensaje WHERE chat_id = ? AND user_id = ? ORDER BY hora_envio DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, this.id_chat);
            stmt.setInt(1, this.Id_user_chat);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.tiempo = rs.getTimestamp("hora_envio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Time(this.tiempo.getTime());
    }

    @Override
    public void setTexto(String texto) {
        // implementación
    }

    @Override
    public void Enviar() {
        String query = "INSERT INTO Mensaje (chat_id, user_id, texto_mensaje, hora_envio) VALUES (?, ?, ?, NOW())";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, this.id_chat);
            stmt.setString(3, this.getTexto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Eliminar() {
        // implementación
    }

    public void setTiempo(Timestamp tiempo) {
        this.tiempo = tiempo;
    }

    public void actualizarMensajesCadaMinuto() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setChat(null); // Actualiza la lista de mensajes
            }
        }, 0, 60000); // Actualiza cada 60 segundos
    }

	public int getId_user_chat() {
		return Id_user_chat;
	}

	public void setId_user_chat(int id_user_chat) {
		Id_user_chat = id_user_chat;
	}
}
