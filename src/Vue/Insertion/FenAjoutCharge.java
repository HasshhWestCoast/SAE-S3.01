package Vue.Insertion;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Controleur.Ajout.GestionFenAjoutCharge;
import Modele.Bien;

import javax.swing.border.EtchedBorder;
import Vue.RoundedButton;

public class FenAjoutCharge extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldIDCharge;
	private JTextField textFieldNom;
	private JTextField textFieldMontantReel;
	private JTextField textFieldMontantPrevisionel;
	private JTextField textFieldDeductible;
	private JTextField textFieldDateCharge;
	private GestionFenAjoutCharge gestionFenAjoutCharge;
	private Bien bien;


	/**
	 * Create the frame.
	 */
	public FenAjoutCharge(Bien bien) {
		this.bien = bien;
		this.gestionFenAjoutCharge = new GestionFenAjoutCharge(this);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblAjoutCharge = new JLabel("Ajout Charge");
		lblAjoutCharge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutCharge.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutCharge.setBounds(148, 23, 131, 21);
		getContentPane().add(lblAjoutCharge);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(117, 42, 188, 2);
		getContentPane().add(separator);
		
		textFieldIDCharge = new JTextField();
		textFieldIDCharge.setColumns(10);
		textFieldIDCharge.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "ID Charge", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldIDCharge.setBounds(31, 74, 161, 36);
		getContentPane().add(textFieldIDCharge);
		
		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Nom", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldNom.setBounds(31, 137, 161, 36);
		getContentPane().add(textFieldNom);
		
		textFieldMontantReel = new JTextField();
		textFieldMontantReel.setColumns(10);
		textFieldMontantReel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Montant Reel", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldMontantReel.setBounds(31, 202, 161, 36);
		getContentPane().add(textFieldMontantReel);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.addActionListener(gestionFenAjoutCharge);
		btnAjouter.setBackground(new Color(33, 153, 88));
		btnAjouter.setBounds(240, 281, 85, 21);
		getContentPane().add(btnAjouter);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.addActionListener(gestionFenAjoutCharge);
		btnAnnuler.setBackground(new Color(33, 153, 88));
		btnAnnuler.setBounds(107, 281, 85, 21);
		getContentPane().add(btnAnnuler);
		
		textFieldMontantPrevisionel = new JTextField();
		textFieldMontantPrevisionel.setColumns(10);
		textFieldMontantPrevisionel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Montant Previsionel", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldMontantPrevisionel.setBounds(240, 79, 161, 36);
		getContentPane().add(textFieldMontantPrevisionel);
		
		textFieldDeductible = new JTextField();
		textFieldDeductible.setColumns(10);
		textFieldDeductible.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Deductible", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldDeductible.setBounds(240, 141, 161, 36);
		getContentPane().add(textFieldDeductible);
		
		textFieldDateCharge = new JTextField();
		textFieldDateCharge.setColumns(10);
		textFieldDateCharge.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,new Color(31, 153, 88), new Color(160, 160, 160)), "Date Charge", TitledBorder.LEADING, TitledBorder.TOP, null,new Color(31, 153, 88)));
		textFieldDateCharge.setBounds(240, 202, 161, 36);
		getContentPane().add(textFieldDateCharge);
		setBounds(100, 100, 462, 388);

	}

	public String getIDCharge() {
	    return textFieldIDCharge.getText();
	}

	public String getNom() {
	    return textFieldNom.getText();
	}

	public String getMontantReel() {
	    return textFieldMontantReel.getText();
	}

	public String getMontantPrevisionel() {
	    return textFieldMontantPrevisionel.getText();
	}

	public String getDeductible() {
	    return textFieldDeductible.getText();
	}

	public String getDateCharge() {
	    return textFieldDateCharge.getText();
	}


	public Bien getBien() {
		return bien;
	}
}
