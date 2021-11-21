package baseline;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WarningCheckerTest {


    @Test
    void checkNameTest()
    {
        String name = "Bob";

        assertTrue(name.length() < 256 && name.length() > 1);
    }

    @Test
    void checkSerialTest()
    {
        String serialNumber = "A-100-200-B30";
        String pattern = ("\\p{Alpha}-\\w\\w\\w-\\w\\w\\w-\\w\\w\\w");
        assertTrue(Pattern.matches(pattern, serialNumber));
    }

    @Test
    void checkValueTest()
    {
        int value = 500;

        assertTrue(value >= 0);
    }


}
