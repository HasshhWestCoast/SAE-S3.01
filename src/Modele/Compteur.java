package Modele;

public class Compteur {
	private String idCompteur;
	private String typeComp;
	private double prixAbonnement;
	private Bien bien;
	private Immeuble immeuble;

	public Compteur(String idCompteur, String typeComp,double prixAbonnement,Bien bien, Immeuble immeuble) {
		this.idCompteur = idCompteur;
		this.typeComp = typeComp;
		this.prixAbonnement = prixAbonnement;
		this.bien = bien;
		this.immeuble = immeuble;
	}

	public String getIdCompteur() {
		return idCompteur;
	}

	public void setIdCompteur(String idCompteur) {
		this.idCompteur = idCompteur;
	}

	public String getTypeComp() {
		return typeComp;
	}

	public void setTypeComp(String typeComp) {
		this.typeComp = typeComp;
	}

	public double getPrix_abonnement() {
		return prixAbonnement;
	}

	public void setPrix_abonnement(double prixAbonnement) {
		this.prixAbonnement = prixAbonnement;
	}

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public Immeuble getImmeuble() {
		return immeuble;
	}

	public void setImmeuble(Immeuble immeuble) {
		this.immeuble = immeuble;
	}

}