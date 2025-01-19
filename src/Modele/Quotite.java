package Modele;

import java.util.Objects;

public class Quotite {

	private String typeQuotite;

	public Quotite(String typeQuotite) {
		this.typeQuotite = typeQuotite;
	}

	public String getTypeQuotite() {
		return this.typeQuotite;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Logement)) {
			return false;
		}
		Quotite other = (Quotite) obj;
		return Objects.equals(typeQuotite, other.typeQuotite);
	}


	@Override
	public String toString() {
		return "Quotite [typeQuotite=" + typeQuotite + "]";
	}	
	

}
