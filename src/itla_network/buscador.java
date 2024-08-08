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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.JPopupMenu;

import basedatos.Conexion_mysql;

public class buscador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
	private int idusuariodechar;

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
        // Configuración básica de la ventana
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
        
        // Etiqueta "Buscar"
        JLabel lblNewLabel = new JLabel("Buscar");
        lblNewLabel.setFont(new Font("Sitka Small", Font.PLAIN, 26));
        lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
        lblNewLabel.setBounds(10, 11, 160, 54);
        contentPane.add(lblNewLabel);
        
        // Campo de texto para ingresar el término de búsqueda
        textField = new JTextField();
        textField.setBackground(SystemColor.activeCaptionBorder);
        textField.setBounds(10, 61, 364, 29);
        contentPane.add(textField);
        textField.setColumns(10);
        
        // Botón "Buscar"
        JButton btnNewButton = new JButton("Buscar");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fetchAndUpdateTable(); // Llama al método para buscar y actualizar la tabla
            }
        });
        btnNewButton.setBounds(378, 61, 77, 29);
        contentPane.add(btnNewButton);
        
        // Panel para contener la tabla
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.windowBorder);
        panel.setBounds(10, 101, 445, 430);
        contentPane.add(panel);
        
        // Tabla para mostrar los resultados de la búsqueda
        table = new JTable();
        table.setForeground(SystemColor.text);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Nombre Usuario"}
        ) {
            boolean[] columnEditables = new boolean[] { false };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column]; // Las celdas no son editables
            }
        });
        table.getColumnModel().getColumn(0).setPreferredWidth(475);
        panel.setLayout(null);
        table.setBorder(null);
        table.setBackground(SystemColor.desktop);

        // Crear JScrollPane y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 11, 425, 408);
        panel.add(scrollPane); // Agregar JScrollPane al panel

        // Añadir MouseListener a la tabla para manejar clics en las filas
        table.addMouseListener(new MouseAdapter() {
       

			@Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Detectar doble clic
                    int row = table.getSelectedRow(); // Obtener la fila seleccionada
                    if (row != -1) { // Si la fila es válida
                        String nombreUsuario = (String) table.getValueAt(row, 0); // Obtener el nombre de usuario de la fila seleccionada
                                              
                        Conexion_mysql conexion = new Conexion_mysql();
                        Connection connection = null;

                        String query2 = "SELECT ID_Usuarios FROM Usuarios WHERE Nombre_USUARIO = ?";
                        connection = conexion.getConnection();
                        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query2)){
                             
                        	// Asignar valor al parámetro de la consulta
                        	stmt.setString(1, nombreUsuario);

                            // Ejecutar la consulta
                            ResultSet rs = stmt.executeQuery();

                            // Procesar el resultado
                            if (rs.next()) {
                            	
                                  idusuariodechar = rs.getInt("ID_Usuarios");
                                  setIdusuariodechar(idusuariodechar);  
                                  // Abrir la ventana perfil con el ID del usuario
                                  perfil perfilWindow = new perfil(getIdusuariodechar());
                                  
                                  perfilWindow.setVisible(true);
                            }

                           } catch (SQLException e1) {
                               e1.printStackTrace();

                               
             
                            
                            
                            
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------                           
                        
                        }
                    }
                }
            }
        });
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
                String query = "SELECT Nombre_USUARIO FROM usuarios WHERE Nombre_USUARIO = ?";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, searchTerm);
                
                rs = pstmt.executeQuery();

                // Verificar si se encontraron resultados
                boolean found = false;
                while (rs.next()) {
                    String nombreUsuario = rs.getString("Nombre_USUARIO");
                    model.addRow(new Object[] { nombreUsuario }); // Agregar el nombre de usuario a la tabla
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

    private int getUserIdByUsername(String username) {
        Conexion_mysql conexion = new Conexion_mysql();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int userId = -1;

        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String query = "SELECT ID_Usuarios FROM usuarios WHERE Nombre_USUARIO = ?";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, username);
                
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    userId = rs.getInt("ID_Usuarios"); // Obtener el ID del usuario
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Cerrar los recursos
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return userId;
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
    
    
    public int getIdusuariodechar() {
        return idusuariodechar;
    }

    // Setter
    public void setIdusuariodechar(int idusuariodechar) {
        this.idusuariodechar = idusuariodechar;
    }
    
}