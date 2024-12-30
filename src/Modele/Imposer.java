package Modele;

public class Imposer {
	
	private Bien bien;
	private Impôt impot;

	public Imposer(Bien bien, Impôt impot) {
		this.bien = bien;
		this.impot = impot;
	}

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public Impôt getImpot() {
		return impot;
	}

	public void setImpot(Impôt impot) {
		this.impot = impot;
	}

	@Override
	public String toString() {
		return "Imposer [bien=" + bien + ", impot=" + impot + "]";
	}
	
	

}