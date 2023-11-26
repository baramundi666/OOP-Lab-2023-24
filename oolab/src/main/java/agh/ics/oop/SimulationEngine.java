package agh.ics.oop;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements Runnable {

    private final List<Simulation> simulationList;

    public SimulationEngine(List<Simulation> simulationList) {
        this.simulationList = simulationList;
    }

    @Override
    public void run() {
        System.out.println("Thread started.");
        try {
            runAsyncInThreadPool();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public void awaitSimulationEnd(ExecutorService executor) throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void runAsyncInThreadPool() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (Simulation simulation : simulationList) {
            executor.submit(simulation);
        }
        awaitSimulationEnd(executor);
    }
}
