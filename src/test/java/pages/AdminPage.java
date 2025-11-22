package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private By adminTab = By.xpath("//span[text()='Admin']");
    private By tableBody = By.cssSelector("div.oxd-table-body");
    private By rows = By.cssSelector("div.oxd-table-card");
    private By addButton = By.xpath("//button[normalize-space()='Add']");
    // More robust username search input (first input following the Username label)
    private By searchUsername = By.xpath("//label[normalize-space()='Username']/following::input[1]");
    private By searchBtn = By.xpath("//button[normalize-space()='Search']");
    private By resetBtn = By.xpath("//button[normalize-space()='Reset']");
    // First row delete (trash) button
    private By firstRowDeleteBtn = By.xpath("(//i[contains(@class,'bi-trash')])[1]/ancestor::button");
    private By confirmDelete = By.xpath("//button[contains(@class,'oxd-button--label-danger') and .//text()[normalize-space()='Yes, Delete']]");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openAdmin() {
        wait.until(ExpectedConditions.elementToBeClickable(adminTab)).click();
        waitForTableToLoad();
    }

    public int getRecordCount() {
        waitForTableToLoad();

        try {
            // A simpler, more reliable locator for the number next to "Records Found"
            WebElement countElement = driver.findElement(
                    By.xpath("//span[contains(text(),'Records Found')]/following-sibling::span")
            );

            String text = countElement.getText().trim();
            if (text.matches("\\d+")) {
                return Integer.parseInt(text);
            }
        } catch (Exception ignored) {
            // If the label is not found, fallback to row count
        }

        // Fallback: count rows in the table
        return driver.findElements(rows).size();
    }

    public void clickAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void searchUser(String username) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchUsername));
        input.clear();
        input.sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        waitForTableToLoad();
    }

    public void deleteUser() {
        // ensure there is at least one row, then click its delete button
        waitForTableToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(firstRowDeleteBtn)).click();
        // confirm dialog
        wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();
        // wait for table to refresh after deletion
        waitForTableToLoad();
    }

    /**
     * Clears any applied filters on the Users table (e.g., Username) so that counts reflect the full list.
     */
    public void clearFilters() {
        waitForTableToLoad();
        try {
            // Prefer clicking the Reset button if available
            WebElement reset = wait.until(ExpectedConditions.elementToBeClickable(resetBtn));
            reset.click();
            waitForTableToLoad();
            return;
        } catch (Exception ignored) {
        }
        // Fallback: clear the username field and click Search
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchUsername));
            input.clear();
            wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
            waitForTableToLoad();
        } catch (Exception ignored) {
        }
    }

    public void waitForTableToLoad() {

        if (!driver.getCurrentUrl().contains("/admin")) {
            driver.findElement(adminTab).click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(tableBody));
    }
}