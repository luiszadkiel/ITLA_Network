package itla_network;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import basedatos.Conexion_mysql;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class perfil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private int idusario;
    editarperfil editarPerfil = new editarperfil();
    JLabel lblNewLabel_5 = new JLabel("Nombre");
    JTextArea txtrDescripcionDeLa = new JTextArea();
   
    
    
    
    
    /**
     * Launch the application.
     */
   
    /**
     * Create the frame.
     */
    public perfil(int idusario) {
    	   	
    	
    	setIdusario(idusario);
    	Conexion_mysql conn = new Conexion_mysql();
    	String sql = "SELECT p.Nombre_PERFIL AS Nombre_Perfil, " +
    	             "p.foto_Perfil AS Foto_Perfil, " +
    	             "p.Biografia AS Biografia_Perfil " +
    	             "FROM Usuarios u " +
    	             "JOIN Perfil p ON u.ID_Usuarios = p.id_perfil " +
    	             "WHERE u.ID_Usuarios = ?";
    	

    	try (PreparedStatement pstmt = conn.getConnection().prepareStatement(sql)) {
    		
    	    // Establece el parámetro de la consulta
    	    pstmt.setInt(1, getIdusario());

    	    // Ejecuta la consulta
    	    try (ResultSet rs = pstmt.executeQuery()) {
    	        // Procesa los resultados
    	        if (rs.next()) {
    	            String nombrePerfil = rs.getString("Nombre_Perfil");
    	            byte[] fotoPerfil = rs.getBytes("Foto_Perfil"); // Asume que la foto está en formato binario
    	            String biografia = rs.getString("Biografia_Perfil");

    	       
    	       
    	            lblNewLabel_5.setBounds(305, 17, 159, 14);
    	          
    	    		lblNewLabel_5.setText(nombrePerfil);
    	    		 txtrDescripcionDeLa.setEnabled(false);
    	    		    txtrDescripcionDeLa.setEditable(false);
    	    		    
    	    		    if(biografia == null) {
    	    		    txtrDescripcionDeLa.setText("Descripcion de la cuenta....");
    	    		    txtrDescripcionDeLa.setForeground(Color.WHITE);
    	    		    }else{
        	    		    txtrDescripcionDeLa.setText(biografia);
        	    		    txtrDescripcionDeLa.setForeground(Color.WHITE);

    	    		    }
    	            
    	        } else {
    	         
    	        }
    	    }

    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}

    
    
    	
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        // Agregar el ActionListener al JMenuItem
        mntmNewMenuItem_1.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí creas y muestras la ventana editarperfil
                editarPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editarPerfil.setVisible(true);
            }
        });
        
        
        


        
        
        mnNewMenu.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Editar Cuenta");
        mntmNewMenuItem_2.setForeground(new Color(255, 255, 255));
        mntmNewMenuItem_2.setBackground(new Color(0, 0, 0));
        mnNewMenu.add(mntmNewMenuItem_2);

        JMenuItem mntmNewMenuItem = new JMenuItem("");
        mntmNewMenuItem.setBackground(new Color(0, 0, 0));
        mnNewMenu.add(mntmNewMenuItem);

        JMenu mnNewMenu_1 = new JMenu("SEGUIDOS");
        mnNewMenu_1.setForeground(Color.WHITE);
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Ver seguidores");
        mntmNewMenuItem_3.setForeground(Color.WHITE);
        mntmNewMenuItem_3.setBackground(Color.BLACK);
        mnNewMenu_1.add(mntmNewMenuItem_3);

        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(10, 29, 586, 169);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nombre de usuario");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(144, 11, 127, 26);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("publicaciones");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(194, 46, 94, 22);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("seguidores");
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(333, 47, 94, 20);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Sin foto de perfil");
        lblNewLabel_3.setForeground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Borde de línea negra de 2 píxeles
        lblNewLabel_3.setBorder(border);
        lblNewLabel_3.setBounds(10, 22, 127, 103);
        panel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("0");
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setBounds(284, 50, 46, 14);
        panel.add(lblNewLabel_4);

     
        panel.add(lblNewLabel_5);
        txtrDescripcionDeLa.setBackground(Color.BLACK);
        txtrDescripcionDeLa.setForeground(Color.BLACK);

      
        txtrDescripcionDeLa.setBounds(10, 133, 566, 36);
        panel.add(txtrDescripcionDeLa);

        JLabel lblNewLabel_6 = new JLabel("0");
        lblNewLabel_6.setForeground(Color.WHITE);
        lblNewLabel_6.setBounds(144, 50, 46, 18);
        panel.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("0");
        lblNewLabel_7.setForeground(Color.GRAY);
        lblNewLabel_7.setBounds(418, 50, 46, 14);
        panel.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("seguidos");
        lblNewLabel_8.setForeground(Color.WHITE);
        lblNewLabel_8.setBounds(468, 50, 108, 14);
        panel.add(lblNewLabel_8);

        JButton btnNewButton = new JButton("Seguir");
        btnNewButton.setBounds(143, 99, 128, 26);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Mensaje");
        btnNewButton_1.setBounds(298, 99, 146, 26);
        panel.add(btnNewButton_1);

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
                "", "", "" // Nombres de columna vacíos
            }
        ) {
            private static final long serialVersionUID = 1L;
            boolean[] columnEditables = new boolean[] {
                false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });

        // Habilitar la selección de celdas individuales
        table.setCellSelectionEnabled(true);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);

        // Configurar el modelo de selección para una sola celda
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ajustar la altura de las filas y el color de las líneas
        table.setRowHeight(150); // Altura de cada fila
        table.setGridColor(Color.BLACK);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30)); // Altura del encabezado

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 11, 566, 420);
        panel_1.add(scrollPane);

        // Añadir un MouseListener para abrir mostrarelpost
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                if (table.getSelectedRow() != -1) {
                    // Abre la ventana mostrarelpost al hacer clic en una celda
                    mostrarelpost mostrarPost = new mostrarelpost();
                    mostrarPost.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    mostrarPost.setVisible(true);
                }
            }
        });

        setVisible(true);
    }

	public int getIdusario() {
		return idusario;
	}

	public void setIdusario(int idusario) {
		this.idusario = idusario;
	}
}
