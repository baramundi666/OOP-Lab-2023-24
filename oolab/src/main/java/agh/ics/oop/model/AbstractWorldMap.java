package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{

    protected final Map<Vector2d, Animal> animals = new HashMap<>();

    protected final MapVisualizer visualizer;

    protected final Set<MapChangeListener> observers = new HashSet<>();

    protected final UUID mapId;


    protected AbstractWorldMap() {
        this.visualizer = new MapVisualizer(this);
        this.mapId = UUID.randomUUID();
    }

    @Override
    public List<Animal> getOrderedAnimals() {
        var animalList = new LinkedList<Animal>();
        for (Vector2d position : animals.keySet()) {
            animalList.add(animals.get(position));
        }
        Comparator<Animal> animalComparator = Comparator.comparing((animal) -> (double) animal.getPosition().getX()+(double) animal.getPosition().getY()/10);
        Collections.sort(animalList, animalComparator);
        return animalList;
    }

    @Override
    public UUID getId() {
        return mapId;
    }

    public void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }

    public void registerObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void deregisterObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    @Override
    public abstract Boundary getCurrentBounds();

    public Map<Vector2d, Animal> getAnimals() {
        return new HashMap<>(animals);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return getElements().containsKey(position);
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        var position = animal.getPosition();
        if (!canMoveTo(position)) throw new PositionAlreadyOccupiedException(position);
        animals.put(position, animal);
        mapChanged("Placed " + animal + " at " + position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.containsValue(animal)) {
            Vector2d originalPosition = animal.getPosition();
            MapDirection originalOrientation = animal.getOrientation();
            animal.move(direction, this);
            Vector2d newPosition = animal.getPosition();
            MapDirection newOrientation = animal.getOrientation();
            if (!originalPosition.equals(newPosition)) {
                animals.remove(originalPosition);
                animals.put(newPosition, animal);
                mapChanged(animal + " moved from " + originalPosition + " to " + newPosition);
            }
            else if (!originalOrientation.equals(newOrientation)) {
                mapChanged(animal + " changed its orientation from " + originalOrientation + " to " +
                           newOrientation);
            }
        }
    }

    @Override
    public String toString() {
        return visualizer.draw(getCurrentBounds().lowerLeft(),getCurrentBounds().upperRight());
    }
}
