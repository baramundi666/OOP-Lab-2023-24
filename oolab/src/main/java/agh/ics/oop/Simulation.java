package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private final List<MoveDirection> directions;
    
    private final List<Animal> animals;

    Simulation (List<MoveDirection> directions, List<Vector2d> positions) {
        this.directions = directions;
        this.animals = new LinkedList<>();
        for (Vector2d position: positions) {
            animals.add(new Animal(position));
        }
    }

    List<MoveDirection> getDirections() {
        return new LinkedList<>(directions);
    }

    List<Animal> getAnimals() {
        return new LinkedList<>(animals);
    }

    public void run() {
        int animals_size = animals.size();
        int i = 0;
        Iterator<Animal> animals_iterator = animals.iterator();
        for (MoveDirection direction : directions) {
            if (!animals_iterator.hasNext()) animals_iterator = animals.iterator();
            Animal animal = animals_iterator.next();
            animal.move(direction);
            System.out.println("Zwierzę " + i%animals_size + " : " +
                    animal.getPosition().toString());
            i++;
        }
    }
}
