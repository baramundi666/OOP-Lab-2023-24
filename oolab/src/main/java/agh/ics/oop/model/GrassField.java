package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements WorldMap<Animal, Vector2d> {

    private final Map<Vector2d, Animal> animals = new HashMap<>();

    private final Map<Vector2d, Grass> grass = new HashMap<>();

    private final int grass_amount;

    //private final Vector2d LOWER_CORNER;
    //private final Vector2d UPPER_CORNER;

    private final MapVisualizer visualization;

    public GrassField(int grass_amount) {
        this.grass_amount = grass_amount;
        visualization = new MapVisualizer(this);

        int n = (int) Math.sqrt(grass_amount*10);
        //LOWER_CORNER = new Vector2d(0, 0);
        //UPPER_CORNER = new Vector2d(n, n);
        int itr = 0;
        while (itr<grass_amount) {
            Random rand = new Random();
            int x = rand.nextInt(n);
            int y = rand.nextInt(n);
            var vector = new Vector2d(x, y);
            if (!grass.containsKey(vector)) {
                grass.put(vector, new Grass(vector));
                itr++;
            }
        }
    }

    public Map<Vector2d, Animal> getAnimals() {
        return new HashMap<>(animals);
    }

    public Map<Vector2d, Grass> getGrass() {
        return new HashMap<>(grass);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public boolean place(Animal animal) {
        var position = animal.getPosition();
        if (!this.canMoveTo(position)) return false;
        animals.put(position, animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.containsValue(animal)) {
            Vector2d original_position = animal.getPosition();
            MoveValidator<Animal, Vector2d> validator = this;
            animal.move(direction, validator);
            Vector2d new_position = animal.getPosition();
            if (!original_position.equals(new_position) && !animals.containsKey(new_position)) {
                animals.remove(original_position);
                animals.put(new_position, animal);
            }
        }

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position) || grass.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (!animals.containsKey(position)) {
            if (!grass.containsKey(position)) return null;
            return grass.get(position);
        }
        return animals.get(position);
    }

    @Override
    public String toString() {
        int lowerX = 0;
        int lowerY = 0;
        int upperX = 0;
        int upperY = 0;

        for (Vector2d position : grass.keySet()) {
            if (position.getX()<=lowerX) lowerX = position.getX();
            if (position.getY()<=lowerY) lowerY = position.getY();
            if (position.getX()>=upperX) upperX = position.getX();
            if (position.getY()>=upperY) upperY = position.getY();
        }

        for (Vector2d position : animals.keySet()) {
            if (position.getX()<=lowerX) lowerX = position.getX();
            if (position.getY()<=lowerY) lowerY = position.getY();
            if (position.getX()>=upperX) upperX = position.getX();
            if (position.getY()>=upperY) upperY = position.getY();
        }

        var lower_left = new Vector2d(lowerX, lowerY);
        var upper_right = new Vector2d(upperX, upperY);
        System.out.println(lower_left);
        System.out.println(upper_right);
        return visualization.draw(lower_left, upper_right);
    }


    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof GrassField))
            return false;
        GrassField that = (GrassField) other;
        for (Grass grass : this.grass.values()) {
            if (!that.grass.containsKey(grass.getPosition())) return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(grass);
    }
}
