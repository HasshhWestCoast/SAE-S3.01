package Vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class FenAccueil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelAssurances;
    private JPanel panelAccueil;
    private JComboBox comboBoxIDAssurance;

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
        setSize(1050,700);

<<<<<<< HEAD
        // Centrer automatiquement la fenêtre
        setLocationRelativeTo(null);
=======
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
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
		
		RoundedButton btnMesBiens = new RoundedButton("Biens", 20);
		btnMesBiens.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesBiens);
		
		RoundedButton btnMesLoactions = new RoundedButton("Locations", 20);
		btnMesLoactions.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesLoactions);
		
		RoundedButton btnMesFactures = new RoundedButton("Factures", 20);
		btnMesFactures.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesFactures);
		
		RoundedButton btnMesAssurances = new RoundedButton("Assurances", 20);
		btnMesAssurances.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesAssurances);
		
		RoundedButton btnMesDocuments = new RoundedButton("Documents", 20);
		btnMesDocuments.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesDocuments);
		
		RoundedButton btnMesArchives = new RoundedButton("Archives", 20);
		btnMesArchives.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelMenuGauche.add(btnMesArchives);
		
		JPanel panelMenuCenter = new JPanel();
		panelMenuCenter.setBackground(new Color(33, 153, 88));
		panelMenu.add(panelMenuCenter, BorderLayout.CENTER);
		
		JPanel panelMenuDroite = new JPanel();
		panelMenuDroite.setBackground(new Color(33, 153, 88));
		panelMenu.add(panelMenuDroite, BorderLayout.EAST);
		
		RoundedButton btnSeDeconnecter = new RoundedButton("Se Déconnecter", 20);
		btnSeDeconnecter.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		panelMenuDroite.add(btnSeDeconnecter);
		
		RoundedButton btnAccueil = new RoundedButton("New button", 20);
		panelMenuDroite.add(btnAccueil);
		
		JPanel panelMenuCentre = new JPanel();
		panelMenuCentre.setBackground(new Color(255, 255, 255));
		contentPane.add(panelMenuCentre, BorderLayout.CENTER);
		panelMenuCentre.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panelNorth = new JPanel();
		panelMenuCentre.add(panelNorth);
		panelNorth.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panelNorthGauche = new JPanel();
		panelNorthGauche.setBackground(new Color(255, 255, 255));
		panelNorth.add(panelNorthGauche);
		
		JPanel panelNorthCenter = new JPanel();
		panelNorthCenter.setBackground(new Color(255, 255, 255));
		panelNorth.add(panelNorthCenter);
		panelNorthCenter.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Accueil");
		lblNewLabel.setForeground(new Color(31, 153, 88));
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorthCenter.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panelNorthDroite = new JPanel();
		panelNorthDroite.setBackground(new Color(255, 255, 255));
		panelNorth.add(panelNorthDroite);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(new Color(255, 255, 255));
		panelMenuCentre.add(panelCenter);
		panelCenter.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panelCenter.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panelCenter.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panelCenter.add(panel_2);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(new Color(255, 255, 255));
		panelMenuCentre.add(panelSouth);
	}
