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

import Controleur.Ajout.GestionFenAjoutLocation;
import Vue.RoundedButton;

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


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FenAjoutLocation() throws SQLException {
		
		this.gestionClicFenAjoutLoc = new GestionFenAjoutLocation(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblProvisionsCharges = new JLabel("Provisions charges");
		lblProvisionsCharges.setBounds(491, 180, 96, 13);
		getContentPane().add(lblProvisionsCharges);
		
		JLabel lblNbMois = new JLabel("Nombre de Mois");
		lblNbMois.setBounds(491, 120, 85, 13);
		getContentPane().add(lblNbMois);
		
		JLabel lblDateDebut = new JLabel("Date début");
		lblDateDebut.setBounds(491, 60, 96, 13);
		getContentPane().add(lblDateDebut);
		
		JLabel lblAjoutLocations = new JLabel("Ajout Location");
		lblAjoutLocations.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutLocations.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutLocations.setBounds(520, 20, 131, 21);
		getContentPane().add(lblAjoutLocations);
		
		JSeparator separatorAjoutLocations = new JSeparator();
		separatorAjoutLocations.setForeground(new Color(255, 255, 255));
		separatorAjoutLocations.setBackground(new Color(31, 151, 83));
		separatorAjoutLocations.setBounds(491, 40, 188, 2);
		getContentPane().add(separatorAjoutLocations);
		
		textFieldDateDebut = new JTextField();
		textFieldDateDebut.setBounds(491, 78, 207, 19);
		getContentPane().add(textFieldDateDebut);
		textFieldDateDebut.setColumns(10);
		
		textFieldNbMois = new JTextField();
		textFieldNbMois.setBounds(491, 136, 207, 19);
		getContentPane().add(textFieldNbMois);
		textFieldNbMois.setColumns(10);
		
		textFieldProvisionsCharge = new JTextField();
		textFieldProvisionsCharge.setBounds(491, 198, 207, 19);
		getContentPane().add(textFieldProvisionsCharge);
		textFieldProvisionsCharge.setColumns(10);
		
		JLabel lblTrouverBien = new JLabel("Trouver Bien");
		lblTrouverBien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverBien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverBien.setBounds(84, 200, 131, 21);
		getContentPane().add(lblTrouverBien);
		
		JSeparator separatorTrouverBien = new JSeparator();
		separatorTrouverBien.setForeground(Color.WHITE);
		separatorTrouverBien.setBackground(new Color(31, 151, 83));
		separatorTrouverBien.setBounds(68, 215, 188, 2);
		getContentPane().add(separatorTrouverBien);
	
		tabMesBiens = new JTable();
		tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] { { null} },
            new String[] { "Id Bien" }
        ));
        JScrollPane scrollPaneMesBiens = new JScrollPane(tabMesBiens);
        scrollPaneMesBiens.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneMesBiens.setBounds(10, 228, 306, 142);        
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
		tabMesLocataires.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "Id Locataire", "nom" }
        ));
		
		JScrollPane scrollPanemesLocataires = new JScrollPane(tabMesLocataires);
		scrollPanemesLocataires.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPanemesLocataires.setBounds(10, 40, 306, 134);
		getContentPane().add(scrollPanemesLocataires);
		
		JLabel lblNewLabel = new JLabel("montant reel");
		lblNewLabel.setBounds(491, 238, 118, 13);
		getContentPane().add(lblNewLabel);
		
		textFieldMontantReel = new JTextField();
		textFieldMontantReel.setBounds(491, 252, 207, 19);
		getContentPane().add(textFieldMontantReel);
		textFieldMontantReel.setColumns(10);
		
		JLabel lblCaution = new JLabel("caution");
		lblCaution.setBounds(491, 296, 118, 13);
		getContentPane().add(lblCaution);
		
		textFieldCaution = new JTextField();
		textFieldCaution.setColumns(10);
		textFieldCaution.setBounds(491, 310, 207, 19);
		getContentPane().add(textFieldCaution);
		
		JLabel lblTrouverICC = new JLabel("Trouver ICC");
		lblTrouverICC.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverICC.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverICC.setBounds(96, 403, 131, 21);
		getContentPane().add(lblTrouverICC);
		
		tabMesICC = new JTable();
		tabMesICC.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null} },
            new String[] { "Annee", "trimestre", "indice" }
        ));
		
		JScrollPane scrollPaneMesICC = new JScrollPane(tabMesICC);
		scrollPaneMesICC.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneMesICC.setBounds(10, 434, 306, 142);
		getContentPane().add(scrollPaneMesICC);
		
		JSeparator separatorTrouverICC = new JSeparator();
		separatorTrouverICC.setForeground(Color.WHITE);
		separatorTrouverICC.setBackground(new Color(31, 151, 83));
		separatorTrouverICC.setBounds(68, 420, 188, 2);
		getContentPane().add(separatorTrouverICC);
		
		RoundedButton btnChargerICC = new RoundedButton("Charger ICC", 20);
		btnChargerICC.addActionListener(this.gestionClicFenAjoutLoc);
		btnChargerICC.setBounds(334, 492, 108, 21);
		getContentPane().add(btnChargerICC);
		
		RoundedButton btnInsererICC = new RoundedButton("Inserer ICC", 20);
		btnInsererICC.addActionListener(this.gestionClicFenAjoutLoc);
		btnInsererICC.setBounds(334, 532, 108, 21);
		getContentPane().add(btnInsererICC);
		
		RoundedButton btnAjouterEtatLieux = new RoundedButton("Ajouter Etat Lieux", 20);
		btnAjouterEtatLieux.addActionListener(this.gestionClicFenAjoutLoc);
		btnAjouterEtatLieux.setBounds(491, 362, 207, 21);
		btnAjouterEtatLieux.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAjouterEtatLieux);
		
		RoundedButton btnAjouterBail = new RoundedButton("Ajouter Lieux", 20);
		btnAjouterBail.addActionListener(this.gestionClicFenAjoutLoc);
		btnAjouterBail.setBounds(491, 402, 207, 21);
		btnAjouterBail.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAjouterBail);
		
		RoundedButton btnInsererLocataire = new RoundedButton("Inserer L", 20);
		btnInsererLocataire.addActionListener(this.gestionClicFenAjoutLoc);
		btnInsererLocataire.setBounds(334, 134, 108, 21);
		getContentPane().add(btnInsererLocataire);
		
		RoundedButton btnAnnulerLocation = new RoundedButton("Annuler", 20);
		btnAnnulerLocation.addActionListener(this.gestionClicFenAjoutLoc);
		btnAnnulerLocation.setBounds(491, 479, 85, 21);
		getContentPane().add(btnAnnulerLocation);
		
		RoundedButton btnAjouterLocation = new RoundedButton("Ajouter", 20);
		btnAjouterLocation.addActionListener(this.gestionClicFenAjoutLoc);
		btnAjouterLocation.setBounds(613, 479, 85, 21);
		getContentPane().add(btnAjouterLocation);
		
		RoundedButton btnChargerBien = new RoundedButton("Charger Bien", 20);
		btnChargerBien.addActionListener(this.gestionClicFenAjoutLoc);
		btnChargerBien.setBounds(330, 283, 112, 21);
		getContentPane().add(btnChargerBien);
		
		RoundedButton btnInsererBien = new RoundedButton("Inserer Bien", 20);
		btnInsererBien.addActionListener(this.gestionClicFenAjoutLoc);
		btnInsererBien.setBounds(330, 317, 112, 21);
		getContentPane().add(btnInsererBien);
		
		RoundedButton btnChargerLocataire = new RoundedButton("Charger L", 20);
		btnChargerLocataire.addActionListener(this.gestionClicFenAjoutLoc);
		btnChargerLocataire.setBounds(334, 98, 108, 21);
		getContentPane().add(btnChargerLocataire);
		
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
	
}
