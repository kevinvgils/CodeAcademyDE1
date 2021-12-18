import domain.student.Student;
import logic.*;

import javafx.application.Application;

public class App {
    public static void main(String[] args) throws Exception {
        StudentController studentController = new StudentController();

        Student testStudent = studentController.getStudent("test@gmail.com");

        System.out.println(testStudent.getName());
        System.out.println("-------------------");
        testStudent.setName("Kevinetje van Gils");

        studentController.updateStudent(testStudent, "test@gmail.com");

        Application.launch(gui.MasterView.class);

    }
}
