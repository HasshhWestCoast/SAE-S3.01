package Vue;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controleur.Afficher.GestionFenCompteursBien;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class FenCompteursBien extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private GestionFenCompteursBien gestionClicFenCompBien;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenCompteursBien frame = new FenCompteursBien();
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
	public FenCompteursBien() {
		
		this.gestionClicFenCompBien = new GestionFenCompteursBien(this);
		
		getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);
		
		 // JTable pour "Bien"
        JTable tabMesBiens = new JTable();
        tabMesBiens.setModel(new DefaultTableModel(
            new Object[][] {
                { null, null, null},
            },
            new String[] {
                "ID Compteur", "Type Compteur", "Prix abonnement"
            }
        ));
        JScrollPane scrollPaneBiens = new JScrollPane(tabMesBiens);
        scrollPaneBiens.setBounds(23, 77, 580, 367);
        scrollPaneBiens.setBorder(new LineBorder(new Color(31, 153, 88), 2));
        getContentPane().add(scrollPaneBiens);
        
        JLabel lblMesCompteursbien = new JLabel("Mes Compteurs Biens");
        lblMesCompteursbien.setHorizontalAlignment(SwingConstants.CENTER);
        lblMesCompteursbien.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        lblMesCompteursbien.setBackground(Color.WHITE);
        lblMesCompteursbien.setBounds(194, 27, 194, 21);
        getContentPane().add(lblMesCompteursbien);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE);
        separator.setBackground(new Color(31, 151, 83));
        separator.setBounds(200, 46, 176, 2);
        getContentPane().add(separator);
        
        RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
        btnAnnuler.addActionListener(this.gestionClicFenCompBien);
        btnAnnuler.setBounds(61, 472, 115, 21);
        getContentPane().add(btnAnnuler);
        
        RoundedButton btnAfficherRelevé = new RoundedButton("Afficher les relevés", 20);
        btnAfficherRelevé.addActionListener(this.gestionClicFenCompBien);
        btnAfficherRelevé.setBounds(194, 472, 182, 21);
        getContentPane().add(btnAfficherRelevé);
        
        RoundedButton btnAjouterRelevé = new RoundedButton("Ajouter un relevé", 20);
        btnAjouterRelevé.addActionListener(this.gestionClicFenCompBien);
        btnAjouterRelevé.setBounds(394, 472, 163, 21);
        getContentPane().add(btnAjouterRelevé);
        
		setBounds(100, 100, 669, 550);

	}
}
