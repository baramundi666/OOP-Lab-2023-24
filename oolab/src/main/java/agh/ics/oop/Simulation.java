package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private final List<MoveDirection> directions;

    private List<Vector2d> positions;
    private final List<Animal> animals;

    Simulation (List<MoveDirection> directions, List<Vector2d> positions) {
        this.directions = directions;
        this.animals = new LinkedList<>();
        for (Vector2d position: positions) {
            this.animals.add(new Animal(position));
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
        int animals_size = this.animals.size();
        int i = 0;
        while (directions_iterator.hasNext()) {
            for (Animal animal : this.animals) {
                animal.move(directions_iterator.next());
                System.out.println("Zwierzę " + i%animals_size + " : " +
                        animal.getPosition().toString());
                i++;
            }
        }
    }
}
