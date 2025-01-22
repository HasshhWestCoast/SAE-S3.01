package Vue;

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

import Controleur.Afficher.GestionFenLocataire;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FenInfosLocataire extends JInternalFrame {
	
	private GestionFenLocataire gestionFenLocataire;

	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdentifiant;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldNumTelephone;
	private JTextField textFieldMail;
	private JTextField textFieldDateNaissance;
	private JTable tabRegCharges;
	private JTable tabSoldeToutCompte;

	
	
	public FenInfosLocataire() throws SQLException {
			this.gestionFenLocataire = new GestionFenLocataire(this);
		getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);
		
		 // JTable pour "Bien"
        tabRegCharges = new JTable();
        tabRegCharges.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null},
            },
            new String[] {
                "Bien", "période du", "au", "charge réelle", "Ordures ménagère", "Total charges", "Restant du loyer", "Total provision","reste"
            }
        ));
        JScrollPane scrollPaneRegCharges = new JScrollPane(tabRegCharges);
        scrollPaneRegCharges.setBounds(324, 119, 580, 71);
        scrollPaneRegCharges.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        getContentPane().add(scrollPaneRegCharges);
        
        JLabel lblMesCompteursbien = new JLabel("Mon locataire");
        lblMesCompteursbien.setBounds(526, 22, 194, 21);
        lblMesCompteursbien.setHorizontalAlignment(SwingConstants.CENTER);
        lblMesCompteursbien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        lblMesCompteursbien.setBackground(Color.WHITE);
        getContentPane().add(lblMesCompteursbien);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(534, 43, 176, 2);
        separator.setForeground(Color.WHITE);
        separator.setBackground(new Color(31, 151, 83));
        getContentPane().add(separator);
        
        RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
        btnAnnuler.addActionListener(this.gestionFenLocataire);
        btnAnnuler.setText("Annuler");
        btnAnnuler.setBackground(new Color(31, 153, 88));
        btnAnnuler.setBounds(443, 468, 96, 21);
        getContentPane().add(btnAnnuler);
        
        RoundedButton btnCharger = new RoundedButton("Charger", 20);
        btnCharger.setText("Retour");
        getContentPane().add(btnCharger);
        
        JLabel label_operations = new JLabel("Opérations");
        label_operations.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_operations.setBounds(324, 96, 96, 21);
        getContentPane().add(label_operations);
        
        tabSoldeToutCompte = new JTable();
        tabSoldeToutCompte.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null, null},
            },
            new String[] {
                "Provision sur charge","Charge Réelles", "Caution", "Travaux imputable","Restant du loyer","Reste"
            }
        ));
        JScrollPane scrollPaneSoldeToutCompte = new JScrollPane(tabSoldeToutCompte);
        scrollPaneSoldeToutCompte.setBounds(324, 327, 580, 71);
        scrollPaneSoldeToutCompte.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        getContentPane().add(scrollPaneSoldeToutCompte);
        
        RoundedButton btn_solde_tout_compte = new RoundedButton("Solde tout compte ", 20);
        btn_solde_tout_compte.addActionListener(this.gestionFenLocataire);
        btn_solde_tout_compte.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn_solde_tout_compte.setBounds(608, 291, 247, 21);
        btn_solde_tout_compte.setBackground(new Color(31, 151, 83));
        getContentPane().add(btn_solde_tout_compte);
        
        JLabel lblSiVotreReste = new JLabel("si votre reste est positif, alors vous devez de l'argent a votre locataire. sinon, il vous doit de l'argent.");
        lblSiVotreReste.setBounds(324, 189, 570, 13);
        getContentPane().add(lblSiVotreReste);
        
        JLabel lblSiVotreReste_2 = new JLabel("si votre reste est négatif, alors votre locataire vous doit de l'argent. sinon, vous lui en devez.");
        lblSiVotreReste_2.setBounds(324, 396, 570, 13);
        getContentPane().add(lblSiVotreReste_2);
        
        RoundedButton btnRegularisation = new RoundedButton("Charger", 20);
        btnRegularisation.addActionListener(this.gestionFenLocataire);
        btnRegularisation.setText("Régularisation des charges");
        btnRegularisation.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegularisation.setBackground(new Color(31, 153, 88));
        btnRegularisation.setBounds(597, 65, 247, 21);
        getContentPane().add(btnRegularisation);
        
        JLabel lblSiVotreReste_2_1 = new JLabel("Départ de votre locataire :");
        lblSiVotreReste_2_1.setBounds(549, 268, 128, 13);
        getContentPane().add(lblSiVotreReste_2_1);
        
        JSeparator separatorVertical = new JSeparator();
        separatorVertical.setOrientation(SwingConstants.VERTICAL);
        separatorVertical.setForeground(Color.WHITE);
        separatorVertical.setBackground(new Color(31, 151, 83));
        separatorVertical.setBounds(288, 39, 11, 450);
        getContentPane().add(separatorVertical);
        
        JLabel lebelDetailLocataire = new JLabel("Détail locataire");
        lebelDetailLocataire.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lebelDetailLocataire.setBounds(70, 52, 142, 21);
        getContentPane().add(lebelDetailLocataire);
        
        textFieldIdentifiant = new JTextField();
        textFieldIdentifiant.setColumns(10);
        textFieldIdentifiant.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Identifiant", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textFieldIdentifiant.setBounds(29, 103, 194, 41);
        getContentPane().add(textFieldIdentifiant);
        
        textFieldNom = new JTextField();
        textFieldNom.setColumns(10);
        textFieldNom.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Nom", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textFieldNom.setBounds(29, 170, 194, 41);
        getContentPane().add(textFieldNom);
        
        textFieldPrenom = new JTextField();
        textFieldPrenom.setColumns(10);
        textFieldPrenom.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Prénom", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textFieldPrenom.setBounds(29, 232, 194, 41);
        getContentPane().add(textFieldPrenom);
        
        textFieldNumTelephone = new JTextField();
        textFieldNumTelephone.setColumns(10);
        textFieldNumTelephone.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Num Téléphone", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textFieldNumTelephone.setBounds(29, 356, 194, 41);
        getContentPane().add(textFieldNumTelephone);
        
        textFieldMail = new JTextField();
        textFieldMail.setColumns(10);
        textFieldMail.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Mail", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textFieldMail.setBounds(29, 413, 194, 41);
        getContentPane().add(textFieldMail);
        
        textFieldDateNaissance = new JTextField();
        textFieldDateNaissance.setColumns(10);
        textFieldDateNaissance.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Date Naissance", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
        textFieldDateNaissance.setBounds(29, 292, 194, 41);
        getContentPane().add(textFieldDateNaissance);
        
