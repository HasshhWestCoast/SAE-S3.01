package Vue.Insertion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import Vue.RoundedButton;

public class FenAjoutReleves extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldDateReleves;
	private JTextField textFieldIndex;
	private RoundedButton btnAnnuler;
	private RoundedButton btnAjouter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenAjoutReleves frame = new FenAjoutReleves();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenAjoutReleves() {
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 384, 281);
		getContentPane().setLayout(null);
		
		JLabel lblAjoutLocataire = new JLabel("Ajout Locataire");
		lblAjoutLocataire.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutLocataire.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblAjoutLocataire.setBounds(116, 10, 131, 21);
		getContentPane().add(lblAjoutLocataire);
		
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
		btnAnnuler.setBounds(75, 179, 85, 21);
		getContentPane().add(btnAnnuler);
		
		btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.setBounds(210, 179, 85, 21);
		getContentPane().add(btnAjouter);

	}
}
