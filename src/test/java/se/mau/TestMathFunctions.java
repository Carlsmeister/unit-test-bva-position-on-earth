package se.mau;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMathFunctions {
    private MathFunctions mathFunctions;

    @BeforeEach
    public void setUp() {
        mathFunctions = new MathFunctions();
    }

    @Test
    public void testAdd() {
        int result = mathFunctions.add(3, 4);
        assertEquals(7, result, "3 + 4 should be 7");
    }

    @Test
    public void testMultiply() {
        int result = mathFunctions.multiply(2, 5);
        assertEquals(10, result, "2 * 5 should be 10");
    }
    
}
