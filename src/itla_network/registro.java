package itla_network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import basedatos.Conexion_mysql;
import clases.Registro;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class registro extends JFrame {
ArrayList<Registro>nuevoRegistros = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public registro() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 903, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 64));
		panel.setBounds(90, 10, 483, 476);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(110, 103, 300, 36);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(110, 257, 300, 36);
		panel.add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(110, 327, 300, 36);
		panel.add(textField_3);
		
		JLabel lblNewLabel = new JLabel("Nombre de usuario");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(210, 80, 107, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Correo");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(237, 234, 45, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(237, 160, 107, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Telefono ");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(237, 304, 61, 13);
		panel.add(lblNewLabel_1_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 182, 300, 36);
		panel.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Registro");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(214, 22, 127, 26);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(582, 10, 295, 260);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre de perfil");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(10, 11, 275, 19);
		panel_1.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 41, 275, 19);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Biografia");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBounds(10, 89, 275, 19);
		panel_1.add(lblNewLabel_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 119, 275, 100);
		panel_1.add(textArea);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(737, 443, 140, 43);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passw = new String(passwordField.getPassword());
				
				nuevoRegistros.add(new Registro(textField.getText() ,textField_1.getText(), passw,  textField_3.getText()));
				nuevoRegistros.removeAll(nuevoRegistros);
		        Conexion_mysql conn1561 = new Conexion_mysql();

				  // Consulta SQL para insertar datos
		        String sql = "INSERT INTO Perfil (Nombre_PERFIL, Biografia) VALUES (?, ?)";

		        try (Connection conn1 = conn1561.getConnection(); // Usar el método getConnection() de Conexion_mysql
		             PreparedStatement pstmt = conn1.prepareStatement(sql)) {

		            // Primer inserción
		            pstmt.setString(1, textField_2.getText()); // Nombre_PERFIL
		            pstmt.setString(2, textArea.getText()); // Biografia
		            int rowsAffected1 = pstmt.executeUpdate();
		        } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
}
