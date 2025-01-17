package Vue.Insertion;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import Controleur.Ajout.GestionFenAjoutReleve;
import Modele.Compteur;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import Vue.RoundedButton;

public class FenAjoutReleves extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldDateReleves;
	private JTextField textFieldIndex;
	private RoundedButton btnAnnuler;
	private RoundedButton btnAjouter;
	private Compteur compteur;
	private GestionFenAjoutReleve gestionFenAjoutReleve;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FenAjoutReleves(Compteur compteur) throws SQLException {
		this.compteur = compteur;
		this.gestionFenAjoutReleve = new GestionFenAjoutReleve(this);
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 384, 281);
		getContentPane().setLayout(null);
		
		JLabel lblAjoutReleve = new JLabel("Ajout Relevé");
		lblAjoutReleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutReleve.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutReleve.setBounds(116, 10, 131, 21);
		getContentPane().add(lblAjoutReleve);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(85, 29, 188, 2);
		getContentPane().add(separator);
		
		JLabel lblDateReleves = new JLabel("Date Relevé");
		lblDateReleves.setBounds(72, 52, 96, 13);
		getContentPane().add(lblDateReleves);
		
		textFieldDateReleves = new JTextField();
		textFieldDateReleves.setColumns(10);
		textFieldDateReleves.setBounds(72, 68, 223, 19);
		getContentPane().add(textFieldDateReleves);
		
		JLabel lblIndex = new JLabel("Index");
		lblIndex.setBounds(74, 107, 80, 13);
		getContentPane().add(lblIndex);
		
		textFieldIndex = new JTextField();
		textFieldIndex.setColumns(10);
		textFieldIndex.setBounds(72, 121, 223, 19);
		getContentPane().add(textFieldIndex);
		
		btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.addActionListener(this.gestionFenAjoutReleve);
		btnAnnuler.setBounds(75, 179, 85, 21);
		getContentPane().add(btnAnnuler);
		
		btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.addActionListener(this.gestionFenAjoutReleve);
		btnAjouter.setBounds(210, 179, 85, 21);
		getContentPane().add(btnAjouter);

	}
	
	public String getDateReleves() {
		return textFieldDateReleves.getText();
	}
	
	public String getIndex() {
		return textFieldIndex.getText();
	}

	public Compteur getCompteur() {
		return compteur;
	}
}
