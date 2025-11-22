package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {

    LoginPage login = new LoginPage(Hooks.driver);

    @Given("I open the OrangeHRM website")
    public void openSite() {
        Hooks.driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @When("I login with username {string} and password {string}")
    public void login(String user, String pass) {
        login.login(user, pass);
    }
}