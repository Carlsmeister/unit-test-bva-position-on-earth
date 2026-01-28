package se.mau;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
		TestMathFunctions.class, TestStringUtils.class, TestRovar.class, TestPositionOnEarth.class
})


public class TestRunner {
    // This class doesn't need any code. It's used to run the suite of test classes.
}
