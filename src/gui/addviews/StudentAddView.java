package gui.addviews;

import java.sql.Date;

import domain.student.Student;
import gui.ErrorView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.StudentController;

public class StudentAddView {
    
    private StudentController studentController;
    private Stage createStage = new Stage();

    //creates the view
    public StudentAddView(int type, boolean addNew, String key) {
        studentController = new StudentController();
        BorderPane body = new BorderPane();
        Label title = new Label();

        title.setText("Add student");
        body.setCenter(addStudent(addNew, key));

        body.setTop(title);

        Scene scene = new Scene(body);
        createStage.setScene(scene);
        createStage.show();
    }

    //returns the student add form
    public VBox addStudent(boolean addNew, String key){
        TextField email = new TextField();
        email.setPromptText("Email address");
        TextField name = new TextField();
        name.setPromptText("Full name");
        DatePicker dateOfBirth = new DatePicker();
        dateOfBirth.setPromptText("Date of birth");

        ChoiceBox gender = new ChoiceBox();
        gender.getItems().addAll("Male", "Female", "Other");

        TextField zipCode = new TextField();
        zipCode.setPromptText("Zipcode");
        TextField houseNumber = new TextField();
        houseNumber.setPromptText("house number");
        TextField street = new TextField();
        street.setPromptText("street of residence");
        TextField location = new TextField();
        location.setPromptText("location of residence");
        TextField country = new TextField();
        country.setPromptText("country of residence");

        Button submitStudent = new Button("Add student");

        //add student
        if(addNew){
            submitStudent.setOnAction(event ->{
                Date sqlDate = Date.valueOf(dateOfBirth.getValue());
                Student student = new Student(email.getText(), name.getText(), sqlDate, gender.getSelectionModel().getSelectedIndex(), zipCode.getText(), Integer.parseInt(houseNumber.getText()), street.getText(), country.getText(), location.getText());
                if(student.vallidate()){
                    studentController.addStudent(student);
                    createStage.close();
                } else{
                    new ErrorView("Make sure that the input is valid");
                }
            });

        //Update student
        } else{
            Student selectedStudent = studentController.getStudent(key);
            email.setText(selectedStudent.getEmail());
            name.setText(selectedStudent.getName());
            dateOfBirth.setValue(selectedStudent.getDateOfBirth().toLocalDate());
            gender.setValue(selectedStudent.getGender());
            zipCode.setText(selectedStudent.getZipCode());
            houseNumber.setText(selectedStudent.getHouseNumber().toString());
            street.setText(selectedStudent.getStreet());
            location.setText(selectedStudent.getLocation());
            country.setText(selectedStudent.getCountry());
            submitStudent.setText("Update student");
            
            submitStudent.setOnAction(event ->{ 
                Date sqlDate = Date.valueOf(dateOfBirth.getValue());
                Student updatedStudent = new Student(email.getText(), name.getText(), sqlDate, gender.getSelectionModel().getSelectedIndex(), zipCode.getText(), Integer.parseInt(houseNumber.getText()), street.getText(), country.getText(), location.getText());
                if(updatedStudent.vallidate()){
                    studentController.updateStudent(updatedStudent, selectedStudent.getEmail());
                    createStage.close();
                } else{
                    new ErrorView("Make sure that the input is valid");
                }
            });
        }
        return new VBox(email, name, dateOfBirth, gender, zipCode, houseNumber, street, location, country, submitStudent);
    }
}
