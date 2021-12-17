package gui;

import domain.student.Student;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailView {

    public DetailView(int type, String itemString){
        Stage viewStage = new Stage();
        BorderPane body = new BorderPane();
        Label title = new Label();

        if(type == 0){
            title.setText("Student");
            body.setCenter(viewStudent(itemString));
        } else if(type == 1){
            //WIP
        } else if(type == 2){
            //WIP   
        }

        body.setTop(title);

        Scene scene = new Scene(body);
        viewStage.setScene(scene);
        viewStage.show();
    }

    //Makes the addStudent form
    public VBox viewStudent(String itemString){
        try {
            String[] parts = itemString.split(" - ");
            Student selectedStudent = new Student();//TODO: get student with parts[1] (email)

            //TODO: getters and setters
            // Label name = new Label("Name: "+ selectedStudent.getName());
            // Label email = new Label("Email: "+ selectedStudent.getEmail());
            // Label dateOfBirth = new Label("Date of birth: "+ selectedStudent.getDateOfBirth());
            // Label gender = new Label("Gender: "+selectedStudent.getGender());
            // Label adress = new Label("Adress: "+ selectedStudent.getZipCode()+" "+selectedStudent.getStreet()+" "+selectedStudent.getHouseNumber());
            // Label country = new Label("Country: "+ selectedStudent.getCountry());

            // return new VBox(name, email, dateOfBirth, gender, adress, country);
            return new VBox(); //remove later
            
        } catch (Exception e) {
            return new VBox();
        }
        
    }
}
