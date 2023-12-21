package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {

    @FXML
    public GridPane mapGrid;

    @FXML
    private TextField arguments;

    @FXML
    private Label infoLabel;

    private WorldMap map;


    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void drawMap(WorldMap map) {
        clearGrid();
        var boundary = map.getCurrentBounds();
        int lowerX = boundary.lowerLeft().getX();
        int upperX = boundary.upperRight().getX();
        int lowerY = boundary.lowerLeft().getY();
        int upperY = boundary.upperRight().getY();
        int rows = upperY-lowerY+1;
        int columns = upperX-lowerX+1;
        double width = (double) 300/columns;
        double height = (double) 300/rows;
        var elements = map.getElements();

        mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
        mapGrid.getRowConstraints().add(new RowConstraints(height));
        Label axis = new Label("y\\x");
        mapGrid.add(axis,0,0);
        GridPane.setHalignment(axis, HPos.CENTER);

        for (int i=0;i<rows;i++){
            mapGrid.getRowConstraints().add(new RowConstraints(height));
            Label label = new Label(String.valueOf(i+lowerY));
            mapGrid.add(label,0,i+1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i=0;i<columns;i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
            var label = new Label(String.valueOf(i+lowerX));
            mapGrid.add(label,i+1,0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for(Vector2d position: elements.keySet()){
            var element = map.objectAt(position);
            if(element.isPresent()) {
                var label = new Label(element.get().toString());
                mapGrid.add(label, position.getX() - lowerX + 1, position.getY() - lowerY + 1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
            infoLabel.setText(message);
        });
    }

    @FXML
    private void onSimulationStartClicked() {
        List<Simulation> simulationList = new LinkedList<>();
        List<MoveDirection> directions = OptionsParser.parse(arguments.getText().split(" "));
        System.out.println(directions);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4),
                new Vector2d(2, 2));
        var map = new GrassField(10);
        setWorldMap(map);
        map.registerObserver(this);
        map.registerObserver((worldMap, message) -> {
            var dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
            var now = LocalDateTime.now();
            System.out.println(dtf.format(now) + message);
        });
        var simulation1 = new Simulation(directions, positions, map);
        simulationList.add(simulation1);
        SimulationEngine engine = new SimulationEngine(simulationList);
        Thread engineThread = new Thread(engine);
        engineThread.start();
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
}
