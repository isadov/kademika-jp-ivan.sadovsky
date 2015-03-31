package day10.junit;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;

@RunWith(JUnit4.class)
public class HousewifeTests {

    private Cat[] cats;
    private Housewife housewife;

    @Before
    public void init() {
        housewife = new Housewife();
        cats = new Cat[3];

        cats[0] = new Cat();
        cats[0].setName("Murzik");

        cats[1] = new Cat();
        cats[1].setName("Murzik1");

        cats[2] = new Cat();
        cats[2].setName("Murzik2");

    }

    @Test
    public void testFeedCats() {
        housewife.feed(cats);
        for (Cat cat : cats) {
           assertFalse(cat.isHungry());
        }
    }

    @Test(expected = CatNotHungryException.class) // what did that Line ??
    public void testCatNotHungryException() {
        cats[1].setName("Murzik3");
        cats[1].setIsHungry(false);

        housewife.feed(cats);
        for (Cat cat : cats) {
           assertFalse(cat.isHungry());
        }
    }
}
