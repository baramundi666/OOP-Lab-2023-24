package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Simulation implements Runnable{

    private final WorldMap map;
    private final List<MoveDirection> directions;

    private final List<Vector2d> positions;
    private final List<Animal> animals;

    public Simulation (List<MoveDirection> directions, List<Vector2d> positions, WorldMap map) {
        this.map = map;
        this.directions = directions;
        this.animals = new LinkedList<>();
        this.positions = positions;
    }

    private void placeAnimals() {
        int animals_length=0;
        for (Vector2d position : positions) {
            try {
                var animal = new Animal(position);
                animals.add(animal);
                animals_length++;
                map.place(animal);
                Thread.sleep(700);
            } catch (PositionAlreadyOccupiedException ignored) {
                System.out.println("Animal skipped!\n");
                animals.remove(animals_length-1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
        placeAnimals();
        Iterator<Animal> animals_iterator = animals.iterator();
        for (MoveDirection direction : directions) {
            if (!animals_iterator.hasNext()) animals_iterator = animals.iterator();
            map.move(animals_iterator.next(), direction);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
