package Modele;

import java.util.Objects;

public class Compteur {
	private String idCompteur;
	private String typeComp;
	private int indexCompteur;
	private String dateRelevé;
	private Bien bien;
	private Logement logement;

	public Compteur(String idCompteur, String typeComp, int indexCompteur, String dateRelevé, Bien bien, Logement logement) {
		this.idCompteur = idCompteur;
		this.typeComp = typeComp;
		this.indexCompteur = indexCompteur;
		this.dateRelevé = dateRelevé;
		this.bien = bien;
		this.logement = logement;
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
		return logement;
	}

	public void setImmeuble(Logement logement) {
		this.logement = logement;
	}

	public String getDateRelevé() {
		return dateRelevé;
	}

	public void setDateRelevé(String dateRelevé) {
		this.dateRelevé = dateRelevé;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCompteur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Compteur)) {
			return false;
		}
		Compteur other = (Compteur) obj;
		return Objects.equals(idCompteur, other.idCompteur);
	}

	@Override
	public String toString() {
		return "Compteur [idCompteur=" + idCompteur + ", typeComp=" + typeComp + ", indexCompteur=" + indexCompteur
				+ ", dateRelevé=" + dateRelevé + ", bien=" + bien + ", logement=" + logement + "]";
	}
	
	
	
	

}