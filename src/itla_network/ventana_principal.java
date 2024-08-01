package itla_network;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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


import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ventana_principal {
	JInternalFrame internalFrame_2 = new JInternalFrame("Estados");
	JInternalFrame internalFrame_3 = new JInternalFrame("Chat");
	JInternalFrame internalFrame_11 = new JInternalFrame("Blog");
	
	JButton btnNewButton_1 = new JButton("comentarios");
	JToggleButton tglbtnNewToggleButton = new JToggleButton("likes");
	ArrayList<JPanel>panelesArrayList =new ArrayList<JPanel>();
	int likes=0;
	JFrame frame;
	private JTextField textField_1;
	

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
		frame.setBounds(100, 100, 1369, 778);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(296, 254, 1, 1);
		desktopPane.add(desktopPane_1);
		

		JInternalFrame internalFrame = new JInternalFrame("");
		internalFrame.getContentPane().setBackground(new Color(0, 0, 0));
		internalFrame.getContentPane().setForeground(new Color(0, 0, 0));
		internalFrame.setBounds(10, 10, 209, 721);

		JInternalFrame internalFrame1 = new JInternalFrame("Menú");
		internalFrame1.setBounds(10, 10, 199, 721);
		desktopPane.add(internalFrame1);
		internalFrame1.getContentPane().setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscador buscador = new buscador();
				buscador.setVisible(true);
			}
		});

		btnBuscar.setBounds(0, 75, 193, 38);

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
			}
		});

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
		separator_2.setBounds(0, 306, 173, 2);
		internalFrame1.getContentPane().add(separator_2);
		
		JButton btnNewButton_3_1_1 = new JButton("Perfil");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perfil perfil = new perfil();
				perfil.setVisible(true);
			}
		});

		btnNewButton_3_1_1.setBounds(0, 318, 193, 36);

		btnNewButton_3_1_1.setBounds(0, 318, 193, 33);

		internalFrame1.getContentPane().add(btnNewButton_3_1_1);
		
		JButton btnNewButton_3_1_2 = new JButton("Configuracion");

		btnNewButton_3_1_2.setBounds(0, 630, 193, 38);

		btnNewButton_3_1_2.setBounds(0, 635, 193, 33);

		internalFrame1.getContentPane().add(btnNewButton_3_1_2);
		
		JButton btnNewButton_3_1_3 = new JButton("Inicio");

		btnNewButton_3_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion_mysql cone = new Conexion_mysql();
			
     
 try {
					Connection coneConnection = cone.getConnection(); 
					
					PreparedStatement consulPreparedStatement = coneConnection.prepareStatement("Select IMAGEN from post");
					 
					ResultSet resultadoResultSet = consulPreparedStatement.executeQuery();
					
					int count =0;
					
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

			    		  JOptionPane.showMessageDialog(null, "funcional");
			    		
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
		btnNewButton_3_1_3.setBounds(0, 258, 193, 38);

		btnNewButton_3_1_3.setBounds(0, 246, 193, 33);

		internalFrame1.getContentPane().add(btnNewButton_3_1_3);
		
		JLabel lblNewLabel = new JLabel("ITLA NETWORK");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(37, 10, 146, 30);
		internalFrame1.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_3_1_1_1 = new JButton("Publicacion");
		btnNewButton_3_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Publicacion nwePublicacion= new Publicacion();
				nwePublicacion.setVisible(true);
				
			}
			
		});
		btnNewButton_3_1_1_1.setBounds(0, 370, 193, 33);
		internalFrame1.getContentPane().add(btnNewButton_3_1_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 1395, 731);
		desktopPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 64));
		panel_2.setBounds(222, 133, 773, 613);
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

		

		internalFrame_11.setBounds(10, 10, 699, 504);
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
		internalFrame_2.setBounds(222, 10, 773, 113);
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
		

		JPanel panel_4 = new JPanel();
		scrollPane_2.setViewportView(panel_4);
		panel_4.setBackground(new Color(0, 0, 0));
		panel_4.setLayout(null);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(316, 31, 195, 28);
		internalFrame_2.getContentPane().add(textPane_1);

		
		JTextPane textPane_1_1 = new JTextPane();
		textPane_1_1.setBounds(92, 28, 154, 34);
		panel_4.add(textPane_1_1);
		
		JTextPane textPane_1_1_1 = new JTextPane();
		textPane_1_1_1.setBounds(345, 28, 154, 34);
		panel_4.add(textPane_1_1_1);
		
		JTextPane textPane_1_1_2 = new JTextPane();
		textPane_1_1_2.setBounds(594, 28, 154, 34);
		panel_4.add(textPane_1_1_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sin foto de perfil");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBackground(new Color(240, 240, 240));
		lblNewLabel_3.setBounds(22, 21, 72, 52);
		panel_4.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Sin foto de perfil");
		lblNewLabel_3_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_1.setBounds(276, 21, 72, 52);
		panel_4.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Sin foto de perfil");
		lblNewLabel_3_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_2.setBounds(521, 21, 72, 52);
		panel_4.add(lblNewLabel_3_2);
		
		internalFrame_3.getContentPane().setBackground(new Color(0, 0, 0));
		internalFrame_3.setClosable(true);
		internalFrame_3.setBounds(1005, 11, 342, 711);
		panel.add(internalFrame_3);
		internalFrame_3.getContentPane().setLayout(null);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setForeground(Color.WHITE);
		panel_3_1.setBackground(new Color(0, 0, 64));
		panel_3_1.setBounds(0, 0, 353, 37);
		internalFrame_3.getContentPane().add(panel_3_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("fulanito de tal");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBackground(Color.BLUE);
		lblNewLabel_1_1.setBounds(0, 0, 353, 38);
		panel_3_1.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(0, 644, 366, 37);
		internalFrame_3.getContentPane().add(textField_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(1066, 668, 437, 0);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 344, 82, 0);
		panel.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 113, 743, 10);
		panel.add(scrollPane);
		internalFrame_3.setVisible(true);
		internalFrame_2.setVisible(true);
		internalFrame_11.setVisible(true);
		internalFrame1.setVisible(true);
	}
}
