package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriverWait wait;
    private final WebDriver driver;

    private By username = By.cssSelector("input[name='username']");
    private By password = By.cssSelector("input[name='password']");
    private By loginBtn = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Explicit-wait until locator is clickable
    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public void login(String userParameter, String passParameter) {
        WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        user.clear();
        user.sendKeys(userParameter);
        WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        pass.clear();
        pass.sendKeys(passParameter);
        waitForClickable(loginBtn).click();

    }


}