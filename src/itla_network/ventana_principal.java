package itla_network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class ventana_principal {


	
	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana_principal window = new ventana_principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**....
	 * Create the application.
	 */
	public ventana_principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1527, 778);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(296, 254, 1, 1);
		desktopPane.add(desktopPane_1);
		
<<<<<<< HEAD
		JInternalFrame internalFrame = new JInternalFrame("");
		internalFrame.getContentPane().setBackground(new Color(0, 0, 0));
		internalFrame.getContentPane().setForeground(new Color(0, 0, 0));
		internalFrame.setBounds(10, 10, 209, 721);
=======
		JInternalFrame internalFrame = new JInternalFrame("Menú");
		internalFrame.setBounds(10, 10, 199, 721);
>>>>>>> refs/heads/rama_prueba
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
<<<<<<< HEAD
		btnBuscar.setBounds(0, 75, 193, 38);
=======
		btnBuscar.setBounds(0, 90, 193, 30);
>>>>>>> refs/heads/rama_prueba
		internalFrame.getContentPane().add(btnBuscar);
		
		JButton btnEstados = new JButton("Estados");
<<<<<<< HEAD
		btnEstados.setBounds(0, 123, 193, 38);
=======
		btnEstados.setBounds(0, 130, 193, 30);
>>>>>>> refs/heads/rama_prueba
		internalFrame.getContentPane().add(btnEstados);
		
		JButton btnChat = new JButton("Chat");
<<<<<<< HEAD
		btnChat.setBounds(0, 164, 193, 36);
=======
		btnChat.setBounds(0, 170, 193, 30);
>>>>>>> refs/heads/rama_prueba
		internalFrame.getContentPane().add(btnChat);
		
		JButton btnNewButton_3_1 = new JButton("Notificaciones");
<<<<<<< HEAD
		btnNewButton_3_1.setBounds(0, 210, 193, 38);
=======
		btnNewButton_3_1.setBounds(0, 206, 193, 30);
>>>>>>> refs/heads/rama_prueba
		internalFrame.getContentPane().add(btnNewButton_3_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 306, 173, 2);
		internalFrame.getContentPane().add(separator_2);
		
		JButton btnNewButton_3_1_1 = new JButton("Perfil");
<<<<<<< HEAD
		btnNewButton_3_1_1.setBounds(0, 318, 193, 36);
=======
		btnNewButton_3_1_1.setBounds(0, 318, 193, 33);
>>>>>>> refs/heads/rama_prueba
		internalFrame.getContentPane().add(btnNewButton_3_1_1);
		
		JButton btnNewButton_3_1_2 = new JButton("Configuracion");
<<<<<<< HEAD
		btnNewButton_3_1_2.setBounds(0, 630, 193, 38);
=======
		btnNewButton_3_1_2.setBounds(0, 635, 193, 33);
>>>>>>> refs/heads/rama_prueba
		internalFrame.getContentPane().add(btnNewButton_3_1_2);
		
		JButton btnNewButton_3_1_3 = new JButton("Inicio");
<<<<<<< HEAD
		btnNewButton_3_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3_1_3.setBounds(0, 258, 193, 38);
=======
		btnNewButton_3_1_3.setBounds(0, 246, 193, 33);
>>>>>>> refs/heads/rama_prueba
		internalFrame.getContentPane().add(btnNewButton_3_1_3);
		
		JLabel lblNewLabel = new JLabel("ITLA NETWORK");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(37, 10, 146, 30);
		internalFrame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 1513, 731);
		desktopPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 64));
		panel_2.setBounds(238, 133, 795, 613);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
<<<<<<< HEAD
		JInternalFrame internalFrame_1 = new JInternalFrame("");
		internalFrame_1.getContentPane().setBackground(new Color(0, 0, 0));
=======
		JInternalFrame internalFrame_1 = new JInternalFrame("Blog");
