package agh.ics.oop.model;


/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface AnyMap<T, P> extends MoveValidator<P> {

    boolean place(T object);

    void move(T object, MoveDirection direction);

    boolean isOccupied(P position);

    T objectAt(P position);
}


