package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddUserPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Fill the OrangeHRM Add User form
    private final By roleDropdown = By.xpath("//label[normalize-space()='User Role']/following::div[contains(@class,'oxd-select-text')][1]");
    private final By adminRole = By.xpath("//div[@role='listbox']//span[normalize-space()='Admin']");

    private final By statusDropdown = By.xpath("//label[normalize-space()='Status']/following::div[contains(@class,'oxd-select-text')][1]");
    private final By enabledStatus = By.xpath("//div[@role='listbox']//span[normalize-space()='Enabled']");

    private final By employeeName = By.xpath("//label[normalize-space()='Employee Name']/following::input[1]");
    private final By firstEmployeeSuggestion = By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-autocomplete-option')][1]");
    private final By username = By.xpath("//label[normalize-space()='Username']/following::input[1]");
    private final By password = By.xpath("//label[normalize-space()='Password']/following::input[1]");
    private final By confirmPassword = By.xpath("//label[normalize-space()='Confirm Password']/following::input[1]");
    private final By saveBtn = By.xpath("//button[normalize-space()='Save']");
    // Marker for Admin users table page after saving
    private final By usersTableBody = By.cssSelector("div.oxd-table-body");

    public AddUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void fillUserDetails(String user, String pass) {
        // Select User Role = Admin
        wait.until(ExpectedConditions.elementToBeClickable(roleDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(adminRole)).click();

        // Select Status = Enabled
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(enabledStatus)).click();

        // Choose an existing employee from autocomplete suggestions (type partial and pick first option)
        WebElement empInput = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName));
        empInput.clear();
        // Type only the letter 'p' to trigger suggestions
        empInput.sendKeys("p");
        // wait 2 seconds to allow the dropdown suggestions to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        // Then choose the first option from the dropdown
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstEmployeeSuggestion)).click();

        // Fill username and passwords
        WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        userInput.clear();
        userInput.sendKeys(user);

        WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        passInput.clear();
        passInput.sendKeys(pass);

        WebElement confirmInput = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword));
        confirmInput.clear();
        confirmInput.sendKeys(pass);
    }

    public void save() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        // After clicking save, the app navigates back to the Users list. Wait for that state.
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/admin/viewSystemUsers"),
                    ExpectedConditions.visibilityOfElementLocated(usersTableBody)
            ));
        } catch (Exception e) {
        }
    }
}