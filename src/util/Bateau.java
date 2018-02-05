package util;

import java.util.Objects;

public class Bateau {
    private final String name;
    private final int time;

    public Bateau(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bateau bateau = (Bateau) o;
        return time == bateau.time &&
                Objects.equals(name, bateau.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, time);
    }
}
