package domain.student;

import java.util.ArrayList;
import java.util.Date;

public class Student {
    private String email;
    private String name;
    private Date dateOfBirth;
    private Integer gender;
    private String zipCode;
    private Integer houseNumber;
    private String street;
    private String country;
    private ArrayList<Enrollment> enrollments;

    public Student(String email, String name, Date dateOfBirth, Integer gender, String zipCode, Integer houseNumber,
            String street, String country) {
        this.email = email;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        this.street = street;
        this.country = country;
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
}
