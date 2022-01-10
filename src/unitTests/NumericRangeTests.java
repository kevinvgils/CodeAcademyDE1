package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.contentItem.ContentItem;



public class NumericRangeTests {
        /**
     * @desc Validates if the input is within range of 0-100%
     * 
     * @subcontract value within valid range {
     *   @requires 0 <= percentage <= 100;
     *   @ensures \result = true;
     * }
     * 
     * @subcontract value out of range low {
     *   @requires percentage < 0;
     *   @ensures \result = false;
     * }
     * 
     * @subcontract value out of range high {
     *   @requires percentage > 100;
     *   @signals \result = false;
     * }
     * 
     */
    @Test
    public void valueWithinValidRange(){
        //Arrange
        ContentItem test = new ContentItem();
        int percentage = 50;

        //Act
        boolean result = test.vallidate(percentage);

        //Assert
        assertEquals(true, result);
    }
    @Test
    public void valueOutOfRangeHigh(){
        //Arrange
        ContentItem test = new ContentItem();
        int percentage = 101;

        //Act
        boolean result = test.vallidate(percentage);

        //Assert
        assertEquals(false, result);
    }
    @Test
    public void valueOutOfRangeLow(){
        //Arrange
        ContentItem test = new ContentItem();
        int percentage = -10;

        //Act
        boolean result = test.vallidate(percentage);

        //Assert
        assertEquals(false, result);
    }

}
