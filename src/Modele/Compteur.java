package Modele;

public class Compteur {
	private String idCompteur;
	private String typeComp;
	private double indexCompteur;
	private String dateRelevé;
	private Bien bien;
	private Immeuble immeuble;

	public Compteur(String idCompteur, String typeComp,double indexCompteur, String dateRelevé, Bien bien, Immeuble immeuble) {
		this.idCompteur = idCompteur;
		this.typeComp = typeComp;
		this.indexCompteur = indexCompteur;
		this.dateRelevé = dateRelevé;
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

	public double getPrix_indexCompteur() {
		return indexCompteur;
	}

	public void setPrix_indexCompteur(double indexCompteur) {
		this.indexCompteur = indexCompteur;
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

	public String getDateRelevé() {
		return dateRelevé;
	}

	public void setDateRelevé(String dateRelevé) {
		this.dateRelevé = dateRelevé;
	}

}