package gui.addviews;

import java.util.ArrayList;

import domain.course.Course;
import gui.ErrorView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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

    // Makes the addCourse form
    public VBox addRecommendedCourse(String key) {
        ChoiceBox courses = new ChoiceBox();
        ArrayList<Course> courseArray = courseController.getAllCourses();
        for (Course course : courseArray) {
            courses.getItems().add(course.getCourseName());
        }

        Button submitCourse = new Button("Add course");

        // add course
        submitCourse.setOnAction(event -> {
            if (courses.getSelectionModel().getSelectedItem() != null) {
                courseController.addRecommended(key, courses.getSelectionModel().getSelectedItem().toString());
                createStage.close();
            } else {
                new ErrorView("Make sure that the input is valid");
            }
        });

        return new VBox(courses, submitCourse);
    }
}
