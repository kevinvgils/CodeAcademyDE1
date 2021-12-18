
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import domain.student.Student;
import javafx.application.Application;
import logic.studentController;

public class App {
    public static void main(String[] args) throws Exception {
        studentController studentController = new studentController();

        Student testStudent = studentController.getStudent("test@gmail.com");

        System.out.println(testStudent.getName());
        System.out.println("-------------------");
        testStudent.setName("Kevinetje van Gils");

        studentController.updateStudent(testStudent, "test@gmail.com");

        Application.launch(gui.MasterView.class);

    }
}
