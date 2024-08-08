package clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basedatos.Conexion_mysql;

public class seguidos extends Perfil {
    private List<String> seguidores = new ArrayList<>();
    private static int userLogged;
    private  Conexion_mysql conexion;
    private Perfil perfil = Perfil.getInstance();
    private int cantidadSeguidores;

    public seguidos() {
        this.conexion = new Conexion_mysql(); // Inicializar conexión con la base de datos
        
        // Obtener el ID del usuario logueado
        String nombre = perfil.getNombre_Perfil();
        String query = "SELECT ID_Usuarios FROM Usuarios WHERE Nombre_USUARIO = ?";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            // Asignar valor al parámetro de la consulta
            stmt.setString(1, nombre);
            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();
            // Procesar el resultado
            if (rs.next()) {
                int userID = rs.getInt("ID_Usuarios");
                setUserLogged(userID);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static  List<String> obtenerSeguidores() {
        List<String> seguidores = new ArrayList<>();
        
        String sql = "SELECT u.Nombre_USUARIO AS Nombre " +
                     "FROM seguidos s " +
                     "JOIN Usuarios u ON s.seguidor_id = u.ID_Usuarios " +
                     "JOIN Cuenta c ON s.FK_Cuenta = c.id_cuenta " +
                     "WHERE c.propietario_id = ?;";
        Conexion_mysql cone = new Conexion_mysql(); // Inicializar conexión con la base de datos
        try (PreparedStatement pstmt = cone.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, userLogged);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String nombreUsuario = rs.getString("Nombre");
                    seguidores.add(nombreUsuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return seguidores;
    }

    public int getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(int userLogged) {
        this.userLogged = userLogged;
    }

    public int getCantidadSeguidores() {
        return cantidadSeguidores;
    }

    public void setCantidadSeguidores(int cantidadSeguidores) {
        this.cantidadSeguidores = cantidadSeguidores;
    }
}
