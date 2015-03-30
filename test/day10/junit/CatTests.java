package day10.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)

public class CatTests {

    private Cat cat;

    @Before // method init would be start before all Tests !! (3 methods = 3 times) !
    public void init() {
        cat = new Cat();
    }

    @Test
    public void checkCatNameDefaultValue() {
        String name = cat.getName();
        assertNull("Default Name Of Cat. Should be null", name);

    }

    @Test
    public void checkCatWeightDefaultValue() {
        assertTrue("Default Weight Of Cat. Should be 0.0", cat.getWeight() == 0.0);

    }

    @Test
    public void checkCatIsHungryDefaultValue() {
        assertTrue("Default isHungry Of Cat. Should be true", cat.isHungry());

    }

    @Test
    public void checkSetName() {
        String name = "Murzik";
        cat.setName(name);
        assertEquals(name, cat.getName());

    }



}

