package gui;

import java.util.ArrayList;
import domain.student.*;
import gui.addviews.AddView;
import gui.detailviews.DetailView;
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

public class MasterView extends Application {
    private StudentController studentController = new StudentController();
    private int type = 0;
    private ToggleGroup items = new ToggleGroup();

    public VBox alltypes() {

        if(type == 0) {
            return setStudent(studentController.getAllStudents());
        } else if(type == 1){
            return new VBox(); //WIP
        } else{
            return new VBox(); //WIP
        }
    }


    //Gets all students from controller and puts them in a list
    public VBox setStudent(ArrayList<Student> students){
        VBox allStudents = new VBox();
        for(Student student : students){
            RadioButton tempButton = new RadioButton(student.getName() +" - " + student.getEmail());
            tempButton.setToggleGroup(items);
            allStudents.getChildren().add(tempButton);
        }
        return allStudents;
    }

    @Override
    public void start(Stage arg0) throws Exception {
        
        BorderPane mainWrap = new BorderPane();

        //Header
        MenuBar header = new MenuBar();
        Menu nav1 = new Menu("Students");
        header.getMenus().add(nav1);
        mainWrap.setTop(header);

        //Main
        mainWrap.setCenter(alltypes());

        //Footer
        Button refreshList = new Button("Refresh list");
        Button addButton = new Button("Add");
        Button viewButton = new Button("View");
        Button removeButton = new Button("Remove");
        HBox buttons = new HBox(refreshList, addButton, viewButton, removeButton);

        //TODO: master doesnt update yet.
        refreshList.setOnAction(event ->{
            mainWrap.setCenter(alltypes());
        });
        addButton.setOnAction(event ->{
            new AddView(type, true, null);
            mainWrap.setCenter(alltypes());
        });
        viewButton.setOnAction(event ->{
            try {
                RadioButton selected = (RadioButton) items.getSelectedToggle();
                new DetailView(type, selected.getText());
                mainWrap.setCenter(alltypes());
            } catch (Exception e) {
                
            }
        });
        removeButton.setOnAction(event ->{
            RadioButton selected = (RadioButton) items.getSelectedToggle();
            try {
                if (type == 0) {
                    studentController.deleteStudent(selected.getText().split(" - ")[1]);
                    mainWrap.setCenter(setStudent(studentController.getAllStudents()));
                } else if (type == 1) {
                    
                } else{

                }
                mainWrap.setCenter(alltypes());
            } catch (Exception e) {

            }
            
            mainWrap.setCenter(setStudent(studentController.getAllStudents()));
        });

        mainWrap.setBottom(buttons);

        //Sets scene
        Scene scene = new Scene(mainWrap, 200, 100);
        arg0.setScene(scene);
        arg0.show();
    }
}
