package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.mk_latn.No;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import java.time.Duration;

public class Login {
    WebDriver chromeDriver;
    FluentWait wait;
    final String url = "https://www.saucedemo.com";

//    Method awal yang dipanggil menggunakan @Before dari io.cucumber.java.
//    Kata kunci : Hook selenium java
    @Before
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver", "Webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Menghindari error forbidden 403
        chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().maximize();
        wait = new FluentWait(chromeDriver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5));

    }
// Method  akhir yang di panggil menggunakan @After dari io.cucumber.java.
// Kata kunci : Hook selenium java
//    @After
    public void tearDown(){
        chromeDriver.close();
        chromeDriver.quit();
    }
    @Given("User open url")
    public void openUrl() {

        //WebDriverManager.chromedriver().setup();
        chromeDriver.get(this.url);
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

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add-to-cart-sauce-labs-backpack")));

        boolean display = chromeDriver.findElement(By.xpath("//img[@src='/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg']")).isDisplayed();
        Assert.assertTrue(display);

    }

    @When("User click add to cart button")
    public void userClickAddToCartButton() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add-to-cart-sauce-labs-backpack")));
        chromeDriver.findElement(By.name("add-to-cart-sauce-labs-backpack")).click();
    }

    @Then("Button remove and cart icon badge should displayed")
    public void buttonRemoveAndCartIconBadgeShouldDisplayed() {
        Assert.assertTrue(chromeDriver.findElement(By.xpath("//button[@name='remove-sauce-labs-backpack']")).isDisplayed());
        Assert.assertTrue(chromeDriver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).isDisplayed());

    }

    @When("User click cart icon")
    public void userClickCartIcon() {
        chromeDriver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    @Then("System should be navigate user to cart page and selected Product must be displayed")
    public void selectedProductMustBeDisplayed() {
        boolean productDisplay = chromeDriver.findElement(By.className("cart_item")).isDisplayed();
        Assert.assertTrue(productDisplay);
    }


    @When("User clicks on the close button next to the error message")
    public void userClicksOnTheCloseButtonNextToTheErrorMessage() {
        String buttonXpath = "//div[@class='error-message-container error']/h3[1]/button[@class='error-button']";
        chromeDriver.findElement(By.xpath(buttonXpath)).click();
    }

    @Then("System should be clearing the input field and removing the error message")
    public void systemShouldBeClearingTheInputFieldAndRemovingTheErrorMessage() {
         String chkValUsnm;
         String chkValPass;
         boolean chkClrMsg;
//      Menangkap error yang muncul dan mengeksekusi proses berikutnya
        try {
            chromeDriver.findElement(By.xpath("//div[@class='error-message-container error']")).isDisplayed();
            chkClrMsg = true;
        } catch (NoSuchElementException e){
            chkClrMsg = false;
        }
        chkValUsnm = chromeDriver.findElement(By.id("user-name")).getAttribute("value");
        chkValPass = chromeDriver.findElement(By.id("password")).getAttribute("value");
        if (chkValUsnm != "" && chkValPass != ""){
            System.out.println("Field Username and password must be Empty !");
        }else{
            System.out.println("As Expected!");
        }

        if (chkClrMsg == false){
            System.out.println("Message As expected");
        }else{
            System.out.println("Message must be removed !");
        }
        Assert.assertTrue(chkValUsnm == "");
        Assert.assertTrue(chkValPass == "");
        Assert.assertFalse(chkClrMsg);

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


    @When("User clicks on the remove button")
    public void userClicksOnTheRemoveButton() {
        chromeDriver.findElement(By.xpath("//button[@name='remove-sauce-labs-backpack']")).click();
    }
//Check cart badge is visible or not
    public boolean checkVisibilityBadge(){
//        Check apakah shopping cart badge ada atau tidak digunakan untuk assertFalse
        try{
            chromeDriver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).isDisplayed();
            return true; // Jika ada mengembalikan nilai true
        } catch (NoSuchElementException e){
            return false; // Jika error atau tidak ada mengembalikan nilai false
        }
    }
    @Then("Add to cart button should be displayed and cart icon badge should be removed")
    public void addToCartButtonShouldBeDisplayedAndCartIconBadgeShouldBeRemoved() {
        boolean add2Cart = chromeDriver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).isDisplayed();
        //boolean badgeVisibility = chromeDriver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).isDisplayed();

        Assert.assertTrue(add2Cart);
        Assert.assertFalse(checkVisibilityBadge());
    }

    @When("User click on remove button")
    public void userClickOnRemoveButton() {
        chromeDriver.findElement(By.name("remove-sauce-labs-backpack")).click();
    }

    @Then("System should remove the item")
    public void systemShouldRemoveTheItem() {
        Assert.assertTrue(chromeDriver.findElement(By.xpath("//div[@class='removed_cart_item']")).isEnabled());
    }

    @Then("System should be navigate user to cart page")
    public void systemShouldBeNavigateUserToCartPage() {
        String currentUrl = chromeDriver.getCurrentUrl();
        Assert.assertEquals("https://www.saucedemo.com/cart.html", currentUrl);
    }

    @When("user click on Continue Shopping button")
    public void userClickOnContinueShoppingButton() {
        chromeDriver.findElement(By.xpath("//button[@id='continue-shopping']")).click();
    }

    @Then("System should be navigate user to inventory page")
    public void systemShouldBeNavigateUserToInventoryPage() {
        String currentUrl = chromeDriver.getCurrentUrl();
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
    }

    @When("User add {int} product to cart")
    public void userAddProductToCart(int amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));
