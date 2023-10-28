package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    private static final Vector2d LOWER_CORNER = new Vector2d(0, 0);
    private static final Vector2d UPPER_CORNER = new Vector2d(4, 4);


    public Animal(Vector2d position) {
        this.orientation = MapDirection.NORTH;
        this.position = position;
    }

    public Animal() {
        this(new Vector2d(2,2));
    }

    @Override
    public String toString() {
        return switch(this.orientation) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
        };
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

    public void move(MoveDirection direction, MoveValidator validator) {
        var new_position = new Vector2d(this.position.getX(), this.position.getY());
        switch(direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> new_position = new_position.add(this.orientation.toUnitVector());
            case BACKWARD -> new_position = new_position.subtract(this.orientation.toUnitVector());
        }
        if (validator.canMoveTo(new_position)) {
            this.position=new_position;
        }
    }

}