>>>>>>> ff5407b033f453f768acd85f6c144bc8e116fd55

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
        btnMesBiens.addActionListener(e -> switchToPanel(panelAccueil));
        btnMesBiens.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesBiens);

        RoundedButton btnMesLoactions = new RoundedButton("Locations", 20);
        btnMesLoactions.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesLoactions);

        RoundedButton btnMesFactures = new RoundedButton("Factures", 20);
        btnMesFactures.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesFactures);

        RoundedButton btnMesAssurances = new RoundedButton("Assurances", 20);
        btnMesAssurances.addActionListener(e -> switchToPanel(panelAssurances));
        btnMesAssurances.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesAssurances);

        RoundedButton btnMesDocuments = new RoundedButton("Documents", 20);
        btnMesDocuments.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesDocuments);

        RoundedButton btnMesArchives = new RoundedButton("Archives", 20);
        btnMesArchives.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        panelMenuGauche.add(btnMesArchives);

        JPanel panelMenuCenter = new JPanel();
        panelMenuCenter.setBackground(new Color(33, 153, 88));
        panelMenu.add(panelMenuCenter, BorderLayout.CENTER);

        JPanel panelMenuDroite = new JPanel();
        panelMenuDroite.setBackground(new Color(33, 153, 88));
        panelMenu.add(panelMenuDroite, BorderLayout.EAST);

        RoundedButton btnSeDeconnecter = new RoundedButton("Se Déconnecter", 20);
        btnSeDeconnecter.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        panelMenuDroite.add(btnSeDeconnecter);

        // LayeredPane for switching panels
        JLayeredPane layeredPane = new JLayeredPane();
        contentPane.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(new CardLayout());

        // Panel Accueil
        panelAccueil = new JPanel();
        panelAccueil.setBackground(Color.WHITE);
        panelAccueil.setLayout(new BorderLayout());
        JLabel lblAccueil = new JLabel("Accueil", SwingConstants.CENTER);
        lblAccueil.setFont(new Font("Sylfaen", Font.PLAIN, 24));
        lblAccueil.setForeground(new Color(31, 153, 88));
        panelAccueil.add(lblAccueil, BorderLayout.CENTER);
        layeredPane.add(panelAccueil, "Accueil");

        // Panel Assurances
        panelAssurances = createAssurancesPanel();
        layeredPane.add(panelAssurances, "Assurances");
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
        lblMesAssurances.setFont(new Font("Sylfaen", Font.ITALIC, 24));
        lblMesAssurances.setForeground(new Color(31, 153, 88));
        lblMesAssurances.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Décalage de 10px vers le bas
        panelTitre.add(lblMesAssurances, BorderLayout.CENTER);


        JSeparator separatorNord = new JSeparator();
        panelTitre.add(separatorNord, BorderLayout.SOUTH);

        panelAssurances.add(panelTitre, BorderLayout.NORTH);

        JPanel panelCentre = new JPanel();
        panelCentre.setBackground(new Color(255, 255, 255));
        panelAssurances.add(panelCentre, BorderLayout.CENTER);
        panelCentre.setLayout(null);

        // Ajouter la JComboBox en haut
        JComboBox comboBoxIDAssurance = new JComboBox();
        comboBoxIDAssurance.setBounds(0, 11, 158, 26);
        comboBoxIDAssurance.setForeground(new Color(255,255,255));
        comboBoxIDAssurance.setFont(new Font("Sylfaen", Font.PLAIN, 14));
        comboBoxIDAssurance.setBackground(new Color(31, 153, 88));
        comboBoxIDAssurance.setModel(new DefaultComboBoxModel(new String[] {"ID Assurance"}));
        comboBoxIDAssurance.setToolTipText("ID Assurance");
        panelCentre.add(comboBoxIDAssurance);

        // Ajouter le tableau
        JTable tabMesLocations = new JTable();
        tabMesLocations.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null, null, null, null, null } },
            new String[] { "Numéro police", "ID assurance", "Protection juridique", "Prime", "Quotité", "Date de début", "Date de fin" }
        ));
        JScrollPane scrollPane = new JScrollPane(tabMesLocations);
        scrollPane.setBounds(0, 48, 1024, 429);
        scrollPane.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        panelCentre.add(scrollPane);
        
                // Panel des boutons
                JPanel panelSud = new JPanel();
                panelSud.setBounds(0, 491, 1024, 64);
                panelCentre.add(panelSud);
                panelSud.setBackground(new Color(240,240,240));
                        panelSud.setLayout(null);
                
                        RoundedButton btnICharger = new RoundedButton("Charger", 20);
                        btnICharger.setBounds(392, 30, 92, 23);
                        panelSud.add(btnICharger);
                        
                                RoundedButton btnInserer = new RoundedButton("Inserer", 20);
                                btnInserer.setBounds(517, 30, 81, 23);
                                panelSud.add(btnInserer);

        return panelAssurances;
    }


    private void switchToPanel(JPanel panelToShow) {
        panelAccueil.setVisible(false);
        panelAssurances.setVisible(false);
        panelToShow.setVisible(true);
    }
}
