package agh.ics.oop;

import java.util.List;

public class SimulationEngine implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread started.");
    }

    private final List<Simulation> simulationList;

    public SimulationEngine(List<Simulation> simulationList) {
        this.simulationList = simulationList;
    }

    public void runSync() {
        for (Simulation simulation : simulationList) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : simulationList) {
            Thread engineThread = new Thread(simulation);
            engineThread.start();
        }
    }

//    public void awaitSimulationEnd(Thread thread) {
//        thread.stop();
//    }
}
