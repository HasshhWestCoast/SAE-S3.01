package vue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;

public class FenetreAssurance extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable tableAssurance;
	private JScrollPane scrollPaneTableauAssurance;

	/**
	 * Create the frame.
	 */
	public FenetreAssurance() {
		setTitle("Assurances");
		setFrameIcon(new ImageIcon(FenetreAssurance.class.getResource("/data/contrat.png")));
		setBorder(new LineBorder(new Color(0, 0, 128), 2));
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 922, 301);
		getContentPane().setLayout(null);
		
		JLabel lblAjoutDuneAssurance = new JLabel("Assurances de mes biens");
		lblAjoutDuneAssurance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutDuneAssurance.setFont(new Font("Segoe UI Variable", Font.BOLD, 20));
		lblAjoutDuneAssurance.setBounds(10, 10, 898, 34);
		getContentPane().add(lblAjoutDuneAssurance);
		
		JComboBox<Object> comboBoxIDImmeuble = new JComboBox<>();
		comboBoxIDImmeuble.setBackground(Color.WHITE);
		comboBoxIDImmeuble.setModel(new DefaultComboBoxModel<>(new String[] {"ID immeuble"}));
		comboBoxIDImmeuble.setToolTipText("ID immeuble");
		comboBoxIDImmeuble.setBounds(10, 58, 124, 21);
		getContentPane().add(comboBoxIDImmeuble);
		
		JButton btnConfirmer = new JButton("Insérer");
		btnConfirmer.setIcon(new ImageIcon(FenetreAssurance.class.getResource("/data/arrow-down.png")));
		btnConfirmer.setBackground(Color.WHITE);
		btnConfirmer.setBounds(341, 229, 103, 21);
		getContentPane().add(btnConfirmer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setIcon(new ImageIcon(FenetreAssurance.class.getResource("/data/marque-de-croix.png")));
		btnAnnuler.setBackground(Color.WHITE);
		btnAnnuler.setBounds(494, 229, 103, 21);
		getContentPane().add(btnAnnuler);
		
		scrollPaneTableauAssurance = new JScrollPane();
		scrollPaneTableauAssurance.setBounds(10, 89, 890, 106);
		getContentPane().add(scrollPaneTableauAssurance);
		
		tableAssurance = new JTable();
		tableAssurance.setBorder(new LineBorder(new Color(0, 0, 128)));
		tableAssurance.setBackground(Color.WHITE);
		tableAssurance.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Numéro police", "ID assurance", "Protection juridique", "Prime", "Quotité", "Date de début", "Date de fin"
			}
		));
		scrollPaneTableauAssurance.setViewportView(tableAssurance);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 128));
		separator.setBounds(318, 46, 272, 11);
		getContentPane().add(separator);

	}
}