package itla_network;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Label;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.xdevapi.Statement;

import basedatos.Conexion_mysql;
import clases.Chat;
import clases.Perfil;

import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import java.awt.GridLayout;

public class ventana_principal {
	
	 int id_del_chat;
	  int a = 0;
	private int iduser_log;
	Set<Integer> processedChatIds = new HashSet<>();
	HashSet<Integer> processedUserIds = new HashSet<>();
	private int i1duser_log;
	JInternalFrame internalFrame_2 = new JInternalFrame("Estados");
	JInternalFrame internalFrame_3 = new JInternalFrame("Chat");

	JInternalFrame internalFrame_11 = new JInternalFrame("Blog");
    int chatId2 =0;
	JPanel panel_5 = new JPanel();
	int nose = -1;
	 int count = 50;
		Perfil miperfil = Perfil.getInstance();
		int id = -1;
	   String nombre_user =miperfil.getNombre_Perfil();
	   int likescount = 0;
	   ActionListener likesActionListener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JToggleButton source = (JToggleButton) e.getSource();
		        int postId = Integer.parseInt(source.getName()); // Obtener el ID del post del nombre del botón
		        Conexion_mysql conexion_mysql = new Conexion_mysql();
		        Connection conen = conexion_mysql.getConnection();
		        try {
		            // Obtener el ID del usuario
		            PreparedStatement preparedStatement4 = conen.prepareStatement("SELECT ID_Usuarios FROM Usuarios WHERE Nombre_USUARIO = ?");
		            preparedStatement4.setString(1, nombre_user);
		            ResultSet resultSet = preparedStatement4.executeQuery();

		            int userId = -1;
		            if (resultSet.next()) {
		                userId = resultSet.getInt("ID_Usuarios");
		            }

		            // Verificar si el usuario ya ha dado like al post
		            PreparedStatement preparedStatementCheck = conen.prepareStatement("SELECT * FROM Likes WHERE ID_Usuarios = ? AND PostID = ?");
		            preparedStatementCheck.setInt(1, userId);
		            preparedStatementCheck.setInt(2, postId);
		            ResultSet checkResultSet = preparedStatementCheck.executeQuery();

		            if (source.isSelected()) {
		                if (!checkResultSet.next()) {
		                    // Insertar nuevo like si no existe
		                    PreparedStatement preparedStatementInsert = conen.prepareStatement("INSERT INTO Likes(ID_Usuarios, Cantidad_Like, PostID) VALUES(?, 1, ?)");
		                    preparedStatementInsert.setInt(1, userId);
		                    preparedStatementInsert.setInt(2, postId);
		                    preparedStatementInsert.executeUpdate();
		                }
		            } else {
		                if (checkResultSet.next()) {
		                    // Eliminar el like si existe
		                    PreparedStatement preparedStatementDelete = conen.prepareStatement("DELETE FROM Likes WHERE ID_Usuarios = ? AND PostID = ?");
		                    preparedStatementDelete.setInt(1, userId);
		                    preparedStatementDelete.setInt(2, postId);
		                    preparedStatementDelete.executeUpdate();
		                }
		            }

		            // Obtener la cantidad total de likes para este post
		            PreparedStatement preparedStatement = conen.prepareStatement("SELECT COUNT(*) AS totalLikes FROM Likes WHERE PostID = ?");
		            preparedStatement.setInt(1, postId);
		            ResultSet cantidadResultSet = preparedStatement.executeQuery();

		            if (cantidadResultSet.next()) {
		                source.setText("Likes " + cantidadResultSet.getInt("totalLikes"));
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		};


	
   String nombreUsuario1 = null;
	
	ActionListener botonescomentariosActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ComentariosV frame = new ComentariosV();
			frame.setVisible(true);		
		}
	};

		

	
	ArrayList<JPanel>panelesArrayList =new ArrayList<JPanel>();
	int likes=0;
	JFrame frame;
	

	/**
	 * Launch the application. otro cambio
	 */
	

	/**....
	 * Create the application.
	 */
	public ventana_principal() {
		initialize();
		veerimages();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		internalFrame_3.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

		Conexion_mysql conn = new Conexion_mysql();
		
		
		
	    Perfil perfil = Perfil.getInstance();
        String nombre = perfil.getNombre_Perfil();
    	
        
        String query = "SELECT ID_Usuarios FROM Usuarios WHERE Nombre_USUARIO = ?";

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(query)){
             
        	// Asignar valor al parámetro de la consulta
        	stmt.setString(1, nombre);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Obtener id de usuario
            if (rs.next()) {
                int userID = rs.getInt("ID_Usuarios");
                setIduser_log(userID);
                setIdUserLoggedIn(userID);
  
            }

           } catch (SQLException e1) {
               e1.printStackTrace();
           }
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1369, 719);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		
		desktopPane_1.setBounds(296, 254, 1, 1);
		desktopPane.add(desktopPane_1);
		
		
		
		  // Agregar el JScrollPane al JInternalFrame
		

	    
	    
		JInternalFrame internalFrame = new JInternalFrame("");
		internalFrame.getContentPane().setBackground(new Color(0, 0, 0));
		internalFrame.getContentPane().setForeground(new Color(0, 0, 0));
		internalFrame.setBounds(10, 10, 209, 721);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 1395, 731);
		desktopPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 64));
		panel_2.setBounds(232, 133, 763, 540);
		panel.add(panel_2);
		panel_2.setLayout(null);
		

		JInternalFrame internalFrame_1 = new JInternalFrame("");
		internalFrame_1.getContentPane().setBackground(new Color(0, 0, 0));
		try {
			internalFrame_11.setMaximizable(true);
			internalFrame_11.setMaximum(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

		internalFrame_11.setBounds(0, 55, 763, 646);
		panel_2.add(internalFrame_11);
		
		
		internalFrame_11.getContentPane().setLayout(null);
		
		
		
		// no se muestran las imagenes dee forma descendente
		
		
		
		
		
		
		
		
	
		internalFrame_11.getContentPane().setLayout(null);
		

		panel_5.setBounds(1, 1, 736, 655);
		internalFrame_11.getContentPane().add(panel_5);

		panel_5.setBackground(Color.BLACK);
	    //panel_5.setBounds(54, 1, 686, 513); // Posición y tamaño iniciales

		// Ajustar el tamaño del panel usando Dimension
		panel_5.setPreferredSize(new Dimension(736, 6000));	
		//panel_5.setLayout(new BorderLayout());

		// Crear el JScrollPane con el panel como contenido
		JScrollPane scrollPane_3 = new JScrollPane(panel_5);
		panel_5.setLayout(new GridLayout(0,1));

		// Ajustar el tamaño y agregar el JScrollPane al internal frame
		scrollPane_3.setBounds(0, 0, 758, 513);	
		internalFrame_11.getContentPane().add(scrollPane_3, BorderLayout.CENTER);

		// Actualizar el internal frame
		internalFrame_11.revalidate();
		internalFrame_11.repaint();
	
		

		
		
		internalFrame_2.getContentPane().setBackground(new Color(240, 240, 240));
		internalFrame_2.setClosable(true);
		internalFrame_2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		internalFrame_2.setBounds(232, 10, 763, 113);
		panel.add(internalFrame_2);
		internalFrame_2.getContentPane().setLayout(null);
		

		JTextPane textPane = new JTextPane();
		textPane.setBounds(59, 31, 185, 28);
		internalFrame_2.getContentPane().add(textPane);
		

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(309, 71, 2, 2);
		internalFrame_2.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();

		scrollPane_2.setBounds(0, 0, 772, 83);

		scrollPane_2.setBounds(0, 69, 782, 12);

		internalFrame_2.getContentPane().add(scrollPane_2);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(316, 31, 195, 28);
		internalFrame_2.getContentPane().add(textPane_1);
		
		
		//----------------------------------------------------------------------------------------
		internalFrame_3.getContentPane().setBackground(new Color(0, 0, 0));
		internalFrame_3.setClosable(true);
		internalFrame_3.setBounds(1005, 10, 342, 662);
		panel.add(internalFrame_3);
		internalFrame_3.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(300, 1000));

		//----------------------------------------------------------------------------------------

		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(1066, 668, 437, 0);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 344, 82, 0);
		panel.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 113, 743, 10);
		panel.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("ITLA_NETWORK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		lblNewLabel.setForeground(UIManager.getColor("Button.background"));
		lblNewLabel.setBounds(10, 10, 212, 55);
		panel.add(lblNewLabel);
		
		JInternalFrame internalFrame1 = new JInternalFrame("Menú");
		internalFrame1.getContentPane().setBackground(Color.BLACK);
		internalFrame1.setBounds(10, 133, 210, 540);
		panel.add(internalFrame1);
		internalFrame1.getContentPane().setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscador buscador = new buscador();
				buscador.setVisible(true);
			}
		});
		
		btnBuscar.setBounds(0, 11, 193, 38);
		
		btnBuscar.setBounds(0, 90, 193, 30);
		
		internalFrame1.getContentPane().add(btnBuscar);
		
		JButton btnEstados = new JButton("Estados");
		btnEstados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			internalFrame_2.setVisible(true);
				
				
			}
		});
		
		btnEstados.setBounds(0, 123, 193, 38);
		
		btnEstados.setBounds(0, 130, 193, 30);
		
		internalFrame1.getContentPane().add(btnEstados);
		
		JButton btnChat = new JButton("Chat");
		btnChat.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent e) {
				
				
				internalFrame_3.setVisible(true);
				//-------------------------------------------------------------------
				// CARGAR LOS USUARIOS EN EL CHAT
				//-------------------------------------------------------------------
				
				
				
				
				String sql =  "SELECT " +
			            "    c.chat_id, " +
			            "    c.creacion_chat, " +
			            "    u.Nombre_USUARIO AS nombre_usuario, " +
			            "    m.texto_mensaje AS ultimo_mensaje, " +
			            "    m.hora_envio, " +
			            "    u.ID_Usuarios AS user_id " + // Agregar la columna user_id
			            "FROM " +
			            "    Chat c " +
			            "JOIN " +
			            "    Chat_usuarios cu ON c.chat_id = cu.chat_id " +
			            "LEFT JOIN " +
			            "    Mensaje m ON c.chat_id = m.chat_id " +
			            "    AND m.hora_envio = ( " +
			            "        SELECT MAX(hora_envio) " +
			            "        FROM Mensaje " +
			            "        WHERE chat_id = c.chat_id " +
			            "        AND user_id = ? " + // Parámetro para el ID del usuario
			            "    ) " +
			            "LEFT JOIN " +
			            "    Usuarios u ON m.user_id = u.ID_Usuarios " +
			            "WHERE " +
			            "    cu.user_id = ? " + // Parámetro para el ID del usuario
			            "GROUP BY " +
			            "    c.chat_id, c.creacion_chat, u.Nombre_USUARIO, m.texto_mensaje, m.hora_envio, u.ID_Usuarios " + // Agregar la columna user_id en GROUP BY
			            "ORDER BY " +
			            "    c.creacion_chat DESC";

				try (Connection coneConnection = conn.getConnection();
				     PreparedStatement pstmt = coneConnection.prepareStatement(sql)) {

				    pstmt.setInt(1, iduser_log); // Establecer el valor del parámetro user_id
				    pstmt.setInt(2, iduser_log); // Establecer el valor del parámetro user_id

				  
				    
				    try (ResultSet rs = pstmt.executeQuery()) {
				        int yOffset = 11; // Offset inicial para la posición Y
				        HashSet<Integer> processedChatIds = new HashSet<>(); // Usar HashSet para evitar duplicados
				        
				        while (rs.next()) {
				            int chatId = rs.getInt("chat_id");
				            
				            // Verificar si el chatId ya ha sido procesado
				            if (processedChatIds.contains(chatId)) {
				                continue; // Saltar este chatId si ya se ha procesado
				            }
				            
				            // Agregar el chatId procesado al HashSet
				            processedChatIds.add(chatId);

				           // int chatId = rs.getInt("chat_id");
				            int Id_user_chat = rs.getInt("user_id");
				            String ultimoMensaje = rs.getString("ultimo_mensaje");
				            // Crear los componentes
				            JLabel lblNewLabel_2 = new JLabel();
				            JButton btnNombrePerfil = new JButton();
				            JTextField textField_1 = new JTextField();
				            JSeparator separator_3 = new JSeparator();
				         // Actualizar la interfaz gráfica
				     
				            
				            btnNombrePerfil.addActionListener(new ActionListener() {
				                @Override
				                public void actionPerformed(ActionEvent e) {
				                    // Consulta SQL para obtener los IDs de los chats
				                    String sql = "SELECT cu.chat_id " +
				                                 "FROM Chat_usuarios cu " +
				                                 "JOIN Usuarios u ON cu.user_id = u.ID_Usuarios " +
				                                 "WHERE u.Nombre_USUARIO = ?";

				                    // Establece la conexión y ejecuta la consulta
				                    try (Connection coneConnection = conn.getConnection();
				                         PreparedStatement pstmt = coneConnection.prepareStatement(sql)) {

				                        // Actualiza nombreUsuario1 aquí para asegurarte de que se utiliza el valor correcto
				                        String query2 = "SELECT u.Nombre_USUARIO " +
				                                        "FROM Usuarios u " +
				                                        "JOIN Chat_usuarios cu ON u.ID_Usuarios = cu.user_id " +
				                                        "WHERE cu.chat_id = ? " +
				                                        "AND u.Nombre_USUARIO != ?";

				                        try (PreparedStatement stmt = conn.getConnection().prepareStatement(query2)) {
				                            stmt.setInt(1, chatId);
				                            stmt.setString(2, nombre);
				                            
				                            try (ResultSet resultSet = stmt.executeQuery()) {
				                                if (resultSet.next()) {
				                                    nombreUsuario1 = resultSet.getString("Nombre_USUARIO");
				                                    
				                                }
				                            }
				                        } catch (SQLException e1) {
				                            e1.printStackTrace();
				                        }

				                        // Establece el parámetro de la consulta
				                        pstmt.setString(1, nombreUsuario1);

				                        // Ejecuta la consulta
				                        try (ResultSet rs = pstmt.executeQuery()) {
				                            // Procesa los resultados
				                            while (rs.next()) {
				                                chatId2 = rs.getInt("chat_id");
				                            }
				                        }

				                    } catch (SQLException e1) {
				                        e1.printStackTrace();
				                    }

				                    // Asegúrate de pasar nombreUsuario1 actualizado
				                  
				                    int i = obtenerChatIdPorNombreUsuario(nombreUsuario1);
				                    chat_principal chat_principal = new chat_principal(nombreUsuario1, chatId2);
				                    chat_principal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				                    chat_principal.setVisible(true);

				                    Chat char_priv = new Chat();
				                    char_priv.setNombre_Chat(nombreUsuario1);
				                    char_priv.setid_chat(chatId);
				                    char_priv.setId_user_chat(Id_user_chat);
				                }
				            });

				            //--------------------------------------


				       
				            
				            
				            
				            
				            
				            
				            
				            
				            String query2 = "SELECT u.Nombre_USUARIO " +
				                    "FROM Usuarios u " +
				                    "JOIN Chat_usuarios cu ON u.ID_Usuarios = cu.user_id " +
				                    "WHERE cu.chat_id = ? " +
				                    "AND u.Nombre_USUARIO != ?";

				    try (PreparedStatement stmt = conn.getConnection().prepareStatement(query2)) {
				        stmt.setInt(1, chatId);
				        stmt.setString(2, nombre);
				        
				        try (ResultSet resultSet = stmt.executeQuery()) {
				            if (resultSet.next()) {
				                nombreUsuario1 = resultSet.getString("Nombre_USUARIO");
				            }
				        }
				    } catch (SQLException e1) {
				        e1.printStackTrace();
				    }

				            //--------------------------------------

				            // Preparar la consulta para obtener la foto de perfil
				            String sql2 = "SELECT foto_Perfil, Nombre_PERFIL FROM Perfil WHERE id_perfil = ?";

				            try (PreparedStatement pstmt12 = coneConnection.prepareStatement(sql2)) {
				                pstmt12.setInt(1, chatId);

				                try (ResultSet rs3 = pstmt12.executeQuery()) {
				                    if (rs3.next()) {
				                        byte[] fotoPerfil = rs3.getBytes("foto_Perfil");

				                        if (fotoPerfil != null) {
				                            ByteArrayInputStream is = new ByteArrayInputStream(fotoPerfil);
				                            Image image = ImageIO.read(is);

				                            // Ajustar la imagen al tamaño del JLabel
				                            Image scaledImage = image.getScaledInstance(77, 66, Image.SCALE_SMOOTH);
				                            lblNewLabel_2.setIcon(new ImageIcon(scaledImage));
				                        } else {
				                            lblNewLabel_2.setIcon(null);
				                        }

				                        btnNombrePerfil.setText(nombreUsuario1);
				                        textField_1.setText(ultimoMensaje);
				                    }
				                }
				            } catch (Exception e1) {
				                e1.printStackTrace();
				            }

				            // Configurar los componentes
				            lblNewLabel_2.setForeground(Color.WHITE);
				            lblNewLabel_2.setBackground(Color.WHITE);
				            lblNewLabel_2.setBounds(10, yOffset, 77, 66);
				            panel_1.add(lblNewLabel_2);

				            btnNombrePerfil.setBounds(92, yOffset, 150, 30); // Ajustar posición y tamaño según sea necesario
				            panel_1.add(btnNombrePerfil);

				            textField_1.setForeground(Color.WHITE); // Letra blanca
				            textField_1.setBackground(Color.BLACK); // Fondo negro
				            textField_1.setBorder(null); // Sin borde
				            textField_1.setEditable(false); // No editable
				            textField_1.setBounds(92, yOffset + 46, 224, 20);
				            panel_1.add(textField_1);
				            textField_1.setColumns(10);

				            separator_3.setBounds(10, yOffset + 85, 306, 2);
				            panel_1.add(separator_3);

				            // Incrementar el offset para la siguiente iteración
				            yOffset += 96; // Ajustar según sea necesario para el espaciado entre elementos
				            
				            
				        }
				    } catch (SQLException e1) {
				        e1.printStackTrace();
				    }
				} catch (SQLException e1) {
				    e1.printStackTrace();
				}

				
				
				
}

			
				
			
		});
		

		// Añadir el evento al botón
	
		
		
		
		  // Crear un JScrollPane y agregarle el JPanel
		JScrollPane scrollPane2 = new JScrollPane(panel_1);
		scrollPane2.setBounds(0, 0, 336, 657); // Ajustar el tamaño y la ubicación del JScrollPane
		internalFrame_3.getContentPane().add(scrollPane2);
		// Agregar el JScrollPane al internal frame
		internalFrame_3.revalidate();
		internalFrame_3.repaint();
		
		
	
		  
			
	    
	
		
		btnChat.setBounds(0, 164, 193, 36);//s
		btnChat.setBounds(0, 170, 193, 30);
		
		internalFrame1.getContentPane().add(btnChat);
		
		JButton btnNewButton_3_1 = new JButton("Notificaciones");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				notificaciones notificaciones = new notificaciones();
				notificaciones.setVisible(true);
			}
		});
		
		btnNewButton_3_1.setBounds(0, 210, 193, 38);
		
		btnNewButton_3_1.setBounds(0, 206, 193, 30);
		
		internalFrame1.getContentPane().add(btnNewButton_3_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 306, 193, 2);
		internalFrame1.getContentPane().add(separator_2);
		
		JButton btnNewButton_3_1_1 = new JButton("Perfil");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				perfil perfil = new perfil(getIduser_log());
				perfil.setVisible(true);
			}
		});
		
		btnNewButton_3_1_1.setBounds(0, 402, 193, 36);
		
		btnNewButton_3_1_1.setBounds(0, 318, 193, 33);
		
		internalFrame1.getContentPane().add(btnNewButton_3_1_1);
		
		JButton btnNewButton_3_1_2 = new JButton("Configuracion");
		
		btnNewButton_3_1_2.setBounds(0, 630, 193, 38);
		
		btnNewButton_3_1_2.setBounds(0, 635, 193, 33);
		
		internalFrame1.getContentPane().add(btnNewButton_3_1_2);
		
		JButton btnNewButton_3_1_3 = new JButton("Inicio");
		
		
		

		
		
				 		 
					
		btnNewButton_3_1_3.setBounds(0, 32, 193, 38);
		
		btnNewButton_3_1_3.setBounds(0, 246, 193, 33);
		
		internalFrame1.getContentPane().add(btnNewButton_3_1_3);
		
		JButton btnNewButton_3_1_1_1 = new JButton("Publicacion");
		btnNewButton_3_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Publicacion nwePublicacion= new Publicacion();
				nwePublicacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				nwePublicacion.setVisible(true);
				
			}
			
		});
		btnNewButton_3_1_1_1.setBounds(0, 362, 193, 33);
		internalFrame1.getContentPane().add(btnNewButton_3_1_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("logo (3) (1).png"));
		lblNewLabel_3.setBounds(10, 76, 64, 47);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("New label");
		lblNewLabel_3_1.setIcon(new ImageIcon("logo (4) (1).png"));
		lblNewLabel_3_1.setBounds(172, 76, 50, 47);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("New label");
		lblNewLabel_3_2.setIcon(new ImageIcon("logo (2) (1).png"));
		lblNewLabel_3_2.setBounds(84, 76, 64, 47);
		panel.add(lblNewLabel_3_2);
		internalFrame1.setVisible(true);
		internalFrame_3.setVisible(true);
		internalFrame_2.setVisible(true);
		internalFrame_11.setVisible(true);
	}
	
	
	public void veerimages() {		
		Conexion_mysql cone = new Conexion_mysql();
		
		try {
			Connection coneConnection = cone.getConnection(); 
			PreparedStatement consulPreparedStatement2 = coneConnection.prepareStatement("Select ID_Usuarios from Usuarios where Nombre_USUARIO = ?");
			consulPreparedStatement2.setString(1, nombre_user);
			ResultSet resultadoResultSet2 = consulPreparedStatement2.executeQuery();
			int resultado = -1;
			if (resultadoResultSet2.next()) {
				resultado = resultadoResultSet2.getInt("ID_Usuarios");
			}
			
			PreparedStatement consulPreparedStatement = coneConnection.prepareStatement("Select ID_post, IMAGEN, DESCRIPCION from post where UsuarioID = ?");
			consulPreparedStatement.setInt(1, resultado);
			 
			ResultSet resultadoResultSet = consulPreparedStatement.executeQuery();
			
			while (resultadoResultSet.next()) {
				int postId = resultadoResultSet.getInt("ID_post"); // Obtener el ID del post
				java.sql.Blob imge = resultadoResultSet.getBlob("IMAGEN");
				String descripcionString = resultadoResultSet.getString("DESCRIPCION");
				
				if (imge != null) {
					byte[] pre = imge.getBytes(1, (int) imge.length());
					BufferedImage imagenBufferedImage = null;

					if (pre != null && pre.length > 0) {
						try {
							imagenBufferedImage = ImageIO.read(new ByteArrayInputStream(pre));
						} catch (IOException e2) {
							java.util.logging.Logger.getLogger(ventana_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, e2);
						}
						
						JPanel panel_3 = new JPanel();
						JLabel otroLabel = new JLabel(nombre_user);
						JLabel otroLabel2 = new JLabel(descripcionString);
						JButton btnNewButton_1 = new JButton("comentarios");
						btnNewButton_1.addActionListener(botonescomentariosActionListener);
						
						JToggleButton tglbtnNewToggleButton = new JToggleButton("likes 0");
						tglbtnNewToggleButton.setName(String.valueOf(postId)); // Asociar el ID del post al botón
						tglbtnNewToggleButton.addActionListener(likesActionListener);
						
						panel_3.setBounds(55, 0, 605, 426);
						panel_3.setLayout(null);
						
						ImageIcon nvlIcon = new ImageIcon(imagenBufferedImage);
						Icon imagenIcon = new ImageIcon(nvlIcon.getImage().getScaledInstance(470, 300, Image.SCALE_SMOOTH));	
						panel_3.add(btnNewButton_1);	
						btnNewButton_1.setBounds(300, 370, 240, 32);
						otroLabel.setSize(btnNewButton_1.getSize());
						otroLabel2.setBounds(0, 10, btnNewButton_1.getWidth(), btnNewButton_1.getHeight());
						
						panel_3.add(tglbtnNewToggleButton);
						tglbtnNewToggleButton.setBounds(65, 370, 240, 32);
						JLabel lblNewLabel_1 = new JLabel(imagenIcon);
						lblNewLabel_1.setSize(panel_3.getSize());
						panel_3.add(otroLabel);
						panel_3.add(otroLabel2);
						panel_3.add(lblNewLabel_1);
						panel_5.add(panel_3);
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private int obtenerChatIdPorNombreUsuario(String nombreUsuario) {
	    String sql = "SELECT cu.chat_id " +
	                 "FROM Chat_usuarios cu " +
	                 "JOIN Usuarios u ON cu.user_id = u.ID_Usuarios " +
	                 "WHERE u.Nombre_USUARIO = ?";

	    try (Connection connection = new Conexion_mysql().getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        pstmt.setString(1, nombreUsuario);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("chat_id");
	            } else {
	                // No se encontró el chat_id para el usuario dado
	                return -1; // O algún otro valor que indique que no se encontró el chat_id
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1; // Retorna un valor indicando que ocurrió un error
	    }
	}

	// Getter para iduser_log
	public int getIduser_log() {
	    return i1duser_log;
	}
	private void setIdUserLoggedIn(int userID) {
		this.iduser_log = userID;
		
	}
	// Setter para iduser_log
	public void setIduser_log(int iduser_log) {
	    this.i1duser_log = iduser_log;
	}
	// Getter
	public int getIdDelChat() {
	    return id_del_chat;
	}

	// Setter
	public void setIdDelChat(int id_del_chat) {
	    this.id_del_chat = id_del_chat;
	}

}
