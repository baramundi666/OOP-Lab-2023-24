package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Simulation {

    private WorldMap map;
    private final List<MoveDirection> directions;

    private List<Vector2d> positions;
    private final List<Animal> animals;

    Simulation (List<MoveDirection> directions, List<Vector2d> positions, WorldMap map) {
        this.map = map;
        this.directions = directions;
        this.animals = new LinkedList<>();
        for (Vector2d position: positions) {
            var animal = new Animal(position);
            this.animals.add(animal);
            this.map.place(animal);
        }
    }

    List<MoveDirection> getDirections() {
        return new LinkedList<>(this.directions);
    }

    List<Vector2d> getPositions() {
        return new LinkedList<>(this.positions);
    }

    List<Animal> getAnimals() {
        return new LinkedList<>(this.animals);
    }

    public void run() {
        Iterator<MoveDirection> directions_iterator = this.directions.iterator();
        while (directions_iterator.hasNext()) {
            for (Animal animal : this.animals) {
                this.map.move(animal, directions_iterator.next());
                System.out.println(this.map.toString());
            }
        }
    }
}
