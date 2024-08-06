package itla_network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class editarperfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editarperfil frame = new editarperfil();
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
	public editarperfil() {
		setTitle("EdItar perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 263);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Foto de perfil");
		btnNewButton.setBounds(10, 11, 174, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Nombre de usuario");
		btnNewButton_1.setBounds(217, 11, 197, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Descripcion");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(434, 11, 197, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Eliminar perfil");
		btnNewButton_3.setForeground(Color.RED);
		btnNewButton_3.setBounds(457, 193, 174, 23);
		contentPane.add(btnNewButton_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(434, 45, 197, 58);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(217, 45, 197, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Guardar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.setBounds(10, 188, 174, 32);
		contentPane.add(btnNewButton_4);
	}
}
