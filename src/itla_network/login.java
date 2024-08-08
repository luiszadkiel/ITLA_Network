package itla_network;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import clases.Perfil;
import clases.loginn;

public class login extends JFrame {
    int i = 0;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private static Clip clip; // Variable global para el Clip

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	
                	
                    // Crear un nuevo hilo para manejar la pausa y reproducir música
                    new Thread(() -> {
                        try {
                        	
                            // Reproducir la música
                            String filePath = "ah.wav"; 
                            playMusic(filePath);
                            
                            // Pausa el hilo por 13 segundos (13,000 milisegundos)
                            Thread.sleep(13000);
                            
                            
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                        

                        // Mostrar la ventana de login después de la pausa
                        SwingUtilities.invokeLater(() -> {
                            login frame = new login();
                            frame.setVisible(true);
                        });
                    }).start();
                    
                    
           
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public login() {
        // Configuración del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 569, 566);
        setLocationRelativeTo(null); // Centra el JFrame en la pantalla

        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 64));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0));
        panel.setBounds(13, 11, 530, 504);
        contentPane.add(panel);
        panel.setLayout(null);

        textField = new JTextField();
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 28));
        textField.setBounds(93, 234, 357, 48);
        panel.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Iniciar sesión");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                loginn ne = new loginn();
                String pass = new String(passwordField.getPassword());

                String nombre = textField.getText();
                Perfil perfil = Perfil.getInstance();
                perfil.setNombre(nombre);
                perfil.setNombre_Perfil(nombre);
                perfil.cargarPerfilPorNombre(nombre);

                if (ne.verificador(textField.getText(), pass)) {
                    ventana_principal ventanaPrincipal = new ventana_principal();
                    ventanaPrincipal.frame.setLocationRelativeTo(null); // Centra el JFrame en la pantalla
                    ventanaPrincipal.frame.setVisible(true); // Hace visible el JFrame
                    stopMusic(); // Detener la música al hacer clic en el botón
                    dispose(); // Cierra el JFrame después de iniciar sesión
                } else {
                    i++;
                    JOptionPane.showMessageDialog(null, "¡Usuario o contraseña incorrectos!", "", JOptionPane.ERROR_MESSAGE);
                    if (i > 3) {
                        JOptionPane.showMessageDialog(null, "Por favor regístrese nuevamente", "", JOptionPane.ERROR_MESSAGE);
                        i = 0;
                    }
                }
            }
        });
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setFont(new Font("Wide Latin", Font.PLAIN, 10));
        btnNewButton.setBounds(158, 365, 234, 57);
        panel.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("ITLA_NETWORK");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Wide Latin", Font.PLAIN, 29));
        lblNewLabel.setBounds(14, 172, 510, 77);
        panel.add(lblNewLabel);

        JButton btnNewButton_1 = new JButton("Registrarse");
        btnNewButton_1.setForeground(Color.BLACK);
        btnNewButton_1.setFont(new Font("Wide Latin", Font.PLAIN, 10));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopMusic(); // Detener la música al hacer clic en el botón
                registro n = new registro();
                n.setVisible(true);
            }
        });
        btnNewButton_1.setBounds(106, 451, 333, 31);
        panel.add(btnNewButton_1);

        passwordField = new JPasswordField();
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 28));
        passwordField.setBounds(93, 305, 357, 48);
        panel.add(passwordField);

        JSeparator separator = new JSeparator();
        separator.setBounds(137, 428, 273, 2);
        panel.add(separator);

        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("logo (1).png"));
        lblNewLabel_1.setBounds(49, 34, 162, 126);
        panel.add(lblNewLabel_1);
    }

    //------------------------- música
    public static void playMusic(String filePath) {
        try {
            File musicPath = new File(filePath);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("No se puede encontrar el archivo");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    // pausar música
    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
