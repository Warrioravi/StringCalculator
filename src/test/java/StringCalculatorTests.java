import org.example.tddKata1.StringCalculator;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class StringCalculatorTests {

    // 1.1 Test case for an empty string input to the Add method
    @Test
    public void testEmptyStringReturnsZero() {
        assertEquals(0, StringCalculator.add(""));
    }

    // 1.2 Test case for a single number string input to the Add method
    @Test
    public void testSingleNumberReturnsSameNumber() {
        assertEquals(5, StringCalculator.add("5"));
    }

    // 1.3 Test case for two numbers separated by a comma to the Add method
    @Test
    public void testTwoNumbersSeparatedByCommaReturnsSum() {
        assertEquals(6, StringCalculator.add("1,5"));
    }

    // 2. Test case for an any number of comma-separated numbers to the Add method
    @Test
    public void testMultipleNumbersSeparatedByCommaReturnsSum() {
        assertEquals(15, StringCalculator.add("10,2,3"));
    }

    // 3. Test case for numbers separated by commas and new lines to the Add method
    @Test
    public void testNewLineAsDelimiter() {
        assertEquals(10, StringCalculator.add("1\n2,3\n4"));
    }

    // 4. Test case for a custom delimiter to the Add method
    @Test
    public void testCustomDelimiter() {
        assertEquals(8, StringCalculator.add("//;\n1;2;5"));
    }

    // 4.1 Test case for custom delimiters that are regex characters
    @Test
    public void testCustomRegexCharacterDelimiter() {
        assertEquals(10, StringCalculator.add("//*\n3*2*5"));
    }

    // 5. Test case to throw an exception for negative numbers to the Add method
    @Test
    public void testNegativeNumbersThrowException() {
        try{
            StringCalculator.add("-1,-2,3,-100,20");
            fail("exception occurred");
        }catch(RuntimeException e) {
            assertEquals("Negative numbers not allowed: -1,-2,-100",e.getMessage());
        }
    }

    // 6: Test case to ignore numbers greater than 1000 in the Add method
    @Test
    public void testIgnoreNumbersGreaterThan1000() {
        assertEquals(30, StringCalculator.add("1,22,3000,7"));
    }

    // 7:Test case for delimiters of any length to the Add method
    @Test
    public void testDelimitersOfAnyLength() {
        assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
    }

    // 8:Test case for multiple single-character delimiters to the Add method
    @Test
    public void testMultipleSingleCharacterDelimiters() {
        assertEquals(16, StringCalculator.add("//[*][%]\n1*2%13"));
    }

    // 9:Test case for multiple delimiters of varying lengths to the Add method
    @Test
    public void testMultipleDelimitersOfVaryingLengths() {
        assertEquals(16, StringCalculator.add("//[**_][***]\n1**_2***13"));
    }
}
