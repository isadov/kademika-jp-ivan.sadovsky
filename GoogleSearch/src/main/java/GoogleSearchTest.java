import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Scanner;

public class GoogleSearchTest {

    public static WebDriver webDriver = new FirefoxDriver();

    public static void main(String[] args) throws InterruptedException {

        webDriver.navigate().to("http://www.google.com/");

        Scanner scanner = new Scanner(System.in);

        String nameOfSearch = scanner.nextLine();

        GoogleHomePageObjects page = new GoogleHomePageObjects(webDriver);

        page.searchGoogle(nameOfSearch);

        WebElement myDynamicElement = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

        List<WebElement> findElement = webDriver.findElements(By.xpath("//*[@id='rso']//h3/a"));

        for (WebElement webElement : findElement) {

            System.out.println(webElement.getAttribute("href"));


        }

    }
}

