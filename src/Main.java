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

        List<Bateau> toMove = new ArrayList<>();
        toMove.add(bat1);
        toMove.add(bat2);

        //transport.move(toMove, 1);
        //transport.display();
        //transport.mouvementsPossibles();
        calcul(transport);
    }

    public static Transport calcul(Transport transportInitial) {

        Transport etatSol = solution();
        Set<Transport> queueState = new HashSet<>();
        Map<Transport, Integer> dist = new HashMap<>();
        Map<Transport, Transport> sp = new HashMap<>();

        dist.put(transportInitial, 0);
        queueState.add(transportInitial);

        while (!queueState.isEmpty()) {
            Transport petit = queueState.stream().min((t, v) -> Integer.compare(dist.get(t),dist.get(v))).get();
            petit.display();
            queueState.remove(petit);
            for (Map.Entry<Transport, Transport> entry : petit.mouvementsPossibles().entrySet())
            {
                Integer ndist = dist.get(entry.getValue()) + cost(petit, entry.getValue());
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

    private static Integer cost(Transport value, Transport petit) {

    }

    public static Transport solution() {
        List<Bateau> bateauxSol = new ArrayList<>();

        bateauxSol.add(new Bateau("XC21", 45));
        bateauxSol.add(new Bateau("XC56", 90));
        bateauxSol.add(new Bateau("XC100", 225));
        bateauxSol.add(new Bateau("XC800", 360));

        return new Transport(bateauxSol, false);
    }
}