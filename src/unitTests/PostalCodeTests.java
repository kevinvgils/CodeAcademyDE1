package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.student.Student;

public class PostalCodeTests {
    /**
     * @desc Formats the input postal code to a uniform output in the form
     * nnnn<space>MM, where nnnn is numeric and > 999 and MM are 2 capital letters.
     * Spaces before and after the input string are trimmed.
     * 
     * @subcontract null postalCode {
     *   @requires postalCode == null;
     *   @signals (NullPointerException) postalCode == null;
     * }
     * 
     * @subcontract valid postalCode {
     *   @requires Integer.valueOf(postalCode.trim().substring(0, 4)) > 999 &&
     *             Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
     *             postalCode.trim().substring(4).trim().length == 2 &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z' &&
     *             'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z';
     *   @ensures \result = postalCode.trim().substring(0, 4) + " " +
     *                  postalCode.trim().substring(4).trim().toUpperCase()
     * }
     *
     * @subcontract invalid postalCode {
     *   @requires no other valid precondition;
     *   @signals (IllegalArgumentException);
     * }
     * 
     */
    
    @Test
    public void nullPostalCode(){
        //Arrange
        Student student = new Student("lorem@gmail.com", null, null, null, null, null, null, null, null);

        //Act
        boolean result = student.vallidate();

        //Assert
        assertEquals(false, result);
    }

    @Test
    public void validPostalCode(){
        //Arrange
        Student student = new Student("lorem@gmail.com", null, null, null, "1000 KK", null, null, null, null);

        //Act
        boolean result = student.vallidate();

        //Assert
        assertEquals(true, result);
    }

    @Test
    public void invalidPostalCode(){
        //Arrange
        Student student = new Student("lorem@gmail.com", null, null, null, "00000k", null, null, null, null);

        //Act
        boolean result = student.vallidate();

        //Assert
        assertEquals(false, result);
    }
}
