package itla_network;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.JobName;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import basedatos.Conexion_mysql;
import javax.swing.SwingConstants;


public class mostrarelpost extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    JPanel panel = new JPanel();
    JTextArea textArea = new JTextArea();
    JPanel panel_1 = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mostrarelpost frame = new mostrarelpost(7);
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
    public mostrarelpost(int id) {
    	imgs(id); 
        setTitle("PUBLICACION");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        

        setBounds(100, 100, 877, 440);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.BLACK);
        panel.setToolTipText("foto");
        panel.setBounds(10, 22, 469, 364);
        contentPane.add(panel);
        panel.setLayout(null);
        
        
        
        JLabel lblNewLabel = new JLabel("Descripcion");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(489, 22, 151, 14);
        contentPane.add(lblNewLabel);
        
        // Crear JTextArea y JScrollPane
        
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        textAreaScrollPane.setBounds(489, 36, 362, 92);
        contentPane.add(textAreaScrollPane);
        
        JLabel lblNewLabel_1 = new JLabel("LIKE");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(501, 139, 44, 27);
        contentPane.add(lblNewLabel_1);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(533, 141, 91, 23);
        contentPane.add(btnNewButton);
        
        JLabel lblNewLabel_2 = new JLabel("Perfil");
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBackground(Color.WHITE);
        lblNewLabel_2.setBounds(634, 145, 46, 14);
        contentPane.add(lblNewLabel_2);
        
        JButton btnNewButton_1 = new JButton("New button");
        btnNewButton_1.setBounds(662, 139, 189, 23);
        contentPane.add(btnNewButton_1);
        
        JLabel lblNewLabel_3 = new JLabel("COMENTARIOS");
        lblNewLabel_3.setForeground(Color.WHITE);
        lblNewLabel_3.setBounds(489, 176, 362, 14);
        contentPane.add(lblNewLabel_3); 
       
        panel_1.setBounds(489, 206, 368, 1000);
        
        String comentarioString = imgs(id);  
        
        ///oojo aqui
        
        
        JScrollPane scrollPane_3 = new JScrollPane(panel_1);
        panel_1.setLayout(new GridLayout(0,1));
        panel_1.setPreferredSize(new Dimension(368, 9000));	
        
      
        
        
		scrollPane_3.setBounds(489, 206, 368, 680);	
		contentPane.add(scrollPane_3);
		// Actualizar el internal frame
		/*internalFrame_11.revalidate();
		internalFrame_11.repaint();*/
        
        // Crear JTable y JScrollPane
      /*  table = new JTable();
    	DefaultTableModel tableModel = new DefaultTableModel(
        	    new Object[][] {},
        	    new String[] {"Comentario"}
        	) {
        	    boolean[] columnEditables = new boolean[] {false};
        	    public boolean isCellEditable(int row, int column) {
        	        return columnEditables[column];
        	    }
        	};
        	tableModel.addRow(new Object[] {comentarioString});
        	table.setModel(tableModel);
        	table.getColumnModel().getColumn(0).setResizable(false);
        	table.getColumnModel().getColumn(0).setPreferredWidth(620);

        	JScrollPane tableScrollPane = new JScrollPane(table);
        	tableScrollPane.setBounds(489, 202, 362, 184);
        	contentPane.add(tableScrollPane);
    	Conexion_mysql bd = new Conexion_mysql();*/
        
        
        
    }
    
    public String imgs(int id) {
    	Conexion_mysql bd = new Conexion_mysql();
    	  String comentarios = null;
    	  String nombre = null;

    	
    	try {
    	    Connection coneshowConexion = bd.getConnection();
    	    PreparedStatement bin = coneshowConexion.prepareStatement("SELECT IMAGEN, DESCRIPCION FROM post where ID_post = ? ");
    	    bin.setInt(1, 7); // Asumiendo que el ID es 7. Ajusta según sea necesario.
    	    ResultSet blb = bin.executeQuery();
    	   
    	  PreparedStatement bin1 = coneshowConexion.prepareStatement("SELECT " +
                  "    c.Comentarios, " + // Cambia 'Comentario' por el nombre real de la columna de comentarios
                  "    u.Nombre_USUARIO " +
                  "FROM " +
                  "    Comentarios c " +
                  "JOIN " +
                  "    Usuarios u ON c.PostID = u.ID_Usuarios " +
                  "WHERE " +
                  "    c.PostID = ?;");
    	    bin1.setInt(1, 7); // Asumiendo que el ID es 7. Ajusta según sea necesario.
    	    ResultSet blb1 = bin1.executeQuery();
    	  
    	    
    	    
    	    while (blb.next()) {
    	        Blob leerbinario = blb.getBlob("IMAGEN");
    	        String descripcion = blb.getString("DESCRIPCION");
    	        
    	        byte[] pasarIMgBytes = leerbinario.getBytes(1, (int) leerbinario.length());

    	        if (pasarIMgBytes != null && pasarIMgBytes.length > 0) {
    	            BufferedImage imagenBufferedImage = null;

    	            try {
    	                imagenBufferedImage = ImageIO.read(new ByteArrayInputStream(pasarIMgBytes));
    	            } catch (IOException e2) {
    	                java.util.logging.Logger.getLogger(mostrarelpost.class.getName()).log(java.util.logging.Level.SEVERE, null, e2);
    	            }

    	            if (imagenBufferedImage != null) {
    	                ImageIcon nvlIcon = new ImageIcon(imagenBufferedImage);
    	                Icon imageIcon = new ImageIcon(nvlIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
    	                JLabel lblNewLabel_4 = new JLabel(imageIcon);
    	                lblNewLabel_4.setBounds(10, 10, 449, 344);
    	                textArea.setText(descripcion);
    	             
    	                panel.add(lblNewLabel_4);
    	               
    	                // Aquí deberías agregar el imageIcon a tu tabla o componente
    	                // TABLA.addRow(new Object[] { imageIcon, imageIcon, imageIcon, imageIcon });
    	            } else {
    	                System.err.println("Error: imagenBufferedImage es null después de intentar leer los bytes de la imagen.");
    	            }
    	        } else {
    	            System.err.println("Error: pasarIMgBytes es null o vacío.");
    	        }
    	    }
    	    
    	    
    	    while (blb1.next()) {
    	    	
    	    	if (blb1!= null ) {
				comentarios = blb1.getString(1);
				nombre = blb1.getString(2);
			}
    	    	  JPanel panel_2 = new JPanel();
    	          panel_2.setBounds(10, 10, 331, 108);
    	          panel_2.setLayout(null);
    	          
    	          JTextArea textArea_1 = new JTextArea(comentarios);
    	          textArea_1.setBounds(0, 35, 331, 30);
    	          
    	    	JLabel lblNewLabel_5 = new JLabel(nombre);
    	        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
    	        lblNewLabel_5.setBounds(10, 0, 311, 25);
    	        panel_2.add(lblNewLabel_5);
    	        panel_2.add(textArea_1);
    	        	          panel_1.add(panel_2);
				
			}
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}

    	return comentarios;
    
}
}
