package day10.initclasstests;


import day10.reflection.ReflectionUsingConstructorLauncher;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ReflectionUsingConstructorLauncherTests {

    private ReflectionUsingConstructorLauncher reflectionUsingConstructorLauncher;

    @Before
    public void init() {
        reflectionUsingConstructorLauncher = new ReflectionUsingConstructorLauncher();

    }
}
