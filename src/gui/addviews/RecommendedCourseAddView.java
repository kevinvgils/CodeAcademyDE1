package gui.addviews;

import java.sql.Date;
import java.util.ArrayList;

import domain.course.Course;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.CourseController;

public class RecommendedCourseAddView {
    private CourseController courseController;
    private Stage createStage = new Stage();

    public RecommendedCourseAddView(String key) {
        courseController = new CourseController();
        BorderPane body = new BorderPane();
        Label title = new Label();

        title.setText("Add course");
        body.setCenter(addRecommendedCourse(key));

        body.setTop(title);

        Scene scene = new Scene(body);
        createStage.setScene(scene);
        createStage.show();
    }

    //Makes the addCourse form
    public VBox addRecommendedCourse(String key){
        ChoiceBox courses = new ChoiceBox();
        ArrayList<Course> courseArray = courseController.getAllCourses();
        for (Course course : courseArray) {
            courses.getItems().add(course.getCourseName());
        }

        Button submitCourse = new Button("Add course");

        //add course
        submitCourse.setOnAction(event ->{
            if (courses != null) {
                courseController.addRecommended(key, courses.getSelectionModel().getSelectedItem().toString());
                createStage.close();
            }
        });
        
        return new VBox(courses, submitCourse);
    }
}
