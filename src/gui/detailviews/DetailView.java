package gui.detailviews;

import domain.student.Student;
import gui.addviews.AddView;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.StudentController;

public class DetailView {

    private StudentController studentController = new StudentController();
    private Student selectedStudent;

    public DetailView(int type, String itemString) {
        Stage viewStage = new Stage();
        BorderPane body = new BorderPane();

        //header
        Label title = new Label();
        body.setTop(title);

        //main
        title.setText("Student");
        body.setCenter(viewStudent(itemString));
 
        //Footer
        Button deleteButton = new Button("Delete");
        Button editButton = new Button("Update");
        HBox buttons = new HBox(deleteButton, editButton);
        body.setBottom(buttons);

        //events
        deleteButton.setOnAction(event -> {
            deleteItem();
            viewStage.close();
        });
        editButton.setOnAction(event -> {
            updateItem();
            viewStage.close();
        });

        Scene scene = new Scene(body);
        viewStage.setScene(scene);
        viewStage.show();
    }

    public void deleteItem(){
        if(selectedStudent != null){
            studentController.deleteStudent(selectedStudent.getEmail());
        }
    }

    public void updateItem(){
        if(selectedStudent != null){
            new AddView(0, false, selectedStudent.getEmail());
        }
    }

    // Makes the addStudent form
    public VBox viewStudent(String itemString) {
        try {
            String[] parts = itemString.split(" - ");
            selectedStudent = studentController.getStudent(parts[1]);

            Label name = new Label("Name: "+ selectedStudent.getName());
            Label email = new Label("Email: "+ selectedStudent.getEmail());
            Label dateOfBirth = new Label("Date of birth: "+
            selectedStudent.getDateOfBirth());

            Label gender = new Label();
            switch(selectedStudent.getGender()) {
                case 0: 
                    gender.setText("Gender: Male");
                    break;
                case 1: 
                    gender.setText("Gender: Female");
                    break;
                case 2: 
                    gender.setText("Gender: Other");
                    break;

            }
            Label adress = new Label("Adress: "+ selectedStudent.getZipCode()+" "+selectedStudent.getStreet()+" "+selectedStudent.getHouseNumber());
            Label location = new Label("Location: "+ selectedStudent.getLocation());
            Label country = new Label("Country: "+ selectedStudent.getCountry());

            return new VBox(name, email, dateOfBirth, gender, adress, location, country);

        } catch (Exception e) {
            return new VBox();
        }

    }
}
