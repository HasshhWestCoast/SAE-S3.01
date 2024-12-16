package Vue;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FenetreBien extends JFrame {
    private JTable tableBiens;
    private JTable tableLogements;

    public FenetreBien() {
        setTitle("Mes Biens");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Utilisation du BorderLayout pour la fenêtre principale
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE); // Fond blanc
        getContentPane().add(mainPanel);

        // Titre centré en haut
        JLabel lblTitle = new JLabel("Mes Biens", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblTitle.setBackground(Color.WHITE);
        lblTitle.setOpaque(true); // Assurez-vous que le fond s'applique
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Section centrale avec deux panneaux (utilisant BoxLayout)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE); // Fond blanc
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Premier panneau : tableau des biens
        JPanel panelBiens = new JPanel(new BorderLayout());
        panelBiens.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 128, 0), 2), "Bien"));
        panelBiens.setBackground(Color.WHITE);
        tableBiens = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "Nom du bien", "Adresse", "Nb de logements", "Type" }));
        tableBiens.setBackground(Color.WHITE);
        JScrollPane scrollPaneBiens = new JScrollPane(tableBiens);
        panelBiens.add(scrollPaneBiens, BorderLayout.CENTER);
        centerPanel.add(panelBiens);

        // Deuxième panneau : tableau des logements
        JPanel panelLogements = new JPanel(new BorderLayout());
        panelLogements.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 128, 0), 2), "Mes Logements"));
        panelLogements.setBackground(Color.WHITE);
        tableLogements = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "Nom", "Surface", "Nb pièces", "Etage", "Acquisition", "Occupé", "Type" }));
        tableLogements.setBackground(Color.WHITE);
        JScrollPane scrollPaneLogements = new JScrollPane(tableLogements);
        panelLogements.add(scrollPaneLogements, BorderLayout.CENTER);
        centerPanel.add(panelLogements);

        // Boutons en bas
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE); // Fond blanc
        JButton btnAjouterBien = new JButton("Ajouter un bien");
        JButton btnAjouterLogement = new JButton("Ajouter un logement");
        buttonPanel.add(btnAjouterBien);
        buttonPanel.add(btnAjouterLogement);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
