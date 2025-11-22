package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        // I use Selenium Manager to handle driver management
        try {
            driver = new EdgeDriver();
        } catch (Throwable t) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }
}