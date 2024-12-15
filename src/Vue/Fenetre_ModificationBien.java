package Vue;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Fenetre_ModificationBien extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre_ModificationBien frame = new Fenetre_ModificationBien();
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
	public Fenetre_ModificationBien() {
		setBounds(100, 100, 450, 300);

	}

}
