import util.Bateau;
import util.Transport;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<Bateau> bateaux = new HashSet<>();

        Bateau bat1 = new Bateau("XC21", 45);
        Bateau bat2 = new Bateau("XC56", 90);
        Bateau bat3 = new Bateau("XC100", 225);
        Bateau bat4 = new Bateau("XC800", 360);

        bateaux.add(bat1);
        bateaux.add(bat2);
        bateaux.add(bat3);
        bateaux.add(bat4);

        Transport transportInitial = new Transport(bateaux, true);
        transportInitial.display();

        Transport etatSol = solution();
        Set<Transport> queueState = new HashSet<>();
        Map<Transport, Integer> dist = new HashMap<>();
        Map<Transport, Transport> sp = new HashMap<>();
        Set<Transport> visited = new HashSet<>();

        dist.put(transportInitial, 0);
        queueState.add(transportInitial);

        while (!queueState.isEmpty()) {
            // On récupère le transport (l'état) le plus proche dans la queue
            Transport etat = queueState.stream().min((t, v) -> Integer.compare(dist.get(t), dist.get(v))).get();
            queueState.remove(etat);
            visited.add(etat);
            etat.display();
            if (etat.equals(etatSol)) {
                break;
            }
            // On récupère les mouvements possibles pour l'état le plus proche
            for (Transport next : etat.mouvementsPossibles()) {
                if (!visited.contains(next)) {
                    int ndist = dist.get(etat) + next.getCost();
                    // on ne l'a pas visité
                    if (dist.get(next) == null) {
                        queueState.add(next);
                        dist.put(next, ndist);
                        sp.put(next, etat);
                    } else if (ndist < dist.get(next)) {
                        dist.put(next, ndist);
                        sp.put(next, etat);
                    }
                }
            }
        }
    }

    private static Transport solution() {
        Set<Bateau> bateauxSol = new HashSet<>();

        bateauxSol.add(new Bateau("XC21", 45));
        bateauxSol.add(new Bateau("XC56", 90));
        bateauxSol.add(new Bateau("XC100", 225));
        bateauxSol.add(new Bateau("XC800", 360));

        return new Transport(bateauxSol, false);
    }
}