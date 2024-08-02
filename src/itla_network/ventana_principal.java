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
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.xdevapi.Statement;

import basedatos.Conexion_mysql;
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

public class ventana_principal {
	JInternalFrame internalFrame_2 = new JInternalFrame("Estados");
	JInternalFrame internalFrame_3 = new JInternalFrame("Chat");
	JInternalFrame internalFrame_11 = new JInternalFrame("Blog");

	Perfil miperfil = Perfil.getInstance();
    
   String nombre_user =miperfil.getNombre_Perfil();
	
	
	

  

	JButton btnNewButton_1 = new JButton("comentarios");
	JToggleButton tglbtnNewToggleButton = new JToggleButton("likes");
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
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

		

		internalFrame_11.setBounds(0, 0, 773, 216);
		panel_2.add(internalFrame_11);
		GroupLayout groupLayout = new GroupLayout(internalFrame_11.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 757, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 666, Short.MAX_VALUE)
		);
		internalFrame_11.getContentPane().setLayout(groupLayout);
		
		
		
		// no se muestran las imagenes dee forma descendente
		
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(230, 395, 107, 21);
		
		internalFrame_11.getContentPane().setLayout(new FlowLayout());
		
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (tglbtnNewToggleButton.isSelected()) {
				likes++;
			}else {
				likes--;
				
			}
				
				Conexion_mysql conexion_mysql = new Conexion_mysql();
				try {
					Connection conen = conexion_mysql.getConnection();
					PreparedStatement preparedStatement3 = conen.prepareStatement("insert into Likes(CuentaID, Cantidad_Like) values()"); // hay que esperar a hacer que la interfaz de registro
					preparedStatement3.executeUpdate();
					PreparedStatement preparedStatement = conen.prepareStatement("Update Likes set Cantidad_Like = '?' ");
					preparedStatement.setInt(1, likes);
					int coneResultSet = preparedStatement.executeUpdate();
					
				   if (coneResultSet>0) {
					   tglbtnNewToggleButton.setText("Likes " + coneResultSet);
				}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error de likes");
				}
			}
		});
		tglbtnNewToggleButton.setBounds(10, 395, 115, 21);
	
		
		
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
		internalFrame_3.setBounds(1005, 11, 342, 657);
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
		lblNewLabel.setBounds(10, 44, 202, 52);
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
				 //CARGAR LOS USUARIOS EN EL CHAT
				//CARGAR LOS USUARIOS EN EL CHAT
				Conexion_mysql conn = new Conexion_mysql();
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
				    // Supongamos que Conexion_mysql es una clase que gestiona la conexión a la base de datos
				    Connection coneConnection = conn.getConnection(); 

				    // Consulta SQL
				    String sql = "SELECT c.chat_id, " +
				                 "       u.ID_Usuarios AS user_id, " +
				                 "       u.Nombre_USUARIO AS nombre_usuario, " +
				                 "       m.texto_mensaje AS ultimo_mensaje, " +
				                 "       m.hora_envio AS ultima_hora_envio " +
				                 "FROM Chat_usuarios cu " +
				                 "JOIN Chat c ON cu.chat_id = c.chat_id " +
				                 "JOIN Mensaje m ON c.chat_id = m.chat_id " +
				                 "JOIN Usuarios u ON m.user_id = u.ID_Usuarios " +
				                 "WHERE cu.chat_id IN ( " +
				                 "    SELECT chat_id " +
				                 "    FROM Chat_usuarios " +
				                 "    WHERE user_id = ( " +
				                 "        SELECT ID_Usuarios " +
				                 "        FROM Usuarios " +
				                 "        WHERE Nombre_USUARIO = ? " +
				                 "    ) " +
				                 ") " +
				                 "AND m.hora_envio = ( " +
				                 "    SELECT MAX(hora_envio) " +
				                 "    FROM Mensaje " +
				                 "    WHERE chat_id = c.chat_id " +
				                 ") " +
				                 "ORDER BY c.chat_id, m.hora_envio DESC";

				    // Crear la consulta preparada
				    pstmt = coneConnection.prepareStatement(sql);
				    pstmt.setString(1, nombre_user); // Aquí puedes establecer el valor del parámetro user_id

				    // Ejecutar la consulta
				    rs = pstmt.executeQuery();

				    int yOffset = 11; // Inicial offset para la posición Y

				    // Procesar el resultado
				    while (rs.next()) {
				        int chatId = rs.getInt("chat_id");
				        String nombreUsuario = rs.getString("nombre_usuario");
				        String ultimoMensaje = rs.getString("ultimo_mensaje");

				        // Crear los componentes
				        JLabel lblNewLabel_2 = new JLabel();
				        JButton btnNombrePerfil = new JButton();
				        JTextField textField_1 = new JTextField();
				        JSeparator separator_3 = new JSeparator();

				        // Preparar la consulta para obtener la foto de perfil
				        String sql2 = "SELECT foto_Perfil, Nombre_PERFIL FROM Perfil WHERE id_perfil = ?";
				        PreparedStatement pstmt12 = null;
				        ResultSet rs3 = null;

				        try {
				            pstmt12 = coneConnection.prepareStatement(sql2);
				            pstmt12.setInt(1, chatId);

				            // Ejecutar la consulta
				            rs3 = pstmt12.executeQuery();

				            // Procesar los resultados
				            if (rs3.next()) {
				                byte[] fotoPerfil = rs3.getBytes("foto_Perfil");

				                if (fotoPerfil != null) {
				                    // Convertir el arreglo de bytes en una imagen
				                    ByteArrayInputStream is = new ByteArrayInputStream(fotoPerfil);
				                    Image image = ImageIO.read(is);

				                    // Ajustar la imagen al tamaño del JLabel
				                    Image scaledImage = image.getScaledInstance(77, 66, Image.SCALE_SMOOTH);
				                    // Establecer la imagen como el ícono del JLabel
				                    lblNewLabel_2.setIcon(new ImageIcon(scaledImage));
				                } else {
				                    // Si no hay foto de perfil, mostrar un ícono por defecto o dejar el JLabel vacío
				                    lblNewLabel_2.setIcon(null);
				                }

				                // Establecer el nombre del perfil en el JButton
				                btnNombrePerfil.setText(nombreUsuario);

				                // Configurar el JTextField con el último mensaje
				                textField_1.setText(ultimoMensaje);
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

				        } catch (Exception e1) {
				            e1.printStackTrace();
				        } finally {
				            // Cerrar recursos
				            try {
				                if (rs3 != null) rs3.close();
				                if (pstmt12 != null) pstmt12.close();
				            } catch (Exception e1) {
				                e1.printStackTrace();
				            }
				        }
				    }

				} catch (SQLException e1) {
				    e1.printStackTrace();
				} finally {
				    // Cerrar recursos
				    try {
				        if (rs != null) rs.close();
				        if (pstmt != null) pstmt.close();
				        if (conn != null) conn.close();
				    } catch (SQLException e1) {
				        e1.printStackTrace();
				    }
				}

						
		 }
						
		
				
				
			
		});
		
		  // Crear un JScrollPane y agregarle el JPanel
		JScrollPane scrollPane2 = new JScrollPane(panel_1);
		scrollPane2.setBounds(0, 0, 342, 657); // Ajustar el tamaño y la ubicación del JScrollPane
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
				perfil perfil = new perfil();
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
		
		btnNewButton_3_1_3.addActionListener(new ActionListener() {
					private int count;

					public void actionPerformed(ActionEvent e) {
						Conexion_mysql cone = new Conexion_mysql();
					
		     
		 try {
							Connection coneConnection = cone.getConnection(); 
							
							PreparedStatement consulPreparedStatement = coneConnection.prepareStatement("Select IMAGEN from post");
							 
							ResultSet resultadoResultSet = consulPreparedStatement.executeQuery();
							
							count = 0;
							
					        while (resultadoResultSet.next()) {
					        
								count++;
						
							java.sql.Blob imge=resultadoResultSet.getBlob("IMAGEN");
							if (imge!=null) {
								 byte[] pre = imge.getBytes(1, (int) imge.length());
						            
									
							
					           
				            	 BufferedImage imagenBufferedImage = null;
		
					           
					           
					            if (pre!= null && pre.length > 0) {
									
					            try {
					            	imagenBufferedImage = ImageIO.read(new ByteArrayInputStream(pre));
								} catch (IOException e2) {
									java.util.logging.Logger.getLogger(ventana_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, e2);
								}
					            JPanel panel_3 = new JPanel();
					            
					            
					            panel_3.setBounds(55, 35, 605, 426);
					    		panel_3.setLayout(null);
					    		panel_3.add(btnNewButton_1);	
					    		panel_3.add(tglbtnNewToggleButton);
					    		
					            
					            
					            ImageIcon nvlIcon = new ImageIcon(imagenBufferedImage);
					           
					           Icon imagenIcon = new ImageIcon(nvlIcon.getImage().getScaledInstance(panel_3.getWidth(), panel_3.getHeight(), Image.SCALE_SMOOTH));	
					           
					           JLabel lblNewLabel_1 = new JLabel(imagenIcon);
					           lblNewLabel_1.setSize(panel_3.getSize());
					           
					           panel_3.add(lblNewLabel_1);
					           panelesArrayList.add(panel_3);
					    	}
		
					    		  //JOptionPane.showMessageDialog(null, "funcional");
					    		
							}
					        
					    	}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
		
			//for (int i = 0 ; i >= panelesArrayList.size()-1; i--) {
				
			//}
			
				 internalFrame_11.getContentPane().add(panelesArrayList.get(5));
				 internalFrame_11.getContentPane().add(panelesArrayList.get(7));
				 
				 
					}
					
				});
		btnNewButton_3_1_3.setBounds(0, 32, 193, 38);
		
		btnNewButton_3_1_3.setBounds(0, 246, 193, 33);
		
		internalFrame1.getContentPane().add(btnNewButton_3_1_3);
		
		JButton btnNewButton_3_1_1_1 = new JButton("Publicacion");
		btnNewButton_3_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Publicacion nwePublicacion= new Publicacion();
				nwePublicacion.setVisible(true);
				
			}
			
		});
		btnNewButton_3_1_1_1.setBounds(0, 362, 193, 33);
		internalFrame1.getContentPane().add(btnNewButton_3_1_1_1);
		internalFrame1.setVisible(true);
		internalFrame_3.setVisible(true);
		internalFrame_2.setVisible(true);
		internalFrame_11.setVisible(true);
	}
}
