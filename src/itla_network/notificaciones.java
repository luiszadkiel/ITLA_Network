package itla_network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.Window.Type;
import javax.swing.table.DefaultTableModel;

public class notificaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					notificaciones frame = new notificaciones();
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
	public notificaciones() {
		setTitle("Notificaciones");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 508);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Notificaciones");
		lblNewLabel.setFont(new Font("Sitka Small", Font.PLAIN, 27));
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setBounds(10, 11, 307, 60);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(10, 59, 414, 399);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				""
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(620);
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(0, 0, 0));

		// Crear JScrollPane y agregar la tabla
		JScrollPane scrollPane = new JScrollPane(table);
		// Establecer el color de fondo del JScrollPane y del viewport
		scrollPane.setBackground(new Color(0, 0, 0));
		scrollPane.getViewport().setBackground(new Color(0, 0, 0));
		scrollPane.setBounds(0, 0, 414, 399);

		// Agregar JScrollPane al panel
		panel.add(scrollPane);

	}

}
