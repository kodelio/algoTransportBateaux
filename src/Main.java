import util.Case;
import util.Grille;

import java.util.*;

public class Main {

    public static void displayHashMap(Map<Case, Case> map){
        for (Case key: map.keySet()){
            System.out.println("Case disponible à bouger :");
            System.out.println("(" + key.getPosX() + ", " + key.getPosY()+ ") = " + key.getValue() + " ==> " + "("
                    + map.get(key).getPosX() + ", " + map.get(key).getPosY() +  ") = " + map.get(key).getValue());
            System.out.println(" ");
        }
    }

    public static Grille gridSol(){
        Grille gridSol = new Grille();

        List<Integer> valuesCases = new ArrayList<>();

        valuesCases.add(1);
        valuesCases.add(2);
        valuesCases.add(3);
        valuesCases.add(4);
        valuesCases.add(5);
        valuesCases.add(6);
        valuesCases.add(7);
        valuesCases.add(8);
        valuesCases.add(0);

        int i = 0;

        for (Case ca : gridSol.getCases()){
            ca.setValue(valuesCases.get(i));
            i++;
        }
        return gridSol;

    }

    public static Grille algoProfondeur(Grille grilleInput, List<String> operationsInput) throws Exception {

        Grille gridSol = gridSol();

        // Creation of the queue of state
        Queue<Grille> queueState = new ArrayDeque<>();

        // Creation of the map of predecessor
        Map<Grille, Grille> predecessors = new HashMap<>();

        // Creation of the list of state visited
        List<Grille> gridVisited = new ArrayList<>();

        // The initial state is being added to the queue
        queueState.add(grilleInput);

        // We set the predecessor as null for the initial state because it's the beginning
        predecessors.put(grilleInput, null);

        // We set the result grid to null
        Grille grilleSolution = null;

        // Iteration over the queue
        while (!queueState.isEmpty() && grilleSolution == null){

            //We pop the first value and work with it
            Grille currentGrille = queueState.poll();
            currentGrille.displayGrille();

            // Collect of the possible states
            List<Grille> possibleState = new ArrayList<>();
            Map<Case, Case> casesMap = currentGrille.casesThatCanMove();
            for (Case ca : casesMap.keySet()){
                Grille gr = new Grille(currentGrille.getCases());

                System.out.println("******************************************************************");
                gr.displayGrille();
                System.out.println("******************************************************************");

                gr = gr.moove(ca, casesMap.get(ca));

                System.out.println("==================================================================");
                gr.displayGrille();
                System.out.println("==================================================================");

                boolean grilleInList = false;

                for (Grille grille : gridVisited){
                    if (grille.equals(gr)){
                        grilleInList = true;
                        break;
                    }
                }

                if (!grilleInList){
                    gridVisited.add(gr);
                    possibleState.add(gr);
                }
            }

            // Iteration into the possible states
            for (Grille grid : possibleState){
                queueState.add(grid);
                predecessors.put(grid, currentGrille);
                if( grid.equals(gridSol)){
                    grilleSolution = grid;
                }
            }
        }

        return grilleSolution;
    }
/*
    public static Grille testMove(Grille grilleInput, List<String> operations){
        Grille grille = grilleInput;

        try {
            Map<Case, Case> cases =  grille.casesThatCanMove();
            System.out.println(cases.size());

            displayHashMap(cases);

            grille.displayGrille();

            for (Case key: cases.keySet()){
                operations.add(grille.moove(key, cases.get(key)));
                System.out.println(" ");
                grille.displayGrille();
                grille = initGrille(2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return grille;
    }
 */


    public static Grille initGrille(int choix){

        List<Integer> valeursCases = new ArrayList<>();

        if (choix == 1){
            valeursCases.add(1);
            valeursCases.add(2);
            valeursCases.add(3);
            valeursCases.add(4);
            valeursCases.add(5);
            valeursCases.add(6);
            valeursCases.add(7);
            valeursCases.add(0);
            valeursCases.add(8);
        } else {
            valeursCases.add(2);
            valeursCases.add(3);
            valeursCases.add(3);
            valeursCases.add(4);
            valeursCases.add(5);
            valeursCases.add(6);
            valeursCases.add(7);
            valeursCases.add(0);
            valeursCases.add(8);
        }

        int i = 0;

        Grille grille = new Grille();

        for (Case ca : grille.getCases()){
            ca.setValue(valeursCases.get(i));
            i++;
        }
        return grille;
    }

    public static void printOperations(List<String> operations){
        int i = 1;

        for (String elem : operations){
            System.out.println(i + " : " + elem);
            i++;
        }
    }

    public static void main(String[] args) {

        List<String> operations = new ArrayList<>();

        Grille grille = initGrille(1);

        System.out.println("Test FINAL");

        try {
            algoProfondeur(grille, operations).displayGrille();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//        valeursCases.add(3);
//        valeursCases.add(1);
//        valeursCases.add(2);
//        valeursCases.add(4);
//        valeursCases.add(7);
//        valeursCases.add(5);
//        valeursCases.add(6);
//        valeursCases.add(0);
//        valeursCases.add(8);

//        valeursCases.add(5);
//        valeursCases.add(3);
//        valeursCases.add(2);
//        valeursCases.add(4);
//        valeursCases.add(1);
//        valeursCases.add(0);
//        valeursCases.add(6);
//        valeursCases.add(7);
//        valeursCases.add(8);