package Modele;

public class Imposer {
	
	private Bien bien;
	private Impot impot;

	public Imposer(Bien bien, Impot impot) {
		this.bien = bien;
		this.impot = impot;
	}

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public Impot getImpot() {
		return impot;
	}

	public void setImpot(Impot impot) {
		this.impot = impot;
	}

}