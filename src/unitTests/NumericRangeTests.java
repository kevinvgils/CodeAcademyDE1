package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.contentItem.ContentItem;

public class NumericRangeTests {
    @Test
    public void valueWithinValidRange(){
        //Arrange
        ContentItem test = new ContentItem();
        int percentage = 50;

        //Act
        boolean result = test.vallidate(50);

        //Assert
        assertEquals(true, result);
    }
}
