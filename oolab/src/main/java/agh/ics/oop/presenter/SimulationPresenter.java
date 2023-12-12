package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {

    private static final int CELL_WIDTH=50;
    private static final int CELL_HEIGHT=50;

    @FXML
    public GridPane mapGrid;

    @FXML
    private TextField textField;

    @FXML
    private Label Label;

    @FXML
    private Label infoLabel;

    @FXML
    private Button startButton;
    private WorldMap map;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void drawMap() {
        clearGrid();
        var boundary = map.getCurrentBounds();
        int rows = boundary.upperRight().getX()-boundary.lowerLeft().getX();
        int columns = boundary.upperRight().getY()-boundary.lowerLeft().getY();
        var elements = map.getElements();

        for (int i=0; i<rows; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            for (int j=0; j<columns; j++) {
                mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            }
        }
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                var current = new Vector2d(i, j);
                var label = new Label(elements.get(boundary.lowerLeft().add(current)).toString());
                mapGrid.add(label, j, i);
            }
        }


        GridPane.setHalignment(mapGrid, HPos.CENTER);
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            // ewentualny inny kod zmieniający kontrolki
        });
    }

    @FXML
    private void onSimulationStartClicked(ActionEvent event) {
        List<Simulation> simulationList = new LinkedList<>();
        List<MoveDirection> directions = OptionsParser.parse(textField.getText().split(" "));
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4),
                new Vector2d(2, 2));
        var map1 = new RectangularMap(5,5);
        setWorldMap(map1);
        map1.registerObserver(this);
        var simulation1 = new Simulation(directions, positions, map1);
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
