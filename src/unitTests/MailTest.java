package unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import domain.student.Student;

public class MailTest {

    /**
     * @desc Validates if mailAddress is valid. It should be in the form of:
     *       <at least 1 character>@<at least 1 character>.<at least 1 character>
     * 
     * @subcontract no mailbox part {
     * @requires !mailAddress.contains("@") ||
     *           mailAddress.split("@")[0].length < 1;
     * @ensures \result = false;
     *          }
     * 
     * @subcontract subdomain-tld delimiter {
     * @requires !mailAddress.contains("@") ||
     *           mailAddress.split("@")[1].split(".").length > 2;
     * @ensures \result = false;
     *          }
     * 
     * @subcontract no subdomain part {
     * @requires !mailAddress.contains("@") ||
     *           mailAddress.split("@")[1].split(".")[0].length < 1;
     * @ensures \result = false;
     *          }
     * 
     * @subcontract no tld part {
     * @requires !mailAddress.contains("@") ||
     *           mailAddress.split("@")[1].split(".")[1].length < 1;
     * @ensures \result = false;
     *          }
     * 
     * @subcontract valid email {
     * @requires no other precondition
     * @ensures \result = true;
     *          }
     * 
     */

    @Test
    public void testMailNoMailBox() {
        // arrange
        Student student = new Student("lorem", null, null, null, "1000 KK", null, null, null, null);

        // act
        Boolean validateResult = student.vallidate();

        // test
        assertEquals(false, validateResult);
    }

    @Test
    public void testSubDomainTldDelimiter() {
        // arrange
        Student student = new Student("loremlorem@ipsum.az.com", null, null, null, "1000 KK", null, null, null, null);

        // act
        Boolean validateResult = student.vallidate();

        // test
        assertEquals(false, validateResult);
    }

    @Test
    public void testMailNoTld() {
        // arrange
        Student student = new Student("loremipsum@gmail", null, null, null, "1000 KK", null, null, null, null);

        // act
        Boolean validateResult = student.vallidate();

        // test
        assertEquals(false, validateResult);
    }

    @Test
    public void testMailNoSubDomain() {
        // arrange
        Student student = new Student("loremipsum.com", null, null, null, "1000 KK", null, null, null, null);

        // act
        Boolean validateResult = student.vallidate();

        // test
        assertEquals(false, validateResult);
    }

    @Test
    public void testValidEmail() {
        // arrange
        Student student = new Student("lorem@ipsum.com", null, null, null, "1000 KK", null, null, null, null);

        // act
        Boolean validateResult = student.vallidate();

        // test
        assertEquals(true, validateResult);
    }

}
