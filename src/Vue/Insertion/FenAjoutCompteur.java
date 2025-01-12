package Vue.Insertion;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controleur.Ajout.GestionFenAjoutCompteur;
import Vue.RoundedButton;

public class FenAjoutCompteur extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdCompteur;
	private JTextField textFieldIndiceCompteur;
	private JTextField textFieldDateRelevé;
	private GestionFenAjoutCompteur gestionFenAjoutCompteur;
	private JComboBox comboBox;


	/**
	 * Create the frame.
	 */
	public FenAjoutCompteur() {
		
		this.gestionFenAjoutCompteur = new GestionFenAjoutCompteur(this);
		setBackground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.addActionListener(gestionFenAjoutCompteur);
		btnAnnuler.setBounds(77, 392, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.addActionListener(gestionFenAjoutCompteur);
		btnAjouter.setBounds(213, 392, 85, 21);
		getContentPane().add(btnAjouter);
		
		JLabel lblCompteur = new JLabel("Ajout Compteur");
		lblCompteur.setBackground(new Color(255, 255, 255));
		lblCompteur.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompteur.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblCompteur.setBounds(109, 26, 131, 21);
		getContentPane().add(lblCompteur);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(89, 45, 176, 2);
		getContentPane().add(separator);
		
		JLabel lblIdCompteur = new JLabel("Id Compteur");
		lblIdCompteur.setBounds(77, 100, 76, 13);
		getContentPane().add(lblIdCompteur);
		
		textFieldIdCompteur = new JTextField();
		textFieldIdCompteur.setBounds(77, 117, 200, 19);
		getContentPane().add(textFieldIdCompteur);
		textFieldIdCompteur.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Indice du Compteur");
		lblNewLabel.setBounds(77, 164, 142, 13);
		getContentPane().add(lblNewLabel);
		
		textFieldIndiceCompteur = new JTextField();
		textFieldIndiceCompteur.setBounds(77, 181, 200, 19);
		getContentPane().add(textFieldIndiceCompteur);
		textFieldIndiceCompteur.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Type de compteur");
		lblNewLabel_1.setBounds(77, 230, 131, 13);
		getContentPane().add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Eau", "Gaz", "Electricité"}));
		comboBox.setBounds(76, 250, 201, 21);
		setBounds(100, 100, 388, 499);
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		comboBox.setBackground(new Color(31, 153, 88));
		getContentPane().add(comboBox);
		
		JLabel lblDateReleve = new JLabel("Date relevé");
		lblDateReleve.setBounds(77, 300, 85, 13);
		getContentPane().add(lblDateReleve);
		
		textFieldDateRelevé = new JTextField();
		textFieldDateRelevé.setBounds(79, 315, 198, 19);
		getContentPane().add(textFieldDateRelevé);
		textFieldDateRelevé.setColumns(10);
	}
	
	public String getTextFieldIdCompteur() {
		return textFieldIdCompteur.getText();
	}
	
	public String  getTextFieldIndiceCompteur() {
		return textFieldIndiceCompteur.getText();
	}
	
	public String getTextFieldDateRelevé() {
		return textFieldDateRelevé.getText();
	}
	
	public String getComboBoxTypeComp() {
		return (String) comboBox.getSelectedItem();
	}
}
