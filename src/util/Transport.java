package util;

import java.util.*;

public class Transport {
    private Set<Bateau> bateauxStart = new HashSet<>();
    private Set<Bateau> bateauxEnd = new HashSet<>();
    private int protectionTeam;
    private int cost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return protectionTeam == transport.protectionTeam &&
                Objects.equals(bateauxStart, transport.bateauxStart) &&
                Objects.equals(bateauxEnd, transport.bateauxEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bateauxStart, bateauxEnd, protectionTeam);
    }

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
        this.cost = 0;
    }

    public Transport(Set<Bateau> bateaux, boolean startOrEnd) {
        for (Bateau b : bateaux) {
            if (startOrEnd) {
                this.protectionTeam = 1;
                this.bateauxStart.add(b);
            } else {
                this.protectionTeam = 2;
                this.bateauxEnd.add(b);
            }
        }
    }

    public Transport(Set<Bateau> bateauxStart, Set<Bateau> bateauxEnd, int protectionTeam, int cost) {
        for (Bateau b : bateauxStart) {
            this.bateauxStart.add(b);
        }
        for (Bateau b : bateauxEnd) {
            this.bateauxEnd.add(b);
        }
        this.protectionTeam = protectionTeam;
        this.cost = cost;
    }

    public Set<Bateau> getBateauxStart() {
        return bateauxStart;
    }

    public void setBateauxStart(Set<Bateau> bateauxStart) {
        this.bateauxStart = bateauxStart;
    }

    public Set<Bateau> getBateauxEnd() {
        return bateauxEnd;
    }

    public void setBateauxEnd(Set<Bateau> bateauxEnd) {
        this.bateauxEnd = bateauxEnd;
    }

    public void display() {
        System.out.println("--------------------- GAUCHE ---------------------");

        for (Bateau b : this.getBateauxStart()) {
            System.out.println(b.getName() + " --- " + b.getTime());
        }
        if (this.protectionTeam == 1) {
            System.out.println("PROTECTION TEAM A GAUCHE");
        }

        System.out.println("--------------------- DROITE ---------------------");
        for (Bateau b : this.getBateauxEnd()) {
            System.out.println(b.getName() + " --- " + b.getTime());
        }
        if (this.protectionTeam == 2) {
            System.out.println("PROTECTION TEAM A DROITE");
        }
    }

    public Set<Transport> mouvementsPossibles() {

        Set<Transport> movable = new HashSet<>();

        if (this.protectionTeam == 2) {
            Bateau batFast = null;
            for (Bateau b : this.getBateauxEnd()) {
                if (batFast == null || b.getTime() < batFast.getTime()) {
                    batFast = b;
                }
            }
            Transport etat = new Transport(this.getBateauxStart(), this.getBateauxEnd(), this.getProtectionTeam(), this.getCost());
            etat.setProtectionTeam(1);
            etat.setCost(batFast.getTime());
            etat.getBateauxEnd().remove(batFast);
            etat.getBateauxStart().add(batFast);
            movable.add(etat);
        } else {
            List<Bateau> list = new ArrayList<>();
            list.addAll(this.getBateauxStart());
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    Bateau b1 = list.get(i);
                    Bateau b2 = list.get(j);

                    Transport etat = new Transport(this.getBateauxStart(), this.getBateauxEnd(), this.getProtectionTeam(), this.getCost());
                    etat.setProtectionTeam(2);
                    etat.setCost(Math.max(b1.getTime(), b2.getTime()));
                    etat.getBateauxEnd().add(b1);
                    etat.getBateauxEnd().add(b2);
                    etat.getBateauxStart().remove(b1);
                    etat.getBateauxStart().remove(b2);
                    movable.add(etat);
                }
            }
        }
        return movable;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
