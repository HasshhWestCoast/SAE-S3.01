package Modele;

import java.util.Objects;

public class ICC {

	private int icc;
	private String annee;
	private String trimestre;
	private double indice;

	public ICC(int ICC, String annee, String trimestre, double indice) {
		this.icc = ICC;
		this.annee = annee;
		this.trimestre = trimestre;
		this.indice = indice;
	}
	
	public int getIcc() {
		return icc;
	}

	public void setIcc(int icc) {
		this.icc = icc;
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

	public void setIndice(double indice) {
		this.indice = indice;
	}

	@Override
	public String toString() {
		return "ICC [icc=" + icc + ", annee=" + annee + ", trimestre=" + trimestre + ", indice=" + indice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(icc);
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
		return icc == other.icc;
	}
}