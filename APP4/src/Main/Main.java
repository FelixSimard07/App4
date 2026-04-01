package Main;

import electronique.*;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Composant r1 = new Resistance(1.1);
        Composant r2 = new Resistance(1.5);
        Composant r3 = new Resistance(2);
        Composant r4 = new Resistance(4.75);

        List<Composant> brancheGauche = new ArrayList<>();
        brancheGauche.add(r1);
        brancheGauche.add(r2);
        Composant serieGauche = new CircuitSerie(brancheGauche);

        double Rg = serieGauche.calculerResistance();

        System.out.println(Rg);

        List<Composant> brancheDroite = new ArrayList<>();
        brancheDroite.add(r3);
        brancheDroite.add(r4);
        Composant serieDroite = new CircuitSerie(brancheDroite);

        double Rd = serieDroite.calculerResistance();

        System.out.println(Rd);

        List<Composant>  circuitPrincipale = new ArrayList<>();
        circuitPrincipale.add(serieDroite);
        circuitPrincipale.add(serieGauche);

        Composant circuit = new CircuitParallele(circuitPrincipale);

        double Req = circuit.calculerResistance();

        System.out.println(Req);

    }
}