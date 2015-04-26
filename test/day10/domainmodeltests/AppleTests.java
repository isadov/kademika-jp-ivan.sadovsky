package day10.domainmodeltests;

import day10.domainmodel.Fruits.Apple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNull;


@RunWith(JUnit4.class)

public class AppleTests {

    private Apple apple;

    @Before
    public void init() {
        apple = new Apple();
    }

    @Test
    public void checkAppleDefaultColor() {
        assertNull("Default color Should be null", apple.getColor());
    }
}
