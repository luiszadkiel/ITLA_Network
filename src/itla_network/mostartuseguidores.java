package itla_network;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.util.List;
import clases.seguidos; // Asegúrate de tener esta clase para representar a un seguidor

public class mostartuseguidores extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;

    /**
     * Create the dialog.
     * @param seguidores Lista de seguidores que se mostrarán en la tabla
     */
    public mostartuseguidores(List<String> seguidores) {
        setTitle("Seguidores");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.BLACK);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

     // Verifica que la lista de seguidores no esté vacía
        if (seguidores == null || seguidores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay seguidores para mostrar.", "Sin seguidores", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        
        // Column names
        String[] columnNames = {"Nombre Usuario"};

        // Prepare data
        Object[][] data = new Object[seguidores.size()][1];
        for (int i = 0; i < seguidores.size(); i++) {
            data[i][0] = seguidores.get(i);
        }
  
        // Create the table and set its model
        table = new JTable(data, columnNames);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.setModel(new DefaultTableModel(data, columnNames) {
            boolean[] columnEditables = new boolean[] { false };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(0).setResizable(false);

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        // Cambiar el color de texto de la tabla a blanco
        table.setForeground(Color.WHITE);
        // Create and add buttons to the bottom panel
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }
}
