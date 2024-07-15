package itla_network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class perfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
//klk44
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					perfil frame = new perfil();
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
	public perfil() {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 731);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 0));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Editar");
		mnNewMenu.setForeground(new Color(255, 255, 255));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Editar perfil");
		mntmNewMenuItem_1.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_1.setBackground(new Color(0, 0, 0));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Editar Cuenta");
		mntmNewMenuItem_2.setForeground(new Color(255, 255, 255));
		mntmNewMenuItem_2.setBackground(new Color(0, 0, 0));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("");
		mntmNewMenuItem.setBackground(new Color(0, 0, 0));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		contentPane =  new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 29, 586, 169);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de usuario");
		lblNewLabel.setBounds(9, 132, 128, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("publicaciones");
		lblNewLabel_1 .setBounds(194, 46, 94, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("seguidores");
		lblNewLabel_2.setBounds(333, 47, 94, 20);
		panel.add(lblNewLabel_2 );
		
		JLabel lblNewLabel_3 = new JLabel("Sin foto de perfil");
		   Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Borde de línea negra de 2 píxeles
		lblNewLabel_3.setBorder(border);
		lblNewLabel_3.setBounds(10, 22, 127, 103);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("0");
		lblNewLabel_4.setBounds(284, 50, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nombre");
		lblNewLabel_5.setBounds(145, 98, 159, 14);
		panel.add(lblNewLabel_5);
		
		JTextArea txtrDescripcionDeLa = new JTextArea();
		txtrDescripcionDeLa.setText("Descripcion de la cuenta....");
		txtrDescripcionDeLa.setBounds(142, 122, 434, 36);
		panel.add(txtrDescripcionDeLa);
		
		JLabel lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setBounds(144, 50, 46, 18);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("0");
		lblNewLabel_7.setBounds(418, 50, 46, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("seguidos");
		lblNewLabel_8.setBounds(468, 50, 108, 14);
		panel.add(lblNewLabel_8);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 217, 586, 442);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(210);
		table.getColumnModel().getColumn(0).setMinWidth(80);
		table.getColumnModel().getColumn(0).setMaxWidth(210);
		table.getColumnModel().getColumn(1).setPreferredWidth(206);
		table.getColumnModel().getColumn(2).setPreferredWidth(206);
		table.setBounds(10, 11, 566, 420);
		
		panel_1.add(table);
		
		/* // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel_1.add(scrollPane, BorderLayout.CENTER);
		*/
		 // Configurar líneas más gruesas
        table.setRowHeight(150); // Altura de cada fila
        table.setGridColor(Color.BLACK);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30)); // Altura del encabezado

}	
}
