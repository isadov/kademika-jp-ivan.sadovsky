package day10.domainmodeltests;

import day10.domainmodel.Fruits.Fruit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith (JUnit4.class)
public class FruitTests {

    private Fruit fruit;

    @Test
    public void checkIdDefaultValue() {
        assertTrue("Default Value (ID) Should be 0", fruit.getId() == 0);
    }

    @Test
    public void checkNameDefaultValue() {
        assertNull("Default Value (Name) Should be null", fruit.getName());
    }

    @Test
    public void checkPriceDefaultValue() {
        assertNull("Default Value (Price) Should be null", fruit.getPrice());
    }

}
