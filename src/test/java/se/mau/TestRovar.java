package se.mau;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRovar {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyzåäö";
    private static final String LOWER_ENROV = "abobcocdodefofgoghohijojkoklolmomnonopopqoqrorsostotuvovwowxoxyzozåäö";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ";
    private static final String UPPER_ENROV = "ABOBCOCDODEFOFGOGHOHIJOJKOKLOLMOMNONOPOPQOQRORSOSTOTUVOVWOWXOXYZOZÅÄÖ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL = "!#€";

    private void assertEnrov(String input, String expected, String message) {
        String result = Rovar.enrov(input);
        assertEquals(expected, result, message);
    }

    private void assertDerov(String input, String expected, String message) {
        String result = Rovar.derov(input);
        assertEquals(expected, result, message);
    }

    @Test
    public void testEnrovNull() {
        String result = Rovar.enrov(null);
        assertEquals(null, result, "Null should be handled correctly");
    }

    @Test
    public void testEnrovEmpty() {
        String result = Rovar.enrov("");
        assertEquals("", result, "Empty string should be handled correctly");
    }

    @Test
    public void testDerovNull() {
        String result = Rovar.derov(null);
        assertEquals(null, result, "Null should be handled correctly");
    }

    @Test
    public void testDerovEmpty() {
        String result = Rovar.derov("");
        assertEquals("", result, "Empty string should be handled correctly");
    }

    @Test
    public void testEnrovLowerCase() {
        assertEnrov(LOWER, LOWER_ENROV, "Lower case letters should be encoded correctly");
    }

    @Test
    public void testEnrovUpperCase() {
        assertEnrov(UPPER, UPPER_ENROV, "Upper case letters should be encoded correctly");
    }

    @Test
    public void testEnrovNumbers() {
        assertEnrov(NUMBERS, NUMBERS, "Numbers should remain unchanged");
    }

    @Test
    public void testEnrovSpecialCharacters() {
        assertEnrov(SPECIAL, SPECIAL, "Special characters should remain unchanged");
    }

    @Test
    public void testDerovLowerCase() {
        assertDerov(LOWER_ENROV, LOWER, "Lower case letters should be decoded correctly");
    }

    @Test
    public void testDerovUpperCase() {
        assertDerov(UPPER_ENROV, UPPER, "Upper case letters should be decoded correctly");
    }

    @Test
    public void testDerovNumbers() {
        assertDerov(NUMBERS, NUMBERS, "Numbers should remain unchanged");
    }

    @Test
    public void testDerovSpecialCharacters() {
        assertDerov(SPECIAL, SPECIAL, "Special characters should remain unchanged");
    }
}
