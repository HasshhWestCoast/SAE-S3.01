package Vue.Insertion;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controleur.Ajout.GestionFenAjoutCompteur;
import Vue.RoundedButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class FenAjoutCompteur extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdCompteur;
	private GestionFenAjoutCompteur gestionFenAjoutCompteur;
	private JComboBox comboBox;
	private JTable tabMesLogements;
	private JTable tabMesBiens;



	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FenAjoutCompteur() throws SQLException {
		
		this.gestionFenAjoutCompteur = new GestionFenAjoutCompteur(this);
		setBackground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.addActionListener(gestionFenAjoutCompteur);
		btnAnnuler.setBounds(394, 347, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.addActionListener(gestionFenAjoutCompteur);
		btnAjouter.setBounds(535, 347, 85, 21);
		getContentPane().add(btnAjouter);
		
		JLabel lblCompteur = new JLabel("Ajout Compteur");
		lblCompteur.setBackground(new Color(255, 255, 255));
		lblCompteur.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompteur.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblCompteur.setBounds(445, 26, 131, 21);
		getContentPane().add(lblCompteur);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(430, 57, 176, 2);
		getContentPane().add(separator);
		
		JLabel lblIdCompteur = new JLabel("Id Compteur");
		lblIdCompteur.setBounds(403, 82, 76, 13);
		getContentPane().add(lblIdCompteur);
		
		textFieldIdCompteur = new JTextField();
		textFieldIdCompteur.setBounds(403, 98, 200, 19);
		getContentPane().add(textFieldIdCompteur);
		textFieldIdCompteur.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Type de compteur");
		lblNewLabel_1.setBounds(400, 153, 131, 13);
		getContentPane().add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Eau", "Gaz", "Electricité"}));
		comboBox.setBounds(400, 174, 201, 21);
		setBounds(100, 100, 673, 462);
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		comboBox.setBackground(new Color(31, 153, 88));
		getContentPane().add(comboBox);
		
		tabMesLogements = new JTable();
		tabMesLogements.getSelectionModel().addListSelectionListener(this.gestionFenAjoutCompteur);
		tabMesLogements.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "Id Logement", "Date Acquisition" }
        ));
		
		JScrollPane scrollPaneLogement = new JScrollPane(tabMesLogements);
		scrollPaneLogement.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneLogement.setBounds(35, 55, 306, 148);
		getContentPane().add(scrollPaneLogement);
		
		JSeparator separatorTrouverLogement = new JSeparator();
		separatorTrouverLogement.setForeground(Color.WHITE);
		separatorTrouverLogement.setBackground(new Color(31, 151, 83));
		separatorTrouverLogement.setBounds(90, 45, 188, 2);
		getContentPane().add(separatorTrouverLogement);
		
		JLabel lblTrouverLogement = new JLabel("Trouver Logement");
		lblTrouverLogement.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverLogement.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverLogement.setBounds(114, 26, 131, 21);
		getContentPane().add(lblTrouverLogement);
		
		tabMesBiens = new JTable();
		tabMesBiens.getSelectionModel().addListSelectionListener(this.gestionFenAjoutCompteur);
		tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] { { null, null} },
            new String[] { "Id Bien", "Type Bien" }
        ));
		
		JScrollPane scrollPaneMesBiens = new JScrollPane(tabMesBiens);
		scrollPaneMesBiens.setBorder(new LineBorder(new Color(109, 109, 109), 2));
		scrollPaneMesBiens.setBounds(35, 253, 306, 148);
		getContentPane().add(scrollPaneMesBiens);
		
		JLabel lblTrouverBien = new JLabel("Trouver Bien");
		lblTrouverBien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrouverBien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblTrouverBien.setBounds(114, 222, 131, 21);
		getContentPane().add(lblTrouverBien);
		
		JSeparator separatorTrouverBien = new JSeparator();
		separatorTrouverBien.setForeground(Color.WHITE);
		separatorTrouverBien.setBackground(new Color(31, 151, 83));
		separatorTrouverBien.setBounds(90, 241, 188, 2);
		getContentPane().add(separatorTrouverBien);
		
		RoundedButton rndbtnCharger = new RoundedButton("Charger", 20);
		rndbtnCharger.addActionListener(gestionFenAjoutCompteur);
		rndbtnCharger.setBounds(394, 239, 85, 21);
		getContentPane().add(rndbtnCharger);
	}
	
	public String getTextFieldIdCompteur() {
		return textFieldIdCompteur.getText();
	}
	
	public String getComboBoxTypeComp() {
		return (String) comboBox.getSelectedItem();
	}
	
	public JTable getTabMesLogements(){
		return tabMesLogements;
	}
	
	public JTable getTabMesBiens(){
		return tabMesBiens;
	}
}
