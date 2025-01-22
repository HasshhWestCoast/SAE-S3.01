package Vue.Insertion;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Controleur.Ajout.GestionFenAjoutQuotite;
import Modele.Logement;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import Vue.RoundedButton;

public class FenAjoutQuotite extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldPourcentage;
	private JTextField textFieldTypeQuotite;
	private Logement logement;
	private GestionFenAjoutQuotite gestionFenAjoutQuotite;

	/**
	 * Create the frame.
	 */
	public FenAjoutQuotite(Logement logement) {
		this.gestionFenAjoutQuotite = new GestionFenAjoutQuotite(this);
		this.logement = logement;
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 320, 303);
		getContentPane().setLayout(null);
		
		textFieldPourcentage = new JTextField();
		textFieldPourcentage.setColumns(10);
		textFieldPourcentage.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Pourcentage", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldPourcentage.setBounds(66, 44, 161, 36);
		getContentPane().add(textFieldPourcentage);
		
		textFieldTypeQuotite = new JTextField();
		textFieldTypeQuotite.setColumns(10);
		textFieldTypeQuotite.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Type de Quotite", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldTypeQuotite.setBounds(66, 105, 161, 36);
		getContentPane().add(textFieldTypeQuotite);
		
		RoundedButton btnAnnuler = new RoundedButton("Supprimer", 20);
		btnAnnuler.setText("Annuler");
		btnAnnuler.addActionListener(gestionFenAjoutQuotite);
		btnAnnuler.setBackground(new Color(31, 153, 88));
		btnAnnuler.setBounds(162, 210, 96, 23);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnInserer = new RoundedButton("Inserer", 20);
		btnInserer.addActionListener(gestionFenAjoutQuotite);
		btnInserer.setBackground(new Color(31, 153, 88));
		btnInserer.setBounds(43, 210, 80, 23);
		getContentPane().add(btnInserer);

	}
	
	public String getPourcentage() {
		return textFieldPourcentage.getText();
	}
	
	public String getTypeQuotite() {
		return textFieldTypeQuotite.getText();
	}

	public Logement getMonLogement() {
		return logement;
	}
}
