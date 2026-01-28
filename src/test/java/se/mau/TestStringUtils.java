package se.mau;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStringUtils {
    private StringUtils stringUtils;

    @BeforeEach
    public void setUp() {
        stringUtils = new StringUtils();
    }

    @Test
    public void testConcat() {
        String result = stringUtils.concat("Hello", "World");
        assertEquals("HelloWorld", result, "Concatenation should be 'HelloWorld'");
    }

    @Test
    public void testLength() {
        int result = stringUtils.length("JUnit");
        assertEquals(5, result, "Length of 'JUnit' should be 5");
    }
}
