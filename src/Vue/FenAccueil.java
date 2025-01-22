package Vue;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controleur.GestionFenBien;
import Controleur.GestionFenDocument;
import Controleur.GestionFenAssurances;
import Controleur.GestionFenFacture;
import Controleur.GestionFenLocation;
import Controleur.GestionFenLogements;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
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
    private JPanel panelArchives;
    private JPanel panelDocuments;
    
    private JLayeredPane layeredPane;
	private GestionFenAssurances gestionClicAssurances;
	private GestionFenFacture gestionClicFacture;
	private GestionFenBien gestionClicBien;
	private GestionFenLocation gestionClicLocation;
	private GestionFenLogements gestionClicLogement;
	private GestionFenDocument gestionFenDocument;
	
	private JTable tabMesBiens;
	
	private JTable tabMesFactures;
	private JTable tabMesLocations;
	private JTable tabMesAssurances;
	private JTable tabMesLogements;
	private JTable tabFactureArchives;
	private JTable tabBienArchives;
	private JTable tabLogementArchives;
	private JTextField textFieldLoyer;
	private JTextField textFieldProvisionSurCharges;
	private JTextField textFieldMontantPayé;
	private JTextField textFieldRestantDu;
	private JTextField textFieldDateEmission;
	private JTextField textFieldDatePaiement;
	private JTextField textFieldCaution;
	
	private LocalDate currentDate;
    private JLabel monthYearLabel;
    private JPanel daysPanel;
    private JTable tabMesdocuments;

	
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
        btnMesBiens.setBackground(new Color(63, 173, 108)
 // Un peu plus sombre
);
        panelMenuGauche.add(btnMesBiens);

        RoundedButton btnMesLocations = new RoundedButton("Locations", 20);
        btnMesLocations.addActionListener(e -> switchToPanel("Locations"));
        btnMesLocations.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesLocations);
        btnMesLocations.setBackground(new Color(63, 173, 108));

        RoundedButton btnMesFactures = new RoundedButton("Factures", 20);
        btnMesFactures.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        btnMesFactures.addActionListener(e -> switchToPanel("Factures"));
        panelMenuGauche.add(btnMesFactures);
        btnMesFactures.setBackground(new Color(63, 173, 108));

        RoundedButton btnMesAssurances = new RoundedButton("Assurances", 20);
        btnMesAssurances.addActionListener(e -> switchToPanel("Assurances"));
        btnMesAssurances.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        btnMesAssurances.setBackground(new Color(63, 173, 108));
        panelMenuGauche.add(btnMesAssurances);

        RoundedButton btnMesDocuments = new RoundedButton("Documents", 20);
        btnMesDocuments.addActionListener(e -> switchToPanel("Documents"));
        btnMesDocuments.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        btnMesDocuments.setBackground(new Color(63, 173, 108));
        panelMenuGauche.add(btnMesDocuments);

        RoundedButton btnMesArchives = new RoundedButton("Archives", 20);
        btnMesArchives.addActionListener(e -> switchToPanel("Archives"));
        btnMesArchives.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        btnMesArchives.setBackground((new Color(63, 173, 108)));
        panelMenuGauche.add(btnMesArchives);
        
        RoundedButton btnMesLogements = new RoundedButton("Logements", 20);
        btnMesLogements.addActionListener(e -> switchToPanel("Logements"));
        btnMesLogements.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        btnMesLogements.setBackground(new Color(63, 173, 108));
        panelMenuGauche.add( btnMesLogements);
        

        JPanel panelMenuDroite = new JPanel();
        panelMenuDroite.setBackground(new Color(33, 153, 88));
        panelMenu.add(panelMenuDroite, BorderLayout.EAST);
        
     // Chargez l'image depuis les ressources
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/domicile_1.png"));

        // Redimensionnez l'image
        Image image = icon.getImage(); // Récupère l'image brute
        Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Taille réduite à 30x30 pixels
        ImageIcon scaledIcon = new ImageIcon(scaledImage); // Crée un nouvel ImageIcon avec l'image redimensionnée

        // Créez le JLabel avec l'image redimensionnée
        JLabel lblImageMaison = new JLabel(scaledIcon);

        // Ajoutez un `MouseListener` pour rediriger vers le panneau "Accueil" en cliquant
        lblImageMaison.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Redirige vers le panneau "Accueil"
                switchToPanel("Accueil");
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                lblImageMaison.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change le curseur au survol
            }
        });

        // Ajoutez le JLabel au panneau
        panelMenuDroite.add(lblImageMaison);

        
        

     

        RoundedButton btnSeDeconnecter = new RoundedButton("Se Déconnecter", 20);
        btnSeDeconnecter.setText("Deconnexion");
        btnSeDeconnecter.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        btnSeDeconnecter.setBackground(new Color(63, 173, 108));
        panelMenuDroite.add(btnSeDeconnecter);
        btnSeDeconnecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Afficher une popup de confirmation
                int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    "Êtes-vous sûr de vouloir vous déconnecter ?",
                    "Confirmation de déconnexion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                // Vérifier la réponse de l'utilisateur
                if (confirmation == JOptionPane.YES_OPTION) {
                    // Fermer l'application
                    System.exit(0);
                }
            }
        });

        
    


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
        panelAccueil.setLayout(null);

        // Bandeau supérieur
        JPanel panelTitre = new JPanel();
        panelTitre.setBackground(new Color(240, 240, 240));
        panelTitre.setLayout(null);
        JLabel lblMesAssurances = new JLabel("Accueil");
        lblMesAssurances.setBounds(159, 0, 625, 48);
        lblMesAssurances.setHorizontalAlignment(SwingConstants.CENTER);
        lblMesAssurances.setFont(new Font("Sylfaen", Font.BOLD, 28));
        lblMesAssurances.setForeground(new Color(31, 153, 88));
        lblMesAssurances.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lblMesAssurances);

        JSeparator separatorNord = new JSeparator();
        separatorNord.setBounds(0, 48, 1056, 2);
        separatorNord.setForeground(new Color(0, 0, 0));
        panelTitre.add(separatorNord);

        panelTitre.setBounds(0, 0, 1056, 50);
        panelAccueil.add(panelTitre);

        // Initialisation des éléments principaux
        currentDate = LocalDate.now(); // Date actuelle

        // Panneau supérieur avec les boutons pour naviguer entre les mois
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBounds(43, 80, 386, 40);
        headerPanel.setLayout(null);

        // Bouton "Précédent"
        JButton prevButton = new JButton("<");
        prevButton.setBounds(0, 10, 50, 20);
        prevButton.setFocusPainted(false);
        prevButton.setBackground(new Color(31, 151, 83)); // Vert
        prevButton.setForeground(Color.WHITE); // Texte blanc
        prevButton.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(prevButton);

        // Bouton "Suivant"
        JButton nextButton = new JButton(">");
        nextButton.setBounds(336, 10, 50, 20);
        nextButton.setFocusPainted(false);
        nextButton.setBackground(new Color(31, 151, 83)); // Vert
        nextButton.setForeground(Color.WHITE); // Texte blanc
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(nextButton);

        // Label pour afficher le mois et l'année
        monthYearLabel = new JLabel("", JLabel.CENTER);
        monthYearLabel.setBounds(60, 10, 270, 20);
        monthYearLabel.setFont(new Font("Arial", Font.BOLD, 13));
        headerPanel.add(monthYearLabel);

        // Ajouter le panneau supérieur au centre
        panelAccueil.add(headerPanel);

        // Panneau pour afficher les jours
        daysPanel = new JPanel();
        daysPanel.setBackground(Color.WHITE);
        daysPanel.setBounds(43, 120, 386, 363);
        daysPanel.setLayout(new GridLayout(0, 7)); // 7 colonnes pour les jours
        daysPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Bordure noire
        panelAccueil.add(daysPanel);

        // Trait vertical
        JSeparator separatorVertical = new JSeparator();
        separatorVertical.setOrientation(SwingConstants.VERTICAL);
        separatorVertical.setForeground(Color.WHITE);
        separatorVertical.setBackground(new Color(31, 151, 83));
        separatorVertical.setBounds(470, 63, 11, 450);
        panelAccueil.add(separatorVertical);

        // Panneau pour le premier graphique
        JPanel graphPanel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fond du graphique
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Dessiner la grille
                g2d.setColor(Color.LIGHT_GRAY);
                for (int i = 1; i <= 4; i++) {
                    int y = getHeight() - 50 - i * 40;
                    g2d.drawLine(50, y, getWidth() - 50, y);
                }

                // Dessiner les valeurs sur l'axe Y
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                for (int i = 0; i <= 4; i++) {
                    int y = getHeight() - 50 - i * 40;
                    g2d.drawString(String.valueOf(i * 1000), 20, y + 5);
                }

                // Dessiner un graphique simple
                g2d.setColor(new Color(31, 151, 83)); // Vert
                g2d.fillRect(100, getHeight() - 110, 80, 70); // Barre 1
                g2d.fillRect(250, getHeight() - 150, 80, 110); // Barre 2
                g2d.fillRect(400, getHeight() - 90, 80, 50); // Barre 3

                // Ajouter les étiquettes sous les barres (axe X)
                g2d.setColor(Color.BLACK);
                g2d.drawString("Jan", 120, getHeight() - 20);
                g2d.drawString("Feb", 270, getHeight() - 20);
                g2d.drawString("Mar", 420, getHeight() - 20);

                // Titre du graphique
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                g2d.drawString("Revenus par Mois", getWidth() / 2 - 60, 15);
            }
        };
        graphPanel1.setBounds(500, 80, 500, 203);
        graphPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Bordure noire
        graphPanel1.setBackground(Color.WHITE);
        panelAccueil.add(graphPanel1);

        // Panneau pour le deuxième graphique
        JPanel graphPanel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fond du graphique
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Dessiner la grille
                g2d.setColor(Color.LIGHT_GRAY);
                for (int i = 1; i <= 4; i++) {
                    int y = getHeight() - 50 - i * 40;
                    g2d.drawLine(50, y, getWidth() - 50, y);
                }

                // Dessiner les valeurs sur l'axe Y
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                for (int i = 0; i <= 4; i++) {
                    int y = getHeight() - 50 - i * 40;
                    g2d.drawString(String.valueOf(i * 10), 20, y + 5);
                }

                // Dessiner un graphique simple
                g2d.setColor(new Color(31, 151, 83)); // Vert
                g2d.fillRect(150, getHeight() - 130, 80, 90); // Barre "Occupé"
                g2d.setColor(Color.GRAY); // Gris clair pour "Non Occupé"
                g2d.fillRect(350, getHeight() - 170, 80, 130); // Barre "Non Occupé"

                // Ajouter les étiquettes sous les barres (axe X)
                g2d.setColor(Color.BLACK);
                g2d.drawString("Occupé", 160, getHeight() - 20);
                g2d.drawString("Non Occupé", 360, getHeight() - 20);


                // Titre du graphique
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                g2d.drawString("Pourcentage de Biens Occupés vs Non Occupés", 60, 15);
            }
        };
        graphPanel2.setBounds(500, 287, 500, 203);
        graphPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Bordure noire
        graphPanel2.setBackground(Color.WHITE);
        panelAccueil.add(graphPanel2);

        // Gestionnaires d'événements pour les boutons de navigation
        prevButton.addActionListener(e -> {
            currentDate = currentDate.minusMonths(1);
            updateCalendar();
        });

        nextButton.addActionListener(e -> {
            currentDate = currentDate.plusMonths(1);
            updateCalendar();
        });

        // Initialiser le calendrier
        updateCalendar();

        return panelAccueil;
    }

    private void updateCalendar() {
        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        monthYearLabel.setText(yearMonth.getMonth().name() + " " + yearMonth.getYear());

        daysPanel.removeAll();

        String[] daysOfWeek = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
        for (String day : daysOfWeek) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 12));
            daysPanel.add(dayLabel);
        }

        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int dayOfWeekValue = firstDayOfMonth.getDayOfWeek().getValue() % 7;
        for (int i = 0; i < dayOfWeekValue; i++) {
            daysPanel.add(new JLabel(""));
        }

        int daysInMonth = yearMonth.lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            JButton dayButton = new JButton(String.valueOf(day));
            dayButton.setFocusPainted(false);
            dayButton.setBackground(Color.WHITE);
            dayButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            dayButton.setFont(new Font("Arial", Font.PLAIN, 12));

            if (currentDate.getDayOfMonth() == day && LocalDate.now().getMonth() == yearMonth.getMonth()
                    && LocalDate.now().getYear() == yearMonth.getYear()) {
                dayButton.setBackground(new Color(31, 151, 83));
                dayButton.setForeground(Color.WHITE);
            }

            daysPanel.add(dayButton);
        }

        daysPanel.revalidate();
        daysPanel.repaint();
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
        lblMesAssurances.setFont(new Font("Sylfaen", Font.BOLD, 28));
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

        // Ajouter le tableau
        tabMesAssurances = new JTable();
        tabMesAssurances.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null, null, null, null } },
            new String[] { "Numéro police", "Montant", "Date échéance", "Entreprise", "Logement", "Bien" }
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
        btnCharger.setBackground(new Color(31, 153, 88));
        panelSud.add(btnCharger);

        RoundedButton btnInserer = new RoundedButton("Inserer", 20);
        btnInserer.addActionListener(this.gestionClicAssurances);
        btnInserer.setBounds(520, 11, 81, 23);
        btnInserer.setBackground(new Color(31, 153, 88));
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
 		lblLocations.setFont(new Font("Sylfaen", Font.BOLD, 28));
 		lblLocations.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
 		panelTitre.add(lblLocations, BorderLayout.CENTER);
 		
 		JSeparator separator = new JSeparator();
 		separator.setForeground(new Color(0,0,0));
		panelTitre.add(separator, BorderLayout.SOUTH);
    	 
    	 JPanel panelCentre = new JPanel();
    	 panelCentre.setBackground(new Color(255, 255, 255));
    	 panelLocations.add(panelCentre, BorderLayout.CENTER);
    	 panelCentre.setLayout(null);
    	 
    	 tabMesLocations = new JTable();
    	 tabMesLocations.getSelectionModel().addListSelectionListener(this.gestionClicLocation);
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
       	  btnICharger.setBackground(new Color(31, 153, 88));
       	  panel.add(btnICharger);
       	  
       	  RoundedButton btnInserer = new RoundedButton("Inserer", 20);
       	  btnInserer.addActionListener(this.gestionClicLocation);
       	  btnInserer.setBounds(405, 11, 80, 23);
       	  btnInserer.setBackground(new Color(31, 153, 88));
       	  panel.add(btnInserer);
       	  
       	  RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
       	  btnSupprimer.addActionListener(this.gestionClicLocation);
       	  btnSupprimer.setBounds(524, 11, 96, 23);
       	  btnSupprimer.setBackground(new Color(31, 153, 88));
       	  panel.add(btnSupprimer);
       	  
       	  
    	  RoundedButton btnLocataire = new RoundedButton("Locataire", 20);
    	  btnLocataire.addActionListener(this.gestionClicLocation);
    	  btnLocataire.setBackground(new Color(33, 153, 88));
    	  btnLocataire.setText("Informations locataire");
    	  btnLocataire.setBounds(53, 11, 161, 23);
    	  panelCentre.add(btnLocataire);
    	  
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
        lblFactures.setFont(new Font("Sylfaen", Font.BOLD, 28));
        lblFactures.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lblFactures, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        panelTitre.add(separator, BorderLayout.SOUTH);

        JPanel panelCentre = new JPanel();
        panelCentre.setBackground(new Color(255, 255, 255));
        panelFactures.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        tabMesFactures = new JTable();
        tabMesFactures.getSelectionModel().addListSelectionListener(this.gestionClicFacture);
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
        btnArchiver.setBounds(400, 11, 85, 23);
        btnArchiver.addActionListener(this.gestionClicFacture);
        btnArchiver.setBackground(new Color(31, 153, 88));
        panel.add(btnArchiver);

        RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
        btnSupprimer.setBounds(531, 11, 96, 23);
        btnSupprimer.addActionListener(this.gestionClicFacture);
        btnSupprimer.setBackground(new Color(31, 153, 88));
        panel.add(btnSupprimer);
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.addActionListener(this.gestionClicFacture);
        btnCharger.setBounds(268, 11, 85, 23);
        btnCharger.setBackground(new Color(31, 153, 88));
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
        lblFactures.setFont(new Font("Sylfaen", Font.BOLD, 28));
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
        tabMesBiens.getSelectionModel().addListSelectionListener(this.gestionClicBien);
        tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null, null, null },
            },
            new String[] {
                "IDBien", "Adresse", "Ville", "Code postal", "Type Bien", "période construction"
            }
        ));
        JScrollPane scrollPaneBiens = new JScrollPane(tabMesBiens);
        scrollPaneBiens.setBounds(46, 60, 708, 375);
        scrollPaneBiens.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPaneBiens);

        JPanel panel = new JPanel();
        panel.setBounds(0, 501, 1024, 48);
        panelCentre.add(panel);
        panel.setLayout(null);

        RoundedButton btnIArchiver = new RoundedButton("Archiver", 20);
        btnIArchiver.addActionListener(this.gestionClicBien);
        btnIArchiver.setBounds(402, 11, 85, 23);
        btnIArchiver.setBackground(new Color(33, 153, 88));
        panel.add(btnIArchiver);

        RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
        btnSupprimer.addActionListener(this.gestionClicBien);
        btnSupprimer.setBounds(528, 11, 96, 23);
        btnSupprimer.setBackground(new Color(33, 153, 88));
        panel.add(btnSupprimer);
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.addActionListener(this.gestionClicBien);
        btnCharger.setBounds(274, 11, 85, 23);
        btnCharger.setBackground(new Color(33, 153, 88));
        panel.add(btnCharger);
        
        // Buttons
        RoundedButton btnAjouterUnBien = new RoundedButton("Ajouter un bien", 20);
        btnAjouterUnBien.addActionListener(this.gestionClicBien);
        btnAjouterUnBien.setBounds(788, 187, 173, 23);
        btnAjouterUnBien.setBackground(new Color(33, 153, 88));
        panelCentre.add(btnAjouterUnBien);
  
        RoundedButton btnAjouterFactures = new RoundedButton("Ajouter des factures", 20);
        btnAjouterFactures.addActionListener(this.gestionClicBien);
        btnAjouterFactures.setBounds(788, 240, 173, 23);
        btnAjouterFactures.setBackground(new Color(33, 153, 88));
        panelCentre.add(btnAjouterFactures);
        
        RoundedButton btnAfficherCompteur = new RoundedButton("Afficher les compteurs", 20);
        btnAfficherCompteur.addActionListener(this.gestionClicBien);
        btnAfficherCompteur.setBounds(788, 297, 173, 23);
        btnAfficherCompteur.setBackground(new Color(33, 153, 88));
        panelCentre.add(btnAfficherCompteur);
        
        RoundedButton btnAjouterDiagnostic = new RoundedButton("Ajouter un diagnostic", 20);
        btnAjouterDiagnostic.addActionListener(this.gestionClicBien);
        btnAjouterDiagnostic.setBackground(new Color(31, 153, 88));
        btnAjouterDiagnostic.setBounds(788, 138, 173, 23);
        panelCentre.add(btnAjouterDiagnostic);
        
        RoundedButton btnGenererWord = new RoundedButton("Modifier", 20);
        btnGenererWord.setText("Generer un word");
        btnGenererWord.addActionListener(this.gestionClicBien);
        btnGenererWord.setBackground(new Color(31, 153, 88));
        btnGenererWord.setBounds(46, 11, 162, 23);
        panelCentre.add(btnGenererWord);
        
        RoundedButton rndbtnAjouterUneCharge = new RoundedButton("Ajouter une charge", 20);
        rndbtnAjouterUneCharge.addActionListener(gestionClicBien);
        rndbtnAjouterUneCharge.setBackground(new Color(33, 153, 88));
        rndbtnAjouterUneCharge.setBounds(788, 343, 173, 23);
        panelCentre.add(rndbtnAjouterUneCharge);
        
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
        lblFactures.setFont(new Font("Sylfaen", Font.BOLD, 28));
        lblFactures.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lblFactures, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        panelTitre.add(separator, BorderLayout.SOUTH);

        JPanel panelCentre = new JPanel();
        panelCentre.setBackground(new Color(255, 255, 255));
        panelLogements.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        // JTable pour "Logement"
        tabMesLogements = new JTable();
        tabMesLogements.getSelectionModel().addListSelectionListener(this.gestionClicLogement);
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
        btnArchiver.setBackground(new Color(31, 153, 88));
        btnArchiver.setBounds(417, 11, 85, 23);
        panel.add(btnArchiver);

        RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
        btnSupprimer.addActionListener(this.gestionClicLogement);
        btnSupprimer.setBounds(583, 11, 96, 23);
        btnSupprimer.setBackground(new Color(31, 153, 88));
        panel.add(btnSupprimer);
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.addActionListener(this.gestionClicLogement);
        btnCharger.setBounds(252, 11, 85, 23);
        btnCharger.setBackground(new Color(31, 153, 88));
        panel.add(btnCharger);
        
        // Buttons
        RoundedButton btnAjouterLogement = new RoundedButton("Ajouter un logement", 20);
        btnAjouterLogement.addActionListener(this.gestionClicLogement);
        btnAjouterLogement.setBounds(728, 182, 173, 23);
        btnAjouterLogement.setBackground(new Color(31, 153, 88));
        panelCentre.add(btnAjouterLogement);
        
        RoundedButton btnAjouterFactures = new RoundedButton("Ajouter des factures", 20);
        btnAjouterFactures.addActionListener(this.gestionClicLogement);
        btnAjouterFactures.setBounds(728, 277, 173, 23);
        btnAjouterFactures.setBackground(new Color(31, 153, 88));
        panelCentre.add(btnAjouterFactures);
        
        
        RoundedButton btnAfficherCompteur = new RoundedButton("Afficher les compteurs", 20);
        btnAfficherCompteur.addActionListener(this.gestionClicLogement);
        btnAfficherCompteur.setBounds(728, 231, 173, 23);
        btnAfficherCompteur.setBackground(new Color(31, 153, 88));
        panelCentre.add(btnAfficherCompteur);
        

        RoundedButton btnAjouterQuotite = new RoundedButton("Ajouter Quotite", 20);
        btnAjouterQuotite.addActionListener(this.gestionClicLogement);
        btnAjouterQuotite.setBackground(new Color(31, 153, 88));
        btnAjouterQuotite.setBounds(728, 136, 173, 23);
        panelCentre.add(btnAjouterQuotite);

        RoundedButton btnGenererWord = new RoundedButton("Ajouter un logement", 20);
        btnGenererWord.addActionListener(this.gestionClicLogement);
        btnGenererWord.setText("Générer un word");
        btnGenererWord.setBackground(new Color(31, 153, 88));
        btnGenererWord.setBounds(47, 38, 173, 23);
        panelCentre.add(btnGenererWord);

        
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
        lblFactures.setFont(new Font("Sylfaen", Font.BOLD, 28));
        lblFactures.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lblFactures, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        panelTitre.add(separator, BorderLayout.SOUTH);

        JPanel panelCentre = new JPanel();
        panelCentre.setBorder(new TitledBorder(null, "Logement", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        panelCentre.setBackground(new Color(255, 255, 255));
        panelArchives.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        // JTable pour "Bien"
        tabFactureArchives = new JTable();
        tabFactureArchives.setModel(new DefaultTableModel(
        	new Object[][] {
        		,
        	},
        	new String[] {
        		"Num\u00E9ro", "Date Paiement", "Montant pay\u00E9", "Désignations", "Date Emission"
        	}
        ));
        JScrollPane scrollPaneFacture = new JScrollPane(tabFactureArchives);
        scrollPaneFacture.setEnabled(false);
        scrollPaneFacture.setBounds(46, 44, 898, 113);
        scrollPaneFacture.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(31, 153, 88), new Color(160, 160, 160)), "Factures", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelCentre.add(scrollPaneFacture);
        
        // JTable pour "Bien"
        tabBienArchives = new JTable();
        tabBienArchives.setModel(new DefaultTableModel(
        	new Object[][] {
        		,
        	},
        	new String[] {
        		"Id Bien", "Adresse", "Ville", "Code Postal", "Type de Bien"
        	}
        ));
        
        JScrollPane scrollPaneLocataire = new JScrollPane(tabBienArchives);
        scrollPaneLocataire.setEnabled(false);
        scrollPaneLocataire.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(31, 153, 88), new Color(160, 160, 160)), "Biens", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        scrollPaneLocataire.setBounds(46, 184, 898, 113);
        panelCentre.add(scrollPaneLocataire);
        
        // JTable pour "Bien"
        tabLogementArchives = new JTable();
        tabLogementArchives.setModel(new DefaultTableModel(
        	new Object[][] {
        		,
        	},
        	new String[] {
        		"Id Bien", "Surface habitable", "Date acquisition", "Type Logement", "Nombre de piéces"
        	}
        ));
        
        JScrollPane scrollPaneLocation = new JScrollPane(tabLogementArchives);
        scrollPaneLocation.setEnabled(false);
        scrollPaneLocation.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(31, 153, 88), new Color(160, 160, 160)), "Logements", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        scrollPaneLocation.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        scrollPaneLocation.setBounds(46, 331, 898, 113);
        panelCentre.add(scrollPaneLocation);

        JPanel panel = new JPanel();
        panel.setBounds(0, 501, 1024, 48);
        panelCentre.add(panel);
        panel.setLayout(null);
        
        
        return panelArchives;
    		
    }
    
    
    private JPanel createDocumentPanel() throws SQLException {
    	

    	this.gestionFenDocument = new GestionFenDocument(this);
    	
    	JPanel paneldocuments = new JPanel();
		paneldocuments.setLayout(new BorderLayout(0, 0));

        JPanel panelTitre = new JPanel();
        paneldocuments.add(panelTitre, BorderLayout.NORTH);
        panelTitre.setLayout(new BorderLayout(0, 0));

        JLabel lbldocuments = new JLabel("Mes Documents");
        lbldocuments.setHorizontalAlignment(SwingConstants.CENTER);
        lbldocuments.setForeground(new Color(31, 153, 88));
        lbldocuments.setFont(new Font("Sylfaen", Font.BOLD, 28));
        lbldocuments.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lbldocuments, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        panelTitre.add(separator, BorderLayout.SOUTH);

        JPanel panelCentre = new JPanel();
        panelCentre.setBackground(new Color(255, 255, 255));
        paneldocuments.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        tabMesdocuments = new JTable();
        tabMesdocuments.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null, null, null, null, null, null },
            },
            new String[] {
                "IDLogement", "IDLocataire", "Date","Loyer","Provision"
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
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.setBounds(451, 11, 85, 23);
        btnCharger.addActionListener(this.gestionFenDocument);
        btnCharger.setBackground((new Color(31, 153, 88)));
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
    
    public void settextFieldLoyer(String x) {
    	this.textFieldLoyer.setText(x);
    }
    
    public void settextFieldProvisionSurCharges(String x) {
    	this.textFieldProvisionSurCharges.setText(x);
    }
    
    public void settextFieldMontantPayé(String x) {
    	this.textFieldMontantPayé.setText(x);
    }
    
    public void settextFieldRestantDu(String x) {
    	this.textFieldRestantDu.setText(x);
    }
    
    public void settextFieldDateEmission(String x) {
    	this.textFieldDateEmission.setText(x);
    }
    
    public void settextFieldDatePaiement(String x) {
    	this.textFieldDatePaiement.setText(x);
    }
    
    public void settextFieldCaution(String x) {
    	this.textFieldCaution.setText(x);
    }
    
    public JTable getTabMesdocuments() {
    	return tabMesdocuments;
    }
    
    public JTable getTabFactureArchives() {
    	return tabFactureArchives;
    }
    
    public JTable getTabBienArchives() {
    	return tabBienArchives;
    }
    
    public JTable gettabLogementArchives() {
    	return tabLogementArchives;
    }
}
