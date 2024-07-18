package itla_network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class chat_principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat_principal frame = new chat_principal();
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
	public chat_principal() {
		setType(Type.UTILITY);
		setTitle("chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 579);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 72, 798, 410);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FOTO DE seguidor");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 110, 50);
		contentPane.add(lblNewLabel);
		
		

        // Envolver el panel en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(10, 72, 798, 410);

        // Agregar el JScrollPane al contentPane
        contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(164, 22, 472, 29);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 493, 519, 40);

		// Crear un JScrollPane y añadir el JTextArea al JScrollPane
		JScrollPane scrollPane2 = new JScrollPane(textArea_1);
		scrollPane2.setBounds(10, 493, 519, 40); // Asegúrate de ajustar los bounds al tamaño correcto del JScrollPane

		// Añadir el JScrollPane al contentPane
		contentPane.add(scrollPane2);
		JButton btnNewButton = new JButton("ENVIAR");
		btnNewButton.setBounds(663, 494, 145, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(601, 494, 56, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(539, 494, 56, 39);
		contentPane.add(btnNewButton_2);
	}
}
