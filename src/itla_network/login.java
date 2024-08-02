package itla_network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import basedatos.Conexion_mysql;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JSeparator;
import clases.*;
import javax.swing.SwingConstants;
public class login extends JFrame {
	int i = 0;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 566);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(13, 11, 530, 504);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField.setBounds(93, 194, 357, 48);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Iniciar sesion ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginn ne =new loginn(); 
				String pass = new String(passwordField.getPassword());
				
				
				String nombre = textField.getText();
				Perfil perfil = Perfil.getInstance();
				perfil.setNombre(nombre);
				perfil.setNombre_Perfil(nombre);
				perfil.cargarPerfilPorNombre(nombre);
				
				
				
				if (ne.verificador(textField.getText(),pass)) {
					JOptionPane.showMessageDialog(null, "Bienvenido");
					ventana_principal ventnaPrincipal = new ventana_principal();// ojo aqui
					ventnaPrincipal.frame.setVisible(true);
				
				}else{
					i++;
					JOptionPane.showMessageDialog(null, "¡Usuario o contraseña incorrectos!", "", JOptionPane.ERROR_MESSAGE);
					if (i>3) {
						JOptionPane.showMessageDialog(null, "Porfavor registrese nuevamente", "", JOptionPane.ERROR_MESSAGE);
						i=0;
					}
				}
				
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Wide Latin", Font.PLAIN, 10));
		btnNewButton.setBounds(161, 347, 234, 57);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("ITLA_NETWORK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Wide Latin", Font.PLAIN, 29));
		lblNewLabel.setBounds(10, 106, 510, 77);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Wide Latin", Font.PLAIN, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registro n = new registro();
				n.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(106, 451, 333, 31);
		panel.add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		passwordField.setBounds(93, 271, 357, 48);
		panel.add(passwordField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(137, 428, 273, 2);
		panel.add(separator);
	}
}
