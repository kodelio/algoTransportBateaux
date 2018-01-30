package util;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Bateau {
    private String name;
    private int time;

    public Bateau() {
        // Empty constructor
    }

    public Bateau(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void copy(Bateau bat) {
        this.name = bat.getName();
        this.time = bat.getTime();
    }

    public Boolean equals(Bateau bat) {
        if (this.getTime() == bat.getTime() && this.getName().equals(bat.getName())) return true;
        return false;
    }
}
