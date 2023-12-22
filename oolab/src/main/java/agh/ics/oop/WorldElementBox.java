package agh.ics.oop;

import agh.ics.oop.model.WorldElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class WorldElementBox {

    private VBox vBox;
    public WorldElementBox(WorldElement worldElement) {
        addImage(worldElement);
    }

    public VBox getVBox() {
        return vBox;
    }

    private void addImage(WorldElement worldElement) {
        var image = new Image(worldElement.getImage());
        var imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        var positionLabel = new Label(worldElement.getPosition().toString());
        var box = new VBox();
        box.getChildren().addAll(imageView, positionLabel);
        box.setAlignment(Pos.CENTER);
        vBox = box;
    }
}
