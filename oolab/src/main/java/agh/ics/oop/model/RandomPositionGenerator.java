package agh.ics.oop.model;

import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d> {

    private final int maxWidth;
    private final int maxHeight;
    private final int grassCount;

    private final List<Vector2d> possible_values;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        this.grassCount = grassCount;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.possible_values = new LinkedList<>();

        for (int i=0; i<=maxHeight; i++) {
            for (int j=0; j<=maxWidth; j++) {
                possible_values.add(new Vector2d(i, j));
            }
        }
    }

    @Override
    public Iterator<Vector2d> iterator() {
        Collections.shuffle(possible_values, new Random());
        return possible_values.subList(0,grassCount).iterator();
    }
}
