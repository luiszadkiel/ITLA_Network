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
		
		JInternalFrame internalFrame_1 = new JInternalFrame("New JInternalFrame");
		internalFrame_1.setBounds(26, 0, 746, 625);
		panel_2.add(internalFrame_1);
		
		JPanel panel_1 = new JPanel();
		internalFrame_1.getContentPane().add(panel_1, BorderLayout.EAST);
		
		JInternalFrame internalFrame_2 = new JInternalFrame("New JInternalFrame");
		internalFrame_2.setBounds(238, 10, 798, 98);
		panel.add(internalFrame_2);
		
		JInternalFrame internalFrame_3 = new JInternalFrame("New JInternalFrame");
		internalFrame_3.setBounds(1066, 10, 437, 655);
		panel.add(internalFrame_3);
		
		textField = new JTextField();
		textField.setBounds(1066, 675, 437, 46);
		panel.add(textField);
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(1066, 668, 437, 0);
		panel.add(separator);
		internalFrame_3.setVisible(true);
		internalFrame_2.setVisible(true);
		internalFrame_1.setVisible(true);
		internalFrame.setVisible(true);
	}
}