>>>>>>> refs/heads/rama_prueba
		internalFrame_1.setBounds(26, 0, 746, 625);
		panel_2.add(internalFrame_1);
		internalFrame_1.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(711, 0, 19, 595);
		internalFrame_1.getContentPane().add(panel_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(711, 0, 19, 572);
		internalFrame_1.getContentPane().add(scrollPane_3);
		
		JInternalFrame internalFrame_2 = new JInternalFrame("Estados");
		internalFrame_2.getContentPane().setBackground(new Color(240, 240, 240));
		internalFrame_2.setClosable(true);
		internalFrame_2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		internalFrame_2.setBounds(238, 10, 798, 113);
		panel.add(internalFrame_2);
		internalFrame_2.getContentPane().setLayout(null);
		
<<<<<<< HEAD
=======
		JTextPane textPane = new JTextPane();
		textPane.setBounds(59, 31, 185, 28);
		internalFrame_2.getContentPane().add(textPane);
		
>>>>>>> refs/heads/rama_prueba
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(309, 71, 2, 2);
		internalFrame_2.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
<<<<<<< HEAD
		scrollPane_2.setBounds(0, 0, 772, 83);
=======
		scrollPane_2.setBounds(0, 69, 782, 12);
>>>>>>> refs/heads/rama_prueba
		internalFrame_2.getContentPane().add(scrollPane_2);
		
<<<<<<< HEAD
		JPanel panel_4 = new JPanel();
		scrollPane_2.setViewportView(panel_4);
		panel_4.setBackground(new Color(0, 0, 0));
		panel_4.setLayout(null);
=======
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(316, 31, 195, 28);
		internalFrame_2.getContentPane().add(textPane_1);
>>>>>>> refs/heads/rama_prueba
		
		JTextPane textPane_1_1 = new JTextPane();
		textPane_1_1.setBounds(92, 28, 154, 34);
		panel_4.add(textPane_1_1);
		
		JTextPane textPane_1_1_1 = new JTextPane();
		textPane_1_1_1.setBounds(345, 28, 154, 34);
		panel_4.add(textPane_1_1_1);
		
		JTextPane textPane_1_1_2 = new JTextPane();
		textPane_1_1_2.setBounds(594, 28, 154, 34);
		panel_4.add(textPane_1_1_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sin foto de perfil");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBackground(new Color(240, 240, 240));
		lblNewLabel_3.setBounds(22, 21, 72, 52);
		panel_4.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Sin foto de perfil");
		lblNewLabel_3_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_1.setBounds(276, 21, 72, 52);
		panel_4.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Sin foto de perfil");
		lblNewLabel_3_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_2.setBounds(521, 21, 72, 52);
		panel_4.add(lblNewLabel_3_2);
		
		JInternalFrame internalFrame_3 = new JInternalFrame("Chat");
		internalFrame_3.getContentPane().setBackground(new Color(0, 0, 0));
		internalFrame_3.setClosable(true);
		internalFrame_3.setBounds(1066, 10, 437, 711);
		panel.add(internalFrame_3);
		internalFrame_3.getContentPane().setLayout(null);
		
		JPanel panel_3 = new JPanel();
<<<<<<< HEAD
		panel_3.setForeground(new Color(255, 255, 255));
		panel_3.setBackground(new Color(0, 0, 64));
		panel_3.setBounds(0, 0, 421, 46);
=======
		panel_3.setBackground(Color.BLUE);
		panel_3.setBounds(0, 0, 432, 37);
>>>>>>> refs/heads/rama_prueba
		internalFrame_3.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("fulanito de tal");
<<<<<<< HEAD
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(149, 10, 262, 38);
=======
		lblNewLabel_1.setBounds(148, 0, 140, 38);
>>>>>>> refs/heads/rama_prueba
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setBackground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textField = new JTextField();
<<<<<<< HEAD
		textField.setBounds(0, 635, 421, 46);
=======
		textField.setBounds(0, 635, 432, 46);
>>>>>>> refs/heads/rama_prueba
		internalFrame_3.getContentPane().add(textField);
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(1066, 668, 437, 0);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 344, 82, 0);
		panel.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 113, 782, 10);
		panel.add(scrollPane);
		internalFrame_3.setVisible(true);
		internalFrame_2.setVisible(true);
		internalFrame_1.setVisible(true);
		internalFrame.setVisible(true);
	}
}
