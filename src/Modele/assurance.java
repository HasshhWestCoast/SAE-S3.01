package Modele;

public class assurance {

	private String numeroPolice;
	private float montant;
	private String dateEcheance;
	private Immeuble immeuble;
	private Entreprise entreprise;

	public assurance(String numeroPolice, float montant, String dateEcheance, Immeuble immeuble, Entreprise entreprise) {
		this.numeroPolice = numeroPolice;
		this.montant = montant;
		this.dateEcheance = dateEcheance;
		this.immeuble = immeuble;
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

	public Immeuble getImmeuble() {
		return immeuble;
	}

	public void setImmeuble(Immeuble immeuble) {
		this.immeuble = immeuble;
	}

	public String getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(String dateEcheance) {
		this.dateEcheance = dateEcheance;
	}



}