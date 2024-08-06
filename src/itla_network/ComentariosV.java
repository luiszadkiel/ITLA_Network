package itla_network;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import basedatos.Conexion_mysql;
import clases.Perfil;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ComentariosV extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	static int posiciones = 39;
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
		 comentarios();
		this.Post = postid;
		panel.setBackground(new Color(0, 0, 0));
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 537, 506);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(0, 40, 523, 923);
		panel.setPreferredSize(new Dimension(400,1600));
		contentPane.add(scrollPane);
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(1, 1056, 391, 48);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(391, 1068, 114, 48);
		btnNewButton.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
			
			
			
			String textFie = textField.getText();
		Conexion_mysql conectarConexion_mysql =  new Conexion_mysql();
		try {
			Connection coneccion = conectarConexion_mysql.getConnection();
			
			PreparedStatement consultaPreparedStatement2 = coneccion.prepareStatement("Select * from Usuarios where Nombre_USUARIO = ? ");
			consultaPreparedStatement2.setString(1, instaciaPerfil.getNombre());
			ResultSet datosisResultSet = consultaPreparedStatement2.executeQuery();
		int datosid = -1;	
if (datosisResultSet.next()) {
	datosid = datosisResultSet.getInt("ID_Usuarios");
	
	
}

PreparedStatement consultaPreparedStatement3 = coneccion.prepareStatement("Select * from post where UsuarioID = ? ");
consultaPreparedStatement3.setInt(1, datosid);

ResultSet datosisResultSet3 = consultaPreparedStatement3.executeQuery();
int IDpOst = -1;
if (datosisResultSet3.next()) {
	IDpOst = datosisResultSet3.getInt("ID_post");
}

			PreparedStatement consultaPreparedStatement = coneccion.prepareStatement("insert into Comentarios(PostID,ID_Usuarios,Comentarios) values(?,?,?)");
			consultaPreparedStatement.setInt(1, IDpOst);
			consultaPreparedStatement.setInt(2, datosid);
			consultaPreparedStatement.setString(3, textFie);
			consultaPreparedStatement.executeUpdate();
			
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "error " + e2);
		}
			
		comentarios(); 
			}
		});
		panel.add(btnNewButton);
		
		
		panel.add(comentarios());
		
		
		JLabel lblNewLabel = new JLabel("Comentarios");
		lblNewLabel.setBounds(10, 10, 504, 29);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
	}
	
	public JPanel comentarios() {
	Conexion_mysql bdConexion_mysql = new Conexion_mysql();
	
	JPanel panel_1 = new JPanel();
	panel_1.setBounds(60, posiciones, 371, 92);
	
	panel_1.setLayout(null);

	
	JLabel lblNewLabel_1 = new JLabel(instaciaPerfil.getNombre());
	lblNewLabel_1.setBounds(163, 10, 45, 13);
	
	
	JTextArea textArea = new JTextArea();
	  textArea.setEditable(false);
	textArea.setBounds(10, 39, 351, 53);

	posiciones++;
	try {
		Connection coneConnection = bdConexion_mysql.getConnection();
		PreparedStatement consulPreparedStatement = coneConnection.prepareStatement("select Comentarios from comentarios where PostID = ?");
		consulPreparedStatement.setInt(1, Post);
		ResultSet comentarioSet = consulPreparedStatement.executeQuery();
		while (comentarioSet.next()) {
			textArea.setText(comentarioSet.getString("Comentarios"));
			
		}
		panel_1.add(textArea);
		panel_1.add(lblNewLabel_1);
		
	} catch (Exception e) {
		// TODO: handle exception
	}

	return panel_1;
}
}
