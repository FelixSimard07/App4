package app;

import electronique.Composant;

import java.io.File;
import java.util.Scanner;

public class CircuitApp {

    private static final String DOSSIER = "C:/Users/2445549/Desktop/App4/APP4/src/donnees/fichiers_json";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CircuitBuilder nouveauCircuit = new CircuitBuilder();

        boolean continuer = true;

        while (continuer) {


            File dossier = new File(DOSSIER);
            File[] fichiers = dossier.listFiles((s, name) -> name.endsWith(".json"));

            if (fichiers == null || fichiers.length == 0) {
                System.out.println("Aucun fichier JSON trouvé.");
            }

            System.out.println();
            System.out.println("*** Choisir un circuit ***");
            System.out.println();

            for (int i = 0; i < fichiers.length; i++) {
                System.out.println("[" + (i + 1) + "] pour " + fichiers[i].getName());
            }

            int choix = -1;

            while (true) {
                System.out.print("Entré ici : ");

                if (sc.hasNextInt()) {
                    choix = sc.nextInt();

                    if (choix >= 1 && choix <= fichiers.length) {
                        break;
                    }
                } else {
                    sc.next();
                }

                System.out.println("Entrée invalide. Réessaie.");
            }


            try {
                String chemin = fichiers[choix - 1].getPath();

                Composant circuit = nouveauCircuit.construireCircuit(chemin);

                double resultat = circuit.calculerResistance();

                System.out.println("Résistance équivalente : " + (String.format("%.2f", resultat)) + " Ω");

            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
            String action;

            while (true) {
                System.out.println();
                System.out.println("       === MENU ===");
                System.out.println();
                System.out.println("[Q] pour fermer l'application");
                System.out.println("[R] pour tester un autre circuit");
                System.out.print("Entré ici : ");
                action = sc.next();

                if (action.equalsIgnoreCase("R") || action.equalsIgnoreCase("Q")) {
                    break;
                }

                System.out.println("Entrée invalide. Tapez R ou Q.");
            }

            if (action.equalsIgnoreCase("Q")) {
                continuer = false;
                System.out.println("Fermeture de l'application.");
                System.out.println("Bonne journée");
            }
        }
        sc.close();
    }
}
