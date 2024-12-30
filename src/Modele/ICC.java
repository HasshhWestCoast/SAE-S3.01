package Modele;

import java.util.Objects;

public class ICC {

	private String annee;
	private String trimestre;
	private int indice;

	public ICC(String annee, String trimestre, int indice) {
		this.annee = annee;
		this.trimestre = trimestre;
		this.indice = indice;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

	public double getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(annee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ICC)) {
			return false;
		}
		ICC other = (ICC) obj;
		return Objects.equals(annee, other.annee);
	}

	@Override
	public String toString() {
		return "ICC [annee=" + annee + ", trimestre=" + trimestre + ", indice=" + indice + "]";
	}
	
	
	
	

}