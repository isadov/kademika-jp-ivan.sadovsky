import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        WebDriver webDriver = new FirefoxDriver();

        Scanner scanner = new Scanner(System.in);

        String nameOfSearch = scanner.nextLine();

        webDriver.get("https://www.google.com/");

        WebElement element = webDriver.findElement(By.name("q"));

        element.sendKeys(nameOfSearch);

        element.submit();

        WebElement myDynamicElement = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

        List<WebElement> findElement = webDriver.findElements(By.xpath("//*[@id='rso']//h3/a"));


        for (WebElement webElement : findElement) {

            System.out.println(webElement.getText());
            System.out.println(webElement.getAttribute("href"));
            System.out.println("--------------------------");

        }
    }
}

