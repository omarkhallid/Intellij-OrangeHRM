OrangeHRM UI Tests (Cucumber + Selenium)

This project contains a simple Cucumber/Selenium test suite for the OrangeHRM demo site.
The main flow logs in, goes to the Admin page, adds a new user, checks that the
record count increases, then Search for the new added User then deletes the
user and confirms the count goes back down.
-----------------------------------------------------------------------
Tech Stack

Java 21

Maven

Selenium WebDriver 4

Cucumber 7 + JUnit

Microsoft Edge (driver auto-managed)

-----------------------------------------------------------------------
Requirements

JDK 21

Maven 3.8+

Microsoft Edge

Selenium Manager handles EdgeDriver automatically.

-----------------------------------------------------------------------
Report:

Open target/report.html

-----------------------------------------------------------------------
Credentials

Username: Admin

Password: admin123

-----------------------------------------------------------------------
Project Structure
features/ → Gherkin scenarios
pages/ → Page Objects
stepDefinitions/ → Steps + Hooks
testRunner/ → Cucumber JUnit runner
pom.xml → Dependencies
