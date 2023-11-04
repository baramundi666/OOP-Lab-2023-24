package agh.ics.oop.model;

public abstract class AbstractWorldMap {
    public boolean place(Animal animal) {
        var position = animal.getPosition();
        if (!this.canMoveTo(position)) return false;
        animals.put(position, animal);
        return true;
    }

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
}
