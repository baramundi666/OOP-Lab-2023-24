package agh.ics.oop.model;


import java.util.Map;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap<T, P> extends AnyMap<T, P> {

    Map<P,WorldElement> getElements();
}


