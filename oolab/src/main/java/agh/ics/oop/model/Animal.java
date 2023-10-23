package agh.ics.oop.model;

import java.util.Map;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal(Vector2d position) {
        this.orientation = MapDirection.NORTH;
        this.position = position;
    }

    public Animal() {
        this(new Vector2d(2,2));
    }

    public String toString() {
        return "position: " + position.toString() +
                ", orientation: " + orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public void move(MoveDirection direction) {
        var new_position = new Vector2d(this.position.getx(), this.position.gety());
        var corner1 = new Vector2d(0, 0);
        var corner2 = new Vector2d(4, 4);
        switch(direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> new_position = new_position.add(this.orientation.toUnitVector());
            case BACKWARD -> new_position = new_position.subtract(this.orientation.toUnitVector());
        }
        if (new_position.follows(corner1) && new_position.precedes(corner2)) {
            this.position=new_position;
        }
    }

}
