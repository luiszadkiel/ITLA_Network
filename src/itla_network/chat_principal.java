package itla_network;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import basedatos.Conexion_mysql;
import clases.Perfil;

public class chat_principal extends JFrame {
    private static chat_principal instance = null;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel;
    private String nombre_user_chat;
    private int id_chat_user;
    private int id_user_logged_in; // Suponiendo que el ID del usuario logueado es 1
    private Conexion_mysql conexion;
	Perfil perfil = Perfil.getInstance();
	private ScheduledExecutorService scheduler;
/*	   // Crear un hilo para ejecutar la tarea repetidamente
    Thread taskThread = new Thread(() -> {
        try {
            while (true) {
                // Ejecutar el método deseado
            	loadMessages();
                
                // Esperar 1 minuto (60000 ms)
                Thread.sleep(60000);
            }
        } catch (InterruptedException e) {
            // Manejo de la excepción si el hilo es interrumpido
            e.printStackTrace();
        }
    
   
    });
    
    // Iniciar el hilo
    taskThread.start();
    
    */
	
    public chat_principal(String nombre_user_chat, int id_chat_user) {
    	startMessageUpdater() ;
    	 setLocationRelativeTo(null); // Centra el diálogo en la pantalla

        this.conexion = new Conexion_mysql(); // Inicializar conexión con la base de datos
        String nombre = perfil.getNombre_Perfil();
    	
        
        String query = "SELECT ID_Usuarios FROM Usuarios WHERE Nombre_USUARIO = ?";

        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)){
             
        	// Asignar valor al parámetro de la consulta
        	stmt.setString(1, nombre);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Procesar el resultado
            if (rs.next()) {
                int userID = rs.getInt("ID_Usuarios");
                setIdUserLoggedIn(userID);
  
            }

           } catch (SQLException e1) {
               e1.printStackTrace();
           }
       
  
 

        this.nombre_user_chat = nombre_user_chat;
        this.id_chat_user = id_chat_user;

        setType(Type.UTILITY);
        setTitle("chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 834, 579);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("FOTO DE seguidor");
        lblNewLabel.setBounds(10, 11, 159, 66);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBackground(Color.WHITE);
        contentPane.add(lblNewLabel);

        // Crear un JTextArea y añadirlo a un JScrollPane
        JTextArea textArea_1 = new JTextArea();
        JScrollPane scrollPane2 = new JScrollPane(textArea_1);
        scrollPane2.setBounds(10, 493, 519, 40);
        contentPane.add(scrollPane2);

        
        
        
        //-------------------------------------------------------------------------------------------------
        JButton btnNewButton = new JButton("ENVIAR");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String mensaje = textArea_1.getText();
        		
                String query = "INSERT INTO Mensaje (chat_id, user_id, texto_mensaje) VALUES (?, ?, ?)";
                
                
                try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)){
                     

                       // Asignar valores a los parámetros de la consulta
                	stmt.setInt(1, id_chat_user); // chat_id
                	stmt.setInt(2, id_user_logged_in); // user_id
                	stmt.setString(3, mensaje); // texto_mensaje

                       // Ejecutar la consulta
                       int rowsAffected = stmt.executeUpdate();
                       // Cargar mensajes al iniciar
                       loadMessages();
                       
                       textArea_1.setText(null);
                       
                   } catch (SQLException e1) {
                       e1.printStackTrace();
                   }
     		
        	}
        });
        
        
        
        
        
        
        
        btnNewButton.setFont(new Font("Sitka Small", Font.PLAIN, 15));
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBounds(663, 494, 145, 39);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("New button");
        btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\zadkiel\\Downloads\\3638361 (1).png"));
        btnNewButton_1.setBounds(601, 494, 56, 39);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("New button");
        btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\zadkiel\\Downloads\\880559 (1).png"));
        btnNewButton_2.setBounds(539, 494, 52, 39);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("New button");
        btnNewButton_3.setFont(new Font("Sitka Small", Font.PLAIN, 16));
        btnNewButton_3.setBackground(Color.BLACK);
        btnNewButton_3.setForeground(Color.WHITE);
        btnNewButton_3.setText(nombre_user_chat);
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                perfil perfil = new perfil();
                perfil.setVisible(true);
            }
        });
        btnNewButton_3.setBounds(187, 23, 613, 40);
        contentPane.add(btnNewButton_3);

        // Crear un panel para los mensajes
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(0, 7000));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Cambio a BoxLayout.Y_AXIS para colocar los mensajes en una columna

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(14, 99, 794, 383);
        contentPane.add(scrollPane);

        // Crear paneles para botones
        JPanel blackButtonPanel = new JPanel();
        blackButtonPanel.setBackground(Color.BLACK);

        JPanel blueButtonPanel = new JPanel();
        blueButtonPanel.setBackground(Color.BLACK);
        blackButtonPanel.setLayout(null);

        // Crear botones negros
        JButton blackButton1 = new JButton("Negro 1");
        blackButton1.setBounds(22, 5, 71, 23);
        blackButton1.setBackground(Color.BLACK);
        blackButton1.setForeground(Color.WHITE);
        blackButtonPanel.add(blackButton1);
        
      
        JButton blackButton2 = new JButton("Negro 2");
        blackButton2.setBounds(668, 5, 71, 23);
        blackButton2.setBackground(Color.BLACK);
        blackButton2.setForeground(Color.WHITE);
        blackButtonPanel.add(blackButton2);
        blueButtonPanel.setLayout(null);

        // Crear botones azules
        JButton blueButton1 = new JButton("Azul 1");
        blueButton1.setBounds(643, 5, 61, 23);
        blueButton1.setBackground(Color.BLUE);
        blueButton1.setForeground(Color.WHITE);
        blueButtonPanel.add(blueButton1);

        JButton blueButton2 = new JButton("Azul 2");
        blueButton2.setBounds(709, 5, 61, 23);
        blueButton2.setBackground(Color.BLUE);
        blueButton2.setForeground(Color.WHITE);
        blueButtonPanel.add(blueButton2);

        // Añadir paneles de botones al panel principal
        panel.add(blackButtonPanel);
        panel.add(blueButtonPanel);

        // Cargar mensajes al iniciar
        loadMessages();
    }

