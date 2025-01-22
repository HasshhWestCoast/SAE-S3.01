package Vue.Insertion;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controleur.Ajout.GestionFenAjoutAssurance;
import Vue.RoundedButton;

public class FenAjoutAssurance extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private GestionFenAjoutAssurance gestionClicFenAjoutEntreprise;
	private JTable tabMesEntreprise;
	private JTable tabMesLogements;
	private JTable tabMesBiens;
	private JTextField textFieldNumeroPolice;
	private JTextField textFieldMontant;
	private JTextField textFieldDateEcheance;
	private JTextField textFieldProtectionJuridique;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FenAjoutAssurance() throws SQLException {
		
		this.gestionClicFenAjoutEntreprise = new GestionFenAjoutAssurance(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblTrouverEntreprise = new JLabel("Trouver Entreprise");
		lblTrouverEntreprise.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverEntreprise.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverEntreprise.setBounds(71, 11, 131, 21);
		getContentPane().add(lblTrouverEntreprise);
		
		JSeparator separatorTrouverEntreprise = new JSeparator();
		separatorTrouverEntreprise.setForeground(Color.WHITE);
		separatorTrouverEntreprise.setBackground(new Color(31, 151, 83));
		separatorTrouverEntreprise.setBounds(36, 33, 188, 2);
		getContentPane().add(separatorTrouverEntreprise);
	
		tabMesEntreprise = new JTable();
        tabMesEntreprise.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutEntreprise);
		tabMesEntreprise.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "SIRET", "nom" }
        ));
        JScrollPane scrollPaneEntreprise = new JScrollPane(tabMesEntreprise);
        scrollPaneEntreprise.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneEntreprise.setBounds(10, 60, 306, 134);        
		getContentPane().add(scrollPaneEntreprise);
		
		JLabel lblTrouverLogement = new JLabel("Trouver Logement");
		lblTrouverLogement.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverLogement.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverLogement.setBounds(684, 234, 131, 21);
		getContentPane().add(lblTrouverLogement);
		
		JSeparator separatorTrouverLogement = new JSeparator();
		separatorTrouverLogement.setForeground(Color.WHITE);
		separatorTrouverLogement.setBackground(new Color(31, 151, 83));
		separatorTrouverLogement.setBounds(653, 253, 188, 2);
		getContentPane().add(separatorTrouverLogement);
		
		tabMesLogements = new JTable();
		tabMesLogements.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutEntreprise);
		tabMesLogements.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "Id Logement", "Date Acquisition" }
        ));
		
		JScrollPane scrollPaneLogement = new JScrollPane(tabMesLogements);
		scrollPaneLogement.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneLogement.setBounds(579, 279, 306, 134);
		getContentPane().add(scrollPaneLogement);
		
		RoundedButton btnChargerEntreprise = new RoundedButton("Charger", 20);
		btnChargerEntreprise.setText("Charger les tableaux");
		btnChargerEntreprise.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnChargerEntreprise.setBounds(633, 41, 182, 21);
		btnChargerEntreprise.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnChargerEntreprise);
		
		RoundedButton btnInsererEntreprise = new RoundedButton("Inserer E", 20);
		btnInsererEntreprise.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnInsererEntreprise.setBounds(176, 225, 107, 21);
		btnInsererEntreprise.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnInsererEntreprise);
		
		RoundedButton btnAnnulerAssurance = new RoundedButton("Annuler", 20);
		btnAnnulerAssurance.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnAnnulerAssurance.setBounds(334, 390, 85, 21);
		btnAnnulerAssurance.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAnnulerAssurance);
		
		RoundedButton btnAjouterAssurance = new RoundedButton("Ajouter", 20);
		btnAjouterAssurance.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnAjouterAssurance.setBounds(449, 390, 85, 21);
		btnAjouterAssurance.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAjouterAssurance);
		
		RoundedButton btnInsererSupprimer = new RoundedButton("Supprimer", 20);
		btnInsererSupprimer.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnInsererSupprimer.setBounds(36, 225, 107, 21);
		btnInsererSupprimer.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnInsererSupprimer);
		
		JLabel lblAjoutAssurance = new JLabel("Ajout Assurance");
		lblAjoutAssurance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutAssurance.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutAssurance.setBounds(383, 11, 131, 21);
		getContentPane().add(lblAjoutAssurance);
		
		JSeparator separatorAjoutAssurance = new JSeparator();
		separatorAjoutAssurance.setForeground(Color.WHITE);
		separatorAjoutAssurance.setBackground(new Color(31, 151, 83));
		separatorAjoutAssurance.setBounds(365, 33, 188, 2);
		getContentPane().add(separatorAjoutAssurance);
		
		textFieldNumeroPolice = new JTextField();
		textFieldNumeroPolice.setColumns(10);
		textFieldNumeroPolice.setBounds(334, 83, 207, 19);
		getContentPane().add(textFieldNumeroPolice);
		
		JLabel lblNumeroPolice = new JLabel("Numero police");
		lblNumeroPolice.setBounds(334, 60, 96, 13);
		getContentPane().add(lblNumeroPolice);
		
		textFieldMontant = new JTextField();
		textFieldMontant.setColumns(10);
		textFieldMontant.setBounds(334, 150, 207, 19);
		getContentPane().add(textFieldMontant);
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setBounds(334, 127, 96, 13);
		getContentPane().add(lblMontant);
		
		textFieldDateEcheance = new JTextField();
		textFieldDateEcheance.setColumns(10);
		textFieldDateEcheance.setBounds(334, 206, 207, 19);
		getContentPane().add(textFieldDateEcheance);
		
		JLabel lblDateEcheance = new JLabel("Date échéance");
		lblDateEcheance.setBounds(334, 178, 85, 13);
		getContentPane().add(lblDateEcheance);
		
		JLabel lblProtectionJuridique = new JLabel("Protection Jurdique");
		lblProtectionJuridique.setBounds(334, 242, 139, 13);
		getContentPane().add(lblProtectionJuridique);
		
		textFieldProtectionJuridique = new JTextField();
		textFieldProtectionJuridique.setColumns(10);
		textFieldProtectionJuridique.setBounds(334, 265, 207, 19);
		getContentPane().add(textFieldProtectionJuridique);
		
		tabMesBiens = new JTable();
		tabMesBiens.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutEntreprise);
		tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "Id Bien", "Ville" }
        ));
		
		JScrollPane scrollPaneBien = new JScrollPane(tabMesBiens);
		scrollPaneBien.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneBien.setBounds(579, 84, 306, 134);
		getContentPane().add(scrollPaneBien);
		
		JLabel lblTrouverBien = new JLabel("Trouver Bien");
		lblTrouverBien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverBien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverBien.setBounds(653, 11, 131, 21);
		getContentPane().add(lblTrouverBien);
		
		JSeparator separatorTrouverBien = new JSeparator();
		separatorTrouverBien.setForeground(Color.WHITE);
		separatorTrouverBien.setBackground(new Color(31, 151, 83));
		separatorTrouverBien.setBounds(633, 30, 188, 2);
		getContentPane().add(separatorTrouverBien);
	}
	
	public JTable getTabMesEntreprise() {
		return tabMesEntreprise;
	}
	
	public JTable getTabMesLogements() {
		return tabMesLogements;
	}
	
	public JTable getTabMesBiens() {
		return tabMesBiens;
	}
	
	public String getNumeroPolice() {
		return textFieldNumeroPolice.getText();
	}
	
	public String getMontant() {
		return textFieldMontant.getText();
	}
	
	public String getDateEcheance() {
		return textFieldDateEcheance.getText();
	}
	
	public String getProtectionJuridique() {
		return textFieldProtectionJuridique.getText();
	}
}
