package app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {
    public CircuitBuilder() {

    }

    public Composant construireCircuit(String chemainFichier) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode branche = mapper.readTree(new File(chemainFichier));
            return lireComposant(branche.get("circuit"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Composant lireComposant(JsonNode node) {
        String type = node.get("type").asText();

        if ("resistance".equals(type)) {
            double valeur = node.get("valeur").asDouble();
            return new Resistance(valeur);

        } else if ("serie".equals(type)) {
            List<Composant> composants = new ArrayList<>();
            for (JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }
            return new CircuitSerie(composants);

        } else if ("parallele".equals(type)) {
            List<Composant> composants = new ArrayList<>();
            for (JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }
            return new CircuitParallele(composants);
        }
        throw new IllegalArgumentException("Type de composant inconnu : " + type);
    }
}
