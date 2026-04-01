package electronique;

public class Resistance extends Composant {

    private double valeurResistance;

    public Resistance (double valeurResistance) {
        this.valeurResistance = valeurResistance;
    }

    @Override
    public double calculerResistance() {
        return valeurResistance;
    }

}
