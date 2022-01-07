package gui.addviews;

import java.sql.Date;
import java.util.ArrayList;

import domain.course.Course;
import domain.student.Enrollment;
import domain.student.Student;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.CourseController;
import logic.EnrollmentController;

public class EnrollmentAddView {
    private EnrollmentController enrollmentController;
    private CourseController courseController;
    private Student selectedStudent;

    private Stage createStage = new Stage();

    public EnrollmentAddView(Student student, boolean addNew, int key) {
        enrollmentController = new EnrollmentController();
        courseController = new CourseController();
        selectedStudent = student;

        BorderPane body = new BorderPane();
        Label title = new Label();

        title.setText("Add enrollment");
        body.setCenter(addEnrollment(addNew, key));

        body.setTop(title);

        Scene scene = new Scene(body);
        createStage.setScene(scene);
        createStage.show();
    }

    // Makes the addStudent form
    public VBox addEnrollment(boolean addNew, int key) {

        ChoiceBox<String> courses = new ChoiceBox<String>();
        ArrayList<Course> courseArray = courseController.getAllCourses();

        for (Course course : courseArray) {
            courses.getItems().add(course.getCourseName());
        }

        DatePicker dateOfEnrollment = new DatePicker();
        dateOfEnrollment.setPromptText("Date of enrollment");

        Button submitEnrollment = new Button("Add enrollment");

        // add enrollment
        if (addNew) {
            submitEnrollment.setOnAction(event -> {
                Date sqlDate = Date.valueOf(dateOfEnrollment.getValue());
                enrollmentController.addEnrollment(selectedStudent.getEmail(),
                        courses.getSelectionModel().getSelectedItem().toString(), sqlDate);
                createStage.close();
            });

            // Update enrollment
        } else {
            Enrollment selectedEnrollment = enrollmentController.getEnrollment(key);

            dateOfEnrollment.setValue(selectedEnrollment.getDateOfEnrollment().toLocalDate());
            courses.setValue(selectedEnrollment.getCourseName());

            submitEnrollment.setText("Update enrollment");

            submitEnrollment.setOnAction(event -> {
                Date sqlDate = Date.valueOf(dateOfEnrollment.getValue());
                Enrollment updatedEnrollment = new Enrollment(selectedEnrollment.getEnrollment_id(),
                        selectedEnrollment.getEmail(), selectedEnrollment.getCertificate_id(),
                        courses.getSelectionModel().getSelectedItem().toString(), sqlDate);
                enrollmentController.updateEnrollment(updatedEnrollment, selectedEnrollment.getEnrollment_id());
                createStage.close();
            });
        }
        return new VBox(courses, dateOfEnrollment, submitEnrollment);
    }
}
