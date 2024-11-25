package Modele;

public class assurance {

	private String numeroPolice;
	private float montant;
	private Bien bien;
	private Entreprise entreprise;

	public assurance(String numeroPolice, float montant, Bien bien, Entreprise entreprise) {
		this.numeroPolice = numeroPolice;
		this.montant = montant;
		this.bien = bien;
		this.entreprise = entreprise;
	}

	public Entreprise getEntreprise() {
		return this.entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public String getNuméroPolice() {
		return this.numeroPolice;
	}

	public void setNuméroPolice(String numeroPolice) {
		this.numeroPolice = numeroPolice;
	}

	public float getMontant() {
		return this.montant;
	}

	public void setMontant(float montantInit) {
		this.montant = montantInit;
	}

	public Bien getBien() {
		return this.bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}
}