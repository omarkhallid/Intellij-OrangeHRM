package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AddUserPage;
import pages.AdminPage;

public class AdminSteps {

    AdminPage admin = new AdminPage(Hooks.driver);
    AddUserPage addPage = new AddUserPage(Hooks.driver);

    int initialCount;
    String newUsername = "Autoo" + System.currentTimeMillis();

    @When("I go to Admin section")
    public void goToAdmin() {
        admin.openAdmin();
    }

    @When("I store the number of existing users")
    public void storeCount() {
        initialCount = admin.getRecordCount();
    }

    @When("I add a new user")
    public void addNewUser() {
        admin.clickAdd();
        addPage.fillUserDetails(newUsername, "Pass1234!");
        addPage.save();
        // table synchronization handled inside AdminPage when we next query it
    }

    @Then("the user count should increase by 1")
    public void verifyIncrease() {
        // Ensure we are counting the full list (no filters applied)
        admin.clearFilters();
        Assert.assertEquals(initialCount + 1, admin.getRecordCount());
    }

    @When("I search for the created user")
    public void searchUser() {
        admin.searchUser(newUsername);
    }

    @When("I delete the created user")
    public void deleteUser() {
        admin.deleteUser();
    }

    @Then("the user count should decrease by 1")
    public void verifyDecrease() {
        // Clear any search filters before counting total users again
        admin.clearFilters();
        Assert.assertEquals(initialCount, admin.getRecordCount());
    }
}