package itla_network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import basedatos.Conexion_mysql;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

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

public class Publicacion extends JFrame {

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
		panel.setBounds(0, 0, 610, 478);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FOTO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
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
		lblNewLabel.setBounds(92, 109, 399, 285);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Subir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				textField.setText("Esta funcionando");
				
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(lblNewLabel, "Este espacio no puede quedar vacio");
					
				}else{
					
					try {
						//Connection conbdConnection = 
						//PreparedStatement preparedStatement = conbdConnection.prepareStatement("insert into Post()");
						//preparedStatement.executeUpdate();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					
					
				}
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(439, 404, 147, 53);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(123, 45, 322, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion ");
		lblNewLabel_1.setBounds(257, 33, 83, 13);
		panel.add(lblNewLabel_1);
	}
}
