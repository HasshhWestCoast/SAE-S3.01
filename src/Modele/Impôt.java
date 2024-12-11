package Modele;

public class Impôt {

	private String idImpot; // AUTO INCREMENT
	private double montant;
	private String annee;

	public Impôt(String idImpot, double montant, String annee) {
		this.idImpot = idImpot;
		this.montant = montant;
		this.annee = annee;
	}

	public String getIdImpot() {
		return this.idImpot;
	}

	public void setIdImpot(String idImpot) {
		this.idImpot = idImpot;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getAnnee() {
		return this.annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

}