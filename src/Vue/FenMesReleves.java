package Vue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controleur.GestionFenMesReleves;
import Modele.Compteur;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class FenMesReleves extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable tabMesReleves;
	private GestionFenMesReleves gestionFenMesReleves;
	private Compteur compteur;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FenMesReleves(Compteur compteur) throws SQLException {
		this.compteur= compteur;
		this.gestionFenMesReleves = new GestionFenMesReleves(this);
		
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 675, 549);
		getContentPane().setLayout(null);
		
		
        tabMesReleves = new JTable();
        tabMesReleves.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null},
            },
            new String[] {
                "ID Compteur", "Date Relevés", "Index"
            }
        ));
        
		JScrollPane scrollPaneCompteursLogements = new JScrollPane(tabMesReleves);
		scrollPaneCompteursLogements.setBorder(new LineBorder(new Color(31, 153, 88), 2));
		scrollPaneCompteursLogements.setBounds(30, 59, 580, 367);
		getContentPane().add(scrollPaneCompteursLogements);
		
		JLabel lblMesReleves = new JLabel("Mes Relevés");
		lblMesReleves.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesReleves.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblMesReleves.setBackground(Color.WHITE);
		lblMesReleves.setBounds(207, 10, 216, 21);
		getContentPane().add(lblMesReleves);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(31, 151, 83));
		separator.setBounds(207, 29, 225, 2);
		getContentPane().add(separator);
		
		RoundedButton btnAjouter = new RoundedButton("Ajouter", 20);
		btnAjouter.addActionListener(this.gestionFenMesReleves);
		btnAjouter.setBounds(250, 455, 104, 21);
		getContentPane().add(btnAjouter);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.addActionListener(this.gestionFenMesReleves);
		btnAnnuler.setBounds(112, 455, 104, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnSupprimer = new RoundedButton("Supprimer", 20);
		btnSupprimer.addActionListener(this.gestionFenMesReleves);
		btnSupprimer.setBounds(379, 455, 129, 21);
		getContentPane().add(btnSupprimer);

	}
	
	public JTable getTabMesReleves() {
		return tabMesReleves;
	}

	public Compteur getMonCompteur() {
		return compteur;
	}
}
