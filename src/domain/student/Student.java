package domain.student;

import java.util.ArrayList;
import java.sql.Date;

public class Student {
    private String email;
    private String name;
    private Date dateOfBirth;
    private Integer gender;
    private String zipCode;
    private Integer houseNumber;
    private String street;
    private String country;
    private String location;
    private ArrayList<Enrollment> enrollments;

    public Student(String email, String name, Date dateOfBirth, Integer gender, String zipCode, Integer houseNumber,
            String street, String country, String location) {
        this.email = email;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        this.street = street;
        this.country = country;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public boolean vallidate(){
        if(email != null && name != null && dateOfBirth != null && gender != null && country != null && houseNumber != null && zipCode != null && street != null && location != null){
            if(email.matches("^[a-zA-Z]+@[a-zA-Z]+.[a-zA-Z]+$") && zipCode.matches("^[1-9]{1}[0-9]{3} [A-Z]{2}$")){
                return true;
            } else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
