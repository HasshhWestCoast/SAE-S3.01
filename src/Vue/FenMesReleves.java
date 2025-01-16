package Vue;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class FenMesReleves extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable tabMesReleves;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenMesReleves frame = new FenMesReleves();
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
	public FenMesReleves() {
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
		btnAjouter.setBounds(250, 455, 104, 21);
		getContentPane().add(btnAjouter);
		
		RoundedButton btnAnnuler = new RoundedButton("Annuler", 20);
		btnAnnuler.setBounds(112, 455, 104, 21);
		getContentPane().add(btnAnnuler);
		
		RoundedButton btnSupprimer = new RoundedButton("Ajouter", 20);
		btnSupprimer.setText("Supprimer");
		btnSupprimer.setBounds(379, 455, 129, 21);
		getContentPane().add(btnSupprimer);

	}
}
