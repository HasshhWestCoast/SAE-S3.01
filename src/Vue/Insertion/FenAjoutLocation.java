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
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import Controleur.Ajout.GestionFenAjoutLocation;
import Vue.RoundedButton;
import javax.swing.JCheckBox;

public class FenAjoutLocation extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldDateDebut;
	private JTextField textFieldNbMois;
	private JTextField textFieldProvisionsCharge;
	private JTextField textFieldMontantReel;
	private JTextField textFieldCaution;
	private GestionFenAjoutLocation gestionClicFenAjoutLoc;
	private JTable tabMesLocataires;
	private JTable tabMesBiens;
	private JTable tabMesICC;
	private JCheckBox checkLoyerPayer;
	private JTextField textFieldDateSortie;
	private JTextField textFieldLoyerMenssTTC;
	private JTextField textFieldEtatLieux;
	private JTextField textFieldBail;
	private RoundedButton btnSupprimerLoc;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FenAjoutLocation() throws SQLException {
		
		this.gestionClicFenAjoutLoc = new GestionFenAjoutLocation(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblProvisionsCharges = new JLabel("Provisions charges");
		lblProvisionsCharges.setBounds(397, 219, 96, 13);
		getContentPane().add(lblProvisionsCharges);
		
		JLabel lblNbMois = new JLabel("Nombre de Mois");
		lblNbMois.setBounds(397, 165, 96, 13);
		getContentPane().add(lblNbMois);
		
		JLabel lblDateDebut = new JLabel("Date début");
		lblDateDebut.setBounds(397, 62, 96, 13);
		getContentPane().add(lblDateDebut);
		
		JLabel lblAjoutLocations = new JLabel("Ajout Location");
		lblAjoutLocations.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutLocations.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutLocations.setBounds(437, 15, 131, 21);
		getContentPane().add(lblAjoutLocations);
		
		JSeparator separatorAjoutLocations = new JSeparator();
		separatorAjoutLocations.setForeground(new Color(255, 255, 255));
		separatorAjoutLocations.setBackground(new Color(31, 151, 83));
		separatorAjoutLocations.setBounds(413, 32, 188, 2);
		getContentPane().add(separatorAjoutLocations);
		
		textFieldDateDebut = new JTextField();
		textFieldDateDebut.setBounds(395, 84, 207, 19);
		getContentPane().add(textFieldDateDebut);
		textFieldDateDebut.setColumns(10);
		
		textFieldNbMois = new JTextField();
		textFieldNbMois.setBounds(395, 186, 207, 19);
		getContentPane().add(textFieldNbMois);
		textFieldNbMois.setColumns(10);
		
		textFieldProvisionsCharge = new JTextField();
		textFieldProvisionsCharge.setBounds(395, 243, 207, 19);
		getContentPane().add(textFieldProvisionsCharge);
		textFieldProvisionsCharge.setColumns(10);
		
		JLabel lblTrouverBien = new JLabel("Trouver Bien");
		lblTrouverBien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverBien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverBien.setBounds(84, 211, 131, 21);
		getContentPane().add(lblTrouverBien);
		
		JSeparator separatorTrouverBien = new JSeparator();
		separatorTrouverBien.setForeground(Color.WHITE);
		separatorTrouverBien.setBackground(new Color(31, 151, 83));
		separatorTrouverBien.setBounds(56, 230, 188, 2);
		getContentPane().add(separatorTrouverBien);
	
		tabMesBiens = new JTable();
		tabMesBiens.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutLoc);
		tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "Id Bien", "Type Bien" }
        ));
        JScrollPane scrollPaneMesBiens = new JScrollPane(tabMesBiens);
        scrollPaneMesBiens.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneMesBiens.setBounds(10, 242, 306, 126);        
		getContentPane().add(scrollPaneMesBiens);
	
		JLabel lblTrouverLocataire = new JLabel("Trouver Locataire");
		lblTrouverLocataire.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverLocataire.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverLocataire.setBounds(84, 15, 131, 21);
		getContentPane().add(lblTrouverLocataire);
		
		JSeparator separatorTrouverLogcataire = new JSeparator();
		separatorTrouverLogcataire.setForeground(Color.WHITE);
		separatorTrouverLogcataire.setBackground(new Color(31, 151, 83));
		separatorTrouverLogcataire.setBounds(56, 32, 188, 2);
		getContentPane().add(separatorTrouverLogcataire);
		
		tabMesLocataires = new JTable();
		tabMesLocataires.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutLoc);
		tabMesLocataires.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "Id Locataire", "nom" }
        ));
		
		JScrollPane scrollPanemesLocataires = new JScrollPane(tabMesLocataires);
		scrollPanemesLocataires.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPanemesLocataires.setBounds(10, 40, 306, 126);
		getContentPane().add(scrollPanemesLocataires);
		
		JLabel lblNewLabel = new JLabel("montant réel");
		lblNewLabel.setBounds(400, 279, 118, 13);
		getContentPane().add(lblNewLabel);
		
		textFieldMontantReel = new JTextField();
		textFieldMontantReel.setBounds(395, 303, 207, 19);
		getContentPane().add(textFieldMontantReel);
		textFieldMontantReel.setColumns(10);
		
		JLabel lblCaution = new JLabel("caution");
		lblCaution.setBounds(400, 325, 118, 13);
		getContentPane().add(lblCaution);
		
		textFieldCaution = new JTextField();
		textFieldCaution.setColumns(10);
		textFieldCaution.setBounds(395, 349, 207, 19);
		getContentPane().add(textFieldCaution);
		
		JLabel lblTrouverICC = new JLabel("Trouver ICC");
		lblTrouverICC.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverICC.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverICC.setBounds(84, 380, 131, 21);
		getContentPane().add(lblTrouverICC);
		
		tabMesICC = new JTable();
		tabMesICC.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutLoc);
		tabMesICC.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null, null} },
            new String[] { "IdIcc", "Annee", "trimestre", "indice" }
        ));
		
		JScrollPane scrollPaneMesICC = new JScrollPane(tabMesICC);
		scrollPaneMesICC.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneMesICC.setBounds(10, 410, 306, 142);
		getContentPane().add(scrollPaneMesICC);
		
		JSeparator separatorTrouverICC = new JSeparator();
		separatorTrouverICC.setForeground(Color.WHITE);
		separatorTrouverICC.setBackground(new Color(31, 151, 83));
		separatorTrouverICC.setBounds(56, 398, 188, 2);
		getContentPane().add(separatorTrouverICC);
		
		RoundedButton btnAjouterEtatLieux = new RoundedButton("Ajouter Etat Lieux", 20);
		btnAjouterEtatLieux.addActionListener(this.gestionClicFenAjoutLoc);
		btnAjouterEtatLieux.setBounds(568, 488, 156, 21);
		btnAjouterEtatLieux.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAjouterEtatLieux);
		
		RoundedButton btnAjouterBail = new RoundedButton("Ajouter Lieux", 20);
		btnAjouterBail.setText("Ajouter Bail");
		btnAjouterBail.addActionListener(this.gestionClicFenAjoutLoc);
		btnAjouterBail.setBounds(568, 520, 156, 21);
		btnAjouterBail.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAjouterBail);
		
		RoundedButton btnAnnulerLocation = new RoundedButton("Annuler", 20);
		btnAnnulerLocation.addActionListener(this.gestionClicFenAjoutLoc);
		btnAnnulerLocation.setBounds(457, 563, 85, 21);
		btnAnnulerLocation.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAnnulerLocation);
		
		RoundedButton btnAjouterLocation = new RoundedButton("Ajouter", 20);
		btnAjouterLocation.addActionListener(this.gestionClicFenAjoutLoc);
		btnAjouterLocation.setBounds(553, 563, 85, 21);
		btnAjouterLocation.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAjouterLocation);
		
		RoundedButton btnCharger = new RoundedButton("Charger", 20);
		btnCharger.addActionListener(this.gestionClicFenAjoutLoc);
		btnCharger.setBounds(347, 563, 96, 21);
		btnCharger.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnCharger);
		
		JLabel lblLoyerPayer = new JLabel("Loyer Payer");
		lblLoyerPayer.setBounds(400, 439, 118, 13);
		getContentPane().add(lblLoyerPayer);
		
		RoundedButton btnInsererICC = new RoundedButton("Inserer ICC", 20);
		btnInsererICC.addActionListener(this.gestionClicFenAjoutLoc);
		btnInsererICC.setBounds(25, 563, 108, 21);
		btnInsererICC.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnInsererICC);
		
		checkLoyerPayer = new JCheckBox("OUI");
		checkLoyerPayer.setBackground(new Color(31, 151, 83));
		checkLoyerPayer.setBounds(400, 459, 93, 21);
		getContentPane().add(checkLoyerPayer);
		
		RoundedButton btnLocataire = new RoundedButton("Inserer L", 20);
		btnLocataire.addActionListener(this.gestionClicFenAjoutLoc);
		btnLocataire.setBounds(25, 172, 108, 21);
		btnLocataire.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnLocataire);
		
		textFieldDateSortie = new JTextField();
		textFieldDateSortie.setColumns(10);
		textFieldDateSortie.setBounds(395, 135, 207, 19);
		getContentPane().add(textFieldDateSortie);
		
		JLabel lblDateSortie = new JLabel("Date Sortie");
		lblDateSortie.setBounds(397, 108, 96, 13);
		getContentPane().add(lblDateSortie);
		
		JLabel lblLoyerMensTTC = new JLabel("Loyer mensuel TCC");
		lblLoyerMensTTC.setBounds(400, 381, 118, 13);
		getContentPane().add(lblLoyerMensTTC);
		
		textFieldLoyerMenssTTC = new JTextField();
		textFieldLoyerMenssTTC.setColumns(10);
		textFieldLoyerMenssTTC.setBounds(395, 409, 207, 19);
		getContentPane().add(textFieldLoyerMenssTTC);
		
		textFieldEtatLieux = new JTextField();
		textFieldEtatLieux.setColumns(10);
		textFieldEtatLieux.setBounds(397, 488, 147, 19);
		getContentPane().add(textFieldEtatLieux);
		
		textFieldBail = new JTextField();
		textFieldBail.setColumns(10);
		textFieldBail.setBounds(397, 520, 147, 19);
		getContentPane().add(textFieldBail);
		
		btnSupprimerLoc = new RoundedButton("Supprimer L", 20);
		btnSupprimerLoc.addActionListener(gestionClicFenAjoutLoc);
		btnSupprimerLoc.setBounds(161, 172, 118, 21);
		btnSupprimerLoc.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnSupprimerLoc);
		
		RoundedButton btnSupprimerIcc = new RoundedButton("Supprimer ICC", 20);
		btnSupprimerIcc.addActionListener(gestionClicFenAjoutLoc);
		btnSupprimerIcc.setBounds(161, 562, 131, 21);
		btnSupprimerIcc.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnSupprimerIcc);
		
	}
	
	public JTable getTabMesLocataires() {
		return tabMesLocataires;
	}
	
	public JTable getTabMesBiens() {
		return tabMesBiens;
	}
	
	public JTable getTabMesICC() {
		return tabMesICC;
	}

	public String getTextFieldDateDebut() {
		return textFieldDateDebut.getText();
	}
	
	public String getTextFieldLoyerMenssTTCt() {
		return textFieldLoyerMenssTTC.getText();
	}
	
	public String getTextFieldNbMois() {
		return textFieldNbMois.getText();
	}
	
	public String getTextFieldProvisionsCharge() {
		return textFieldProvisionsCharge.getText();
	}
	
	public String getTextFielDateSortie() {
		return textFieldDateSortie.getText();
	}
	
	public  String getTextFieldMontantReel() {
		return textFieldMontantReel.getText();
	}
	
	public String getTextFieldCaution() {
		return textFieldCaution.getText();
	}
	
	public Boolean getcheckLoyerPayer() {
		return checkLoyerPayer.isSelected();
	}
	
	public String getTextFieldEtatLieux() {
		return textFieldEtatLieux.getText();
	}
	
	public String getTextFieldBail() {
		return textFieldBail.getText();
	}
	
	public JTable getSelectedTable(ListSelectionEvent e) {
	    Object source = e.getSource();
	    
	    if (source == tabMesLocataires.getSelectionModel()) {
	        return tabMesLocataires;
	    } else if (source == tabMesBiens.getSelectionModel()) {
	        return tabMesBiens;
	    } else if (source == tabMesICC.getSelectionModel()) {
	        return tabMesICC;
	    }
	    return null;
	}
}
