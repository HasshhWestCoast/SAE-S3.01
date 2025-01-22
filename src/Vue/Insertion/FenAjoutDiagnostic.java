package Vue.Insertion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Controleur.Ajout.GestionFenAjoutDiagnostic;
import Modele.Bien;

import javax.swing.border.EtchedBorder;
import Vue.RoundedButton;

public class FenAjoutDiagnostic extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdDiagnostic;
	private JTextField textFieldType;
	private JTextField textFieldDate;
	private GestionFenAjoutDiagnostic gestionFenAjoutDiagnostic;
	private Bien precedent;


	/**
	 * Create the frame.
	 */
	public FenAjoutDiagnostic(Bien precedent) {
		this.precedent = precedent;
		this.gestionFenAjoutDiagnostic = new GestionFenAjoutDiagnostic(this);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 411, 341);
		getContentPane().setLayout(null);
		
		JLabel lblAjoutDiagnostic = new JLabel("Ajout Diagnostic");
		lblAjoutDiagnostic.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutDiagnostic.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutDiagnostic.setBounds(132, 24, 131, 21);
		getContentPane().add(lblAjoutDiagnostic);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(101, 43, 188, 2);
		getContentPane().add(separator);
		
		textFieldIdDiagnostic = new JTextField();
		textFieldIdDiagnostic.setColumns(10);
		textFieldIdDiagnostic.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "ID Diagnostic", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldIdDiagnostic.setBounds(115, 78, 161, 36);
		getContentPane().add(textFieldIdDiagnostic);
		
		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Date Validité", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldDate.setBounds(115, 140, 161, 36);
		getContentPane().add(textFieldDate);
		
		textFieldType = new JTextField();
		textFieldType.setColumns(10);
		textFieldType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Type", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldType.setBounds(115, 201, 161, 36);
		getContentPane().add(textFieldType);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.setBackground(new Color(33, 153, 88));
		btnAnnuler.addActionListener(gestionFenAjoutDiagnostic);
		btnAnnuler.setBounds(95, 262, 85, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.setBackground(new Color(33, 153, 88));
		btnAjouter.addActionListener(gestionFenAjoutDiagnostic);
		btnAjouter.setBounds(209, 262, 85, 21);
		getContentPane().add(btnAjouter);

	}
	
	public String getIdDiagnostic() {
		return textFieldIdDiagnostic.getText();
	}
	
	public String getType() {
		return textFieldType.getText();
	}
	
	public String getDate() {
		return textFieldDate.getText();
	}
	
	public Bien getPrecedent() {
		return precedent;
	}
}
