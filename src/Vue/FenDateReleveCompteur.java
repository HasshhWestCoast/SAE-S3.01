package Vue;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenDateReleveCompteur extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private RoundedButton btnSupprimer;
	private RoundedButton btnAnnuler;
	private JTable tabMesDatesReleves;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenDateReleveCompteur frame = new FenDateReleveCompteur();
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
	public FenDateReleveCompteur() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 656, 549);
		getContentPane().setLayout(null);
		
        tabMesDatesReleves = new JTable();
        tabMesDatesReleves.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null},
            },
            new String[] {
                "ID Compteur", "Date Relevé", "Index Compteur"
            }
        ));
        
		JScrollPane scrollPaneCompteursLogements = new JScrollPane(tabMesDatesReleves);
		scrollPaneCompteursLogements.setBorder(new LineBorder(new Color(31, 153, 88), 2));
		scrollPaneCompteursLogements.setBounds(21, 79, 580, 367);
		getContentPane().add(scrollPaneCompteursLogements);
		
		JLabel lblMesDateReleve = new JLabel("Mes Dates Releves");
		lblMesDateReleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesDateReleve.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblMesDateReleve.setBackground(Color.WHITE);
		lblMesDateReleve.setBounds(192, 29, 216, 21);
		getContentPane().add(lblMesDateReleve);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(181, 48, 225, 2);
		getContentPane().add(separator);
		
		btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.setBounds(111, 474, 104, 21);
		getContentPane().add(btnAnnuler);
		
		btnSupprimer = new RoundedButton("Charger", 20);
		btnSupprimer.setText("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSupprimer.setBounds(247, 474, 104, 21);
		getContentPane().add(btnSupprimer);
		
		RoundedButton rndbtnAjouter = new RoundedButton("Charger", 20);
		rndbtnAjouter.setText("Ajouter");
		rndbtnAjouter.setBounds(371, 474, 104, 21);
		getContentPane().add(rndbtnAjouter);

	}
	
	public JTable getTabMesDatesReleves() {
		return tabMesDatesReleves;
	}
}
