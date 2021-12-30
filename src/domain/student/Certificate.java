package domain.student;

public class Certificate {
    private Integer certificateID;
    private Integer grade;
    private String staffName;

    public Certificate(Integer certificateID, Integer grade, String staffName) {
        this.certificateID = certificateID;
        this.grade = grade;
        this.staffName = staffName;
    }

    public Integer getCertificateID() {
        return certificateID;
    }

    public void setCertificateID(Integer certificateID) {
        this.certificateID = certificateID;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

}
