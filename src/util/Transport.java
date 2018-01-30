package util;

import java.util.*;

public class Transport {
    private List<Bateau> bateauxStart = new ArrayList<>();
    private List<Bateau> bateauxEnd = new ArrayList<>();
    private int protectionTeam;

    public int getProtectionTeam() {
        return protectionTeam;
    }

    public void setProtectionTeam(int protectionTeam) {
        this.protectionTeam = protectionTeam;
    }

    public Transport() {
        this.bateauxStart.add(new Bateau("XC21", 45));
        this.bateauxStart.add(new Bateau("XC56", 90));
        this.bateauxStart.add(new Bateau("XC100", 225));
        this.bateauxStart.add(new Bateau("XC800", 360));
        this.protectionTeam = 1;
    }

    public Transport(List<Bateau> bateaux, boolean startOrEnd) {
        for (int i = 0; i < bateaux.size(); i++) {
            Bateau bat = new Bateau();
            bat.copy(bateaux.get(i));
            if (startOrEnd) {
                this.protectionTeam = 1;
                this.bateauxStart.add(bat);
            } else {
                this.protectionTeam = 2;
                this.bateauxEnd.add(bat);
            }
        }
    }

    public Transport(List<Bateau> bateauxStart, List<Bateau> bateauxEnd, int protectionTeam) {
        for (int i = 0; i < bateauxStart.size(); i++) {
            Bateau bat = new Bateau();
            bat.copy(bateauxStart.get(i));
            this.bateauxStart.add(bat);
        }
        for (int i = 0; i < bateauxEnd.size(); i++) {
            Bateau bat = new Bateau();
            bat.copy(bateauxEnd.get(i));
            this.bateauxEnd.add(bat);
        }
        this.protectionTeam = protectionTeam;
    }

    public List<Bateau> getBateauxStart() {
        return bateauxStart;
    }

    public void setBateauxStart(List<Bateau> bateauxStart) {
        this.bateauxStart = bateauxStart;
    }

    public List<Bateau> getBateauxEnd() {
        return bateauxEnd;
    }

    public void setBateauxEnd(List<Bateau> bateauxEnd) {
        this.bateauxEnd = bateauxEnd;
    }

    public void display() {
        System.out.println("--------------------- DEBUT ---------------------");
        for (int i = 0; i < this.bateauxStart.size(); i++) {
            System.out.println(this.bateauxStart.get(i).getName() + " --- " + this.bateauxStart.get(i).getTime());
        }
        if (this.protectionTeam == 1) {
            System.out.println("PROTECTION TEAM");
        }

        System.out.println("---------------------- FIN ----------------------");
        for (int i = 0; i < this.bateauxEnd.size(); i++) {
            System.out.println(this.bateauxEnd.get(i).getName() + " --- " + this.bateauxEnd.get(i).getTime());
        }
        if (this.protectionTeam == 2) {
            System.out.println("PROTECTION TEAM");
        }
    }

    public void mouvementsPossibles() {

        Map<Transport, Transport> movable = new HashMap<>();

        Transport eI = new Transport(getBateauxStart(), getBateauxEnd(), getProtectionTeam());

        if (eI.getProtectionTeam() == 1) {
            Map<Bateau, Bateau> al = new HashMap<>();
            for (Bateau b : eI.getBateauxStart()) {
                for (Bateau bat : eI.getBateauxStart()) {
                    if (!b.equals(bat)) {
                        al.put(b, bat);
                    }
                }
            }
            createMap(al);
            //movable.put(eI, );
        }
    }

    public static <K, V> Map<K, V> createMap(Map<K, V> m) {
        Map<K, V> map = new HashMap<K, V>();
        Map<V, K> tmpMap = new HashMap<V, K>();
        for(Map.Entry<K, V> entry : m.entrySet()) {
            if (!tmpMap.containsKey(entry.getValue())) {
                tmpMap.put(entry.getValue(), entry.getKey());
            }
        }
        for(Map.Entry<V, K> entry : tmpMap.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        return map;
    }

    public void move(List<Bateau> bateaux, int sens) {
        if (sens == 1) {
            for (Bateau bat : bateaux) {
                this.bateauxEnd.add(bat);

                Bateau deleteBat = new Bateau();

                for (Bateau bat2 : this.bateauxStart) {
                    if (bat2.equals(bat)) {
                        deleteBat = bat2;
                    }
                }
                this.bateauxStart.remove(deleteBat);
                this.protectionTeam = 2;
            }
        } else {
            for (Bateau bat : bateaux) {
                this.bateauxStart.add(bat);

                Bateau deleteBat = new Bateau();

                for (Bateau bat2 : this.bateauxEnd) {
                    if (bat2.equals(bat)) {
                        deleteBat = bat2;
                    }
                }
                this.bateauxEnd.remove(deleteBat);
                this.protectionTeam = 1;
            }
        }

    }
}
