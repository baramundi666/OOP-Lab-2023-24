package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap<WorldElement, Vector2d>{

    protected final Map<Vector2d, Animal> animals = new HashMap<>();

    protected final Map<Vector2d, Grass> grass = new HashMap<>();

    protected final MapVisualizer visualization;


    protected AbstractWorldMap() {
        visualization = new MapVisualizer(this);
    }

    public Map<Vector2d, WorldElement> getElements() {
        Map<Vector2d,WorldElement> list = new HashMap<>();
        list.putAll(grass);
        list.putAll(animals);
        return list;
    }

    public Map<Vector2d, Animal> getAnimals() {
        return new HashMap<>(animals);
    }

    public Map<Vector2d, Grass> getGrass() {
        return new HashMap<>(grass);
    }

    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return getElements().containsKey(position);
    }

    public void place(WorldElement object) throws PositionAlreadyOccupiedException {
        var animal = (Animal) object;
        var position = animal.getPosition();
        if (!canMoveTo(position)) throw new PositionAlreadyOccupiedException(position);
        animals.put(position, animal);
    }

    public void move(WorldElement object, MoveDirection direction) {
        var animal = (Animal) object;
        if (animals.containsValue(animal)) {
            Vector2d original_position = animal.getPosition();
            animal.move(direction, this);
            Vector2d new_position = animal.getPosition();
            if (!original_position.equals(new_position)) {
                animals.remove(original_position);
                animals.put(new_position, animal);
            }
        }

    }

    public WorldElement objectAt(Vector2d position) {
        if (!animals.containsKey(position) && !grass.containsKey(position)) return null;
        if (animals.containsKey(position)) return animals.get(position);
        return grass.get(position);
    }
}
