package Vue.Insertion;

import java.awt.Color;
import java.awt.Font;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controleur.Ajout.GestionFenAjoutLocataire;
import Vue.RoundedButton;

public class FenAjoutLocataire extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldMail;
	private JTextField textFieldDateNaissance;
	private JTextField textFieldTelephone;
	private JTextField textFieldQuotite;
	private GestionFenAjoutLocataire gestionClicFenAjoutLocataire;


	/**
	 * Create the frame.
	 */
	public FenAjoutLocataire() {
		
		this.gestionClicFenAjoutLocataire = new GestionFenAjoutLocataire(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(82, 123, 80, 13);
		getContentPane().add(lblNom);
		
		JTextField textNom = new JTextField();
		textNom.setBounds(80, 137, 223, 19);
		getContentPane().add(textNom);
		textNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(82, 179, 94, 13);
		getContentPane().add(lblPrenom);
		
		JTextField textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(82, 195, 223, 19);
		getContentPane().add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setBounds(82, 234, 80, 13);
		getContentPane().add(lblTelephone);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(82, 293, 94, 13);
		getContentPane().add(lblMail);
		
		JLabel lblIdLocataire = new JLabel("ID Locataire");
		lblIdLocataire.setBounds(80, 68, 96, 13);
		getContentPane().add(lblIdLocataire);
		
		JTextField textFieldIDLocataire = new JTextField();
		textFieldIDLocataire.setBounds(80, 84, 223, 19);
		getContentPane().add(textFieldIDLocataire);
		textFieldIDLocataire.setColumns(10);
		
		JLabel lblAjoutLocataire = new JLabel("Ajout Locataire");
		lblAjoutLocataire.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutLocataire.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutLocataire.setBounds(124, 26, 131, 21);
		getContentPane().add(lblAjoutLocataire);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(93, 45, 188, 2);
		getContentPane().add(separator);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(80, 311, 223, 19);
		getContentPane().add(textFieldMail);
		textFieldMail.setColumns(10);
		
		JLabel lbldateNaissance = new JLabel("Date de Naissance");
		lbldateNaissance.setBounds(82, 352, 109, 13);
		getContentPane().add(lbldateNaissance);
		
		textFieldDateNaissance = new JTextField();
		textFieldDateNaissance.setBounds(80, 370, 223, 19);
		getContentPane().add(textFieldDateNaissance);
		textFieldDateNaissance.setColumns(10);
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setColumns(10);
		textFieldTelephone.setBounds(82, 252, 223, 19);
		getContentPane().add(textFieldTelephone);
		
		JLabel lblQuotite = new JLabel("Quotite");
		lblQuotite.setBounds(82, 413, 109, 13);
		getContentPane().add(lblQuotite);
		
		textFieldQuotite = new JTextField();
		textFieldQuotite.setColumns(10);
		textFieldQuotite.setBounds(80, 428, 223, 19);
		getContentPane().add(textFieldQuotite);
		setBounds(200, 100, 423, 540);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.addActionListener(this.gestionClicFenAjoutLocataire);
		btnAnnuler.setBounds(82, 468, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.addActionListener(this.gestionClicFenAjoutLocataire);
		btnAjouter.setBounds(217, 468, 85, 21);
		getContentPane().add(btnAjouter);
	}
}
