package Vue;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controleur.GestionFenBien;
import Controleur.GestionFenAssurances;
import Controleur.GestionFenFacture;
import Controleur.GestionFenLocation;
import Controleur.GestionFenLogements;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class FenAccueil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelAssurances;
    private JPanel panelAccueil;
    private JPanel panelLocations;
    private JPanel panelFactures;
    private JPanel panelBiens;
    private JPanel panelLogements;
    private JPanel panelArchives;
    private JPanel panelDocuments;
    
    private JLayeredPane layeredPane;
	private GestionFenAssurances gestionClicAssurances;
	private GestionFenFacture gestionClicFacture;
	private GestionFenBien gestionClicBien;
	private GestionFenLocation gestionClicLocation;
	private GestionFenLogements gestionClicLogement;
	
	private JTable tabMesBiens;
	
	private JTable tabMesFactures;
	private JTable tabMesLocations;
	private JTable tabMesAssurances;
	private JTable tabMesLogements;
	private JTable tabFactureArchives;
	private JTable tabLocataireArchives;
	private JTable tabLocationArchives;
	private JTextField textFieldLoyer;
	private JTextField textFieldProvisionSurCharges;
	private JTextField textFieldMontantPayé;
	private JTextField textFieldRestantDu;
	private JTextField textFieldDateEmission;
	private JTextField textFieldDatePaiement;
	private JTextField textFieldCaution;

	
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
     * @throws SQLException 
     */
    public FenAccueil() throws SQLException {
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
        btnMesDocuments.addActionListener(e -> switchToPanel("Documents"));
        btnMesDocuments.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesDocuments);

        RoundedButton btnMesArchives = new RoundedButton("Archives", 20);
        btnMesArchives.addActionListener(e -> switchToPanel("Archives"));
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
        
        // Panel Locations
        panelLocations = createLocationsPanel();
        layeredPane.add(panelLocations, "Locations");
        
        // Panel Facture
        panelFactures = createFacturesPanel();
        layeredPane.add(panelFactures, "Factures");
        
        // Panel Biens
        panelBiens = createBiensPanel();
        layeredPane.add(panelBiens, "Biens");
        
        
        // Panel Logements
        panelLogements = createLogementsPanel();
        layeredPane.add(panelLogements, "Logements");
        
        // Panel Logements
        panelArchives = createArchivesPanel();
        layeredPane.add(panelArchives, "Archives");
        
        panelDocuments = createDocumentPanel();
        layeredPane.add(panelDocuments, "Documents");
        

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

    private JPanel createAssurancesPanel() throws SQLException {
    	
    	this.gestionClicAssurances = new GestionFenAssurances(this);

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
        tabMesAssurances = new JTable();
        tabMesAssurances.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null, null, null } },
            new String[] { "Numéro police", "Montant", "Date échéance", "Entreprise", "Logement" }
        ));
        JScrollPane scrollPane = new JScrollPane(tabMesAssurances);
        scrollPane.setBounds(59, 48, 890, 407);
        scrollPane.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPane);

        // Panel des boutons
        JPanel panelSud = new JPanel();
        panelSud.setBounds(10, 508, 1024, 41);
        panelCentre.add(panelSud);
        panelSud.setBackground(new Color(240, 240, 240));
        panelSud.setLayout(null);

        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.addActionListener(this.gestionClicAssurances);
        btnCharger.setBounds(393, 11, 92, 23);
        panelSud.add(btnCharger);

        RoundedButton btnInserer = new RoundedButton("Inserer", 20);
        btnInserer.addActionListener(this.gestionClicAssurances);
        btnInserer.setBounds(520, 11, 81, 23);
        panelSud.add(btnInserer);

        return panelAssurances;
    }
    
    private JPanel createLocationsPanel() throws SQLException {
    	
        this.gestionClicLocation = new GestionFenLocation(this);

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
    	 
    	 tabMesLocations = new JTable();
         tabMesLocations.setModel(new DefaultTableModel(
             new Object[][] { { null, null, null, null, null } },
             new String[] { "Locataire", "Bien", "Type", "Date début", "Dernière régularisation" }
         ));
         JScrollPane scrollPane = new JScrollPane(tabMesLocations);
         scrollPane.setBounds(54, 50, 627, 418);
         scrollPane.setBorder(new LineBorder(new Color(31, 153, 88), 2));
         panelCentre.add(scrollPane);
          
          JPanel panel = new JPanel();
          panel.setBounds(0, 501, 1024, 48);
          panelCentre.add(panel);
          panel.setLayout(null);
                    
       	  RoundedButton btnICharger = new RoundedButton("Charger", 20);
       	  btnICharger.addActionListener(this.gestionClicLocation);
       	  btnICharger.setBounds(265, 11, 85, 23);
       	  panel.add(btnICharger);
       	  
       	  RoundedButton btnInserer = new RoundedButton("Inserer", 20);
       	  btnInserer.addActionListener(this.gestionClicLocation);
       	  btnInserer.setBounds(405, 11, 80, 23);
       	  panel.add(btnInserer);
       	  
       	  RoundedButton btnMiseAJour = new RoundedButton("Mise à jour", 20);
       	  btnMiseAJour.addActionListener(this.gestionClicLocation);
       	  btnMiseAJour.setBounds(528, 11, 96, 23);
       	  panel.add(btnMiseAJour);
       	  
       	  RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
       	  btnSupprimer.addActionListener(this.gestionClicLocation);
       	  btnSupprimer.setBounds(657, 11, 96, 23);
       	  panel.add(btnSupprimer);
       	  
       	  textFieldLoyer = new JTextField();
       	  textFieldLoyer.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Loyer", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
       	  textFieldLoyer.setBounds(713, 42, 161, 36);
       	  panelCentre.add(textFieldLoyer);
       	  textFieldLoyer.setColumns(10);
       	  
       	  textFieldProvisionSurCharges = new JTextField();
       	  textFieldProvisionSurCharges.setColumns(10);
       	  textFieldProvisionSurCharges.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Provision sur charges", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
       	  textFieldProvisionSurCharges.setBounds(713, 103, 161, 36);
       	  panelCentre.add(textFieldProvisionSurCharges);
       	  
       	  textFieldMontantPayé = new JTextField();
       	  textFieldMontantPayé.setColumns(10);
       	  textFieldMontantPayé.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(31, 153, 88), new Color(160, 160, 160)), "Montant pay\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
       	  textFieldMontantPayé.setBounds(713, 164, 161, 36);
       	  panelCentre.add(textFieldMontantPayé);
       	  
       	  textFieldRestantDu = new JTextField();
       	  textFieldRestantDu.setColumns(10);
       	  textFieldRestantDu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(31, 153, 88), new Color(160, 160, 160)), "Restant d\u00FB", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
       	  textFieldRestantDu.setBounds(713, 228, 161, 36);
       	  panelCentre.add(textFieldRestantDu);
       	  
       	  textFieldDateEmission = new JTextField();
       	  textFieldDateEmission.setColumns(10);
       	  textFieldDateEmission.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(31, 153, 88), new Color(160, 160, 160)), "Date \u00E9mission", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
       	  textFieldDateEmission.setBounds(713, 292, 161, 36);
       	  panelCentre.add(textFieldDateEmission);
       	  
       	  textFieldDatePaiement = new JTextField();
       	  textFieldDatePaiement.setColumns(10);
       	  textFieldDatePaiement.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(31, 153, 88), new Color(160, 160, 160)), "Date paiement", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
       	  textFieldDatePaiement.setBounds(713, 351, 161, 36);
       	  panelCentre.add(textFieldDatePaiement);
       	  
       	  textFieldCaution = new JTextField();
       	  textFieldCaution.setColumns(10);
       	  textFieldCaution.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(31, 153, 88), new Color(160, 160, 160)), "Caution", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
       	  textFieldCaution.setBounds(713, 417, 161, 36);
       	  panelCentre.add(textFieldCaution);
  	
    	 return panelLocations;
		
    	
    }
    
    private JPanel createFacturesPanel() throws SQLException {
    	
    	this.gestionClicFacture = new GestionFenFacture(this);

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

        tabMesFactures = new JTable();
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
        
        RoundedButton btnArchiver = new RoundedButton("Archiver", 20);
        btnArchiver.setBounds(345, 11, 85, 23);
        btnArchiver.addActionListener(this.gestionClicFacture);
        panel.add(btnArchiver);

        RoundedButton btnModifier = new RoundedButton("Modifier", 20);
        btnModifier.setBounds(557, 11, 96, 23);
        btnModifier.addActionListener(this.gestionClicFacture);
        panel.add(btnModifier);

        RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
        btnSupprimer.setBounds(451, 11, 96, 23);
        btnSupprimer.addActionListener(this.gestionClicFacture);
        panel.add(btnSupprimer);
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.addActionListener(this.gestionClicFacture);
        btnCharger.setBounds(243, 11, 85, 23);
        panel.add(btnCharger);

        return panelFactures;
    }
    
    private JPanel createBiensPanel() throws SQLException {
    	
        this.gestionClicBien = new GestionFenBien(this);

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
        tabMesBiens = new JTable();
        tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null, null, null },
            },
            new String[] {
                "IDBien", "Adresse", "Ville", "Code postal", "Type Bien", "période construction"
            }
        ));
        JScrollPane scrollPaneBiens = new JScrollPane(tabMesBiens);
        scrollPaneBiens.setBounds(46, 60, 658, 375);
        scrollPaneBiens.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPaneBiens);

        JPanel panel = new JPanel();
        panel.setBounds(0, 501, 1024, 48);
        panelCentre.add(panel);
        panel.setLayout(null);

        RoundedButton btnIArchiver = new RoundedButton("Archiver", 20);
        btnIArchiver.addActionListener(this.gestionClicBien);
        btnIArchiver.setBounds(255, 11, 85, 23);
        panel.add(btnIArchiver);

        RoundedButton btnModifier = new RoundedButton("Modifier", 20);
        btnModifier.addActionListener(this.gestionClicBien);
        btnModifier.setBounds(364, 11, 96, 23);
        panel.add(btnModifier);

        RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
        btnSupprimer.addActionListener(this.gestionClicBien);
        btnSupprimer.setBounds(480, 11, 96, 23);
        panel.add(btnSupprimer);
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.addActionListener(this.gestionClicBien);
        btnCharger.setBounds(157, 11, 85, 23);
        panel.add(btnCharger);
        
        // Buttons
        RoundedButton btnAjouterUnBien = new RoundedButton("Ajouter un bien", 20);
        btnAjouterUnBien.addActionListener(this.gestionClicBien);
        btnAjouterUnBien.setBounds(743, 198, 173, 23);
        panelCentre.add(btnAjouterUnBien);
  
        RoundedButton btnAjouterFactures = new RoundedButton("Ajouter des factures", 20);
        btnAjouterFactures.addActionListener(this.gestionClicBien);
        btnAjouterFactures.setBounds(743, 251, 173, 23);
        panelCentre.add(btnAjouterFactures);
        
        RoundedButton btnAfficherCompteur = new RoundedButton("Afficher les compteurs", 20);
        btnAfficherCompteur.addActionListener(this.gestionClicBien);
        btnAfficherCompteur.setBounds(743, 303, 173, 23);
        panelCentre.add(btnAfficherCompteur);
        
        return panelBiens;
    }

    private JPanel createLogementsPanel() throws SQLException {
    	
    	this.gestionClicLogement = new GestionFenLogements(this);
    	
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
        tabMesLogements = new JTable();
        tabMesLogements.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null, null, null },
            },
            new String[] {
                "IDLogement", "Surface habitable", "Date acquisition", "Type Logement", "Nombre de pièces", "Num étage"
            }
        ));
        JScrollPane scrollPaneBiens = new JScrollPane(tabMesLogements);
        scrollPaneBiens.setBounds(46, 76, 636, 359);
        scrollPaneBiens.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPaneBiens);

        JPanel panel = new JPanel();
        panel.setBounds(0, 501, 1024, 48);
        panelCentre.add(panel);
        panel.setLayout(null);

        RoundedButton btnArchiver = new RoundedButton("Archiver", 20);
        btnArchiver.addActionListener(this.gestionClicLogement);
        btnArchiver.setBounds(242, 11, 85, 23);
        panel.add(btnArchiver);

        RoundedButton btnModifier = new RoundedButton("Modifier", 20);
        btnModifier.addActionListener(this.gestionClicLogement);
        btnModifier.setBounds(350, 11, 96, 23);
        panel.add(btnModifier);

        RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
        btnSupprimer.addActionListener(this.gestionClicLogement);
        btnSupprimer.setBounds(469, 11, 96, 23);
        panel.add(btnSupprimer);
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.addActionListener(this.gestionClicLogement);
        btnCharger.setBounds(134, 11, 85, 23);
        panel.add(btnCharger);
        
        // Buttons
        RoundedButton btnAjouterLogement = new RoundedButton("Ajouter un logement", 20);
        btnAjouterLogement.addActionListener(this.gestionClicLogement);
        btnAjouterLogement.setBounds(728, 182, 173, 23);
        panelCentre.add(btnAjouterLogement);
  
        RoundedButton btnAjouterDiagnostic = new RoundedButton("Ajouter un diagnostic", 20);
        btnAjouterDiagnostic.addActionListener(this.gestionClicLogement);
        btnAjouterDiagnostic.setBounds(728, 228, 173, 23);
        panelCentre.add(btnAjouterDiagnostic);
        
        RoundedButton btnAjouterFactures = new RoundedButton("Ajouter des factures", 20);
        btnAjouterFactures.addActionListener(this.gestionClicLogement);
        btnAjouterFactures.setBounds(728, 277, 173, 23);
        panelCentre.add(btnAjouterFactures);
        
        
        RoundedButton btnAfficherCompteur = new RoundedButton("Afficher les compteurs", 20);
        btnAfficherCompteur.addActionListener(this.gestionClicLogement);
        btnAfficherCompteur.setBounds(728, 325, 173, 23);
        panelCentre.add(btnAfficherCompteur);
        
        return panelLogements;
    }
    
    private JPanel createArchivesPanel() throws SQLException {
    	
    	
    	JPanel panelArchives = new JPanel();
    	 panelArchives.setLayout(new BorderLayout(0, 0));

        JPanel panelTitre = new JPanel();
        panelArchives.add(panelTitre, BorderLayout.NORTH);
        panelTitre.setLayout(new BorderLayout(0, 0));

        JLabel lblFactures = new JLabel("Mes Archives ");
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
        panelArchives.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        // JTable pour "Bien"
        tabFactureArchives = new JTable();
        tabFactureArchives.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"Num\u00E9ro ", "D\u00E9signation", "Montant pay\u00E9", "Montant", "Date Emission"
        	}
        ));
        JScrollPane scrollPaneFacture = new JScrollPane(tabFactureArchives);
        scrollPaneFacture.setBounds(46, 57, 636, 93);
        scrollPaneFacture.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPaneFacture);
        
        // JTable pour "Bien"
        tabLocataireArchives = new JTable();
        tabLocataireArchives.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"Num\u00E9ro ", "D\u00E9signation", "Montant pay\u00E9", "Montant", "Date Emission"
        	}
        ));
        
        JScrollPane scrollPaneLocataire = new JScrollPane(tabLocataireArchives);
        scrollPaneLocataire.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        scrollPaneLocataire.setBounds(46, 197, 636, 83);
        panelCentre.add(scrollPaneLocataire);
        
        // JTable pour "Bien"
        tabLocationArchives = new JTable();
        tabLocationArchives.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null},
        	},
        	new String[] {
        		"Num\u00E9ro ", "D\u00E9signation", "Montant pay\u00E9", "Montant", "Date Emission"
        	}
        ));
        
        JScrollPane scrollPaneLocation = new JScrollPane(tabLocationArchives);
        scrollPaneLocation.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        scrollPaneLocation.setBounds(46, 325, 636, 83);
        panelCentre.add(scrollPaneLocation);

        JPanel panel = new JPanel();
        panel.setBounds(0, 501, 1024, 48);
        panelCentre.add(panel);
        panel.setLayout(null);
        
        // Buttons
        RoundedButton btnChargerFacture = new RoundedButton("Ajouter un logement", 20);
        btnChargerFacture.setText("Charger Facture");
        btnChargerFacture.addActionListener(this.gestionClicLogement);
        btnChargerFacture.setBounds(743, 96, 194, 30);
        panelCentre.add(btnChargerFacture);
        
        RoundedButton btnChargerLocataire = new RoundedButton("Ajouter un logement", 20);
        btnChargerLocataire.setText("Charger Locataire");
        btnChargerLocataire.setBounds(743, 227, 194, 30);
        panelCentre.add(btnChargerLocataire);
        
        RoundedButton rndbtnChargerLocation = new RoundedButton("Ajouter un logement", 20);
        rndbtnChargerLocation.setText("Charger Location");
        rndbtnChargerLocation.setBounds(743, 349, 194, 30);
        panelCentre.add(rndbtnChargerLocation);
        
      
        
        return panelArchives;
    	
    	
    	
    	
    }
    
    
    private JPanel createDocumentPanel() throws SQLException {
    	JPanel paneldocuments = new JPanel();
		paneldocuments.setLayout(new BorderLayout(0, 0));

        JPanel panelTitre = new JPanel();
        paneldocuments.add(panelTitre, BorderLayout.NORTH);
        panelTitre.setLayout(new BorderLayout(0, 0));

        JLabel lbldocuments = new JLabel("Mes Documents");
        lbldocuments.setHorizontalAlignment(SwingConstants.CENTER);
        lbldocuments.setForeground(new Color(31, 153, 88));
        lbldocuments.setFont(new Font("Sylfaen", Font.ITALIC, 28));
        lbldocuments.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lbldocuments, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        panelTitre.add(separator, BorderLayout.SOUTH);

        JPanel panelCentre = new JPanel();
        panelCentre.setBackground(new Color(255, 255, 255));
        paneldocuments.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        JComboBox comboBoxIDdocuments = new JComboBox();
        comboBoxIDdocuments.setForeground(new Color(255, 255, 255));
        comboBoxIDdocuments.setBackground(new Color(31, 153, 88));
        comboBoxIDdocuments.setToolTipText("");
        comboBoxIDdocuments.setModel(new DefaultComboBoxModel(new String[] { "" }));

        comboBoxIDdocuments.setBounds(41, 11, 126, 22);
        panelCentre.add(comboBoxIDdocuments);

        JTable tabMesdocuments = new JTable();
        tabMesdocuments.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null, null, null, null, null, null },
            },
            new String[] {
                "Nom", "Montant", "Année"
            }
        ));
        JScrollPane scrollPane = new JScrollPane(tabMesdocuments);
        scrollPane.setBounds(41, 50, 947, 408);
        scrollPane.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPane);

        JPanel panel = new JPanel();
        panel.setBounds(0, 501, 1024, 48);
        panelCentre.add(panel);
        panel.setLayout(null);

        RoundedButton btnInserer = new RoundedButton("Inserer un impot", 20);
        btnInserer.setBounds(433, 11, 80, 23);
        panel.add(btnInserer);

        RoundedButton btnGenerer = new RoundedButton("Génerer une annexe", 20);
        btnGenerer.setBounds(591, 11, 96, 23);
        panel.add(btnGenerer);
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.setBounds(261, 11, 85, 23);
        panel.add(btnCharger);

        return paneldocuments;
    	
    	
    }


    private void switchToPanel(String panelName) {
        CardLayout layout = (CardLayout) layeredPane.getLayout();
        layout.show(layeredPane, panelName);
    }
    
    public JTable getTabMesBiens() {
        return tabMesBiens;
    }
    
    public JTable getTabMesFactures() {
        return tabMesFactures;
    }
    
    public JTable getTabMesLocations() {
        return tabMesLocations;
    }
    
    public JTable getTabMesAssurances() {
    	return tabMesAssurances;
    }
    
    public JTable getTabMesLogements() {
    	return tabMesLogements;
    }
}
