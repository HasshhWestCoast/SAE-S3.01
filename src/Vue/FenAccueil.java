package Vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenAccueil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelAssurances;
    private JPanel panelAccueil;
    private JPanel panelLocations;
    private JPanel panelFactures;
    private JPanel panelBiens;
    private JPanel panelLogements;
    
    

    private JPanel panel;
    private JComboBox comboBoxIDAssurance;
    private JLayeredPane layeredPane;
	
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FenAccueil frame = new FenAccueil();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public FenAccueil() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Définir seulement la taille
        setSize(1050, 700);

        // Centrer automatiquement la fenêtre
        setLocationRelativeTo(null);

        setResizable(false); // Empêche le redimensionnement de la fenêtre
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Menu Panel
        JPanel panelMenuNorth = new JPanel();
        contentPane.add(panelMenuNorth, BorderLayout.NORTH);
        panelMenuNorth.setLayout(new BorderLayout(0, 0));

        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(33, 153, 88));
        panelMenuNorth.add(panelMenu, BorderLayout.CENTER);
        panelMenu.setLayout(new BorderLayout(0, 0));

        JPanel panelMenuGauche = new JPanel();
        panelMenuGauche.setBackground(new Color(33, 153, 88));
        panelMenuGauche.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelMenu.add(panelMenuGauche, BorderLayout.WEST);

        // Buttons
        RoundedButton btnMesBiens = new RoundedButton("Biens", 20);
        btnMesBiens.addActionListener(e -> switchToPanel("Biens"));
        btnMesBiens.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesBiens);

        RoundedButton btnMesLocations = new RoundedButton("Locations", 20);
        btnMesLocations.addActionListener(e -> switchToPanel("Locations"));
        btnMesLocations.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesLocations);

        RoundedButton btnMesFactures = new RoundedButton("Factures", 20);
        btnMesFactures.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        btnMesFactures.addActionListener(e -> switchToPanel("Factures"));
        panelMenuGauche.add(btnMesFactures);

        RoundedButton btnMesAssurances = new RoundedButton("Assurances", 20);
        btnMesAssurances.addActionListener(e -> switchToPanel("Assurances"));
        btnMesAssurances.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesAssurances);

        RoundedButton btnMesDocuments = new RoundedButton("Documents", 20);
        btnMesDocuments.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesDocuments);

        RoundedButton btnMesArchives = new RoundedButton("Archives", 20);
        btnMesArchives.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesArchives);
        
        RoundedButton btnMesLogements = new RoundedButton("Logements", 20);
        btnMesLogements.addActionListener(e -> switchToPanel("Logements"));
        btnMesLogements.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add( btnMesLogements);
        

        JPanel panelMenuDroite = new JPanel();
        panelMenuDroite.setBackground(new Color(33, 153, 88));
        panelMenu.add(panelMenuDroite, BorderLayout.EAST);

        RoundedButton btnSeDeconnecter = new RoundedButton("Se Déconnecter", 20);
        btnSeDeconnecter.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        panelMenuDroite.add(btnSeDeconnecter);

        // LayeredPane for switching panels
        layeredPane = new JLayeredPane();
        contentPane.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(new CardLayout());

        // Panel Accueil
        panelAccueil = createAccueilPanel();
        layeredPane.add(panelAccueil, "Accueil");

        // Panel Assurances
        panelAssurances = createAssurancesPanel();
        layeredPane.add(panelAssurances, "Assurances");
        
        // Panel Facture
        panelLocations = createLocationsPanel();
        layeredPane.add(panelLocations, "Locations");
        
        // Panel Facture
        panelFactures = createFacturesPanel();
        layeredPane.add(panelFactures, "Factures");
        
        // Panel Facture
        panelBiens = createBiensPanel();
        layeredPane.add(panelBiens, "Biens");
        
        
        // Panel Facture
        panelLogements = createLogementsPanel();
        layeredPane.add(panelLogements, "Logements");

    }

    private JPanel createAccueilPanel() {
        JPanel panelAccueil = new JPanel();
        panelAccueil.setBackground(Color.WHITE);
        panelAccueil.setLayout(new BorderLayout());

        JLabel lblAccueil = new JLabel("Accueil", SwingConstants.CENTER);
        lblAccueil.setFont(new Font("Sylfaen", Font.PLAIN, 24));
        lblAccueil.setForeground(new Color(31, 153, 88));

        panelAccueil.add(lblAccueil, BorderLayout.CENTER);

        return panelAccueil;
    }

    private JPanel createAssurancesPanel() {
        JPanel panelAssurances = new JPanel();
        panelAssurances.setBackground(Color.WHITE);
        panelAssurances.setLayout(new BorderLayout(0, 0));

        // Bandeau supérieur
        JPanel panelTitre = new JPanel();
        panelTitre.setLayout(new BorderLayout());
        panelTitre.setBackground(new Color(240, 240, 240));
        JLabel lblMesAssurances = new JLabel("Mes Assurances");
        lblMesAssurances.setHorizontalAlignment(SwingConstants.CENTER);
        lblMesAssurances.setFont(new Font("Sylfaen", Font.ITALIC, 28));
        lblMesAssurances.setForeground(new Color(31, 153, 88));
        lblMesAssurances.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lblMesAssurances, BorderLayout.CENTER);

        JSeparator separatorNord = new JSeparator();
        separatorNord.setForeground(new Color(0,0,0));
        panelTitre.add(separatorNord, BorderLayout.SOUTH);

        panelAssurances.add(panelTitre, BorderLayout.NORTH);

        JPanel panelCentre = new JPanel();
        panelCentre.setBackground(new Color(255, 255, 255));
        panelAssurances.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        // Ajouter la JComboBox en haut
        JComboBox comboBoxIDAssurance = new JComboBox();
        comboBoxIDAssurance.setBounds(59, 11, 148, 26);
        comboBoxIDAssurance.setForeground(new Color(255, 255, 255));
        comboBoxIDAssurance.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        comboBoxIDAssurance.setBackground(new Color(31, 153, 88));
        comboBoxIDAssurance.setModel(new DefaultComboBoxModel(new String[] { "ID Assurance" }));
        comboBoxIDAssurance.setToolTipText("ID Assurance");
        panelCentre.add(comboBoxIDAssurance);

        // Ajouter le tableau
        JTable tabMesLocations = new JTable();
        tabMesLocations.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null, null, null, null, null } },
            new String[] { "Numéro police", "ID assurance", "Protection juridique", "Prime", "Quotité", "Date de début", "Date de fin" }
        ));
        JScrollPane scrollPane = new JScrollPane(tabMesLocations);
        scrollPane.setBounds(59, 48, 890, 407);
        scrollPane.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPane);

        // Panel des boutons
        JPanel panelSud = new JPanel();
        panelSud.setBounds(0, 479, 1024, 65);
        panelCentre.add(panelSud);
        panelSud.setBackground(new Color(240, 240, 240));
        panelSud.setLayout(null);

        RoundedButton btnICharger = new RoundedButton("Charger", 20);
        btnICharger.setBounds(393, 11, 92, 23);
        panelSud.add(btnICharger);

        RoundedButton btnInserer = new RoundedButton("Inserer", 20);
        btnInserer.setBounds(520, 11, 81, 23);
        panelSud.add(btnInserer);

        return panelAssurances;
    }
    
    private JPanel createLocationsPanel() {
    	 JPanel panelLocations = new JPanel();
    	 panelLocations.setLayout(new BorderLayout(0, 0));
    	 
    	 JPanel panelTitre = new JPanel();
    	 panelLocations.add(panelTitre, BorderLayout.NORTH);
    	 panelTitre.setLayout(new BorderLayout(0, 0));
    	 
    	 JLabel lblLocations = new JLabel("Mes Locations");
 		lblLocations.setHorizontalAlignment(SwingConstants.CENTER);
 		lblLocations.setForeground(new Color(31, 153, 88));
 		lblLocations.setFont(new Font("Sylfaen", Font.ITALIC, 28));
 		lblLocations.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
 		panelTitre.add(lblLocations, BorderLayout.CENTER);
 		
 		JSeparator separator = new JSeparator();
 		separator.setForeground(new Color(0,0,0));
		panelTitre.add(separator, BorderLayout.SOUTH);
    	 
    	 JPanel panelCentre = new JPanel();
    	 panelCentre.setBackground(new Color(255, 255, 255));
    	 panelLocations.add(panelCentre, BorderLayout.CENTER);
    	 panelCentre.setLayout(null);
    	 
    	 JComboBox comboBoxIDLocations = new JComboBox();
    	 comboBoxIDLocations.setForeground(new Color(255, 255, 255));
    	 comboBoxIDLocations.setBackground(new Color(31, 153, 88));
         comboBoxIDLocations.setToolTipText("ID Locations");
         comboBoxIDLocations.setModel(new DefaultComboBoxModel(new String[] {"ID Locations"}));
    	 
    	 comboBoxIDLocations.setBounds(54, 11, 126, 22);
    	 panelCentre.add(comboBoxIDLocations);
    	 
    	 JTable tabMesLocations = new JTable();
         tabMesLocations.setModel(new DefaultTableModel(
             new Object[][] { { null, null, null, null, null } },
             new String[] { "Locataire", "Bien", "Type", "Date début", "Dernière régularisation" }
         ));
         JScrollPane scrollPane = new JScrollPane(tabMesLocations);
         scrollPane.setBounds(54, 50, 928, 418);
         scrollPane.setBorder(new LineBorder(new Color(31, 153, 88), 2));
         panelCentre.add(scrollPane);
          
          JPanel panel = new JPanel();
          panel.setBounds(0, 501, 1024, 48);
          panelCentre.add(panel);
           	  panel.setLayout(null);
          
           	  RoundedButton btnICharger = new RoundedButton("Charger", 20);
           	  btnICharger.setBounds(265, 11, 85, 23);
           	  panel.add(btnICharger);
           	  
           	  RoundedButton btnInserer = new RoundedButton("Inserer", 20);
           	  btnInserer.setBounds(405, 11, 80, 23);
           	  panel.add(btnInserer);
           	  
           	  RoundedButton btnMiseAJour = new RoundedButton("Mise à jour", 20);
           	  btnMiseAJour.setBounds(528, 11, 96, 23);
           	  panel.add(btnMiseAJour);
           	  
           	  RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
           	  btnSupprimer.setBounds(657, 11, 96, 23);
           	  panel.add(btnSupprimer);
  	
    	 return panelLocations;
		
    	
    }
    
    private JPanel createFacturesPanel() {
        JPanel panelFactures = new JPanel();
        panelFactures.setLayout(new BorderLayout(0, 0));

        JPanel panelTitre = new JPanel();
        panelFactures.add(panelTitre, BorderLayout.NORTH);
        panelTitre.setLayout(new BorderLayout(0, 0));

        JLabel lblFactures = new JLabel("Mes Factures");
        lblFactures.setHorizontalAlignment(SwingConstants.CENTER);
        lblFactures.setForeground(new Color(31, 153, 88));
        lblFactures.setFont(new Font("Sylfaen", Font.ITALIC, 28));
        lblFactures.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lblFactures, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        panelTitre.add(separator, BorderLayout.SOUTH);

        JPanel panelCentre = new JPanel();
        panelCentre.setBackground(new Color(255, 255, 255));
        panelFactures.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        JComboBox comboBoxIDLocations = new JComboBox();
        comboBoxIDLocations.setForeground(new Color(255, 255, 255));
        comboBoxIDLocations.setBackground(new Color(31, 153, 88));
        comboBoxIDLocations.setToolTipText("");
        comboBoxIDLocations.setModel(new DefaultComboBoxModel(new String[] { "" }));

        comboBoxIDLocations.setBounds(41, 11, 126, 22);
        panelCentre.add(comboBoxIDLocations);

        JTable tabMesFactures = new JTable();
        tabMesFactures.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null, null, null, null, null, null },
            },
            new String[] {
                "Numéro", "Date d'émission", "Date de paiement", "Mode de paiement", 
                "Désignation", "Montant réel versé", "Montant", "Imputable locataire", "Acompte versé"
            }
        ));
        JScrollPane scrollPane = new JScrollPane(tabMesFactures);
        scrollPane.setBounds(41, 50, 947, 408);
        scrollPane.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPane);

        JPanel panel = new JPanel();
        panel.setBounds(0, 501, 1024, 48);
        panelCentre.add(panel);
        panel.setLayout(null);

        RoundedButton btnIArchiver = new RoundedButton("Charger", 20);
        btnIArchiver.setText("Archiver");
        btnIArchiver.setBounds(269, 11, 85, 23);
        panel.add(btnIArchiver);

        RoundedButton btnInserer = new RoundedButton("Inserer", 20);
        btnInserer.setBounds(410, 11, 80, 23);
        panel.add(btnInserer);

        RoundedButton btnModifier = new RoundedButton("Mise à jour", 20);
        btnModifier.setText("Modifier");
        btnModifier.setBounds(536, 11, 96, 23);
        panel.add(btnModifier);

        RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
        btnSupprimer.setBounds(676, 11, 96, 23);
        panel.add(btnSupprimer);

        return panelFactures;
    }
    
    private JPanel createBiensPanel() {
        JPanel panelBiens = new JPanel();
        panelBiens.setLayout(new BorderLayout(0, 0));

        JPanel panelTitre = new JPanel();
        panelBiens.add(panelTitre, BorderLayout.NORTH);
        panelTitre.setLayout(new BorderLayout(0, 0));

        JLabel lblFactures = new JLabel("Mes Biens ");
        lblFactures.setHorizontalAlignment(SwingConstants.CENTER);
        lblFactures.setForeground(new Color(31, 153, 88));
        lblFactures.setFont(new Font("Sylfaen", Font.ITALIC, 28));
        lblFactures.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lblFactures, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        panelTitre.add(separator, BorderLayout.SOUTH);

        JPanel panelCentre = new JPanel();
        panelCentre.setBackground(new Color(255, 255, 255));
        panelBiens.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        // JTable pour "Bien"
        JTable tabMesBiens = new JTable();
        tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null, null, null },
            },
            new String[] {
                "IDBien", "Type de Bien", "Surface Habitable", "Nb Pièces", "Date Acquisition", "Nb Places"
            }
        ));
        JScrollPane scrollPaneBiens = new JScrollPane(tabMesBiens);
        scrollPaneBiens.setBounds(46, 76, 636, 359);
        scrollPaneBiens.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPaneBiens);

        JPanel panel = new JPanel();
        panel.setBounds(0, 501, 1024, 48);
        panelCentre.add(panel);
        panel.setLayout(null);

        RoundedButton btnIArchiver = new RoundedButton("Charger", 20);
        btnIArchiver.setText("Archiver");
        btnIArchiver.setBounds(255, 11, 85, 23);
        panel.add(btnIArchiver);

        RoundedButton btnInserer = new RoundedButton("Inserer", 20);
        btnInserer.setBounds(373, 11, 80, 23);
        panel.add(btnInserer);

        RoundedButton btnModifier = new RoundedButton("Mise à jour", 20);
        btnModifier.setText("Modifier");
        btnModifier.setBounds(489, 11, 96, 23);
        panel.add(btnModifier);

        RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
        btnSupprimer.setBounds(616, 11, 96, 23);
        panel.add(btnSupprimer);
        
        // Buttons
        RoundedButton btnAjouterUnBien = new RoundedButton("Ajouter un bien", 20);
        btnAjouterUnBien.setBounds(728, 164, 173, 23);
        panelCentre.add(btnAjouterUnBien);
  
        RoundedButton btnAjouterFactures = new RoundedButton("Ajouter des factures", 20);
        btnAjouterFactures.setBounds(728, 233, 173, 23);
        panelCentre.add(btnAjouterFactures);
        
        RoundedButton btnAfficherCompteur = new RoundedButton("Afficher les compteurs", 20);
        btnAfficherCompteur.setBounds(728, 305, 173, 23);
        panelCentre.add(btnAfficherCompteur);
        
        

        return panelBiens;
    }

    private JPanel createLogementsPanel() {
    	JPanel panelLogements = new JPanel();
    	 panelLogements.setLayout(new BorderLayout(0, 0));

        JPanel panelTitre = new JPanel();
        panelLogements.add(panelTitre, BorderLayout.NORTH);
        panelTitre.setLayout(new BorderLayout(0, 0));

        JLabel lblFactures = new JLabel("Mes Logements ");
        lblFactures.setHorizontalAlignment(SwingConstants.CENTER);
        lblFactures.setForeground(new Color(31, 153, 88));
        lblFactures.setFont(new Font("Sylfaen", Font.ITALIC, 28));
        lblFactures.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lblFactures, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        panelTitre.add(separator, BorderLayout.SOUTH);

        JPanel panelCentre = new JPanel();
        panelCentre.setBackground(new Color(255, 255, 255));
        panelLogements.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        // JTable pour "Bien"
        JTable tabMesBiens = new JTable();
        tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null, null, null },
            },
            new String[] {
                "IDLogement", "Surface habitable", "Date acquisition", "Type Logement", "Nombre de pièces", "Num étage"
            }
        ));
        JScrollPane scrollPaneBiens = new JScrollPane(tabMesBiens);
        scrollPaneBiens.setBounds(46, 76, 636, 359);
        scrollPaneBiens.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPaneBiens);

        JPanel panel = new JPanel();
        panel.setBounds(0, 501, 1024, 48);
        panelCentre.add(panel);
        panel.setLayout(null);

        RoundedButton btnIArchiver = new RoundedButton("Charger", 20);
        btnIArchiver.setText("Archiver");
        btnIArchiver.setBounds(255, 11, 85, 23);
        panel.add(btnIArchiver);

        RoundedButton btnInserer = new RoundedButton("Inserer", 20);
        btnInserer.setBounds(373, 11, 80, 23);
        panel.add(btnInserer);

        RoundedButton btnModifier = new RoundedButton("Mise à jour", 20);
        btnModifier.setText("Modifier");
        btnModifier.setBounds(489, 11, 96, 23);
        panel.add(btnModifier);

        RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
        btnSupprimer.setBounds(616, 11, 96, 23);
        panel.add(btnSupprimer);
        
        // Buttons
        RoundedButton btnAjouterLogement = new RoundedButton("Ajouter un logement", 20);
        btnAjouterLogement.setBounds(728, 164, 173, 23);
        panelCentre.add(btnAjouterLogement);
  
        RoundedButton btnAjouterDiagnostic = new RoundedButton("Ajouter un diagnostic", 20);
        btnAjouterDiagnostic.setBounds(728, 205, 173, 23);
        panelCentre.add(btnAjouterDiagnostic);
        
        RoundedButton btnAjouterFactures = new RoundedButton("Ajouter des factures", 20);
        btnAjouterFactures.setBounds(728, 249, 173, 23);
        panelCentre.add(btnAjouterFactures);
        
        
        RoundedButton btnAfficherCompteur = new RoundedButton("Afficher les compteurs", 20);
        btnAfficherCompteur.setBounds(728, 300, 173, 23);
        panelCentre.add(btnAfficherCompteur);
        
        return  panelLogements;
    }


    private void switchToPanel(String panelName) {
        CardLayout layout = (CardLayout) layeredPane.getLayout();
        layout.show(layeredPane, panelName);
    }
}