<<<<<<< HEAD
        RoundedButton btnAnnuler_1 = new RoundedButton("Annuler", 20);
        btnAnnuler_1.setText("Modifier");
        btnAnnuler_1.setBackground(new Color(31, 153, 88));
        btnAnnuler_1.setBounds(597, 468, 96, 21);
        getContentPane().add(btnAnnuler_1);
=======
        RoundedButton btnArchiver = new RoundedButton("Annuler", 20);
        btnArchiver.setText("Archiver");
        btnArchiver.setBackground(new Color(31, 153, 88));
        btnArchiver.setBounds(622, 468, 96, 21);
        getContentPane().add(btnArchiver);
>>>>>>> c1f09023258610508133b9bf01bcd162c66afc9e
        
        RoundedButton rndbtnWordRegularisation = new RoundedButton("Word Regularisation des charges", 20);
        rndbtnWordRegularisation.addActionListener(this.gestionFenLocataire);
        rndbtnWordRegularisation.setBackground(new Color(31, 153, 88));
        rndbtnWordRegularisation.setBounds(309, 67, 247, 21); // Ajustez la taille si nécessaire
        getContentPane().add(rndbtnWordRegularisation);

        RoundedButton btnWordSoldeToutCompte = new RoundedButton("Word solde tout compte", 20);
        btnWordSoldeToutCompte.addActionListener(this.gestionFenLocataire);
        btnWordSoldeToutCompte.setBackground(new Color(31, 153, 88));
        btnWordSoldeToutCompte.setBounds(322, 293, 247, 21); // Ajustez la taille si nécessaire
        getContentPane().add(btnWordSoldeToutCompte);
        RoundedButton rndbtnWordRegulaularisationD = new RoundedButton("Annuler", 20);
        rndbtnWordRegulaularisationD.setText("Word Regularisation d charges");
        rndbtnWordRegulaularisationD.setBackground(new Color(31, 153, 88));
        rndbtnWordRegulaularisationD.setBounds(348, 67, 116, 21);
        getContentPane().add(rndbtnWordRegulaularisationD);
        
		setBounds(100, 100, 948, 550);
	}
	
	// Méthodes pour textFieldIdentifiant
	public String getTextFieldIdentifiant() {
	    return textFieldIdentifiant.getText();
	}

	public void setTextFieldIdentifiant(String identifiant) {
	    textFieldIdentifiant.setText(identifiant);
	}

	// Méthodes pour textFieldNom
	public String getTextFieldNom() {
	    return textFieldNom.getText();
	}

	public void setTextFieldNom(String nom) {
	    textFieldNom.setText(nom);
	}

	// Méthodes pour textFieldPrenom
	public String getTextFieldPrenom() {
	    return textFieldPrenom.getText();
	}

	public void setTextFieldPrenom(String prenom) {
	    textFieldPrenom.setText(prenom);
	}

	// Méthodes pour textFieldNumTelephone
	public String getTextFieldNumTelephone() {
	    return textFieldNumTelephone.getText();
	}

	public void setTextFieldNumTelephone(String numTelephone) {
	    textFieldNumTelephone.setText(numTelephone);
	}

	// Méthodes pour textFieldMail
	public String getTextFieldMail() {
	    return textFieldMail.getText();
	}

	public void setTextFieldMail(String mail) {
	    textFieldMail.setText(mail);
	}

	// Méthodes pour textFieldDateNaissance
	public String getTextFieldDateNaissance() {
	    return textFieldDateNaissance.getText();
	}

	public void setTextFieldDateNaissance(String dateNaissance) {
	    textFieldDateNaissance.setText(dateNaissance);
	}
	public JTable getTabRegCharges() {
	    return this.tabRegCharges;
	}
	
	public JTable gettabSoldeToutCompte() {
	    return this.tabSoldeToutCompte;
	}
}
