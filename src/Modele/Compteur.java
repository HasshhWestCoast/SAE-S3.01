package Modele;

public class Compteur {
	private String idCompteur;
	private String typeComp;
	private int indexCompteur;
	private String dateRelevé;
	private Bien bien;
	private Logement immeuble;

	public Compteur(String idCompteur, String typeComp, int indexCompteur, String dateRelevé, Bien bien, Logement immeuble) {
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

	public int getIndexCompteur() {
		return indexCompteur;
	}

	public void setIndexCompteur(int indexCompteur) {
		this.indexCompteur = indexCompteur;
	}

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public Logement getImmeuble() {
		return immeuble;
	}

	public void setImmeuble(Logement immeuble) {
		this.immeuble = immeuble;
	}

	public String getDateRelevé() {
		return dateRelevé;
	}

	public void setDateRelevé(String dateRelevé) {
		this.dateRelevé = dateRelevé;
	}

}