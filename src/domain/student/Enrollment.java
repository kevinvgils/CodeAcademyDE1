package domain.student;

import java.util.ArrayList;
import java.sql.Date;

public class Enrollment {
    private Integer enrollment_id;
    private String email;
    private Integer certificate_id;
    private String courseName; 
    private Date dateOfEnrollment;

    public Enrollment(Integer enrollment_id, String email, Integer certificate_id, String courseName,
            Date dateOfEnrollment) {
        this.enrollment_id = enrollment_id;
        this.email = email;
        this.certificate_id = certificate_id;
        this.courseName = courseName;
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public Integer getEnrollment_id() {
        return enrollment_id;
    }
    public void setEnrollment_id(Integer enrollment_id) {
        this.enrollment_id = enrollment_id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getCertificate_id() {
        return certificate_id;
    }
    public void setCertificate_id(Integer certificate_id) {
        this.certificate_id = certificate_id;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }
    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

}

