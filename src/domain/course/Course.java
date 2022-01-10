package domain.course;

public class Course {
    private String courseName;
    private String level;
    private String subject;
    private String introductionText;

    public Course(String courseName, String level, String subject, String introductionText) {
        this.courseName = courseName;
        this.level = level;
        this.subject = subject;
        this.introductionText = introductionText;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getIntroductionText() {
        return introductionText;
    }
    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public boolean vallidate(){
        if(courseName != null && level != null && subject != null && introductionText != null){
            return true;
        } else{
            return false;
        }
    }
}
