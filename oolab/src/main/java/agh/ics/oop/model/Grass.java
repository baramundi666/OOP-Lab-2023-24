package agh.ics.oop.model;

import java.util.Objects;

public class Grass implements WorldElement{

    private final Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    @Override
    public String getImage() {
        return "grass.png";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Grass))
            return false;
        Grass that = (Grass) other;
        return this.position == that.getPosition();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(position);
    }
}
