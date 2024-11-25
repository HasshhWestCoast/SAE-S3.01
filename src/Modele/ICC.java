package Modele;

public class ICC {

	private String annee;
	private String trimestre;
	private double indice;

	public ICC(String annee, String trimestre, double indice) {
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

	public void setIndice(double indice) {
		this.indice = indice;
	}

}