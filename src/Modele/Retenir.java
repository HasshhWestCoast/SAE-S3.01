package Modele;

public class Retenir {
    private Charge charge; 
    private Louer louer; 

    // Constructeur avec paramètres
    public Retenir(Charge charge, Louer louer) {
        this.charge = charge;
        this.louer = louer;
    }

    // Getters et Setters
    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public Louer getLouer() {
        return louer;
    }

    public void setLouer(Louer louer) {
        this.louer = louer;
    }


    // Méthode toString()
    @Override
    public String toString() {
        return "Retenir{" +
                "charge=" + (charge != null ? charge.getIdCharge() : "null") +
                ", louer=" + (louer != null ? louer.getLocataire().getIdLocataire() : "null") +
                '}';
    }
}
