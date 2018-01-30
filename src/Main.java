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
        transport.mouvementsPossibles();
    }

    public static Transport calcul(Transport transport) throws Exception {

        Transport etatSol = solution();

        // Creation of the queue of state
        Queue<Transport> queueState = new ArrayDeque<>();

        // Creation of the map of predecessor
        Map<Transport, Transport> predecessors = new HashMap<>();

        List<Transport> etatsVisited = new ArrayList<>();

        while(!queueState.isEmpty()) {

        }

        return etatSol;
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