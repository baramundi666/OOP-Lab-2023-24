package agh.ics.oop;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationEngine implements Runnable {

    private final List<Simulation> simulationList;

    public SimulationEngine(List<Simulation> simulationList) {
        this.simulationList = simulationList;
    }

    @Override
    public void run() {
        System.out.println("Thread started.");
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

    public void awaitSimulationEnd(Thread thread) {
        try {
            thread.wait();
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runAsyncInThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (Simulation simulation : simulationList) {
            executor.submit(simulation);
        }
        executor.shutdown();
    }
}
