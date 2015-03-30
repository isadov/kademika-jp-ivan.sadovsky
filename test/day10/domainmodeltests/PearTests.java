package day10.domainmodeltests;

import day10.domainmodel.Fruits.Pear;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)

public class PearTests {
    private Pear pear;

    @Before
    public void init () {
        pear = new Pear();
    }

    @Test
    public void checkFreshnessDefaultValue() {
        assertTrue("Default Value Should Be True", pear.isFreshness());
    }



}
