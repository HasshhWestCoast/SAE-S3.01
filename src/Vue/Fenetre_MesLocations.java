package Vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Fenetre_MesLocations extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table_MesLocations;

    public Fenetre_MesLocations() {
        setTitle("Mes Locations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrer la fenêtre
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Titre en haut
        JPanel panelTitre = new JPanel(new BorderLayout());
        panelTitre.setBackground(Color.WHITE);
        JLabel lbl_MesLocations = new JLabel("Mes Locations", SwingConstants.CENTER);
        lbl_MesLocations.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_MesLocations.setForeground(new Color(0, 128, 0));
        panelTitre.add(lbl_MesLocations, BorderLayout.CENTER);
        panelTitre.add(new JSeparator(), BorderLayout.SOUTH);

        // Tableau des locations
        JPanel panelTable = new JPanel(new BorderLayout());
        panelTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        table_MesLocations = new JTable();
        table_MesLocations.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null, null, null } },
            new String[] { "Locataire", "Bien", "Type", "Date début", "Dernière régularisation" }
        ));
        JScrollPane scrollPane = new JScrollPane(table_MesLocations);
        scrollPane.setBorder(new LineBorder(new Color(0, 128, 0), 2));
        panelTable.add(scrollPane, BorderLayout.CENTER);

        // Champs de texte à droite
        JPanel panelChamps = new JPanel(new GridLayout(0, 1, 5, 5));
        panelChamps.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelChamps.setBackground(Color.WHITE);

        // Boutons en bas
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBoutons.setBackground(Color.WHITE);

        // Ajouter les panneaux principaux à la fenêtre
        getContentPane().add(panelTitre, BorderLayout.NORTH);
        getContentPane().add(panelTable, BorderLayout.CENTER);
        getContentPane().add(panelChamps, BorderLayout.EAST);
        getContentPane().add(panelBoutons, BorderLayout.SOUTH);
        
        JButton btnCharger = new JButton("Charger");
        panelBoutons.add(btnCharger);
        
        JButton btnInserer = new JButton("Inserer");
        panelBoutons.add(btnInserer);
        
        JButton btnMaj = new JButton("Mise à jour");
        panelBoutons.add(btnMaj);
        
        JButton btnSupprimer = new JButton("Supprimer");
        panelBoutons.add(btnSupprimer);
    }

    
    /**
     * Méthode pour créer un bouton avec fond vert et texte blanc.
     */
    private JButton createButton(String text) {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Fenetre_MesLocations frame = new Fenetre_MesLocations();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
