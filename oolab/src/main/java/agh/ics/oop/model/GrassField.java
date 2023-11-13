package agh.ics.oop.model;

public class GrassField extends AbstractWorldMap {

    private Vector2d lowerLeft;
    private Vector2d upperRight;

    public GrassField(int grassCount) {
        super();
        int maxWidth = (int) Math.sqrt(grassCount * 10);
        int maxHeight = (int) Math.sqrt(grassCount * 10);
        this.lowerLeft = new Vector2d(maxWidth, maxHeight);
        this.upperRight = new Vector2d(0, 0);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth, maxHeight, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
            lowerLeft=lowerLeft.lowerLeft(grassPosition);
            upperRight=upperRight.upperRight(grassPosition);
        }
    }

    @Override
    public String toString() {
        var newLowerLeft = new Vector2d(lowerLeft.getX(), lowerLeft.getY());
        var newUpperRight = new Vector2d(upperRight.getX(), upperRight.getY());
        for (Vector2d position : getAnimals().keySet()) {
            newLowerLeft=newLowerLeft.lowerLeft(position);
            newUpperRight=newUpperRight.upperRight(position);
        }
        return visualization.draw(newLowerLeft, newUpperRight);
    }
}
