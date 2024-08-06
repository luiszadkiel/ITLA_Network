package itla_network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class mostrarelpost extends JFrame {

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
                    mostrarelpost frame = new mostrarelpost();
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
    public mostrarelpost() {
        setTitle("PUBLICACION");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        

        setBounds(100, 100, 877, 440);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.BLACK);
        panel.setToolTipText("foto");
        panel.setBounds(10, 22, 469, 364);
        contentPane.add(panel);
        
        JLabel lblNewLabel = new JLabel("Descripcion");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(489, 22, 151, 14);
        contentPane.add(lblNewLabel);
        
        // Crear JTextArea y JScrollPane
        JTextArea textArea = new JTextArea();
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        textAreaScrollPane.setBounds(489, 36, 362, 92);
        contentPane.add(textAreaScrollPane);
        
        JLabel lblNewLabel_1 = new JLabel("LIKE");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(501, 139, 44, 27);
        contentPane.add(lblNewLabel_1);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(533, 141, 64, 23);
        contentPane.add(btnNewButton);
        
        JLabel lblNewLabel_2 = new JLabel("Perfil");
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBackground(Color.WHITE);
        lblNewLabel_2.setBounds(606, 145, 46, 14);
        contentPane.add(lblNewLabel_2);
        
        JButton btnNewButton_1 = new JButton("New button");
        btnNewButton_1.setBounds(662, 139, 189, 23);
        contentPane.add(btnNewButton_1);
        
        JLabel lblNewLabel_3 = new JLabel("COMENTARIOS");
        lblNewLabel_3.setForeground(Color.WHITE);
        lblNewLabel_3.setBounds(489, 177, 362, 14);
        contentPane.add(lblNewLabel_3);
        
        // Crear JTable y JScrollPane
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Comentario"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(620);
        
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(489, 202, 362, 184);
        contentPane.add(tableScrollPane);
    }
}
