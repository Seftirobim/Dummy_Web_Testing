package StepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class Login {
    WebDriver chromeDriver;

    @Given("User open url")
    public void openUrl() {
        System.setProperty("webdriver.chrome.driver", "Webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        chromeDriver = new ChromeDriver(options);
        String url = "https://www.saucedemo.com";
        //WebDriverManager.chromedriver().setup();

        chromeDriver.get(url);
    }

    @When("User input valid username {string} and valid password {string}")
    public void inputValidUsernameAndValidPassword(String username, String password) {
        chromeDriver.findElement(By.id("user-name")).sendKeys(username);
        chromeDriver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
    }

    @When("User input invalid username {string} and invalid password {string}")
    public void userInputInvalidUsernameAndInvalidPassword(String username, String password) {
        chromeDriver.findElement(By.id("user-name")).sendKeys(username);
        chromeDriver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
    }

    @And("Click Login button")
    public void clickLoginButton() {
        chromeDriver.findElement(By.name("login-button")).click();
    }


    @Then("User should success login")
    public void shouldSuccessLogin() {
        Assert.assertTrue(chromeDriver.findElement(By.id("inventory_container")).isDisplayed());
    }

    @Then("System should display error message")
    public void shouldDisplayErrorMessage() {
        Assert.assertTrue(chromeDriver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed());

    }

    @Then("System should display locked out message")
    public void shouldDisplayLockedOutMessage() {
        Assert.assertTrue(chromeDriver.findElement(By.xpath("//h3[contains(text(),'Epic sadface: Sorry, this user has been locked out.')]")).isDisplayed());

    }

    @And("The product image must be displayed and be appropriate")
    public void theProductImageMustBeAppropriate() {
        FluentWait wait = new FluentWait(chromeDriver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add-to-cart-sauce-labs-backpack")));

        boolean display = chromeDriver.findElement(By.xpath("//img[@src='/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg']")).isDisplayed();
        Assert.assertTrue(display);

    }

    @When("User click add to cart button")
    public void userClickAddToCartButton() {

        FluentWait wait = new FluentWait(chromeDriver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add-to-cart-sauce-labs-backpack")));
        chromeDriver.findElement(By.name("add-to-cart-sauce-labs-backpack")).click();
    }

    @Then("button remove and cart icon badge should displayed")
    public void buttonRemoveAndCartIconBadgeShouldDisplayed() {
        Assert.assertTrue(chromeDriver.findElement(By.xpath("//button[@name='remove-sauce-labs-backpack']")).isDisplayed());
        Assert.assertTrue(chromeDriver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).isDisplayed());

    }

    @When("User click cart icon")
    public void userClickCartIcon() {
        chromeDriver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    @Then("The Product must be displayed")
    public void theProductMustBeDisplayed() {
        boolean productDisplay = chromeDriver.findElement(By.id("item_4_title_link")).isDisplayed();
        Assert.assertTrue(productDisplay);
    }


    @When("User clicks on the close button next to the error message")
    public void userClicksOnTheCloseButtonNextToTheErrorMessage() {
        String buttonXpath = "//div[@class='error-message-container error']/h3[1]/button[@class='error-button']";
        chromeDriver.findElement(By.xpath(buttonXpath)).click();
    }

    @Then("System should be clearing the input field and removing the error message")
    public void systemShouldBeClearingTheInputFieldAndRemovingTheErrorMessage() {
        boolean clearUsername = false; // inisialisasi bahwa field username tidak kosong saya tandai dengan false
        boolean clearPassword = false; // inisialisasi bahwa field password tidak kosong saya tandai dengan false
        String getValUsername = chromeDriver.findElement(By.id("user-name")).getAttribute("value");
        String getValPassword = chromeDriver.findElement(By.id("password")).getAttribute("value");
        boolean clearMessage  = chromeDriver.findElement(By.xpath("//div[@class='error-message-container']")).isDisplayed();
        if (getValUsername == "" && getValPassword == ""){
            clearUsername = true; // Jika value kosong maka ubah variable clearUsername ke true
            clearPassword = true; // Jika value kosong maka ubah variable clearPassword ke true
        }else{
            System.out.println("Fail!  System must be clearing all input field ");
        }

        Assert.assertTrue(clearUsername);
        Assert.assertTrue(clearPassword);
        Assert.assertTrue(clearMessage);
    }

    @Then("System should display error message {string}")
    public void systemShouldDisplayErrorMessage(String message) {
        boolean xpathErrorMessage = chromeDriver.findElement(By.xpath("//div[@class='error-message-container error']/h3[1][text()='"+message+"']")).isDisplayed();
        Assert.assertTrue(xpathErrorMessage);
    }

    @When("User input empty username and valid password {string}")
    public void userInputEmptyUsernameAndValidPassword(String password) {
        chromeDriver.findElement(By.id("password")).sendKeys(password);
    }

    @When("User input valid username {string} and empty password")
    public void userInputValidUsernameAndEmptyPassword(String username) {
        chromeDriver.findElement(By.id("user-name")).sendKeys(username);
    }


}
