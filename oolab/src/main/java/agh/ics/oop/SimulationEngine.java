package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class SimulationEngine implements Runnable {

    private final List<Simulation> simulationList;

    private final List<Thread> threads = new LinkedList<>();

    private ExecutorService executor = newFixedThreadPool(4);

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

    public void runAsync() throws InterruptedException {
        for (Simulation simulation : simulationList) {
            Thread engineThread = new Thread(simulation);
            threads.add(engineThread);
            engineThread.start();
        }
        awaitSimulationEnd();
    }

    public void awaitSimulationEnd() throws InterruptedException {
        if (!threads.isEmpty()) {
            for (Thread thread : threads) {
                thread.join();
            }
            threads.clear();
        }
        if (!executor.isShutdown()) {
            executor.shutdown();
            if(!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        }
    }

    public void runAsyncInThreadPool() throws InterruptedException {
        executor = newFixedThreadPool(4);
        for (Simulation simulation : simulationList) {
            executor.submit(simulation);
        }
        awaitSimulationEnd();
    }
}