//       Click add to cart button sebanyak amount
        for (int i = 1; i <= amount; i++){
            chromeDriver.findElement(By.xpath("//button[contains(@id,'add-to-cart')]")).click();
        }
    }

    @Then("System should display a remove button according to the selected product and display a cart badge with the corresponding number")
    public void systemShouldDisplayRemoveButtonAndDisplayACartBadgeWithTheNumber() {

        boolean isRemoveDisplay = chromeDriver.findElement(By.xpath("//button[contains(@id,'remove')]")).isDisplayed();
        int countRemoveBtn = chromeDriver.findElements(By.xpath("//button[contains(@id,'remove')]")).size();
//       Check remove button sebanyak amountRemove
        for (int i = 1; i<= countRemoveBtn; i++){
            Assert.assertTrue(isRemoveDisplay);
        }
//        Menghitung jumlah button remove sebagai acuan untuk mencari badge dengan angka yang sesuai
//        Jika banyak pakai findElements
        chromeDriver.findElement(By.xpath("//span[contains(@class,'shopping_cart_badge') and text()="+countRemoveBtn+"]"));
    }

    @When("User remove {string} Product")
    public void userRemoveProduct(String prodName) {
//        Replace String yang mengandung spasi ke "-" dan ubah ke lower case semua dan simpan di xpath
        String replaceName = prodName.replaceAll("\\s","-").toLowerCase();
        chromeDriver.findElement(By.xpath("//button[contains(@id,'"+replaceName+"')]")).click();
    }

    @Then("System should remove {string} product and display a cart badge with the corresponding number")
    public void systemShouldRemoveProductAndDisplayACartBadgeWithTheCorrespondingNumber(String prodName) {
//    Cara yang berbeda untuk mengecek visibility element
//    sebelumnya bisa dilihat di fungsi "checkVisibilityBadge"
//      Replace String yang mengandung spasi ke "-" dan ubah ke lower case semua dan simpan di xpath
        String replaceName = prodName.replaceAll("\\s","-").toLowerCase();
        boolean check;
        try{
            chromeDriver.findElement(By.xpath("//button[contains(@id,'"+replaceName+"')]")).isDisplayed();
        } catch (NoSuchElementException e){
            check = false;// Jika error atau tidak ada mengembalikan nilai false
            // Check pada assertFalse, jika false maka as expected
            Assert.assertFalse(check);
        }
    }
}
