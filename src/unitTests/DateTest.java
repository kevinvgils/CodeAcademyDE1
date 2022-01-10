package unitTests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import domain.student.Student;

public class DateTest {
    /**
    * @desc Validates is a given date in the form of day, month and year is valid.
    * 
    * @subcontract 31 days in month {
    *   @requires (month == 1 || month == 3 || month == 5 || month == 7 || 
    *             month == 8 || month == 10 || month == 12) && 1 <= day <= 31;
    *   @ensures \result = true;
    * }
    * 
    * @subcontract 30 days in month {
    *   @requires (month == 4 || month == 6 || month == 9 || month == 11) &&
    *              1 <= day <= 30;
    *   @ensures \result = true;
    * }
    * 
    * @subcontract 29 days in month {
    *   @requires month == 2 && 1 <= day <= 29 &&
    *             (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    *   @ensures \result = true;
    * }
    * 
    * @subcontract 28 days in month {
    *   @requires month == 2 && 1 <= day <= 28 &&
    *             (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0));
    *   @ensures \result = true;
    * }
    * 
    * @subcontract all other cases {
    *   @requires no other accepting precondition;
    *   @ensures \result = false;
    * }
    * 
    */ 

    @Test
    public void thirthyOneDaysInMonth(){
        //Arrange
        Student student = new Student("lorem@gmail.com", null, Date.valueOf("2015-01-31"), null, "1000 KK", null, null, null, null);

        //Act
        boolean result = student.vallidate();

        //Assert
        assertEquals(true, result);
    }
    @Test
    public void thirthyDaysInMonth(){
        //Arrange
        Student student = new Student("lorem@gmail.com", null, Date.valueOf("2015-04-30"), null, "1000 KK", null, null, null, null);

        //Act
        boolean result = student.vallidate();

        //Assert
        assertEquals(true, result);
    }
    @Test
    public void twentyEightDaysInMonth(){
        //Arrange
        Student student = new Student("lorem@gmail.com", null, Date.valueOf("2019-02-28"), null, "1000 KK", null, null, null, null);

        //Act
        boolean result = student.vallidate();

        //Assert
        assertEquals(true, result);
    }
    @Test
    public void twentyNineDaysInMonth(){
        //Arrange
        Student student = new Student("lorem@gmail.com", null, Date.valueOf("2020-02-29"), null, "1000 KK", null, null, null, null);

        //Act
        boolean result = student.vallidate();

        //Assert
        assertEquals(true, result);
    }
    @Test (expected = IllegalArgumentException.class)
    public void allOtherCases(){
        //Arrange
        Student student = new Student("lorem@gmail.com", null, Date.valueOf("202029"), null, "1000 KK", null, null, null, null);

        //Act
        boolean result = student.vallidate();

        //Assert
        assertEquals(false, result);
    }
}
