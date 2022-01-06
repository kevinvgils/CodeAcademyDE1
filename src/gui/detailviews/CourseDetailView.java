package gui.detailviews;

import domain.course.Course;
import gui.addviews.CourseAddView;
import gui.addviews.RecommendedCourseAddView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.CourseController;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseDetailView {

    private CourseController courseController = new CourseController();
    private Course selectedCourse;
    private BorderPane body = new BorderPane();

    public CourseDetailView(int type, String itemString) {
        Stage viewStage = new Stage();

        // header
        Label title = new Label();
        body.setTop(title);

        // main
        title.setText("Course");
        body.setCenter(viewCourse(itemString));

        // Footer
        Button refreshButton = new Button("Refresh");
        Button addButton = new Button("Add recommended course");
        Button deleteButton = new Button("Delete");
        Button editButton = new Button("Update");
        HBox buttons = new HBox(refreshButton, addButton, deleteButton, editButton);
        body.setBottom(buttons);

        // events
        refreshButton.setOnAction(event -> {
            body.setCenter(viewCourse(itemString));
        });
        addButton.setOnAction(event -> {
            new RecommendedCourseAddView(selectedCourse.getCourseName());
        });
        deleteButton.setOnAction(event -> {
            if (selectedCourse != null) {
                courseController.deleteCourse(selectedCourse.getCourseName());
            }
            viewStage.close();
        });
        editButton.setOnAction(event -> {
            if (selectedCourse != null) {
                new CourseAddView(0, false, selectedCourse.getCourseName());
            }
            viewStage.close();
        });

        Scene scene = new Scene(body);
        viewStage.setScene(scene);
        viewStage.show();
    }

    // Makes the addCourse form
    public VBox viewCourse(String itemString) {
        try {
            String[] parts = itemString.split(" - ");
            selectedCourse = courseController.getCourse(parts[0]);
            HashMap<String, String> avgPerModule = courseController
                    .getAvergareProgressPerModule(selectedCourse.getCourseName());
            ArrayList<String> titleAndAverage = new ArrayList<>();

            Label level = new Label("Level: " + selectedCourse.getLevel());
            Label subject = new Label("Subject: " + selectedCourse.getSubject());
            Label introductionText = new Label("Introduction text: " + selectedCourse.getIntroductionText());
            Label passedCourse = new Label(
                    "Passed course: " + courseController.getAmountPassedForCourse(selectedCourse.getCourseName()));
            Label avgModel = new Label("Average progress per module:");

            for (String key : avgPerModule.keySet()) {
                titleAndAverage.add(key + ": " + avgPerModule.get(key));
            }

            ObservableList<String> modules = FXCollections.observableArrayList(titleAndAverage);
            ListView<String> listView = new ListView<String>(modules);
            listView.setMaxHeight(90);

            return new VBox(level, subject, introductionText, passedCourse, avgModel, listView,
                    recommendedCoure(itemString));

        } catch (Exception e) {
            return new VBox();
        }
    }

    public VBox recommendedCoure(String itemString) {
        String[] parts = itemString.split(" - ");
        ArrayList<Course> recommended = courseController.getRecommendedCourses(parts[0]);
        VBox result = new VBox(new Label("Recommended courses:"));
        for (Course course : recommended) {
            Button delete = new Button("X");
            HBox courseBox = new HBox(delete, new Label(course.getCourseName()));
            result.getChildren().add(courseBox);

            delete.setOnAction(event -> {
                courseController.deleteRecommended(parts[0], course.getCourseName());
                body.setCenter(viewCourse(itemString));
            });
        }
        return result;
    }
}
