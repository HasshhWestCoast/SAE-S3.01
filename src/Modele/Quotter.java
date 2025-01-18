package Modele;

import java.util.Objects;

public class Quotter {

	private Logement logemenet;
	private Quotite quotite;
	private int pourcentage;
	
	public Quotter(int pourcentage, Logement logemenet, Quotite quotite) {
		this.pourcentage = pourcentage;
		this.logemenet = logemenet;
		this.quotite = quotite;
	}

	public Logement getLogemenet() {
		return logemenet;
	}
	public void setLogemenet(Logement logemenet) {
		this.logemenet = logemenet;
	}
	public Quotite getQuotite() {
		return quotite;
	}
	public void setQuotite(Quotite quotite) {
		this.quotite = quotite;
	}
	public int getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(logemenet.getIdLogement(), pourcentage, quotite.getTypeQuotite());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Quotter)) {
			return false;
		}
		Quotter other = (Quotter) obj;
		return Objects.equals(logemenet.getIdLogement(), other.logemenet.getIdLogement()) && pourcentage == other.pourcentage
				&& Objects.equals(quotite.getTypeQuotite(), other.quotite.getTypeQuotite());
	}
	
	@Override
	public String toString() {
		return "Quotter [logemenet=" + logemenet + ", quotite=" + quotite + ", pourcentage=" + pourcentage + "]";
	}

}
