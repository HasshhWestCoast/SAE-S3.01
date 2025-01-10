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
	private JTextField textFieldNumeroPolice;
	private JTextField textFieldMontant;
	private JTextField textFieldDateEcheance;
	private GestionFenAjoutAssurance gestionClicFenAjoutEntreprise;
	private JTable tabMesEntreprise;
	private JTable tabMesLogements;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FenAjoutAssurance() throws SQLException {
		
		this.gestionClicFenAjoutEntreprise = new GestionFenAjoutAssurance(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setBounds(489, 127, 96, 13);
		getContentPane().add(lblMontant);
		
		JLabel lblDateEcheance = new JLabel("Date échéance");
		lblDateEcheance.setBounds(489, 179, 85, 13);
		getContentPane().add(lblDateEcheance);
		
		JLabel lblNumeroPolice = new JLabel("Numero police");
		lblNumeroPolice.setBounds(489, 60, 96, 13);
		getContentPane().add(lblNumeroPolice);
		
		JLabel lblAjoutAssurance = new JLabel("Ajout Assurance");
		lblAjoutAssurance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutAssurance.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutAssurance.setBounds(536, 14, 131, 21);
		getContentPane().add(lblAjoutAssurance);
		
		JSeparator separatorAjoutAssurance = new JSeparator();
		separatorAjoutAssurance.setForeground(new Color(255, 255, 255));
		separatorAjoutAssurance.setBackground(new Color(31, 151, 83));
		separatorAjoutAssurance.setBounds(510, 33, 188, 2);
		getContentPane().add(separatorAjoutAssurance);
		
		textFieldNumeroPolice = new JTextField();
		textFieldNumeroPolice.setBounds(489, 83, 207, 19);
		getContentPane().add(textFieldNumeroPolice);
		textFieldNumeroPolice.setColumns(10);
		
		textFieldMontant = new JTextField();
		textFieldMontant.setBounds(489, 146, 207, 19);
		getContentPane().add(textFieldMontant);
		textFieldMontant.setColumns(10);
		
		textFieldDateEcheance = new JTextField();
		textFieldDateEcheance.setBounds(489, 202, 207, 19);
		getContentPane().add(textFieldDateEcheance);
		textFieldDateEcheance.setColumns(10);
		
		JLabel lblTrouverEntreprise = new JLabel("Trouver Entreprise");
		lblTrouverEntreprise.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverEntreprise.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverEntreprise.setBounds(80, 217, 131, 21);
		getContentPane().add(lblTrouverEntreprise);
		
		JSeparator separatorTrouverEntreprise = new JSeparator();
		separatorTrouverEntreprise.setForeground(Color.WHITE);
		separatorTrouverEntreprise.setBackground(new Color(31, 151, 83));
		separatorTrouverEntreprise.setBounds(55, 236, 188, 2);
		getContentPane().add(separatorTrouverEntreprise);
	
		tabMesEntreprise = new JTable();
        tabMesEntreprise.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutEntreprise);
		tabMesEntreprise.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "SIRET", "nom" }
        ));
        JScrollPane scrollPaneEntreprise = new JScrollPane(tabMesEntreprise);
        scrollPaneEntreprise.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneEntreprise.setBounds(10, 248, 306, 134);        
		getContentPane().add(scrollPaneEntreprise);
		
		JLabel lblTrouverLogement = new JLabel("Trouver Logement");
		lblTrouverLogement.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverLogement.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverLogement.setBounds(80, 14, 131, 21);
		getContentPane().add(lblTrouverLogement);
		
		JSeparator separatorTrouverLogement = new JSeparator();
		separatorTrouverLogement.setForeground(Color.WHITE);
		separatorTrouverLogement.setBackground(new Color(31, 151, 83));
		separatorTrouverLogement.setBounds(55, 33, 188, 2);
		getContentPane().add(separatorTrouverLogement);
		
		tabMesLogements = new JTable();
		tabMesLogements.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutEntreprise);
		tabMesLogements.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "Id Logement", "Date Acquisition" }
        ));
		
		JScrollPane scrollPaneLogement = new JScrollPane(tabMesLogements);
		scrollPaneLogement.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneLogement.setBounds(10, 45, 306, 134);
		getContentPane().add(scrollPaneLogement);
		
		RoundedButton btnChargerEntreprise = new RoundedButton("Charger", 20);
		btnChargerEntreprise.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnChargerEntreprise.setBounds(444, 277, 96, 21);
		getContentPane().add(btnChargerEntreprise);
		
		RoundedButton btnInsererEntreprise = new RoundedButton("Inserer E", 20);
		btnInsererEntreprise.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnInsererEntreprise.setBounds(104, 399, 107, 21);
		getContentPane().add(btnInsererEntreprise);
		
		RoundedButton btnAnnulerAssurance = new RoundedButton("Annuler", 20);
		btnAnnulerAssurance.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnAnnulerAssurance.setBounds(562, 277, 85, 21);
		getContentPane().add(btnAnnulerAssurance);
		
		RoundedButton btnAjouterAssurance = new RoundedButton("Ajouter", 20);
		btnAjouterAssurance.addActionListener(this.gestionClicFenAjoutEntreprise);
		btnAjouterAssurance.setBounds(669, 277, 85, 21);
		getContentPane().add(btnAjouterAssurance);
	}
	
	public JTable getTabMesEntreprise() {
		return tabMesEntreprise;
	}
	
	public JTable getTabMesLogements() {
		return tabMesLogements;
	}
	
	public String getTextFieldNumeroPolice() {
		return textFieldNumeroPolice.getText();
	}
	
	public String getTextFieldMontant() {
		return textFieldMontant.getText();
	}
	
	public String getTextFieldDateEcheance() {
		return textFieldDateEcheance.getText();
	}
}
