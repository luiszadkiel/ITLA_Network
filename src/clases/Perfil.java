package clases;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import basedatos.Conexion_mysql;

public class Perfil extends Usuario {
    private static Perfil instance; // Instancia única de la clase Perfil

    private Image foto_Perfil;
    private String Nombre_Perfil;
    private String Biografía;
    private int Seguidores;
    private List<?> publicaciones; // Considera usar un tipo específico en lugar de `List<?>`

    // Constructores
    public Perfil() {
        super();
    }


    // Método estático para obtener la instancia única de Perfil
    public static synchronized Perfil getInstance() {
        if (instance == null) {
            instance = new Perfil();
        }
        return instance;
    }
    
    public Perfil(String nombre, String correo, String contraseña, String telefono, 
                  Image foto_Perfil, String Nombre_Perfil, String Biografía, 
                  int Seguidores, int Seguidos, List<?> publicaciones, String Descripción) {
        super(nombre, correo, contraseña, telefono);
        this.foto_Perfil = foto_Perfil;
        this.Nombre_Perfil = Nombre_Perfil;
        this.Biografía = Biografía;
        this.Seguidores = Seguidores;
       // this.Seguidos = Seguidos;
        this.publicaciones = publicaciones;
    }

    // Getters y Setters
    public Image getFoto_Perfil() {
        return foto_Perfil;
    }

    public void setFoto_Perfil(Image foto_Perfil) {
        this.foto_Perfil = foto_Perfil;
    }

    public String getNombre_Perfil() {
        return Nombre_Perfil;
    }

    public void setNombre_Perfil(String Nombre_Perfil) {
        this.Nombre_Perfil = Nombre_Perfil;
    }

    public String getBiografía() {
        return Biografía;
    }

    public void setBiografía(String Biografía) {
        this.Biografía = Biografía;
    }

    public int getSeguidores() {
        return Seguidores;
    }

    public void setSeguidores(int Seguidores) {
        this.Seguidores = Seguidores;
    }



    public List<?> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<?> publicaciones) {
        this.publicaciones = publicaciones;
    }

  

    public void editarPerfil(String Usuario, String Correo, String Contraseña) {
        // implementación
    }

    public boolean cargarPerfilPorNombre(String nombrePerfil) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión
            Conexion_mysql cone = new Conexion_mysql();
            conn = cone.getConnection();

            // Preparar la consulta
            String sql = "SELECT u.Nombre_USUARIO, u.Correo, u.contraseña, u.telefono, u.FECHA_REGISTRO, " +
                         "p.Nombre_PERFIL, p.Biografia, p.Seguidores, p.foto_Perfil " +
                         "FROM Usuarios u " +
                         "JOIN Perfil p ON u.ID_Usuarios = p.id_perfil " +
                         "WHERE p.Nombre_PERFIL = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombrePerfil);

            // Ejecutar la consulta
            rs = pstmt.executeQuery();

            // Procesar los resultados
            if (rs.next()) {
                String nombre = rs.getString("Nombre_USUARIO");
                String correo = rs.getString("Correo");
                String contraseña = rs.getString("contraseña");
                String telefono = rs.getString("telefono");
                // Time fechaRegistro = rs.getTime("FECHA_REGISTRO"); // Puedes ajustar según el tipo de dato de `fechaRegistro`

                this.setNombre(nombre);
                this.setCorreo(correo);
                this.setContraseña(contraseña);
                this.setTelefono(telefono);

                this.Nombre_Perfil = rs.getString("Nombre_USUARIO");
                this.Biografía = rs.getString("Biografia");
                this.Seguidores = rs.getInt("Seguidores");

                byte[] fotoPerfilBytes = rs.getBytes("foto_Perfil");
                if (fotoPerfilBytes != null) {
                    InputStream is = new ByteArrayInputStream(fotoPerfilBytes);
                    BufferedImage bufferedImage = ImageIO.read(is);
                    this.foto_Perfil = bufferedImage;
                }

                // Si tienes una lista de publicaciones, la puedes inicializar aquí
                this.publicaciones = new ArrayList<>(); // Ajusta según cómo manejes las publicaciones
                return true;
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
