package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Simulation {

    private final WorldMap<Animal, Vector2d> map;
    private final List<MoveDirection> directions;

    private final List<Vector2d> positions;
    private final List<Animal> animals;

    Simulation (List<MoveDirection> directions, List<Vector2d> positions, WorldMap<Animal, Vector2d> map) {
        this.map = map;
        this.directions = directions;
        this.positions = positions;
        this.animals = new LinkedList<>();
        for (Vector2d position: positions) {
            var animal = new Animal(position);
            animals.add(animal);
            this.map.place(animal);
        }
    }

    List<MoveDirection> getDirections() {
        return new LinkedList<>(directions);
    }

    List<Vector2d> getPositions() {
        return new LinkedList<>(positions);
    }

    List<Animal> getAnimals() {
        return new LinkedList<>(animals);
    }

    public void run() {
        Iterator<Animal> animals_iterator = animals.iterator();
        for (MoveDirection direction : directions) {
            if (!animals_iterator.hasNext()) animals_iterator = animals.iterator();
            map.move(animals_iterator.next(), direction);
            System.out.println(map.toString());
        }
    }
}
