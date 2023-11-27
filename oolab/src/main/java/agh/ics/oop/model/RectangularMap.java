package agh.ics.oop.model;


public class RectangularMap extends AbstractWorldMap {

    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        super();
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.precedes(upperRight) &&
                position.follows(lowerLeft);
    }

    @Override
    public String toString() {
        return visualization.draw(lowerLeft, upperRight);
    }
}
