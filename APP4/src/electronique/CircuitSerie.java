package electronique;

import java.util.List;

public class CircuitSerie extends Circuit {

    public CircuitSerie (List<Composant> circuitSerie) {
        super(circuitSerie);
    }

    @Override
    public double calculerResistance() {
        double somme = 0;
        for (Composant c : composants) {
            somme += c.calculerResistance();
        }
        return somme;
    }
}
