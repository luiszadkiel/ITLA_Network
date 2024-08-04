package itla_network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import basedatos.Conexion_mysql;
import clases.Perfil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Publicacion extends JFrame {
	Perfil miperfil = Perfil.getInstance();
	   String nombre_user =miperfil.getNombre_Perfil();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
private int num;
private FileInputStream fileInputStream;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Publicacion frame = new Publicacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Publicacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 608, 478);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Subir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Este espacio no puede quedar vacio");
					
				}else{
					Conexion_mysql conexion_mysql = new Conexion_mysql();
					try {
						Connection conbdConnection = conexion_mysql.getConnection();
						PreparedStatement preparedStatement2 = conbdConnection.prepareStatement("Select ID_Usuarios from Usuarios where Nombre_USUARIO = ?");
						preparedStatement2.setString(1, nombre_user);
						 ResultSet datoSet = preparedStatement2.executeQuery();
						 int i = -1;
						 if (datoSet.next()) {
							i = datoSet.getInt("ID_Usuarios");
						}
						PreparedStatement preparedStatement = conbdConnection.prepareStatement("insert into Post(CuentaID, IMAGEN, DESCRIPCION) values(?,?,?)");
						preparedStatement.setInt(1, i);
						preparedStatement.setBlob(2,fileInputStream, num);
						preparedStatement.setString(3, textField.getText());
						
					    preparedStatement.executeUpdate();
					    JOptionPane.showMessageDialog(null, "Foto subida exitosamente ");

					
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos " + e2);
					}
					
					
					
				}
				
				
				textField.setText("");
				
				
			}
		});
		btnNewButton.setBounds(440, 410, 147, 53);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(132, 49, 322, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(255, 26, 83, 13);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(60, 93, 463, 307);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Click para  cargar imagen");
		lblNewLabel.setBounds(0, 0, 480, 307);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				JFileChooser sChooser = new JFileChooser();
				sChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int estado = sChooser.showOpenDialog(null);
		if (estado == JFileChooser.APPROVE_OPTION) {
			try {
				fileInputStream = new FileInputStream(sChooser.getSelectedFile());
				num = (int) sChooser.getSelectedFile().length();
				Image iconoImage = ImageIO.read(sChooser.getSelectedFile()).getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT);
				lblNewLabel.setIcon(new ImageIcon(iconoImage));
				lblNewLabel.updateUI();
			
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error de imagen" + e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error de imagen 205" + e);
			}
			
			
		}
				
				
			}
		});
	}
}
