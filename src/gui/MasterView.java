package gui;

import java.util.ArrayList;
import domain.student.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MasterView extends Application {
    private int type = 0;
    private ToggleGroup items = new ToggleGroup();

    public VBox alltypes() {

        if(type == 0) {
            //TODO: get all students from database
            return setStudent(new ArrayList<Student>());
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
            RadioButton tempButton = new RadioButton(/*student.getName() +*/" - "/* + student.getEmail()*/); //TODO: make getters and setters
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
        mainWrap.setCenter(setStudent(new ArrayList<Student>())); //TODO: VERVANG ARRAYLIST MET CONTROLER METHOD

        //Footer
        Button addButton = new Button("Add");
        Button viewButton = new Button("View");
        Button removeButton = new Button("Remove");
        HBox buttons = new HBox(addButton, viewButton, removeButton);

        addButton.setOnAction(event ->{
            AddView addItem = new AddView();
            addItem.createItem(type);
        });
        viewButton.setOnAction(event ->{
            try {
                RadioButton selected = (RadioButton) items.getSelectedToggle();
                DetailView viewItem = new DetailView(type, selected.getText());
            } catch (Exception e) {
                DetailView viewItem = new DetailView(type, null);
            }
            
        });
        removeButton.setOnAction(event ->{
            
            mainWrap.setCenter(setStudent(new ArrayList<Student>()));
        }); //TODO: VOEG DE DELETE CONTROLLER TOE

        mainWrap.setBottom(buttons);

        //Sets scene
        Scene scene = new Scene(mainWrap, 200, 100);
        arg0.setScene(scene);
        arg0.show();
    }
}
