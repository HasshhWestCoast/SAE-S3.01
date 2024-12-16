package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;



import java.awt.Dimension;
import java.awt.EventQueue;

public class Fenetre_Accueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLayeredPane layeredPane_MesArchives;




	
	private JPanel panel_5;
	private JTable table_MesArchives_Locataire;
	private JTable table_MesArchives_Louer;
	private JTable table_MesArchives_Facture;
	private JPanel panel_MesArchives;
	
	
	
	// Méthode principale pour lancer l'application
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Création et affichage de la fenêtre principale
                    Fenetre_Accueil frame = new Fenetre_Accueil();
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
	public Fenetre_Accueil() {
		
	
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 970, 646);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_Menu = new JPanel();
		panel_Menu.setBackground(Color.LIGHT_GRAY);
		this.contentPane.add(panel_Menu, BorderLayout.WEST);
		panel_Menu.setLayout(new BorderLayout(0, 0));

		JPanel panel_Menu_Boutons = new JPanel();
		panel_Menu_Boutons.setBackground(Color.LIGHT_GRAY);
		panel_Menu.add(panel_Menu_Boutons, BorderLayout.CENTER);
		panel_Menu_Boutons.setLayout(new GridLayout(6, 1, 0, 0));

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MENU DE BOUTONS SUR LE CÔTE
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JButton btnMesBiens = new JButton("Mes Biens");
	
		btnMesBiens.setBackground(new Color(0, 128, 0));
		btnMesBiens.setForeground(new Color(255, 255, 255));
		btnMesBiens.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesBiens.setName("btnMesBiens");
		panel_Menu_Boutons.add(btnMesBiens);

		JButton btnMesLocations = new JButton("Mes Locations");
		btnMesLocations.setForeground(new Color(255, 255, 255));
		
		btnMesLocations.setBackground(new Color(0, 128, 0));
		btnMesLocations.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesLocations.setName("btnMesLocations");
		panel_Menu_Boutons.add(btnMesLocations);

		JButton btnMesChargesLocatives = new JButton("Mes Factures");
		btnMesChargesLocatives.setForeground(new Color(255, 255, 255));
		
		btnMesChargesLocatives.setBackground(new Color(0, 128, 0));
		btnMesChargesLocatives.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesChargesLocatives.setName("btnMesChargesLocatives");
		panel_Menu_Boutons.add(btnMesChargesLocatives);

		JButton btnMesAssurances = new JButton("Mes Assurances");
		btnMesAssurances.setForeground(new Color(255, 255, 255));
	
		btnMesAssurances.setBackground(new Color(0, 128, 0));
		btnMesAssurances.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesAssurances.setName("btnMesAssurances");
		panel_Menu_Boutons.add(btnMesAssurances);

		JButton btnMesDocuments = new JButton("Mes Documents");
		btnMesDocuments.setForeground(new Color(255, 255, 255));
		
		btnMesDocuments.setBackground(new Color(0, 128, 0));
		btnMesDocuments.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesDocuments.setName("btnMesDocuments");
		panel_Menu_Boutons.add(btnMesDocuments);

		JButton btnMesArchives = new JButton("          Mes Archives          ");
		btnMesArchives.setMaximumSize(new Dimension(151, 21));
		btnMesArchives.setMinimumSize(new Dimension(127, 21));
		btnMesArchives.setForeground(new Color(255, 255, 255));
	
		btnMesArchives.setBackground(new Color(0, 128, 0));
		btnMesArchives.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesArchives.setName("btnMesArchives");
		panel_Menu_Boutons.add(btnMesArchives);
		

		

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
// LAYERED MES ARCHIVES
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		this.layeredPane_MesArchives = new JLayeredPane();
		this.contentPane.add(this.layeredPane_MesArchives, BorderLayout.CENTER);
		this.layeredPane_MesArchives.setLayout(new BorderLayout(0, 0));

		JPanel panel_MesArchives = new JPanel();
		panel_MesArchives.setBackground(Color.WHITE);
		this.layeredPane_MesArchives.add(panel_MesArchives);
		panel_MesArchives.setLayout(null);

		// Labels
		JLabel lbl_MesArchives = new JLabel("Mes Archives");
		lbl_MesArchives.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MesArchives.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_MesArchives.setBounds(244, 22, 216, 43);
		panel_MesArchives.add(lbl_MesArchives);

		// Separators
		JSeparator separator_MesArchives = new JSeparator();
		separator_MesArchives.setForeground(new Color(0, 128, 0));
		separator_MesArchives.setBounds(258, 63, 190, 2);
		panel_MesArchives.add(separator_MesArchives);
		
		
		//////////////////////// Facture ////////////////////////////////
		// Label Facture
		JLabel lbl_Archive_Facture = new JLabel("Facture");
		lbl_Archive_Facture.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Archive_Facture.setBounds(117, 63, 66, 13);
		panel_MesArchives.add(lbl_Archive_Facture);
		
		// Tableau et scroll pour Facture
		JScrollPane scrollPane_MesArchives_Facture = new JScrollPane();
		scrollPane_MesArchives_Facture.setBorder(new LineBorder(new Color(0, 128, 0), 2, true));
		scrollPane_MesArchives_Facture.setBounds(117, 81, 463, 99);
		panel_MesArchives.add(scrollPane_MesArchives_Facture);

		this.table_MesArchives_Facture = new JTable();
		this.table_MesArchives_Facture.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Numero", "Désignation", "Montant payé", "Montant", "Date Emission" }));
		scrollPane_MesArchives_Facture.setViewportView(this.table_MesArchives_Facture);
		
		// Boutons pour Facture
		JButton btn_MesArchives_Facture = new JButton("Facture");
		btn_MesArchives_Facture.setForeground(Color.WHITE);
		btn_MesArchives_Facture.setBackground(new Color(0, 128, 0));
		btn_MesArchives_Facture.setBounds(298, 449, 94, 31);
		btn_MesArchives_Facture.setName("btn_MesArchives_Facture");

		panel_MesArchives.add(btn_MesArchives_Facture);

		//////////////////////// Locataire ////////////////////////////////
		// Label locataire
		JLabel lbl_Archive_Locataire = new JLabel("Locataire");
		lbl_Archive_Locataire.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Archive_Locataire.setBounds(117, 190, 66, 13);
		panel_MesArchives.add(lbl_Archive_Locataire);
		
		// Tableau et scroll pour le bouton Locataire
		JScrollPane scrollPane_MesArchives_Locataire = new JScrollPane();
		scrollPane_MesArchives_Locataire.setBorder(new LineBorder(new Color(0, 128, 0), 2, true));
		scrollPane_MesArchives_Locataire.setBounds(117, 211, 463, 99);
		panel_MesArchives.add(scrollPane_MesArchives_Locataire);

		this.table_MesArchives_Locataire = new JTable();
		this.table_MesArchives_Locataire.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Id Locataire", "Nom", "Prenom", "Telephone", "Mail" }));
		scrollPane_MesArchives_Locataire.setViewportView(this.table_MesArchives_Locataire);

		// Bouton Locataire
		JButton btn_MesArchives_Locataire = new JButton("Locataire");
		btn_MesArchives_Locataire.setForeground(Color.WHITE);
		btn_MesArchives_Locataire.setBackground(new Color(0, 128, 0));
		btn_MesArchives_Locataire.setBounds(468, 449, 94, 31);
		btn_MesArchives_Locataire.setName("btn_MesArchives_Locataire");
		panel_MesArchives.add(btn_MesArchives_Locataire);

		//////////////////////// Location ////////////////////////////////
		
		// Tableau et scroll pour le bouton Location
		JScrollPane scrollPane_MesArchives_Louer = new JScrollPane();
		scrollPane_MesArchives_Louer.setBorder(new LineBorder(new Color(0, 128, 0), 2, true));
		scrollPane_MesArchives_Louer.setBounds(117, 338, 463, 94);
		panel_MesArchives.add(scrollPane_MesArchives_Louer);

		this.table_MesArchives_Louer = new JTable();
		this.table_MesArchives_Louer.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Id Locataire", "Id Bien", "Date Debut ", "loyer TTC", "Provision charges" }));
		scrollPane_MesArchives_Louer.setViewportView(this.table_MesArchives_Louer);

		// Bouton Louer
		JButton btn_MesArchives_Louer = new JButton("Location");
		btn_MesArchives_Louer.setForeground(Color.WHITE);
		btn_MesArchives_Louer.setBackground(new Color(0, 128, 0));
		btn_MesArchives_Louer.setBounds(117, 449, 94, 31);
		btn_MesArchives_Louer.setName("btn_MesArchives_Louer");
		panel_MesArchives.add(btn_MesArchives_Louer);

		JLabel lbl_Archive_Louer = new JLabel("Location");
		lbl_Archive_Louer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Archive_Louer.setBounds(117, 320, 66, 13);
		panel_MesArchives.add(lbl_Archive_Louer);


	}
	
	// GETTERS & SETTERS
	public JLayeredPane getLayeredPane_MesDocuments() {
		return this.layeredPane_MesDocuments;
	}

	public JLayeredPane getLayeredPane_MesArchives() {
		return this.layeredPane_MesArchives;
	}

	public JLayeredPane getLayeredPane_Accueil() {
		return this.layeredPane_Accueil;
	}

	public JLayeredPane getLayeredPane_MesLocations() {
		return this.layeredPane_MesLocations;
	}

	public JLayeredPane getLayeredPane_MesBiens() {
		return this.layeredPane_MesBiens;
	}

	public JLayeredPane getLayeredPane_MesTravaux() {
		return this.layeredPane_MesTravaux;
	}

	public JLayeredPane getLayeredPane_MesChargesLocatives() {
		return this.layeredPane_MesFactures;
	}

	public JLayeredPane getLayeredPane_MesAssurances() {
		return this.layeredPane_MesAssurances;
	}

	@Override
	public JPanel getContentPane() {
		return this.contentPane;
	}

	
	public JTable getTableBiens() {
		return this.tableMesBiens;
	}

	public JTable getTableLogementsParBien() {
		return this.tableMesBiens_Logements;
	}

	public JTable getTableLocations() {
		return this.table_MesLocations;
	}

	public JTable getTableAssurances() {
		return this.table_MesAssurances;
	}

	public JTable getTableTravaux() {
		return this.table_MesTravaux;
	}

	public JTable getTableChargesLocatives() {
		return this.table_MesFactures;
	}

	public JTable getTableDocuments() {
		return this.table_MesDocuments;
	}

	
	public JTextField getTextField_loyer() {
		return this.textField_loyer;
	}

	public JTextField getTextField_provisionCharges() {
		return this.textField_provisionCharges;
	}

	public JTextField getTextField_caution() {
		return this.textField_caution;
	}

	public JTextField getTextField_dateEmission() {
		return this.textField_dateEmission;
	}

	public JTextField getTextField_dateEcheance() {
		return this.textField_datePaiement;
	}

	public JTextField getTextField_paye() {
		return this.textField_paye;
	}

	public JTextField getTextField_restantDu() {
		return this.textField_restantDu;
	}


	public JComboBox<String> getComboBox_MesAssurances() {
		return this.comboBox_MesAssurances;
	}

	public JComboBox<String> getComboBox_MesChargesLocatives() {
		return this.comboBox_MesFactures;
	}

	public JComboBox<String> getComboBox_MesDocuments() {
		return this.comboBox_MesDocuments;
	}
	
	

	public JPanel getPanel_Accueil_graphiqueHautGauche() {
		return this.panelAccueil_graphiqueHautGauche;
	}

	public void setPanel_Accueil_graphiqueHautGauche(JPanel panel) {
		this.panelAccueil_graphiqueHautGauche = panel;
	}
	

	public JPanel getPanel_Accueil_graphiqueHautDroite() {
		return this.panelAccueil_graphiqueHautDroite;
	}
	
	public void setPanel_Accueil_graphiqueHautDroite(JPanel panel_2) {
		this.panelAccueil_graphiqueHautDroite = panel_2;
	}
	

	public JPanel getPanel_Accueil_graphiqueBasDroite() {
		return this.panelAccueil_graphiqueBasDroite;
	}

	public void setPanel_Accueil_graphiqueBasDroite(JPanel panel_3) {
		this.panelAccueil_graphiqueBasDroite = panel_3;
	}

	
	public JPanel getPanel_Accueil_graphiqueBasGauche() {
		return this.panelAccueil_graphiqueBasGauche;
	}

	public void setPanel_Accueil_graphiqueBasGauche(JPanel panel_4) {
		this.panelAccueil_graphiqueBasGauche = panel_4;
	}


	public JPanel getPanel_Accueil_mediane() {
		return this.panelAccueil_mediane;
	}
	

	public JPanel getPanel_Accueil_moyenne() {
		return this.panelAccueil_moyenne;
	}
	
	

	public JTable getTable_MesArchives_Facture() {
		return this.table_MesArchives_Facture;
	}

	public void setTable_MesArchives_Facture(JTable table_MesArchives_Facture) {
		this.table_MesArchives_Facture = table_MesArchives_Facture;
	}

	public JTable getTable_MesArchives_Louer() {
		return this.table_MesArchives_Louer;
	}

	public void setTable_MesArchives_Louer(JTable table_MesArchives_Louer) {
		this.table_MesArchives_Louer = table_MesArchives_Louer;
	}

	public JTable getTable_MesArchives_Locataire() {
		return this.table_MesArchives_Locataire;
	}

	public void setTable_MesArchives_Locataire(JTable table_MesArchives_Locataire) {
		this.table_MesArchives_Locataire = table_MesArchives_Locataire;
	}

}