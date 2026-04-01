package app;

import com.fasterxml.jackson.databind.JsonNode;
import electronique.Composant;
import electronique.Resistance;

import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {
    public CircuitBuilder() {

    }

    public Composant construireCircuit(String type) {
        return null;
    }

    private Composant lireComposant(JsonNode node) {
        String type = node.get("type").asText();

        if ("resistance".equals(type)) {
            return new Resistance(node.get(valeur).asText());

        } else if ("serie".equals(type)) {
            List<Composant> composants = new ArrayList<>();
            for (JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }

        } else if ("parallele".equals(type)) {
            List<Composant> composants = new ArrayList<>();

        }

    }
}
