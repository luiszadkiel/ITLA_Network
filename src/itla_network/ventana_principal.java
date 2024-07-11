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

	// Adrian estuvo aqui
	
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
		
		JInternalFrame internalFrame = new JInternalFrame("");
		internalFrame.setClosable(true);
		internalFrame.setBounds(10, 10, 209, 721);
		desktopPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBounds(0, 92, 193, 21);
		internalFrame.getContentPane().add(btnBuscar);
		
		JButton btnEstados = new JButton("Estados");
		btnEstados.setBounds(0, 164, 193, 21);
		internalFrame.getContentPane().add(btnEstados);
		
		JButton btnChat = new JButton("Chat");
		btnChat.setBounds(0, 191, 193, 21);
		internalFrame.getContentPane().add(btnChat);
		
		JButton btnNewButton_3_1 = new JButton("Notificaciones");
		btnNewButton_3_1.setBounds(0, 222, 193, 21);
		internalFrame.getContentPane().add(btnNewButton_3_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 289, 173, 2);
		internalFrame.getContentPane().add(separator_2);
		
		JButton btnNewButton_3_1_1 = new JButton("Perfil");
		btnNewButton_3_1_1.setBounds(0, 318, 193, 21);
		internalFrame.getContentPane().add(btnNewButton_3_1_1);
		
		JButton btnNewButton_3_1_2 = new JButton("Configuracion");
		btnNewButton_3_1_2.setBounds(0, 647, 193, 21);
		internalFrame.getContentPane().add(btnNewButton_3_1_2);
		
		JButton btnNewButton_3_1_3 = new JButton("Inicio");
		btnNewButton_3_1_3.setBounds(0, 258, 193, 21);
		internalFrame.getContentPane().add(btnNewButton_3_1_3);
		
		JLabel lblNewLabel = new JLabel("ITLA NETWORK");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(37, 10, 146, 30);
		internalFrame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 1513, 731);
		desktopPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.RED);
		panel_2.setBounds(238, 133, 795, 613);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JInternalFrame internalFrame_1 = new JInternalFrame("");
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
		internalFrame_2.setBounds(238, 10, 798, 113);
		panel.add(internalFrame_2);
		internalFrame_2.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(76, 31, 185, 28);
		internalFrame_2.getContentPane().add(textPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(309, 71, 2, 2);
		internalFrame_2.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 71, 782, 12);
		internalFrame_2.getContentPane().add(scrollPane_2);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(328, 31, 185, 28);
		internalFrame_2.getContentPane().add(textPane_1);
		
		JTextPane textPane_1_1 = new JTextPane();
		textPane_1_1.setBounds(587, 31, 185, 28);
		internalFrame_2.getContentPane().add(textPane_1_1);
		
		JInternalFrame internalFrame_3 = new JInternalFrame("Chat");
		internalFrame_3.setClosable(true);
		internalFrame_3.setBounds(1066, 10, 437, 655);
		panel.add(internalFrame_3);
		internalFrame_3.getContentPane().setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLUE);
		panel_3.setBounds(0, 0, 421, 52);
		internalFrame_3.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("fulanito de tal");
		lblNewLabel_1.setBounds(148, 10, 140, 38);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setBackground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textField = new JTextField();
		textField.setBounds(1066, 675, 437, 46);
		panel.add(textField);
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
