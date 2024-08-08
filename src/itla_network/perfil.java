package itla_network;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import basedatos.Conexion_mysql;
import clases.seguidos;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class perfil extends JFrame {

    private static final long serialVersionUID1 = 1L;
    private JPanel contentPane;
    private int idusario;
    private editarperfil editarPerfil = new editarperfil();
    private JLabel lblNewLabel_5 = new JLabel("Nombre");
    private JTextArea txtrDescripcionDeLa = new JTextArea();
    JPanel panel_3_8 = new JPanel();
     int posicionador=0;
     JLabel lblNewLabel_9_3 = new JLabel("");
     JLabel lblNewLabel_9_2 = new JLabel("");
     JLabel lblNewLabel_9_4 = new JLabel("");
     JLabel lblNewLabel_9_1 = new JLabel("");
     JLabel lblNewLabel_9_8 = new JLabel("");
     JLabel lblNewLabel_9_7 = new JLabel("");        
     JLabel lblNewLabel_9 = new JLabel("");
     JLabel lblNewLabel_9_6 = new JLabel("");
     JLabel lblNewLabel_9_5 = new JLabel("");



    public static void main(String[] args) {
        perfil ne = new perfil(1);
        ne.setVisible(true);
    }

    public perfil(int idusario) {
    	 AGRAGARIMG();
     //  setIdusario(idusario);
        Conexion_mysql conn = new Conexion_mysql();
        String sql = "SELECT p.Nombre_PERFIL AS Nombre_Perfil, " +
                     "p.foto_Perfil AS Foto_Perfil, " +
                     "p.Biografia AS Biografia_Perfil " +
                     "FROM Usuarios u " +
                     "JOIN Perfil p ON u.ID_Usuarios = p.id_perfil " +
                     "WHERE u.ID_Usuarios = ?";

        try (PreparedStatement pstmt = conn.getConnection().prepareStatement(sql)) {
           //pstmt.setInt(1, getIdusario());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nombrePerfil = rs.getString("Nombre_Perfil");
                    byte[] fotoPerfil = rs.getBytes("Foto_Perfil");
                    String biografia = rs.getString("Biografia_Perfil");

                    lblNewLabel_5.setText(nombrePerfil);
                    txtrDescripcionDeLa.setEnabled(false);
                    txtrDescripcionDeLa.setEditable(false);

                    if (biografia == null) {
                        txtrDescripcionDeLa.setText("Descripcion de la cuenta....");
                        txtrDescripcionDeLa.setForeground(Color.WHITE);
                    } else {
                        txtrDescripcionDeLa.setText(biografia);
                        txtrDescripcionDeLa.setForeground(Color.WHITE);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 622, 847);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0, 0, 0));
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Editar");
        mnNewMenu.setForeground(new Color(255, 255, 255));
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Editar perfil");
        mntmNewMenuItem_1.setForeground(new Color(255, 255, 255));
        mntmNewMenuItem_1.setBackground(new Color(0, 0, 0));

        mntmNewMenuItem_1.addActionListener(e -> {
            editarPerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            editarPerfil.setVisible(true);
        });

        mnNewMenu.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Editar Cuenta");
        mntmNewMenuItem_2.setForeground(new Color(255, 255, 255));
        mntmNewMenuItem_2.setBackground(new Color(0, 0, 0));
        mnNewMenu.add(mntmNewMenuItem_2);

        JMenu mnNewMenu_1 = new JMenu("SEGUIDOS");
        mnNewMenu_1.setForeground(Color.WHITE);
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Ver seguidores");
        mntmNewMenuItem_3.setForeground(Color.WHITE);
        mntmNewMenuItem_3.setBackground(Color.BLACK);
        mnNewMenu_1.add(mntmNewMenuItem_3);
        mntmNewMenuItem_3.addActionListener(e -> {
            String nombreuser = lblNewLabel_5.getText();
            seguidos seguidos = new seguidos();
            List<String> listaSeguidores = seguidos.obtenerSeguidores();

            mostartuseguidores dialog = new mostartuseguidores(listaSeguidores);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
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
        lblNewLabel_1.setBounds(194, 46, 94, 22);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("seguidores");
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(333, 47, 94, 20);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Sin foto de perfil");
        lblNewLabel_3.setForeground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
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
        panel_1.setBounds(10, 217, 586, 691);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        JPanel panel_3 = new JPanel();
     
        panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3.setBounds(10, 10, 174, 168);
        panel_1.add(panel_3);
        panel_3.setLayout(null);
        lblNewLabel_9_8.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		JLabel source = (JLabel) e.getSource();
       		 int postId = Integer.parseInt(source.getName());
       		 mostrarelpost verimgMostrarelpost =new mostrarelpost(postId);
       		 verimgMostrarelpost.setVisible(true);
        	}
        });
        
        lblNewLabel_9_8.setBounds(10, 10, 154, 152);
        panel_3.add(lblNewLabel_9_8);
        
        JPanel panel_3_1 = new JPanel();
       
        panel_3_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3_1.setBounds(204, 10, 174, 168);
        panel_1.add(panel_3_1);
        panel_3_1.setLayout(null);
        
        lblNewLabel_9_7.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		JLabel source = (JLabel) e.getSource();
       		 int postId = Integer.parseInt(source.getName());
       		 mostrarelpost verimgMostrarelpost =new mostrarelpost(idusario);
       		 verimgMostrarelpost.setVisible(true);
        	}
        });
        lblNewLabel_9_7.setBounds(10, 10, 154, 152);
        panel_3_1.add(lblNewLabel_9_7);
        
        JPanel panel_3_2 = new JPanel();
      
        panel_3_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3_2.setBounds(402, 10, 174, 168);
        panel_1.add(panel_3_2);
        panel_3_2.setLayout(null);
        lblNewLabel_9_6.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		
        		JLabel source = (JLabel) e.getSource();
       		 int postId = Integer.parseInt(source.getName());
       		 mostrarelpost verimgMostrarelpost =new mostrarelpost(postId);
       		 verimgMostrarelpost.setVisible(true);
        	}
        });
        
        lblNewLabel_9_6.setBounds(10, 10, 154, 152);
        panel_3_2.add(lblNewLabel_9_6);
        
        JPanel panel_3_3 = new JPanel();
      
        panel_3_3.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3_3.setBounds(10, 201, 174, 168);
        panel_1.add(panel_3_3);
        panel_3_3.setLayout(null);
        lblNewLabel_9_5.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		
        		JLabel source = (JLabel) e.getSource();
       		 int postId = Integer.parseInt(source.getName());
       		 mostrarelpost verimgMostrarelpost =new mostrarelpost(postId);
       		 verimgMostrarelpost.setVisible(true);
        	}
        });
        
        lblNewLabel_9_5.setBounds(10, 10, 154, 152);
        panel_3_3.add(lblNewLabel_9_5);
        
        JPanel panel_3_4 = new JPanel();
      
        panel_3_4.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3_4.setBounds(204, 201, 174, 168);
        panel_1.add(panel_3_4);
        panel_3_4.setLayout(null);
        lblNewLabel_9_4.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		
        		
        		JLabel source = (JLabel) e.getSource();
       		 int postId = Integer.parseInt(source.getName());
       		 mostrarelpost verimgMostrarelpost =new mostrarelpost(postId);
       		 verimgMostrarelpost.setVisible(true);
        	}
        });
        
        lblNewLabel_9_4.setBounds(10, 10, 154, 152);
        panel_3_4.add(lblNewLabel_9_4);
        
        JPanel panel_3_5 = new JPanel();
     
        panel_3_5.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3_5.setBounds(402, 201, 174, 168);
        panel_1.add(panel_3_5);
        panel_3_5.setLayout(null);
        lblNewLabel_9_3.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		JLabel source = (JLabel) e.getSource();
       		 int postId = Integer.parseInt(source.getName());
       		 mostrarelpost verimgMostrarelpost =new mostrarelpost(postId);
       		 verimgMostrarelpost.setVisible(true);
        	}
        });
       
        
        lblNewLabel_9_3.setBounds(10, 10, 154, 152);
        panel_3_5.add(lblNewLabel_9_3);
        
        JPanel panel_3_6 = new JPanel();
       
        panel_3_6.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3_6.setBounds(10, 396, 174, 168);
        panel_1.add(panel_3_6);
        panel_3_6.setLayout(null);
        lblNewLabel_9_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		JLabel source = (JLabel) e.getSource();
       		 int postId = Integer.parseInt(source.getName());
       		 mostrarelpost verimgMostrarelpost =new mostrarelpost(postId);
       		 verimgMostrarelpost.setVisible(true);
        	}
        });
        
        lblNewLabel_9_2.setBounds(10, 10, 154, 152);
        panel_3_6.add(lblNewLabel_9_2);
        
        JPanel panel_3_7 = new JPanel();
        
        panel_3_7.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3_7.setBounds(204, 396, 174, 168);
        panel_1.add(panel_3_7);
        panel_3_7.setLayout(null);
        lblNewLabel_9_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		
        		JLabel source = (JLabel) e.getSource();
       		 int postId = Integer.parseInt(source.getName());
       		 mostrarelpost verimgMostrarelpost =new mostrarelpost(idusario);
       		 verimgMostrarelpost.setVisible(true);
        	}
        });
       
        
        lblNewLabel_9_1.setBounds(10, 10, 154, 152);
        panel_3_7.add(lblNewLabel_9_1);
        
  
        panel_3_8.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_3_8.setBounds(402, 396, 174, 168);
        panel_1.add(panel_3_8);
        panel_3_8.setLayout(null);
        
        JLabel lblNewLabel_9 = new JLabel("");
        lblNewLabel_9.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		
        		JLabel source = (JLabel) e.getSource();
       		 int postId = Integer.parseInt(source.getName());
       		 mostrarelpost verimgMostrarelpost =new mostrarelpost(postId);
       		 verimgMostrarelpost.setVisible(true);
        		
        	};
        });
        lblNewLabel_9.setBounds(10, 6, 154, 152);
        panel_3_8.add(lblNewLabel_9);
        
     /*   DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][] {
            	
            	
            	{null, null, null, null},
            	{null, null, null, null},
            	{null, null, null, null},
            },
            new String[] { "","","",""} // Solo una columna para las imágenes
        ) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false; // Las celdas no son editables
            }
        };*/

    }

   public void AGRAGARIMG() {
        Conexion_mysql conexion = new Conexion_mysql();
        try {
            Connection coneshowConexion = conexion.getConnection();
            PreparedStatement bin = coneshowConexion.prepareStatement("SELECT * FROM post WHERE UsuarioID = ?");                                           // OJO AQUI SOLO ES DE PRUEBA EL 7 SE DEBE CAMBIAR POR UNA VARIABLE
            bin.setInt(1, 7); //   QUITA EL 7 Y PON EL ID                                                                                                         AQUI VA EL ID QUE SE EXTRAE DESDE EL BONTON PEFIL EN EL INCIO EN VEZ DE  7 VA ESTA
            ResultSet blb = bin.executeQuery();

         

            while (blb.next()) {
            	  posicionador++;
                Blob leerbinario = blb.getBlob("IMAGEN");
                int id = blb.getInt("ID_post");

                byte[] pasarIMgBytes = leerbinario.getBytes(1, (int) leerbinario.length());
                BufferedImage imagenBufferedImage = null;

                try {
                    imagenBufferedImage = ImageIO.read(new ByteArrayInputStream(pasarIMgBytes));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

                if (imagenBufferedImage != null) {
                    ImageIcon imageIcon = new ImageIcon(imagenBufferedImage);
                   Image scaledImage = imageIcon.getImage().getScaledInstance(154, 152, Image.SCALE_SMOOTH);
                   ImageIcon scaledIcon = new ImageIcon(scaledImage);
                 
                   if (posicionador==1) {
                	   lblNewLabel_9.setIcon(scaledIcon);
                	   lblNewLabel_9.setName(String.valueOf(id)); // Asociar el ID del post al botón

                	   
				}
                   
                   if (posicionador==2) {
                	   lblNewLabel_9_1.setIcon(scaledIcon);
                	   lblNewLabel_9_1.setName(String.valueOf(id)); // Asociar el ID del post al botón

				}

                   if (posicionador==3) {
                	   lblNewLabel_9_2.setIcon(scaledIcon);
                	   lblNewLabel_9_2.setName(String.valueOf(id)); // Asociar el ID del post al botón

				}

                   if (posicionador==4) {
                	   lblNewLabel_9_3.setIcon(scaledIcon);
                	   lblNewLabel_9_3.setName(String.valueOf(id)); // Asociar el ID del post al botón

				}

                   if (posicionador==5) {
                	   lblNewLabel_9_4.setIcon(scaledIcon);
                	   lblNewLabel_9_4.setName(String.valueOf(id)); // Asociar el ID del post al botón

				}

                   if (posicionador==6) {
                	   lblNewLabel_9_5.setIcon(scaledIcon);
                	   lblNewLabel_9_5.setName(String.valueOf(id)); // Asociar el ID del post al botón

				}

                   if (posicionador==7) {
                	   lblNewLabel_9_6.setIcon(scaledIcon);
                	   lblNewLabel_9_6.setName(String.valueOf(id)); // Asociar el ID del post al botón

				}

                   if (posicionador==8) {
                	   lblNewLabel_9_7.setIcon(scaledIcon);
                	   lblNewLabel_9_7.setName(String.valueOf(id)); // Asociar el ID del post al botón

				}

                   if (posicionador==9) {
                	   lblNewLabel_9_8.setIcon(scaledIcon);
                	   lblNewLabel_9_8.setName(String.valueOf(id)); // Asociar el ID del post al botón

				}
                   
                   
                }
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    public int getIdusario() {
        return idusario;
    }

    public void setIdusario(int idusario) {
        this.idusario = idusario;
    }
}
