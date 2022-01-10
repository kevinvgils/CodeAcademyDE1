package gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorView {
    public ErrorView(String message) {
        Stage errorStage = new Stage();
        errorStage.setTitle("Something went wrong");
        Scene scene = new Scene(new Label(message));
        errorStage.setScene(scene);
        errorStage.show();
    }
}
