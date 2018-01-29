package util;

public class Case {
    private int posX;
    private int posY;
    private int value;

    public Case(){
        // Empty constructor
    }

    public Case(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public Case(int posX, int posY, int value){
        this.posX = posX;
        this.posY = posY;
        this.value = value;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int valeur) {
        this.value = valeur;
    }

    public boolean equals(Case cas){
        if (this.posX == cas.getPosX() && this.posY == cas.getPosY() && this.getValue() == cas.getValue()){
            return true;
        }
        return false;
    }

    public void displayCase(){
        System.out.println("Case :");
        System.out.println("position x : " + this.getPosX());
        System.out.println("position y : " + this.getPosY());
    }
}
