package itla_network;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;

import basedatos.Conexion_mysql;
import clases.Perfil;

public class ComentariosV extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    Perfil instaciaPerfil = Perfil.getInstance();
    JPanel panel = new JPanel();
    int Post;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        ComentariosV comentariosV = new ComentariosV(1);
        comentariosV.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public ComentariosV(int postid) {
        this.Post = postid;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 20, 537, 600);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Scroll panel for comments
        JScrollPane scrollPane = new JScrollPane(panel);
        panel.setPreferredSize(new Dimension(400, 9000));
        scrollPane.setViewportView(panel);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Text field and button panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(Color.BLACK);
        
        textField = new JTextField();
        inputPanel.add(textField, BorderLayout.CENTER);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Enviar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarComentario();
                textField.setText("");  // Limpiar el campo de texto después de enviar
                actualizarComentarios();  // Actualizar la lista de comentarios
            }
        });
        inputPanel.add(btnNewButton, BorderLayout.EAST);

        contentPane.add(inputPanel, BorderLayout.SOUTH);

        // Title label
        JLabel lblNewLabel = new JLabel("Comentarios");
        contentPane.add(lblNewLabel, BorderLayout.NORTH);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Actualizar los comentarios al iniciar
        actualizarComentarios();
    }

    private void agregarComentario() {
        String textFie = textField.getText();
        Conexion_mysql conectarConexion_mysql = new Conexion_mysql();
        try {
            Connection coneccion = conectarConexion_mysql.getConnection();

            PreparedStatement consultaPreparedStatement2 = coneccion.prepareStatement("Select * from Usuarios where Nombre_USUARIO = ? ");
            consultaPreparedStatement2.setString(1, instaciaPerfil.getNombre());
            ResultSet datosisResultSet = consultaPreparedStatement2.executeQuery();
            int datosid = -1;
            if (datosisResultSet.next()) {
                datosid = datosisResultSet.getInt("ID_Usuarios");
            }

          /*  PreparedStatement consultaPreparedStatement3 = coneccion.prepareStatement("Select * from post where UsuarioID = ? ");
            consultaPreparedStatement3.setInt(1, datosid);

            ResultSet datosisResultSet3 = consultaPreparedStatement3.executeQuery();
            int IDpOst = -1;
            if (datosisResultSet3.next()) {
                IDpOst = datosisResultSet3.getInt("ID_post");
            }*/

            PreparedStatement consultaPreparedStatement = coneccion.prepareStatement("insert into Comentarios(PostID,ID_Usuarios,Comentarios) values(?,?,?)");
            consultaPreparedStatement.setInt(1, Post);
            consultaPreparedStatement.setInt(2, datosid);
            consultaPreparedStatement.setString(3, textFie);
            consultaPreparedStatement.executeUpdate();

        } catch (SQLException e2) {
            JOptionPane.showMessageDialog(null, "error " + e2);
        }
    }

    private void actualizarComentarios() {
        panel.removeAll(); // Limpiar el panel de comentarios antes de actualizar
        List<String> comentarios = obtenerComentarios(Post);
        for (String comentario : comentarios) {
            JPanel panel_1 = new JPanel();
            panel_1.setLayout(new BorderLayout());
            panel_1.setPreferredSize(new Dimension(371, 92));
            panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));

            JLabel lblNewLabel_1 = new JLabel(instaciaPerfil.getNombre());
            lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
            panel_1.add(lblNewLabel_1, BorderLayout.NORTH);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
           textArea.setPreferredSize(new Dimension(351, 30)); 
            textArea.setText(comentario);
            panel_1.add(textArea, BorderLayout.CENTER);

            panel.add(panel_1);
        }
        panel.revalidate(); // Revalidar el panel después de actualizar
        panel.repaint(); // Repintar el panel para mostrar los cambios
    }

    private List<String> obtenerComentarios(int postID) {
        List<String> comentarios = new ArrayList<>();
        Conexion_mysql bdConexion_mysql = new Conexion_mysql();
        try {
            Connection coneConnection = bdConexion_mysql.getConnection();
            PreparedStatement consulPreparedStatement = coneConnection.prepareStatement("select Comentarios from comentarios where PostID = ?");
            consulPreparedStatement.setInt(1, postID);
            ResultSet comentarioSet = consulPreparedStatement.executeQuery();
            while (comentarioSet.next()) {
                comentarios.add(comentarioSet.getString("Comentarios"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comentarios;
    }
}
