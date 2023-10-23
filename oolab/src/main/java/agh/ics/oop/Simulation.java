package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    List<MoveDirection> directions;
    List<Vector2d> positions;
    List<Animal> animals;

    Simulation (List<MoveDirection> directions, List<Vector2d> positions) {
        this.directions = directions;
        this.positions = positions;
        this.animals = new ArrayList<>();
        for (Vector2d position: positions) {
            this.animals.add(new Animal(position));
        }
    }

    public void run() {
        int animals_size = this.animals.size();
        for (int i=0; i<this.directions.size(); i++) {
            int which_animal = i%animals_size;
            Animal animal = this.animals.get(which_animal);
            animal.move(directions.get(i));
            System.out.println("Zwierzę " + which_animal + " : " +
                    animal.getPosition().toString());
        }
    }



}
