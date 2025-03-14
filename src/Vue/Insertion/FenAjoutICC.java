package Vue.Insertion;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controleur.Ajout.GestionFenAjoutICC;
import Vue.RoundedButton;

public class FenAjoutICC extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldAnnee;
	private JTextField textFieldTrimestre;
	private JTextField textFieldIndice;
	private GestionFenAjoutICC gestionClicFenAjoutICC;
    private FenAjoutLocation fenAjoutLocation; 
    private JTextField textFieldIdIcc;

	/**
	 * Create the frame.
	 */
	public FenAjoutICC(FenAjoutLocation fenAjoutLocation) {
		
		this.fenAjoutLocation = fenAjoutLocation;
		this.gestionClicFenAjoutICC = new GestionFenAjoutICC(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblAnnee = new JLabel("Année");
		lblAnnee.setBounds(79, 142, 45, 13);
		getContentPane().add(lblAnnee);
		
		JLabel lblTrimestre = new JLabel("Trimestre");
		lblTrimestre.setBounds(79, 202, 66, 13);
		getContentPane().add(lblTrimestre);
		
		JLabel lblIndice = new JLabel("Indice");
		lblIndice.setBounds(80, 267, 131, 13);
		getContentPane().add(lblIndice);
		
		JLabel lblICC = new JLabel("Ajout ICC");
		lblICC.setHorizontalAlignment(SwingConstants.CENTER);
		lblICC.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblICC.setBounds(124, 26, 131, 21);
		getContentPane().add(lblICC);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(93, 45, 188, 2);
		getContentPane().add(separator);
		
		textFieldAnnee = new JTextField();
		textFieldAnnee.setBounds(79, 162, 226, 19);
		getContentPane().add(textFieldAnnee);
		textFieldAnnee.setColumns(10);
		
		textFieldTrimestre = new JTextField();
		textFieldTrimestre.setBounds(80, 226, 223, 19);
		getContentPane().add(textFieldTrimestre);
		textFieldTrimestre.setColumns(10);
		
		textFieldIndice = new JTextField();
		textFieldIndice.setBounds(80, 290, 226, 19);
		getContentPane().add(textFieldIndice);
		textFieldIndice.setColumns(10);
		setBounds(100, 100, 423, 453);


		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.addActionListener(this.gestionClicFenAjoutICC);
		btnAnnuler.setBounds(80, 353, 85, 21);
		btnAnnuler.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.addActionListener(this.gestionClicFenAjoutICC);
		btnAjouter.setBounds(215, 353, 85, 21);
		btnAjouter.setBackground(new Color(31, 151, 83));
		getContentPane().add(btnAjouter);
		
		JLabel lblIDIcc = new JLabel("ID icc");
		lblIDIcc.setBounds(79, 76, 45, 13);
		getContentPane().add(lblIDIcc);
		
		textFieldIdIcc = new JTextField();
		textFieldIdIcc.setColumns(10);
		textFieldIdIcc.setBounds(79, 99, 226, 19);
		getContentPane().add(textFieldIdIcc);
	}
	
	public FenAjoutLocation getFenAjoutLocation() {
        return this.fenAjoutLocation;
    }
	
	public String getAnnee() {
		return textFieldAnnee.getText();
	}
	
	public String getTrimestre() {
		return textFieldTrimestre.getText();
	}
	
	public String getIndice() {
		return textFieldIndice.getText();
	}
	
	public String getIDIcc() {
		return textFieldIdIcc.getText();
	}

}
