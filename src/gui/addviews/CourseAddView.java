package gui.addviews;

import domain.course.Course;
import gui.ErrorView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.CourseController;

public class CourseAddView {

    private CourseController courseController;
    private Stage createStage = new Stage();

    public CourseAddView(int type, boolean addNew, String key) {
        courseController = new CourseController();
        BorderPane body = new BorderPane();
        Label title = new Label();

        title.setText("Add course");
        body.setCenter(addCourse(addNew, key));

        body.setTop(title);

        Scene scene = new Scene(body);
        createStage.setScene(scene);
        createStage.show();
    }

    // Makes the addCourse form
    public VBox addCourse(boolean addNew, String key) {
        TextField courseName = new TextField();
        courseName.setPromptText("Course name");
        TextField level = new TextField();
        level.setPromptText("Level");
        TextField subject = new TextField();
        subject.setPromptText("Subject");
        TextField introductionText = new TextField();
        introductionText.setPromptText("Introduction text");

        Button submitCourse = new Button("Add course");

        // add course
        if (addNew) {
            submitCourse.setOnAction(event -> {
                Course newCourse = new Course(courseName.getText(), level.getText(), subject.getText(),
                        introductionText.getText());

                if (newCourse.vallidate()) {
                    courseController.addCourse(courseName.getText(), level.getText(), subject.getText(),
                            introductionText.getText());
                    createStage.close();
                } else {
                    new ErrorView("Make sure that the input is valid");
                }
            });

            // Update course
        } else {
            Course selectedCourse = courseController.getCourse(key);
            courseName.setText(selectedCourse.getCourseName());
            level.setText(selectedCourse.getLevel());
            subject.setText(selectedCourse.getSubject());
            introductionText.setText(selectedCourse.getIntroductionText());
            submitCourse.setText("Update course");

            submitCourse.setOnAction(event -> {
                Course updatedCourse = new Course(courseName.getText(), level.getText(), subject.getText(),
                        introductionText.getText());
                if (updatedCourse.vallidate()) {
                    courseController.updateCourse(updatedCourse, selectedCourse.getCourseName());
                    createStage.close();
                } else {
                    new ErrorView("Make sure that the input is valid");
                }
            });
        }
        return new VBox(courseName, level, subject, introductionText, submitCourse);
    }
}
