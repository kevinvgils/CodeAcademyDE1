package gui;

import java.util.ArrayList;

import domain.course.Course;
import domain.student.*;
import gui.addviews.CourseAddView;
import gui.addviews.StudentAddView;
import gui.detailviews.CourseDetailView;
import gui.detailviews.StudentDetailView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.StudentController;
import logic.CourseController;

public class MasterView extends Application {
    private StudentController studentController = new StudentController();
    private CourseController courseController = new CourseController();
    private int type = 0;
    private ToggleGroup items = new ToggleGroup();

    public VBox alltypes() {

        if(type == 0) {
            return setStudent(studentController.getAllStudents());
        } else if(type == 1){
            return setCourse(courseController.getAllCourses());
        } else{
            return new VBox(); //WIP
        }
    }

    //Gets all students from studentController and puts them in a list
    public VBox setStudent(ArrayList<Student> students){
        VBox allStudents = new VBox();
        for(Student student : students){
            RadioButton tempButton = new RadioButton(student.getName() +" - " + student.getEmail());
            tempButton.setToggleGroup(items);
            allStudents.getChildren().add(tempButton);
        }
        return allStudents;
    }

    //Gets all courses from courseController and puts them in a list
    public VBox setCourse(ArrayList<Course> courses){
        VBox allCourses = new VBox();
        for(Course course : courses){
            RadioButton tempButton = new RadioButton(course.getCourseName() + " - " + course.getSubject());
            tempButton.setToggleGroup(items);
            allCourses.getChildren().add(tempButton);
        }
        return allCourses;
    }

    @Override
    public void start(Stage arg0) throws Exception {
        
        BorderPane mainWrap = new BorderPane();

        //Header
        Button nav1 = new Button("Students");
        Button nav2 = new Button("Course");
        HBox header = new HBox(nav1, nav2);
        mainWrap.setTop(header);

        //Main
        mainWrap.setCenter(alltypes());

        //Footer
        Button refreshList = new Button("Refresh list");
        Button addButton = new Button("Add");
        Button viewButton = new Button("View");
        Button removeButton = new Button("Remove");
        HBox buttons = new HBox(refreshList, addButton, viewButton, removeButton);

        refreshList.setOnAction(event ->{
            mainWrap.setCenter(alltypes());
        });
        addButton.setOnAction(event ->{
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
        viewButton.setOnAction(event ->{
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

        removeButton.setOnAction(event ->{
            RadioButton selected = (RadioButton) items.getSelectedToggle();
            try {
                if (type == 0) {
                    studentController.deleteStudent(selected.getText().split(" - ")[1]);
                    mainWrap.setCenter(setStudent(studentController.getAllStudents()));
                } else if (type == 1) {
                    courseController.deleteCourse(selected.getText().split(" - ")[0]);
                    mainWrap.setCenter(setCourse(courseController.getAllCourses()));
                } else{

                }
                mainWrap.setCenter(alltypes());
            } catch (Exception e) {

            }
        });

        mainWrap.setBottom(buttons);

        //Sets scene
        Scene scene = new Scene(mainWrap, 225, 125);
        arg0.setScene(scene);
        arg0.show();
    }
}
