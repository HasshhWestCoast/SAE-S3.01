package Vue.Insertion;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Font;

import javax.swing.SwingConstants;

import Controleur.Ajout.GestionFenAjoutLogement;
import Vue.RoundedButton;

import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;

public class FenAjoutLogement extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldDateAcquisition;
	private JTextField textFieldIdLogement;
	private JTextField textFieldSurfaceHabitable;
	private GestionFenAjoutLogement gestionFenAjoutLogement;
	private JComboBox comboBoxTypeDeLogement;
	private JSpinner spinnerNbPiece;
	private JSpinner spinnerNumEtage;
	private JTable tabMesBiens;
	private JCheckBox checkGarage;


	/**
	 * Create the frame.
	 */
	public FenAjoutLogement() {
	
	    gestionFenAjoutLogement = new GestionFenAjoutLogement(this);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblDateAcquisition = new JLabel("Date d'acquisition");
		lblDateAcquisition.setBounds(80, 130, 96, 13);
		getContentPane().add(lblDateAcquisition);
		
		textFieldDateAcquisition = new JTextField();
		textFieldDateAcquisition.setBounds(80, 146, 223, 19);
		getContentPane().add(textFieldDateAcquisition);
		textFieldDateAcquisition.setColumns(10);
		
		JLabel lbNombreDePiece = new JLabel("Nombre de pieces");
		lbNombreDePiece.setBounds(80, 302, 112, 13);
		getContentPane().add(lbNombreDePiece);
		
		JLabel lblTypeDebien = new JLabel("Type de bien");
		lblTypeDebien.setBounds(80, 185, 66, 13);
		getContentPane().add(lblTypeDebien);

		comboBoxTypeDeLogement = new JComboBox();
		comboBoxTypeDeLogement.setBounds(80, 205, 223, 21);
		comboBoxTypeDeLogement.setForeground(new Color(0, 0, 0));
		comboBoxTypeDeLogement.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		comboBoxTypeDeLogement.setBackground(new Color(31, 153, 88));
		comboBoxTypeDeLogement.setModel(new DefaultComboBoxModel(new String[] {"Appartement", "Maison"}));
		comboBoxTypeDeLogement.setToolTipText("ID Immeuble");
		getContentPane().add(comboBoxTypeDeLogement);
		
		JLabel lblIdLogement = new JLabel("ID Logement");
		lblIdLogement.setBounds(80, 68, 96, 13);
		getContentPane().add(lblIdLogement);
		
		textFieldIdLogement = new JTextField();
		textFieldIdLogement.setBounds(80, 84, 223, 19);
		getContentPane().add(textFieldIdLogement);
		textFieldIdLogement.setColumns(10);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.addActionListener(gestionFenAjoutLogement);
		btnAnnuler.setBounds(80, 419, 85, 21);
		getContentPane().add(btnAnnuler);
		
	
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.addActionListener(gestionFenAjoutLogement);
		btnAjouter.setBounds(209, 419, 85, 21);
		getContentPane().add(btnAjouter);
		
		JLabel lblNewLabel_1 = new JLabel("Ajout Logement");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(124, 26, 131, 21);
		getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(93, 45, 188, 2);
		getContentPane().add(separator);
		
		spinnerNbPiece = new JSpinner();
		spinnerNbPiece.setBounds(80, 320, 49, 20);
		getContentPane().add(spinnerNbPiece);
		
		JLabel lblNumEtage = new JLabel("N° etage");
		lblNumEtage.setBounds(198, 302, 71, 13);
		getContentPane().add(lblNumEtage);
		
		spinnerNumEtage = new JSpinner();
		spinnerNumEtage.setBounds(198, 320, 49, 20);
		getContentPane().add(spinnerNumEtage);
		
		JLabel lblSurfaceHabitable = new JLabel("Surface habitable");
		lblSurfaceHabitable.setBounds(80, 243, 90, 13);
		getContentPane().add(lblSurfaceHabitable);
		
		textFieldSurfaceHabitable = new JTextField();
		textFieldSurfaceHabitable.setBounds(80, 261, 223, 19);
		getContentPane().add(textFieldSurfaceHabitable);
		textFieldSurfaceHabitable.setColumns(10);
		
		JLabel lblMesBiens = new JLabel("Mes Biens");
		lblMesBiens.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesBiens.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblMesBiens.setBackground(Color.WHITE);
		lblMesBiens.setBounds(358, 27, 216, 21);
		getContentPane().add(lblMesBiens);
		
		tabMesBiens = new JTable();
		tabMesBiens.getSelectionModel().addListSelectionListener(this.gestionFenAjoutLogement);
		tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "Id Bien", "Type Bien" }
        ));
		
		JScrollPane scrollPane = new JScrollPane(tabMesBiens);
		scrollPane.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPane.setBounds(357, 68, 268, 134);
		getContentPane().add(scrollPane);
		
		JSeparator separatorMesBiens = new JSeparator();
		separatorMesBiens.setForeground(Color.WHITE);
		separatorMesBiens.setBackground(new Color(31, 151, 83));
		separatorMesBiens.setBounds(372, 45, 188, 2);
		getContentPane().add(separatorMesBiens);
		
		RoundedButton btnCharger = new RoundedButton("Charger", 20);
		btnCharger.addActionListener(gestionFenAjoutLogement);
		btnCharger.setBounds(450, 212, 85, 21);
		getContentPane().add(btnCharger);
		
		checkGarage = new JCheckBox("OUI");
		checkGarage.setBackground(new Color(31, 151, 83));
		checkGarage.setBounds(80, 376, 93, 21);
		getContentPane().add(checkGarage);
		
		JLabel lbGarage = new JLabel("Garage");
		lbGarage.setBounds(80, 357, 112, 13);
		getContentPane().add(lbGarage);
		setBounds(300, 100, 686, 507);

	}
	
	public Boolean getcheckGarage() {
		return checkGarage.isSelected();
	}
	
	public String getDateAcquisition() {
	    return textFieldDateAcquisition.getText();
	}

	public String getIdLogement() {
	    return textFieldIdLogement.getText();
	}

	public String getSurfaceHabitable() {
	    return textFieldSurfaceHabitable.getText();
	}

	public String getComboBoxTypeDeLogement() {
	    return comboBoxTypeDeLogement.getSelectedItem().toString();
	}

	public String getNbPiece() {
	    return spinnerNbPiece.getValue().toString();
	}

	public String getNumEtage() {
	    return spinnerNumEtage.getValue().toString();
	}
	
	public JTable getTabMesBiens() {
		return tabMesBiens;
	}
}
