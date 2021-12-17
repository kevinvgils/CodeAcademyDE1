package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddView {
    
    public void createItem(int type){
        Stage createStage = new Stage();
        BorderPane body = new BorderPane();
        Label title = new Label();

        if(type == 0){
            title.setText("Add student");
            body.setCenter(addStudent());
        } else if(type == 1){
            //WIP
        } else if(type == 2){
            //WIP   
        }

        body.setTop(title);

        Scene scene = new Scene(body);
        createStage.setScene(scene);
        createStage.show();
    }

    //Makes the addStudent form
    public VBox addStudent(){
        TextField name = new TextField();
        DatePicker dateOfBirth = new DatePicker();
        //Genders
        TextField zipCode = new TextField();
        TextField houseNumber = new TextField();
        TextField street = new TextField();
        TextField country = new TextField();
        Button submitStudent = new Button("Add student");

        return new VBox(name, dateOfBirth, zipCode, houseNumber, street, country, submitStudent);
    }
}
