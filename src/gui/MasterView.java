package gui;

import java.text.DecimalFormat;
import java.util.ArrayList;

import domain.course.Course;
import domain.student.*;
import gui.addviews.CourseAddView;
import gui.addviews.StudentAddView;
import gui.detailviews.CourseDetailView;
import gui.detailviews.StudentDetailView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.StudentController;
import logic.ContentItemController;
import logic.CourseController;
import logic.EnrollmentController;

public class MasterView extends Application {
    private StudentController studentController = new StudentController();
    private CourseController courseController = new CourseController();
    private EnrollmentController enrollmentController = new EnrollmentController();
    private ContentItemController contentItemController = new ContentItemController();
    private int type = 0;
    private ToggleGroup items = new ToggleGroup();

    public VBox alltypes() {

        if (type == 0) {
            return setStudent(studentController.getAllStudents());
        } else if (type == 1) {
            return setCourse(courseController.getAllCourses());
        } else if (type == 2) {
            return new VBox(allStats()); // WIP
        } else {
            return null;
        }
    }

    // Gets all students from studentController and puts them in a list
    public VBox setStudent(ArrayList<Student> students) {
        VBox allStudents = new VBox();
        for (Student student : students) {
            RadioButton tempButton = new RadioButton(student.getName() + " - " + student.getEmail());
            tempButton.setToggleGroup(items);
            allStudents.getChildren().add(tempButton);
        }
        return allStudents;
    }

    // Gets all courses from courseController and puts them in a list
    public VBox setCourse(ArrayList<Course> courses) {
        VBox allCourses = new VBox();
        for (Course course : courses) {
            RadioButton tempButton = new RadioButton(course.getCourseName() + " - " + course.getSubject());
            tempButton.setToggleGroup(items);
            allCourses.getChildren().add(tempButton);
        }
        return allCourses;
    }

    // Gives stats
    public Accordion allStats() {
        Accordion accordion = new Accordion();
        TitledPane pane1 = new TitledPane("Percent certificate per gender", getPercentCertificate());
        TitledPane pane2 = new TitledPane("Top 3 Courses", getTop3Courses());
        TitledPane pane3 = new TitledPane("Top 3 Webcasts", getTop3Webcasts());

        accordion.getPanes().addAll(pane1, pane2, pane3);

        return accordion;
    }

    public Accordion getPercentCertificate() {
        Accordion PCAccordion = new Accordion();
        ArrayList<Course> courses = courseController.getAllCourses();

        for (Course c : courses) {
            VBox innerWrap = new VBox();
            ArrayList<double[]> percents = courseController.getPercentCertificate(c.getCourseName());
            for (double[] perc : percents) {
                String gender = "";
                switch ((int) perc[0]) {
                    case 0:
                        gender = ("Male: ");
                        break;
                    case 1:
                        gender = ("Female: ");
                        break;
                    case 2:
                        gender = ("Other: ");
                        break;
                }
                DecimalFormat df = new DecimalFormat("###.#");
                innerWrap.getChildren().add(new Label(gender + df.format(perc[1]) + "%"));
            }
            PCAccordion.getPanes().add(new TitledPane(c.getCourseName(), innerWrap));
        }

        return PCAccordion;
    }

    // Gives Top 3 Courses in a listView
    public ListView<String> getTop3Courses() {
        ArrayList<String> top3Courses = enrollmentController.getTop3Courses();
        ObservableList<String> modules = FXCollections.observableArrayList(top3Courses);
        ListView<String> listView = new ListView<String>(modules);
        listView.setMaxHeight(70);
        listView.setMinHeight(70);

        return listView;
    }

    // Gives Top 3 Webcasts in a listview
    public ListView<String> getTop3Webcasts() {
        ArrayList<String> top3Webcasts = contentItemController.getTop3Webcasts();
        ObservableList<String> modules = FXCollections.observableArrayList(top3Webcasts);
        ListView<String> listView = new ListView<String>(modules);
        listView.setMaxHeight(70);
        listView.setMinHeight(70);

        return listView;
    }

    @Override
    public void start(Stage arg0) throws Exception {

        BorderPane mainWrap = new BorderPane();

        // Header
        Button nav1 = new Button("Students");
        Button nav2 = new Button("Course");
        Button nav3 = new Button("Stats");
        HBox header = new HBox(nav1, nav2, nav3);
        mainWrap.setTop(header);

        // Main
        mainWrap.setCenter(alltypes());

        // Footer
        Button refreshList = new Button("Refresh list");
        Button addButton = new Button("Add");
        Button viewButton = new Button("View");
        Button removeButton = new Button("Remove");
        HBox buttons = new HBox(refreshList, addButton, viewButton, removeButton);

        refreshList.setOnAction(event -> {
            mainWrap.setCenter(alltypes());
        });
        addButton.setOnAction(event -> {
            try {
                if (type == 0) {
                    new StudentAddView(type, true, null);
                    mainWrap.setCenter(alltypes());
                } else if (type == 1) {
                    new CourseAddView(type, true, null);
                    mainWrap.setCenter(alltypes());
                } else {

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        viewButton.setOnAction(event -> {
            try {
                if (type == 0) {
                    RadioButton selected = (RadioButton) items.getSelectedToggle();
                    new StudentDetailView(type, selected.getText());
                } else if (type == 1) {
                    RadioButton selected = (RadioButton) items.getSelectedToggle();
                    new CourseDetailView(type, selected.getText());
                } else {

                }
                mainWrap.setCenter(alltypes());
            } catch (Exception e) {

            }
        });
        nav1.setOnAction(event -> {
            type = 0;
            mainWrap.setCenter(alltypes());
        });
        nav2.setOnAction(event -> {
            type = 1;
            mainWrap.setCenter(alltypes());
        });
        nav3.setOnAction(event -> {
            type = 2;
            mainWrap.setCenter(alltypes());
        });

        removeButton.setOnAction(event -> {
            RadioButton selected = (RadioButton) items.getSelectedToggle();
            try {
                if (type == 0) {
                    studentController.deleteStudent(selected.getText().split(" - ")[1]);
                    mainWrap.setCenter(setStudent(studentController.getAllStudents()));
                } else if (type == 1) {
                    courseController.deleteCourse(selected.getText().split(" - ")[0]);
                    mainWrap.setCenter(setCourse(courseController.getAllCourses()));
                } else {

                }
                mainWrap.setCenter(alltypes());
            } catch (Exception e) {

            }
        });

        mainWrap.setBottom(buttons);

        // Sets scene
        Scene scene = new Scene(mainWrap, 420, 250);
        arg0.setScene(scene);
        arg0.show();
    }
}
