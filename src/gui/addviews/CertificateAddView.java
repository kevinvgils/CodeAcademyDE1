package gui.addviews;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.CertificateController;

public class CertificateAddView {
    private CertificateController certificateController = new CertificateController();
    private Stage createStage = new Stage();
    private Integer enrollmentId;

    public CertificateAddView(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
        BorderPane body = new BorderPane();
        Label title = new Label();

        title.setText("Give certificate");
        body.setCenter(addCertificate(enrollmentId));

        body.setTop(title);

        Scene scene = new Scene(body);
        createStage.setScene(scene);
        createStage.show();
    }

    public VBox addCertificate(int enrollmentId) {
        TextField staffName = new TextField();
        staffName.setPromptText("Staff name");
        ChoiceBox grade = new ChoiceBox<>();
        int i = 0;
        while (i <= 10) {
            grade.getItems().add(i);
            i++;
        }

        Button submitCertificate = new Button("Give certificate");

        submitCertificate.setOnAction(event -> {
            certificateController.addCertificate(enrollmentId,
                    Integer.valueOf(grade.getSelectionModel().getSelectedItem().toString()),
                    staffName.getText());
            createStage.close();
        });
        return new VBox(staffName, grade, submitCertificate);
    }
}
