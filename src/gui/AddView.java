package gui;

import domain.student.Student;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.StudentController;

public class AddView {
    
    private StudentController studentController;
    private Stage createStage = new Stage();

    public AddView(int type, boolean addNew, String key) {
        studentController = new StudentController();
        BorderPane body = new BorderPane();
        Label title = new Label();

        if(type == 0){
            title.setText("Add student");
            body.setCenter(addStudent(addNew, key));
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
    public VBox addStudent(boolean addNew, String key){
        TextField email = new TextField();
        email.setPromptText("Email address");
        TextField name = new TextField();
        name.setPromptText("Full name");
        DatePicker dateOfBirth = new DatePicker();
        dateOfBirth.setPromptText("Date of birth");
        //TODO: Gender field
        TextField zipCode = new TextField();
        zipCode.setPromptText("Zipcode");
        TextField houseNumber = new TextField();
        houseNumber.setPromptText("house number");
        TextField street = new TextField();
        street.setPromptText("street of residence");
        TextField country = new TextField();
        country.setPromptText("country of residence");

        Button submitStudent = new Button("Add student");

        //add student
        if(addNew){
            submitStudent.setOnAction(event ->{
                java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis()); //TODO: Date needs to be get from textfield
                studentController.addStudent(email.getText(), name.getText(), sqlDate, 0, zipCode.getText(), Integer.parseInt(houseNumber.getText()), street.getText(), country.getText());
                createStage.close();
            });

        //Update student
        } else{
            Student selectedStudent = studentController.getStudent(key);
            email.setText(selectedStudent.getEmail());
            name.setText(selectedStudent.getName());
            //Genders
            zipCode.setText(selectedStudent.getZipCode());
            houseNumber.setText(selectedStudent.getHouseNumber().toString());
            street.setText(selectedStudent.getStreet());
            country.setText(selectedStudent.getCountry());
            submitStudent.setText("Update student");;
            submitStudent.setOnAction(event ->{ 
                java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis()); //TODO: Date needs to be get from textfield
                Student updatedStudent = new Student(email.getText(), name.getText(), sqlDate, 0, zipCode.getText(), Integer.parseInt(houseNumber.getText()), street.getText(), country.getText());
                studentController.updateStudent(updatedStudent, selectedStudent.getEmail());
                createStage.close();
            });
        }
        return new VBox(email, name, dateOfBirth, zipCode, houseNumber, street, country, submitStudent);
    }
}
