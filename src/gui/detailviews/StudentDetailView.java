package gui.detailviews;

import java.util.ArrayList;

import domain.student.Enrollment;
import domain.student.Student;
import gui.addviews.CertificateAddView;
import gui.addviews.EnrollmentAddView;
import gui.addviews.StudentAddView;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.StudentController;
import logic.EnrollmentController;

public class StudentDetailView {

    private StudentController studentController = new StudentController();
    private EnrollmentController enrollmentController = new EnrollmentController();
    private Student selectedStudent;

    private BorderPane body = new BorderPane();
    private String itemString;

    public StudentDetailView(int type, String itemString) {
        Stage viewStage = new Stage();

        // header
        Label title = new Label();
        body.setTop(title);

        // main
        title.setText("Student");
        body.setCenter(viewStudent(itemString));

        // Footer
        Button refreshList = new Button("Refresh list");
        Button addButton = new Button("Enroll");
        Button deleteButton = new Button("Delete");
        Button editButton = new Button("Update");
        HBox buttons = new HBox(refreshList, addButton, deleteButton, editButton);
        body.setBottom(buttons);

        // events
        refreshList.setOnAction(event -> {
            body.setCenter(viewStudent(itemString));
        });
        addButton.setOnAction(event -> {
            addEnrollment();
        });
        deleteButton.setOnAction(event -> {
            deleteItem();
            viewStage.close();
        });
        editButton.setOnAction(event -> {
            updateItem();
            viewStage.close();
        });

        Scene scene = new Scene(body);
        viewStage.setScene(scene);
        viewStage.show();
    }

    public void addEnrollment() {
        new EnrollmentAddView(selectedStudent, true, 0);
    }

    public void deleteItem() {
        if (selectedStudent != null) {
            studentController.deleteStudent(selectedStudent.getEmail());
        }
    }

    public void updateItem() {
        if (selectedStudent != null) {
            new StudentAddView(0, false, selectedStudent.getEmail());
        }
    }

    // Makes the addStudent form
    public VBox viewStudent(String itemString) {
        try {
            this.itemString = itemString;
            String[] parts = itemString.split(" - ");
            selectedStudent = studentController.getStudent(parts[1]);

            Label name = new Label("Name: " + selectedStudent.getName());
            Label email = new Label("Email: " + selectedStudent.getEmail());
            Label dateOfBirth = new Label("Date of birth: " +
                    selectedStudent.getDateOfBirth());

            Label gender = new Label();
            switch (selectedStudent.getGender()) {
                case 0:
                    gender.setText("Gender: Male");
                    break;
                case 1:
                    gender.setText("Gender: Female");
                    break;
                case 2:
                    gender.setText("Gender: Other");
                    break;

            }
            Label adress = new Label("Adress: " + selectedStudent.getZipCode() + " " + selectedStudent.getStreet() + " "
                    + selectedStudent.getHouseNumber());
            Label location = new Label("Location: " + selectedStudent.getLocation());
            Label country = new Label("Country: " + selectedStudent.getCountry());

            return new VBox(name, email, dateOfBirth, gender, adress, location, country,
                    getEnrollments(enrollmentController.getEnrollments(selectedStudent.getEmail())));

        } catch (Exception e) {
            return new VBox();
        }

    }

    public VBox getEnrollments(ArrayList<Enrollment> enrollments) {
        VBox allEnrollmentsForStude = new VBox();
        for (Enrollment enrollment : enrollments) {
            Button update = new Button("update");
            Button delete = new Button("delete");
            Button certificate = new Button("certificate");

            String certificateStatus = "";
            if (enrollment.getCertificate_id() != 0) {
                certificateStatus = " ✓ ";
            } else {
                certificateStatus = " X ";
            }

            Label enrollmentLabel = new Label(
                    certificateStatus + enrollment.getCourseName() + " - " + enrollment.getDateOfEnrollment());

            update.setOnAction(event -> {
                new EnrollmentAddView(selectedStudent, false, enrollment.getEnrollment_id());
            });
            delete.setOnAction(event -> {
                enrollmentController.deleteEnrollment(enrollment.getEnrollment_id());
                body.setCenter(viewStudent(itemString));
            });

            certificate.setOnAction(event -> {
                new CertificateAddView(enrollment.getEnrollment_id());
            });
            HBox enrollmentWrap = new HBox();
            if (enrollment.getCertificate_id() != 0) {
                enrollmentWrap = new HBox(update, delete, enrollmentLabel);
            } else {
                enrollmentWrap = new HBox(update, delete, certificate, enrollmentLabel);
            }
            allEnrollmentsForStude.getChildren().add(enrollmentWrap);
        }
        return allEnrollmentsForStude;
    }
}
