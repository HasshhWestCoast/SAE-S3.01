package Vue.Insertion;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controleur.Ajout.GestionFenAjoutFacture;
import Modele.Bien;
import Vue.RoundedButton;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class FenAjoutFacture extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdFacture;
	private JTextField textFieldDateEmission;
	private JTextField textFieldDatePaiement;
	private JTextField textFieldMontantReelVerse;
	private JTextField textFieldNuméroDevis;
	private JTextField textFieldMontant;
	private JTable tabMesEntreprise;
	private JComboBox comboBoxModePaiement;
	private JComboBox comboBoxDesignation;
	private JCheckBox checkImputableLocataire;
	
	private GestionFenAjoutFacture gestionClicFenAjoutFacture; 
	private JScrollPane scrollPane;
	private JTextField textFieldAcompteVersé;
	
	private Object precedent;

	/**
	 * Create the frame.
	 * @param bien 
	 * @throws SQLException 
	 */
	public FenAjoutFacture(Object precedent) throws SQLException {
		
		this.precedent = precedent;
		gestionClicFenAjoutFacture = new GestionFenAjoutFacture(this);
				
		setBounds(50, 100, 950, 500); // Exemple de dimensions

		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblDateEmission = new JLabel("Date emission");
		lblDateEmission.setBounds(80, 130, 96, 13);
		getContentPane().add(lblDateEmission);

		JLabel lblNumeroDevis = new JLabel("Numéro Devis");
		lblNumeroDevis.setBounds(80, 302, 96, 13);
		getContentPane().add(lblNumeroDevis);
		
		JLabel lblDatePaiement = new JLabel("Date Paiemant");
		lblDatePaiement.setBounds(80, 185, 85, 13);
		getContentPane().add(lblDatePaiement);
		
		JLabel lblIdFacture = new JLabel("ID Facture");
		lblIdFacture.setBounds(80, 68, 96, 13);
		getContentPane().add(lblIdFacture);
		
		JLabel lblAjoutFacture = new JLabel("Ajout Facture");
		lblAjoutFacture.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutFacture.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutFacture.setBounds(124, 26, 131, 21);
		getContentPane().add(lblAjoutFacture);
		
		JSeparator separatorAjoutFacture = new JSeparator();
		separatorAjoutFacture.setForeground(new Color(255, 255, 255));
		separatorAjoutFacture.setBackground(new Color(31, 151, 83));
		separatorAjoutFacture.setBounds(93, 45, 188, 2);
		getContentPane().add(separatorAjoutFacture);
		
		JLabel lblModePaiement = new JLabel("Mode paiement");
		lblModePaiement.setBounds(80, 243, 90, 13);
		getContentPane().add(lblModePaiement);
		
		textFieldIdFacture = new JTextField();
		textFieldIdFacture.setBounds(80, 87, 207, 19);
		getContentPane().add(textFieldIdFacture);
		textFieldIdFacture.setColumns(10);
		
		textFieldDateEmission = new JTextField();
		textFieldDateEmission.setBounds(80, 145, 207, 19);
		getContentPane().add(textFieldDateEmission);
		textFieldDateEmission.setColumns(10);
		
		textFieldDatePaiement = new JTextField();
		textFieldDatePaiement.setBounds(80, 202, 207, 19);
		getContentPane().add(textFieldDatePaiement);
		textFieldDatePaiement.setColumns(10);
		
		textFieldMontantReelVerse = new JTextField();
		textFieldMontantReelVerse.setBounds(347, 87, 210, 19);
		getContentPane().add(textFieldMontantReelVerse);
		textFieldMontantReelVerse.setColumns(10);
		
		textFieldNuméroDevis = new JTextField();
		textFieldNuméroDevis.setBounds(80, 323, 207, 19);
		getContentPane().add(textFieldNuméroDevis);
		textFieldNuméroDevis.setColumns(10);
		
		JLabel lblMontantReelVersé = new JLabel("Montant versé");
		lblMontantReelVersé.setBounds(349, 68, 85, 13);
		getContentPane().add(lblMontantReelVersé);
		
		comboBoxModePaiement = new JComboBox();
		comboBoxModePaiement.setModel(new DefaultComboBoxModel(new String[] {"Virement bancaire", "Espéces"}));
		comboBoxModePaiement.setBounds(80, 260, 207, 21);
		getContentPane().add(comboBoxModePaiement);
		
		JLabel lblDésignation = new JLabel("Désignation");
		lblDésignation.setBounds(347, 185, 85, 13);
		getContentPane().add(lblDésignation);
		
		comboBoxDesignation = new JComboBox();
		comboBoxDesignation.setModel(new DefaultComboBoxModel(new String[] {"Travaux", "Eau", "Electricité", "Gaz", "Autre"}));
		comboBoxDesignation.setBounds(347, 201, 210, 21);
		getContentPane().add(comboBoxDesignation);
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setBounds(347, 130, 45, 13);
		getContentPane().add(lblMontant);
		
		textFieldMontant = new JTextField();
		textFieldMontant.setBounds(347, 145, 210, 19);
		getContentPane().add(textFieldMontant);
		textFieldMontant.setColumns(10);
		
		JLabel lblImputableLocataire = new JLabel("Imputable Locataire");
		lblImputableLocataire.setBounds(347, 302, 118, 13);
		getContentPane().add(lblImputableLocataire);
		
		checkImputableLocataire = new JCheckBox("OUI");
		checkImputableLocataire.setBackground(new Color(31, 151, 83));
		checkImputableLocataire.setBounds(347, 322, 93, 21);
		getContentPane().add(checkImputableLocataire);
		
		JLabel lblTrouverEntreprise = new JLabel("Trouver Entreprise");
		lblTrouverEntreprise.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverEntreprise.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverEntreprise.setBounds(672, 27, 131, 21);
		getContentPane().add(lblTrouverEntreprise);
		
		JSeparator separatorTrouverEntreprise = new JSeparator();
		separatorTrouverEntreprise.setForeground(Color.WHITE);
		separatorTrouverEntreprise.setBackground(new Color(31, 151, 83));
		separatorTrouverEntreprise.setBounds(645, 45, 188, 2);
		getContentPane().add(separatorTrouverEntreprise);
	
		tabMesEntreprise = new JTable();
		tabMesEntreprise.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutFacture);
		tabMesEntreprise.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "SIRET", "nom" }
        ));
        scrollPane = new JScrollPane(tabMesEntreprise);
        scrollPane.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPane.setBounds(645, 87, 268, 134);        
		getContentPane().add(scrollPane);
		
		RoundedButton btnInserer = new RoundedButton("Inserer", 20);
		btnInserer.addActionListener(this.gestionClicFenAjoutFacture);
		btnInserer.setBounds(737, 239, 85, 21);
		getContentPane().add(btnInserer);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.addActionListener(this.gestionClicFenAjoutFacture);
		btnAnnuler.setBounds(323, 388, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.addActionListener(this.gestionClicFenAjoutFacture);
		btnAjouter.setBounds(519, 388, 85, 21);
		getContentPane().add(btnAjouter);
		
		JLabel lblAcompteVersé = new JLabel("Acompte Versé");
		lblAcompteVersé.setBounds(347, 243, 87, 13);
		getContentPane().add(lblAcompteVersé);
		
		textFieldAcompteVersé = new JTextField();
		textFieldAcompteVersé.setColumns(10);
		textFieldAcompteVersé.setBounds(347, 261, 210, 19);
		getContentPane().add(textFieldAcompteVersé);
	}
	
	public JTable getTabMesEntreprise() {
		return tabMesEntreprise;
	}
	
	public String getTextFieldIdFacture(){
		return textFieldIdFacture.getText();
	}
	
	public String getTextFieldDateEmission() {
		return textFieldDateEmission.getText();
	}
	
	public String getTextFieldDatePaiement() {
		return textFieldDatePaiement.getText();
	}
	
	public String getTextFieldMontantReelVerse() {
		return textFieldMontantReelVerse.getText();
	}
	
	public String getTextFieldNumeroDevis() {
		return textFieldNuméroDevis.getText();
	}
	
	public String getTextFieldMontant() {
		return textFieldMontant.getText();
	}
	

	public String getComboBoxModePaiement() {
		return (String) comboBoxModePaiement.getSelectedItem();
	}
	
	public String getComboBoxDesignation() {
		return (String) comboBoxDesignation.getSelectedItem();

	}
	
	public boolean getcheckImputableLocataire(){
	    return checkImputableLocataire.isSelected() ;
	}

	public String getTextFieldAcompteVersé() {
		return textFieldAcompteVersé.getText();
	}

	public Object getPrecedent() {
		return precedent;
	}

}
