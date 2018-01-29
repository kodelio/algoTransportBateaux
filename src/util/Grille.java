package util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grille {
    private List<Case> cases = new ArrayList<>();

    public Grille(){
        this.cases.add(new Case(0,0));
        this.cases.add(new Case(1,0));
        this.cases.add(new Case(2,0));
        this.cases.add(new Case(0,1));
        this.cases.add(new Case(1,1));
        this.cases.add(new Case(2,1));
        this.cases.add(new Case(0,2));
        this.cases.add(new Case(1,2));
        this.cases.add(new Case(2,2));
    }

    public Grille(List<Case> casesInput){
        this.cases = casesInput;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public Map<Case, Case> casesThatCanMove() throws Exception {

        Case emptyCase = new Case();
        for (Case ca : cases){
            if (ca.getValue() == 0) {
                emptyCase = ca;
            }
        }

        if (emptyCase == null) {
            throw new Exception(" pas de case vide");
        }

        Map<Case, Case> moovableCases = new HashMap<>();

        Case testCase = new Case();

        // Case du haut
        testCase = getCaseFromListbyPos(emptyCase.getPosX(), emptyCase.getPosY() - 1);
        if ( testCase != null)
            moovableCases.put(testCase, emptyCase);

        // Case du bas
        testCase = getCaseFromListbyPos(emptyCase.getPosX(), emptyCase.getPosY() + 1);
        if ( testCase != null)
            moovableCases.put(testCase, emptyCase);

        // Case de gauche
        testCase = getCaseFromListbyPos(emptyCase.getPosX() - 1, emptyCase.getPosY());
        if ( testCase != null)
            moovableCases.put(testCase, emptyCase);

        // Case de droite
        testCase = getCaseFromListbyPos(emptyCase.getPosX() + 1, emptyCase.getPosY());
        if ( testCase != null)
            moovableCases.put(testCase, emptyCase);


        return moovableCases;
    }

    public Grille moove(Case ca1, Case ca2){
        Case caTmp = new Case(ca1.getPosX(), ca1.getPosY(), ca1.getValue());
        ca1.setValue(ca2.getValue());
        ca2.setValue(caTmp.getValue());

        return this;

//        if (ca1.getPosY() < ca2.getPosY())
//            return "↓";

//        if (ca1.getPosY() > ca2.getPosY())
//            return "↑";

//        if (ca1.getPosX() < ca2.getPosX())
//            return "→";

//        return "←";
    }

    public Case getCaseFromListbyPos(int posX, int posY){
        for (Case ca : this.cases){
            if (ca.getPosX() == posX && ca.getPosY() == posY){
                return ca;
            }
        }
        return null;
    }

    public void displayGrille(){
        System.out.println("+----+----+----+");
        System.out.println("+ "+ this.cases.get(0).getValue() + "  " + "+ " + this.cases.get(1).getValue() + "  " + "+ " + this.cases.get(2).getValue() + "  +");
        System.out.println("+----+----+----+");
        System.out.println("+ "+ this.cases.get(3).getValue() + "  " + "+ " + this.cases.get(4).getValue() + "  " + "+ " + this.cases.get(5).getValue() + "  +");
        System.out.println("+----+----+----+");
        System.out.println("+ "+ this.cases.get(6).getValue() + "  " + "+ " + this.cases.get(7).getValue() + "  " + "+ " + this.cases.get(8).getValue() + "  +");
        System.out.println("+----+----+----+");
    }

    public boolean equals(Grille grille){
        boolean state = true;
        for (int i = 0; i < this.getCases().size(); i ++){
            if (!this.getCases().get(i).equals(grille.getCases().get(i))){
                state = false;
                break;
            }
        }
        return state;
    }
}
