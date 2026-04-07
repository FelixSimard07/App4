package app;

import electronique.Composant;

import java.io.File;
import java.util.Scanner;

public class CircuitApp {

    private static final String DOSSIER = "donnees";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CircuitBuilder nouveauCircuit = new CircuitBuilder();

        boolean continuer = true;

        while (continuer) {

            File dossier = new File(DOSSIER);
            File[] fichiers = dossier.listFiles((dir, name) -> name.endsWith(".json"));

            if (fichiers == null || fichiers.length == 0) {
                System.out.println("Aucun fichier JSON trouvé.");
            }

            System.out.println("=== Choisir un circuit ===");
            for (int i = 0; i < fichiers.length; i++) {
                System.out.println("[" + (i + 1) + "] " + fichiers[i].getName());
            }

            int choix = -1;

            while (true) {
                System.out.print("Votre choix: ");

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

                System.out.printf("Résistance équivalente : " + resultat + " Ω");

            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }

            System.out.println("[R] Tester un autre fichier");
            System.out.println("[Q] Quitter");

            String action = sc.next();

            if (action.equalsIgnoreCase("Q")) {
                continuer = false;
                System.out.println("Fermeture de l'application.");
            }
        }

        sc.close();
    }
}
