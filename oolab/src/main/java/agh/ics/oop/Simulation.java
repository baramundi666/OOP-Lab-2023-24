package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Simulation implements Runnable{

    private final WorldMap map;
    private final List<MoveDirection> directions;
    private final List<Animal> animals;

    public Simulation (List<MoveDirection> directions, List<Vector2d> positions, WorldMap map) {
        this.map = map;
        this.directions = directions;
        this.animals = new LinkedList<>();
        for (Vector2d position : positions) {
            try {
                var animal = new Animal(position);
                animals.add(animal);
                this.map.place(animal);
            } catch (PositionAlreadyOccupiedException ignored) {
                System.out.println("Animal skipped!\n");
            }
        }
    }

    List<MoveDirection> getDirections() {
        return new LinkedList<>(directions);
    }

    List<Animal> getAnimals() {
        return new LinkedList<>(animals);
    }

    public void run() {
        Iterator<Animal> animals_iterator = animals.iterator();
        for (MoveDirection direction : directions) {
            if (!animals_iterator.hasNext()) animals_iterator = animals.iterator();
            map.move(animals_iterator.next(), direction);
        }
    }
}
