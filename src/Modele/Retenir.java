package Modele;

public class Retenir {
    private Charge charge; // Référence à une instance de Charge
    private Louer louer; // Référence à une instance de Louer
    private double montantAssocie; // Montant associé pour cette relation (si nécessaire)

    // Constructeur vide
    public Retenir() {}

    // Constructeur avec paramètres
    public Retenir(Charge charge, Louer louer, double montantAssocie) {
        this.charge = charge;
        this.louer = louer;
        this.montantAssocie = montantAssocie;
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

    public double getMontantAssocie() {
        return montantAssocie;
    }

    public void setMontantAssocie(double montantAssocie) {
        this.montantAssocie = montantAssocie;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Retenir{" +
                "charge=" + (charge != null ? charge.getIdCharge() : "null") +
                ", louer=" + (louer != null ? louer.getLocataire().getIdLocataire() : "null") +
                ", montantAssocie=" + montantAssocie +
                '}';
    }
}
