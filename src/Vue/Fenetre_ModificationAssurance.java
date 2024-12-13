package Vue;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Fenetre_ModificationAssurance extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre_ModificationAssurance frame = new Fenetre_ModificationAssurance();
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
	public Fenetre_ModificationAssurance() {
		setBounds(100, 100, 450, 300);

	}

}
