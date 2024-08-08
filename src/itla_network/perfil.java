package itla_network;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import basedatos.Conexion_mysql;
import clases.Perfil;
import clases.seguidos;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class perfil extends JFrame {
int a;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private int idusario;
    editarperfil editarPerfil = new editarperfil();
    JLabel lblNewLabel_5 = new JLabel("Nombre");
    JTextArea txtrDescripcionDeLa = new JTextArea();
	int cantidad;
    int cantidadElementos = 0; // Variable para almacenar la cantidad de elementos
	 int candidad_de_seguidors;
	  JLabel lblNewLabel_6 = new JLabel();
	  private int idCuenta;
	    private int idUsuario; // Nuevo atributo

	  private Perfil perfil = Perfil.getInstance();
      String nombrePErfil = perfil.getNombre_Perfil();
  	JButton btnNewButton = new JButton();
	private String nombrePerfil2;


    /**
     * Launch the application.
     */
   
    /**
     * Create the frame.
     */
    public perfil(int idusario) {
    	 btnNewButton.setText("Seguir");
    	Conexion_mysql conn156 = new Conexion_mysql();
    	String query0934 = "SELECT COUNT(*) FROM Seguidos WHERE FK_Cuenta = ? AND seguidor_id = ? AND seguido_id = ?";

    	try (Connection connect_90345 = conn156.getConnection();
    	     PreparedStatement preparedStatement4 = connect_90345.prepareStatement(query0934)) {

    	    // Establecer los valores de los parámetros
    	    preparedStatement4.setInt(1, getIdCuenta());
    	    preparedStatement4.setInt(2, getIdUsuario());
    	    preparedStatement4.setInt(3, getIdusario());

    	    // Ejecutar la consulta de verificación
    	    try (ResultSet resultSet = preparedStatement4.executeQuery()) {
    	        // Cambiar el texto del botón si existe al menos una fila
    	        if (resultSet.next() && resultSet.getInt(1) > 0) {
    	        	btnNewButton.setText("Seguiendo");
    	        	a = 1;
    	        
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        System.out.println("Error al verificar la relación de seguimiento.");
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	    System.out.println("Error al establecer la conexión o preparar la consulta.");
    	}

    

    	
    	
    	
    	  JLabel lblNewLabel_9 = new JLabel("New label");
    	  lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	  lblNewLabel_9.setForeground(Color.BLUE);
    	  lblNewLabel_9.setBackground(Color.BLUE);
    	
    	setIdusario(idusario);
    	Conexion_mysql conn = new Conexion_mysql();
    	String sql567 = "SELECT p.Nombre_PERFIL AS Nombre_Perfil, " +
    	             "p.foto_Perfil AS Foto_Perfil, " +
    	             "p.Biografia AS Biografia_Perfil " +
    	             "FROM Usuarios u " +
    	             "JOIN Perfil p ON u.ID_Usuarios = p.id_perfil " +
    	             "WHERE u.ID_Usuarios = ?";
    	

    	try (PreparedStatement pstmt = conn.getConnection().prepareStatement(sql567)) {
    		
    	    // Establece el parámetro de la consulta
    	    pstmt.setInt(1, getIdusario());

    	    // Ejecuta la consulta
    	    try (ResultSet rs = pstmt.executeQuery()) {
    	        // Procesa los resultados
    	        if (rs.next()) {
    	            String nombrePerfil = rs.getString("Nombre_Perfil");
    	            byte[] fotoPerfil = rs.getBytes("Foto_Perfil"); // Asume que la foto está en formato binario
    	            String biografia = rs.getString("Biografia_Perfil");

    	             settextnombredelperfilbuscado(nombrePerfil);
    	       
    	            lblNewLabel_5.setBounds(305, 17, 159, 14);
    	          
    	    		lblNewLabel_5.setText(nombrePerfil);
    	    		lblNewLabel_5.setForeground(Color.YELLOW);
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

    
    	 Conexion_mysql conn17 = new Conexion_mysql();
       

         String query114 = "SELECT u.ID_Usuarios, c.id_cuenta " +
                        "FROM usuarios u " +
                        "JOIN cuenta c ON u.ID_Usuarios = c.propietario_id " +
                        "WHERE u.Nombre_USUARIO = ?";

         try (Connection connection = conn17.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query114)) {
             
             // Establecer el valor del parámetro
             preparedStatement.setString(1, nombrePErfil);
             
             // Ejecutar la consulta
             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 while (resultSet.next()) {
                     int idUsuario = resultSet.getInt("ID_Usuarios");
                     int idCuenta = resultSet.getInt("id_cuenta");
                     setIdCuenta(idCuenta);
                     setIdUsuario(idUsuario);
                  
                 }
             }
             
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println("Error al obtener los datos del usuario y cuenta.");
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
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String nombreuser = lblNewLabel_5.getText();
              lblNewLabel_5.setForeground(Color.YELLOW);
                seguidos seguidos = new seguidos();
               
                List<String> listaSeguidores = seguidos.obtenerSeguidores();
                cantidad = listaSeguidores.size();
                
                mostartuseguidores dialog = new mostartuseguidores(listaSeguidores);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });

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
        lblNewLabel_1.setBounds(144, 46, 94, 22);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("seguidores");
        lblNewLabel_2.setBackground(Color.BLUE);
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(318, 47, 94, 20);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Sin foto de perfil");
        lblNewLabel_3.setForeground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Borde de línea negra de 2 píxeles
        lblNewLabel_3.setBorder(border);
        lblNewLabel_3.setBounds(10, 22, 127, 103);
        panel.add(lblNewLabel_3);
        lblNewLabel_5.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
        lblNewLabel_5.setForeground(Color.YELLOW);
      
     
        panel.add(lblNewLabel_5);
        txtrDescripcionDeLa.setBackground(Color.BLACK);
        txtrDescripcionDeLa.setForeground(Color.BLACK);

      
        txtrDescripcionDeLa.setBounds(10, 133, 566, 36);
        panel.add(txtrDescripcionDeLa);
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6.setBackground(Color.BLUE);

      
        lblNewLabel_6.setForeground(Color.BLUE);
        lblNewLabel_6.setBounds(262, 48, 46, 18);
        String cantidadStr = Integer.toString(cantidad);
        panel.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("0");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_7.setForeground(Color.BLUE);
        lblNewLabel_7.setBounds(418, 50, 46, 14);
        panel.add(lblNewLabel_7);
     // Crea una instancia de la clase de conexión
     // Crea una instancia de la clase de conexión
        Conexion_mysql conn11906 = new Conexion_mysql();

        // Obtén la conexión
        try (Connection connection = conn11906.getConnection()) {
            String sql209 = "SELECT " +
                         "    u.ID_Usuarios AS Seguidor_ID, " +
                         "    u.Nombre_USUARIO AS Seguidor_Nombre, " +
                         "    u.Correo AS Seguidor_Correo " +
                         "FROM " +
                         "    seguidos s " +
                         "JOIN " +
                         "    Usuarios u ON s.seguidor_id = u.ID_Usuarios " +
                         "JOIN " +
                         "    Cuenta c ON s.FK_Cuenta = c.id_cuenta " +
                         "WHERE " +
                         "    c.propietario_id = ?";

            // Prepara el statement
            try (PreparedStatement pstmt = connection.prepareStatement(sql209)) {
                pstmt.setInt(1, getIdusario());

                // Ejecuta la consulta y obtiene los resultados
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    while (resultSet.next()) {

                        // Incrementa el contador de elementos
                    	cantidadElementos++;
                    	lblNewLabel_7.setText(String.valueOf(cantidadElementos));
                    	lblNewLabel_7.setForeground(Color.BLUE);

                    }

                  
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        
            
        JLabel lblNewLabel_8 = new JLabel("seguidos");
        lblNewLabel_8.setForeground(Color.WHITE);
        lblNewLabel_8.setBounds(456, 50, 108, 14);
        panel.add(lblNewLabel_8);

        
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.addActionListener(new ActionListener() {
        	

		

			public void actionPerformed(ActionEvent e) {
        		
  //-----------------------------------------------puevo seguidor      		
				Conexion_mysql conn1 = new Conexion_mysql();
		        String queryVerificar = "SELECT COUNT(*) FROM Seguidos WHERE FK_Cuenta = ? AND seguidor_id = ? AND seguido_id = ?";
		        String queryInsertar = "INSERT INTO Seguidos (FK_Cuenta, seguidor_id, seguido_id) VALUES (?, ?, ?)";

		        try (Connection connection = conn1.getConnection();
		             PreparedStatement preparedStatementVerificar = connection.prepareStatement(queryVerificar);
		             PreparedStatement preparedStatementInsertar = connection.prepareStatement(queryInsertar)) {

		            // Establecer los valores de los parámetros para la verificación
		            preparedStatementVerificar.setInt(1, getIdCuenta());
		            preparedStatementVerificar.setInt(2, getIdUsuario());
		            preparedStatementVerificar.setInt(3, getIdusario());

		            // Ejecutar la consulta de verificación
		            try (ResultSet resultSet = preparedStatementVerificar.executeQuery()) {
		                if (resultSet.next()) {
		                    int count = resultSet.getInt(1);
		                    if (count == 0) {
		                    
		                        // Si no existe, proceder con la inserción
		                        preparedStatementInsertar.setInt(1, getIdCuenta());
		                        preparedStatementInsertar.setInt(2, getIdUsuario());
		                        preparedStatementInsertar.setInt(3, getIdusario());

		                       
		                    } else {
		                    	a = 1;
		                    	 // La relación ya existe, no hacer nada
		                    	btnNewButton.setText("Seguiendo");
		                    	
		                    }
		                }
		            }

		        } catch (SQLException e1) {
		            e1.printStackTrace();
		            System.out.println("Error al manejar la relación de seguimiento.");
		        }
        		
        	}
        });
        btnNewButton.setBackground(Color.WHITE);
        if(a != 0 || a > 0 || a == 1) {
        	btnNewButton.setText("Seguiendo");
        }
        
        btnNewButton.setBounds(143, 99, 128, 26);
        panel.add(btnNewButton);
        
       
        
//---------------------------------------------------------------------------------------------------------------------------------------------------------------
        JButton btnNewButton_1 = new JButton("Mensaje");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Conexion_mysql conn156 = new Conexion_mysql();
                String query0_34 = "SELECT c.chat_id, c.creacion_chat FROM Chat c JOIN Chat_usuarios cu ON c.chat_id = cu.chat_id WHERE cu.user_id = ?";
                boolean chatCompartidoEncontrado = true;

                try (Connection connect_90345 = conn156.getConnection();
                     PreparedStatement preparedStatement4 = connect_90345.prepareStatement(query0_34)) {

                    preparedStatement4.setInt(1, getIdusario());
                    try (ResultSet rs = preparedStatement4.executeQuery()) {
                        if (!rs.next()) {
                            // No existen chats para este usuario, insertar nuevo chat
                            Conexion_mysql conexion = new Conexion_mysql();
                            try (Connection conn = conexion.getConnection()) {
                                conn.setAutoCommit(false);

                                try {
                                    // Insertar en la tabla Chat
                                    String consulta1 = "INSERT INTO Chat (creacion_chat) VALUES (NOW());";
                                    try (PreparedStatement pstmt1 = conn.prepareStatement(consulta1)) {
                                        pstmt1.executeUpdate();
                                    }

                                    // Obtener el último chat_id insertado
                                    String consulta_chat_id = "SELECT chat_id FROM Chat ORDER BY chat_id DESC LIMIT 1";
                                    int chatId = 0;
                                    try (PreparedStatement pstmt2 = conn.prepareStatement(consulta_chat_id);
                                         ResultSet rs1 = pstmt2.executeQuery()) {
                                        if (rs1.next()) {
                                            chatId = rs1.getInt("chat_id");
                                        }
                                    }

                                    if (chatId != 0) {
                                        // Insertar en la tabla Chat_usuarios
                                        String consulta12 = "INSERT INTO Chat_usuarios (chat_id, user_id) VALUES (?, ?), (?, ?);";
                                        try (PreparedStatement pstmt3 = conn.prepareStatement(consulta12)) {
                                            pstmt3.setInt(1, chatId);
                                            pstmt3.setInt(2, getIdUsuario());
                                            pstmt3.setInt(3, chatId);
                                            pstmt3.setInt(4, getIdusario());
                                            pstmt3.executeUpdate();
                                        }

                                        // Insertar en la tabla Mensaje
                                        String query45677 = "INSERT INTO Mensaje (chat_id, user_id, texto_mensaje) VALUES (?, ?, ?)";
                                        try (PreparedStatement stmt = conn.prepareStatement(query45677)) {
                                            stmt.setInt(1, chatId); // chat_id
                                            stmt.setInt(2, getIdUsuario()); // user_id
                                            stmt.setString(3, "NUEVO CHAT"); // texto_mensaje
                                            stmt.executeUpdate();
                                            
                                            
                                          
                                            stmt.setInt(1, chatId); // chat_id
                                            stmt.setInt(2, getIdusario()); // user_id
                                            stmt.setString(3, "NUEVO CHAT"); // texto_mensaje
                                            stmt.executeUpdate();
                                            
                                            
                                        }
                                        
                                        
                                        
                                    }

                                    // Confirmar la transacción
                                    conn.commit();
                                } catch (SQLException e1) {
                                    // Revertir la transacción en caso de error
                                    conn.rollback();
                                    e1.printStackTrace();
                                }
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            // El usuario ya tiene chats, verificar si hay chats compartidos
                            String queryChatsCompartidos = "SELECT c.chat_id, c.creacion_chat, u1.Nombre_USUARIO AS Usuario_1, u2.Nombre_USUARIO AS Usuario_2, m.texto_mensaje, m.hora_envio " +
                                                            "FROM Chat c " +
                                                            "JOIN Chat_usuarios cu1 ON c.chat_id = cu1.chat_id " +
                                                            "JOIN Usuarios u1 ON cu1.user_id = u1.ID_Usuarios " +
                                                            "JOIN Chat_usuarios cu2 ON c.chat_id = cu2.chat_id AND cu1.user_id != cu2.user_id " +
                                                            "JOIN Usuarios u2 ON cu2.user_id = u2.ID_Usuarios " +
                                                            "LEFT JOIN Mensaje m ON c.chat_id = m.chat_id " +
                                                            "WHERE cu1.user_id = ? " +
                                                            "ORDER BY c.chat_id, m.hora_envio";

                            try (PreparedStatement preparedStatementCompartidos = connect_90345.prepareStatement(queryChatsCompartidos)) {
                                preparedStatementCompartidos.setInt(1, getIdusario());
                       
                                try (ResultSet rsCompartidos = preparedStatementCompartidos.executeQuery()){
                                	          
								while (rsCompartidos.next()) {
                                       String nombreUserLogeado = rsCompartidos.getString("Usuario_1");
                                       if (!nombreUserLogeado.equals(gettextnombredelperfilbuscado())) {
                                           chatCompartidoEncontrado = false;
                                           break;
                                       }
                                   }
                                    if (!chatCompartidoEncontrado) {
                                        // No hay chats compartidos, realiza la lógica de inserción como antes
                                        Conexion_mysql conexion = new Conexion_mysql();
                                        try (Connection conn = conexion.getConnection()) {
                                            conn.setAutoCommit(false);

                                            try {
                                                // Insertar en la tabla Chat
                                                String consulta1 = "INSERT INTO Chat (creacion_chat) VALUES (NOW());";
                                                try (PreparedStatement pstmt1 = conn.prepareStatement(consulta1)) {
                                                    pstmt1.executeUpdate();
                                                }

                                                // Obtener el último chat_id insertado
                                                String consulta_chat_id = "SELECT chat_id FROM Chat ORDER BY chat_id DESC LIMIT 1";
                                                int chatId = 0;
                                                try (PreparedStatement pstmt2 = conn.prepareStatement(consulta_chat_id);
                                                     ResultSet rs1 = pstmt2.executeQuery()) {
                                                    if (rs1.next()) {
                                                        chatId = rs1.getInt("chat_id");
                                                    }
                                                }

                                                if (chatId != 0) {
                                                    // Insertar en la tabla Chat_usuarios
                                                    String consulta12 = "INSERT INTO Chat_usuarios (chat_id, user_id) VALUES (?, ?), (?, ?);";
                                                    try (PreparedStatement pstmt3 = conn.prepareStatement(consulta12)) {
                                                        pstmt3.setInt(1, chatId);
                                                        pstmt3.setInt(2, getIdUsuario());
                                                        pstmt3.setInt(3, chatId);
                                                        pstmt3.setInt(4, getIdusario());
                                                        pstmt3.executeUpdate();
                                                    }

                                                    // Insertar en la tabla Mensaje
                                                    String query45677 = "INSERT INTO Mensaje (chat_id, user_id, texto_mensaje) VALUES (?, ?, ?)";
                                                    try (PreparedStatement stmt = conn.prepareStatement(query45677)) {
                                                        stmt.setInt(1, chatId); // chat_id
                                                        stmt.setInt(2, getIdusario()); // user_id
                                                        stmt.setString(3, "NUEVO CHAT"); // texto_mensaje
                                                        stmt.executeUpdate();
                                                    }
                                                }

                                                // Confirmar la transacción
                                                conn.commit();
                                            } catch (SQLException e1) {
                                                // Revertir la transacción en caso de error
                                                conn.rollback();
                                                e1.printStackTrace();
                                            }
                                        } catch (SQLException e1) {
                                            e1.printStackTrace();
                                        }
                                    } 
                                }
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });


        
        
        
        btnNewButton_1.setForeground(Color.BLACK);
        btnNewButton_1.setBackground(Color.WHITE);
        btnNewButton_1.setBounds(298, 99, 146, 26);
        panel.add(btnNewButton_1);
        
    
        lblNewLabel_9.setBounds(530, 50, 46, 14);
        panel.add(lblNewLabel_9);

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
            /**
			 * 
			 */
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

        

        // Consulta SQL
        String query90 = "SELECT " +
                       "    u1.ID_Usuarios AS Usuario_ID_Seguidor, " +
                       "    u1.Nombre_USUARIO AS Nombre_Seguidor, " +
                       "    u2.ID_Usuarios AS Usuario_ID_Seguido, " +
                       "    u2.Nombre_USUARIO AS Nombre_Seguido " +
                       "FROM " +
                       "    Seguidos s " +
                       "JOIN " +
                       "    Usuarios u1 ON s.seguidor_id = u1.ID_Usuarios " +
                       "JOIN " +
                       "    Usuarios u2 ON s.seguido_id = u2.ID_Usuarios " +
                       "WHERE " +
                       "    u1.ID_Usuarios = ?"; // '?' es el marcador de posición para el ID del usuario

        Conexion_mysql conn12 = new Conexion_mysql();

        // Obtén la conexión
        try (Connection connection = conn12.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query90)) {

            // Establece el valor del marcador de posición
            pstmt.setInt(1, getIdusario());

            // Ejecuta la consulta
            try (ResultSet rs = pstmt.executeQuery()) {
                // Procesa los resultados
                while (rs.next()) {
                 
                	candidad_de_seguidors++;
                	lblNewLabel_9.setText(String.valueOf(candidad_de_seguidors));
                	lblNewLabel_9.setForeground(Color.BLUE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mostrarCantidadDePosts( getIdusario());
        
   
        
        setVisible(true);
    }

	 void settextnombredelperfilbuscado(String nombrePerfil2) {
		this.nombrePerfil2 =nombrePerfil2;
		
	}

	 String gettextnombredelperfilbuscado() {
		return nombrePerfil2;
	}
	
	
	
	

	public int getIdusario() {
		return idusario;
	}

	public void setIdusario(int idusario) {
		this.idusario = idusario;
	}
	
	public void mostrarCantidadDePosts(int idUsuario) {
	    // Consulta SQL
	    String query = "SELECT COUNT(*) AS cantidad_de_posts FROM post WHERE UsuarioID = ?"; // '?' es el marcador de posición para el ID del usuario

	    Conexion_mysql conn12 = new Conexion_mysql();

	    // Obtén la conexión
	    try (Connection connection = conn12.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(query)) {

	        // Establece el valor del marcador de posición
	        pstmt.setInt(1, idUsuario);

	        // Ejecuta la consulta
	        try (ResultSet rs = pstmt.executeQuery()) {
	            // Procesa los resultados
	            if (rs.next()) {
	                int cantidadDePosts = rs.getInt("cantidad_de_posts");
	                lblNewLabel_6.setText(String.valueOf(cantidadDePosts));
	                lblNewLabel_6.setForeground(Color.BLUE);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	// Getter para idCuenta
    public int getIdCuenta() {
        return idCuenta;
    }

    // Setter para idCuenta
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
 // Getter para idUsuario
    // di del usuario logeado
    public int getIdUsuario() {
        return idUsuario;
    }

    // Setter para idUsuario
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}