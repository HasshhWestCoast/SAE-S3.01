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


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FenAjoutLocation() throws SQLException {
		
		this.gestionClicFenAjoutLoc = new GestionFenAjoutLocation(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblProvisionsCharges = new JLabel("Provisions charges");
		lblProvisionsCharges.setBounds(510, 180, 96, 13);
		getContentPane().add(lblProvisionsCharges);
		
		JLabel lblNbMois = new JLabel("Nombre de Mois");
		lblNbMois.setBounds(510, 120, 85, 13);
		getContentPane().add(lblNbMois);
		
		JLabel lblDateDebut = new JLabel("Date début");
		lblDateDebut.setBounds(510, 60, 96, 13);
		getContentPane().add(lblDateDebut);
		
		JLabel lblAjoutLocations = new JLabel("Ajout Location");
		lblAjoutLocations.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutLocations.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutLocations.setBounds(541, 20, 131, 21);
		getContentPane().add(lblAjoutLocations);
		
		JSeparator separatorAjoutLocations = new JSeparator();
		separatorAjoutLocations.setForeground(new Color(255, 255, 255));
		separatorAjoutLocations.setBackground(new Color(31, 151, 83));
		separatorAjoutLocations.setBounds(510, 40, 188, 2);
		getContentPane().add(separatorAjoutLocations);
		
		textFieldDateDebut = new JTextField();
		textFieldDateDebut.setBounds(510, 78, 207, 19);
		getContentPane().add(textFieldDateDebut);
		textFieldDateDebut.setColumns(10);
		
		textFieldNbMois = new JTextField();
		textFieldNbMois.setBounds(510, 136, 207, 19);
		getContentPane().add(textFieldNbMois);
		textFieldNbMois.setColumns(10);
		
		textFieldProvisionsCharge = new JTextField();
		textFieldProvisionsCharge.setBounds(510, 198, 207, 19);
		getContentPane().add(textFieldProvisionsCharge);
		textFieldProvisionsCharge.setColumns(10);
		
		JLabel lblTrouverBien = new JLabel("Trouver Bien");
		lblTrouverBien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverBien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverBien.setBounds(84, 195, 131, 21);
		getContentPane().add(lblTrouverBien);
		
		JSeparator separatorTrouverBien = new JSeparator();
		separatorTrouverBien.setForeground(Color.WHITE);
		separatorTrouverBien.setBackground(new Color(31, 151, 83));
		separatorTrouverBien.setBounds(56, 213, 188, 2);
		getContentPane().add(separatorTrouverBien);
	
		tabMesBiens = new JTable();
		tabMesBiens.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutLoc);
		tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] { { null} },
            new String[] { "Id Bien" }
        ));
        JScrollPane scrollPaneMesBiens = new JScrollPane(tabMesBiens);
        scrollPaneMesBiens.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneMesBiens.setBounds(10, 226, 306, 142);        
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
		scrollPanemesLocataires.setBounds(10, 40, 306, 134);
		getContentPane().add(scrollPanemesLocataires);
		
		JLabel lblNewLabel = new JLabel("montant reel");
		lblNewLabel.setBounds(510, 238, 118, 13);
		getContentPane().add(lblNewLabel);
		
		textFieldMontantReel = new JTextField();
		textFieldMontantReel.setBounds(510, 252, 207, 19);
		getContentPane().add(textFieldMontantReel);
		textFieldMontantReel.setColumns(10);
		
		JLabel lblCaution = new JLabel("caution");
		lblCaution.setBounds(510, 296, 118, 13);
		getContentPane().add(lblCaution);
		
		textFieldCaution = new JTextField();
		textFieldCaution.setColumns(10);
		textFieldCaution.setBounds(510, 310, 207, 19);
		getContentPane().add(textFieldCaution);
		
		JLabel lblTrouverICC = new JLabel("Trouver ICC");
		lblTrouverICC.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverICC.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverICC.setBounds(84, 383, 131, 21);
		getContentPane().add(lblTrouverICC);
		
		tabMesICC = new JTable();
		tabMesICC.getSelectionModel().addListSelectionListener(this.gestionClicFenAjoutLoc);
		tabMesICC.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null} },
            new String[] { "Annee", "trimestre", "indice" }
        ));
		
		JScrollPane scrollPaneMesICC = new JScrollPane(tabMesICC);
		scrollPaneMesICC.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneMesICC.setBounds(10, 415, 306, 142);
		getContentPane().add(scrollPaneMesICC);
		
		JSeparator separatorTrouverICC = new JSeparator();
		separatorTrouverICC.setForeground(Color.WHITE);
		separatorTrouverICC.setBackground(new Color(31, 151, 83));
		separatorTrouverICC.setBounds(56, 402, 188, 2);
		getContentPane().add(separatorTrouverICC);
		
		RoundedButton btnInsererICC = new RoundedButton("Inserer ICC", 20);
		btnInsererICC.addActionListener(this.gestionClicFenAjoutLoc);
		btnInsererICC.setBounds(323, 458, 108, 21);
		getContentPane().add(btnInsererICC);
		
		RoundedButton btnAjouterEtatLieux = new RoundedButton("Ajouter Etat Lieux", 20);
		btnAjouterEtatLieux.addActionListener(this.gestionClicFenAjoutLoc);
		btnAjouterEtatLieux.setBounds(510, 414, 207, 21);
		btnAjouterEtatLieux.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAjouterEtatLieux);
		
		RoundedButton btnAjouterBail = new RoundedButton("Ajouter Lieux", 20);
		btnAjouterBail.addActionListener(this.gestionClicFenAjoutLoc);
		btnAjouterBail.setBounds(510, 445, 207, 21);
		btnAjouterBail.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAjouterBail);
		
		RoundedButton btnInsererLocataire = new RoundedButton("Inserer L", 20);
		btnInsererLocataire.addActionListener(this.gestionClicFenAjoutLoc);
		btnInsererLocataire.setBounds(323, 77, 108, 21);
		getContentPane().add(btnInsererLocataire);
		
		RoundedButton btnAnnulerLocation = new RoundedButton("Annuler", 20);
		btnAnnulerLocation.addActionListener(this.gestionClicFenAjoutLoc);
		btnAnnulerLocation.setBounds(569, 512, 85, 21);
		getContentPane().add(btnAnnulerLocation);
		
		RoundedButton btnAjouterLocation = new RoundedButton("Ajouter", 20);
		btnAjouterLocation.addActionListener(this.gestionClicFenAjoutLoc);
		btnAjouterLocation.setBounds(708, 512, 85, 21);
		getContentPane().add(btnAjouterLocation);
		
		RoundedButton btnCharger = new RoundedButton("Charger", 20);
		btnCharger.addActionListener(this.gestionClicFenAjoutLoc);
		btnCharger.setBounds(404, 512, 112, 21);
		getContentPane().add(btnCharger);
		
		RoundedButton btnInsererBien = new RoundedButton("Inserer Bien", 20);
		btnInsererBien.addActionListener(this.gestionClicFenAjoutLoc);
		btnInsererBien.setBounds(326, 288, 112, 21);
		getContentPane().add(btnInsererBien);
		
		JLabel lblLoyerPayer = new JLabel("Loyer Payer");
		lblLoyerPayer.setBounds(510, 354, 118, 13);
		getContentPane().add(lblLoyerPayer);
		
		checkLoyerPayer = new JCheckBox("OUI");
		checkLoyerPayer.setBackground(new Color(31, 151, 83));
		checkLoyerPayer.setBounds(510, 373, 93, 21);
		getContentPane().add(checkLoyerPayer);
		
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
	
	public String getTextFieldNbMois() {
		return textFieldNbMois.getText();
	}
	
	public String getTextFieldProvisionsCharge() {
		return textFieldProvisionsCharge.getText();
	}
	
	public  String getTextFieldMontantReel() {
		return textFieldMontantReel.getText();
	}
	
	public String getTextFieldCaution() {
		return textFieldCaution.getText();
	}
	
	public String getcheckLoyerPayer() {
		return checkLoyerPayer.getToolTipText();
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
