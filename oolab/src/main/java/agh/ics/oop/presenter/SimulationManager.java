package agh.ics.oop.presenter;

import agh.ics.oop.SimulationApp;

public class SimulationManager {

    SimulationApp app;

    public void setApp(SimulationApp app) {
        this.app=app;
    }

    public void onStart() throws Exception {
        app.startSimulation();
    }
}
