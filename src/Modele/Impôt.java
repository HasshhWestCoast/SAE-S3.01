package Modele;

public class Impôt {

	private int idImpot; // AUTO INCREMENT
	private String nom;
	private double montant;
	private String annee;

	public Impôt(String nom, double montant, String annee) {
		this.nom = nom;
		this.montant = montant;
		this.annee = annee;
	}

	public int getIdImpot() {
		return this.idImpot;
	}

	public void setIdImpot(int idImpot) {
		this.idImpot = idImpot;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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