///---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------       
    
    
    
    private void loadMessages() {
        panel.removeAll();

        String query = "SELECT texto_mensaje, hora_envio, user_id FROM Mensaje WHERE chat_id = ? ORDER BY hora_envio ASC";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id_chat_user);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String messageText = rs.getString("texto_mensaje");
                Timestamp timestamp = rs.getTimestamp("hora_envio");
                int userId = rs.getInt("user_id");

                JLabel messageLabel = new JLabel(messageText);
                messageLabel.setOpaque(true);
                messageLabel.setBackground(userId == id_user_logged_in ? Color.DARK_GRAY : Color.BLUE);
                messageLabel.setForeground(Color.WHITE);
                messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JLabel timestampLabel = new JLabel(timestamp.toString());
                timestampLabel.setForeground(Color.WHITE);
                timestampLabel.setBackground(Color.black);


                JPanel messagePanel = new JPanel();
                messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
                messagePanel.add(messageLabel);
                messagePanel.add(timestampLabel);
                // Alineación del mensaje
                if (userId == id_user_logged_in) {
                    messagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                } else {
                    messagePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                }
                
                // Define el color del borde y el grosor
                Color borderColor = Color.BLACK; // Puedes cambiar el color según sea necesario
                int borderThickness = 2; // Grosor del borde en píxeles
                messagePanel.setBackground(Color.BLACK);
                // Crea el borde con el color y grosor especificados
                Border border = BorderFactory.createLineBorder(borderColor, borderThickness);

                // Configura el borde y el margen para el mensaje del usuario actual
                if (userId == id_user_logged_in) {
                    messagePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                    
                    Border margin = BorderFactory.createEmptyBorder(0, 0, 0, 120); // Margen: arriba, izquierda, abajo, derecha
                    messagePanel.setBorder(BorderFactory.createCompoundBorder(border, margin));
                } else { // Mensaje de otro usuario
                    messagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    Border margin = BorderFactory.createEmptyBorder(0, 120, 0, 0); // Margen: arriba, izquierda, abajo, derecha
                    messagePanel.setBorder(BorderFactory.createCompoundBorder(border, margin));
                }
                
                panel.add(messagePanel);
            }

            panel.revalidate();
            panel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void startMessageUpdater() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            SwingUtilities.invokeLater(() -> {
                loadMessages();
            });
        }, 0, 1, TimeUnit.MINUTES);
    }

    
    public int getIdUserLoggedIn() {
        return id_user_logged_in;
    }

    // Setter
    public void setIdUserLoggedIn(int id_user_logged_in) {
        this.id_user_logged_in = id_user_logged_in;
    }
}
