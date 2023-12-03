package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {

    @FXML
    private TextField textField;

    @FXML
    private Label infoLabel;

    @FXML
    private Button startButton;
    private WorldMap map;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void drawMap() {
        infoLabel.setText(map.toString());
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        drawMap();
    }

    @FXML
    private void onSimulationStartClicked(ActionEvent event) {
        List<Simulation> simulationList = new LinkedList<>();
        List<MoveDirection> directions = OptionsParser.parse(textField.getText().split(" "));
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4),
                new Vector2d(2, 2));
        var map1 = new GrassField(10);
        setWorldMap(map1);
        map1.registerObserver(this);
        var simulation1 = new Simulation(directions, positions, map1);
        simulationList.add(simulation1);
        SimulationEngine engine = new SimulationEngine(simulationList);
        Thread engineThread = new Thread(engine);
        engineThread.start();
    }
}
