import util.Bateau;
import util.Transport;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Bateau> bateaux = new ArrayList<>();

        Bateau bat1 = new Bateau("XC21", 45);
        Bateau bat2 = new Bateau("XC56", 90);
        Bateau bat3 = new Bateau("XC100", 225);
        Bateau bat4 = new Bateau("XC800", 360);

        bateaux.add(bat1);
        bateaux.add(bat2);
        bateaux.add(bat3);
        bateaux.add(bat4);

        Transport transport = new Transport(bateaux, true);
        transport.display();

        cost(transport, solution());

        // TODO equals pour les états (Transport)

    }

    private static Transport calcul(Transport transportInitial) {

        Transport etatSol = solution();
        Set<Transport> queueState = new HashSet<>();
        Map<Transport, Integer> dist = new HashMap<>();
        Map<Transport, Transport> sp = new HashMap<>();

        dist.put(transportInitial, 0);
        queueState.add(transportInitial);

        while (!queueState.isEmpty()) {
            // On récupère le transport (l'état) le plus proche dans la queue
            Transport petit = queueState.stream().min((t, v) -> Integer.compare(dist.get(t), dist.get(v))).get();
            queueState.remove(petit);
            // On récupère les mouvements possibles pour l'état le plus proche
            for (Map.Entry<Transport, Transport> entry : petit.mouvementsPossibles().entrySet()) {
                int ndist = dist.get(petit) + cost(petit, entry.getValue());
                // on ne l'a pas visité
                if (dist.get(entry.getValue()) == null) {
                    queueState.add(entry.getValue());
                    dist.put(entry.getValue(), -1);
                }
                if (ndist > dist.get(entry.getValue())) {
                    dist.put(entry.getValue(), ndist);
                    sp.put(entry.getValue(), petit);
                }
            }
        }
        return etatSol;
    }

    // TODO demander au prof si je suis sur la bonne voie
    private static int cost(Transport eI, Transport eF) {
//        Collection firstList = eI.getBateauxStart();
//        Collection secondList = eF.getBateauxStart();
//
//        for (int i = 0; i < firstList.toArray().length; i++) {
//            firstList
//        }
//
//        System.out.println("First List: " + firstList);
//        System.out.println("Second List: " + secondList);
//
//        secondList.removeAll(firstList);
//
//        System.out.println("Result: " + secondList);

        return 1;
    }

    private static Transport solution() {
        List<Bateau> bateauxSol = new ArrayList<>();

        bateauxSol.add(new Bateau("XC21", 45));
        bateauxSol.add(new Bateau("XC56", 90));
        bateauxSol.add(new Bateau("XC100", 225));
        bateauxSol.add(new Bateau("XC800", 360));

        return new Transport(bateauxSol, false);
    }
}