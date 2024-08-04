package itla_network;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.PreparedStatement; // Asegúrate de importar PreparedStatement
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import basedatos.Conexion_mysql;
import javax.swing.JPopupMenu;

public class buscador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    buscador frame = new buscador();
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
    public buscador() {
        setForeground(SystemColor.desktop);
        setTitle("Buscador");
        setType(Type.UTILITY);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 483, 581);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.desktop);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Buscar");
        lblNewLabel.setFont(new Font("Sitka Small", Font.PLAIN, 26));
        lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
        lblNewLabel.setBounds(10, 11, 160, 54);
        contentPane.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBackground(SystemColor.activeCaptionBorder);
        textField.setBounds(10, 61, 364, 29);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("Buscar");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fetchAndUpdateTable();
            }
        });
        btnNewButton.setBounds(378, 61, 77, 29);
        contentPane.add(btnNewButton);
        
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.windowBorder);
        panel.setBounds(10, 101, 445, 430);
        contentPane.add(panel);
        table = new JTable();
        table.setForeground(SystemColor.text);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Nombre Usuario"}
        ) {
            boolean[] columnEditables = new boolean[] { false };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(0).setPreferredWidth(475);
        panel.setLayout(null);
        table.setBorder(null);
        table.setBackground(SystemColor.desktop);

        // Crear JScrollPane y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 11, 425, 408);

        // Agregar JScrollPane al panel
        panel.add(scrollPane);
    }

    private void fetchAndUpdateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar el modelo de la tabla antes de agregar nuevos datos
        Conexion_mysql conexion = new Conexion_mysql();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String searchTerm = textField.getText().trim(); // Obtener el término de búsqueda del JTextField

        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre de usuario para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            connection = conexion.getConnection();
            if (connection != null) {
                // Usar PreparedStatement para evitar inyecciones SQL
                String query = "SELECT Nombre_USUARIO FROM Usuarios WHERE Nombre_USUARIO = ?";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, searchTerm);
                
                rs = pstmt.executeQuery();

                // Verificar si se encontraron resultados
                boolean found = false;
                while (rs.next()) {
                    String nombreUsuario = rs.getString("Nombre_USUARIO");
                    model.addRow(new Object[] { nombreUsuario });
                    found = true;
                }

                if (!found) {
                    JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                System.out.println("No se pudo establecer la conexión con la base de datos.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Cerrar los recursos
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
