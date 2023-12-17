package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.LinkedList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            var observer = new ConsoleMapDisplay();
            List<Simulation> simulationList= new LinkedList<>();
            for (int i=0; i<100; i++) {
                List<MoveDirection> directions = OptionsParser.parse(args);
                List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4),
                        new Vector2d(2, 2));
                var map1 = new GrassField(10);
                var map2 = new RectangularMap(10, 10);
                map1.registerObserver(observer);
                map2.registerObserver(observer);
                var simulation1 = new Simulation(directions, positions, map1);
                var simulation2 = new Simulation(directions, positions, map2);
                simulationList.add(simulation1);
                simulationList.add(simulation2);
            }
            SimulationEngine engine = new SimulationEngine(simulationList);
            Thread engineThread = new Thread(engine);
            engineThread.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("System zakończył działanie!");
    }

    public static void run(MoveDirection[] directions) {
        for(MoveDirection step : directions) {
            switch(step) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
            }
        }
    }
}
