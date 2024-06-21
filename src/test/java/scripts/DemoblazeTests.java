package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import java.time.Duration;

public class DemoblazeTests {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(dataProvider = "userData", dataProviderClass = dataProvider.ProductsData.class)
    public void signUp(String user, String password) {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUpButton();
        homePage.completeUserDataAndSignUp(user, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(driver.switchTo().alert().getText(), "Sign up successful.");

        driver.switchTo().alert().accept();
    }

    @Test(dataProvider = "userData", dataProviderClass = dataProvider.ProductsData.class)
    public void logIn(String user, String password) {
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();
        homePage.completeUserDataAndLogin(user, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

        Assert.assertEquals(driver.findElement(By.id("nameofuser")).getText(), "Welcome " + user);
    }

    @Test(dataProvider = "productName", dataProviderClass = dataProvider.ProductsData.class)
    public void addProductToCart(String productName){
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = homePage.clickProduct(productName);
        productPage.clickAddToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(driver.switchTo().alert().getText(), "Product added");

        driver.switchTo().alert().accept();

        homePage.clickCart();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//tbody[@id = \"tbodyid\"]//tr[@class = \"success\"]")));

        Assert.assertTrue(driver.findElement(By.xpath("//tbody[@id = \"tbodyid\"]//td[text() = \"" + productName + "\"]"
                )).isDisplayed());
    }

    @Test(dataProvider = "productName", dataProviderClass = dataProvider.ProductsData.class)
    public void deleteProductInCart(String productName) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = homePage.clickProduct(productName);
        productPage.clickAddToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(driver.switchTo().alert().getText(), "Product added");

        driver.switchTo().alert().accept();

        CartPage cartPage = homePage.clickCart();
        cartPage.deleteProduct(productName);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@id = \"totalp\" and not(text())]")));

        Assert.assertEquals(driver.findElements(By.xpath("//tbody[@id = \"tbodyid\"]/tr")).size(), 0);
    }

    @Test(dataProvider = "purchaseData", dataProviderClass = dataProvider.ProductsData.class)
    public void purchaseProduct(String productName, String price, String name, String country, String city, String card,
                String month, String year) {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = homePage.clickProduct(productName);
        productPage.clickAddToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(driver.switchTo().alert().getText(), "Product added");

        driver.switchTo().alert().accept();

        CartPage cartPage = homePage.clickCart();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//tbody[@id = \"tbodyid\"]//tr[@class = \"success\"]")));
        cartPage.clickPlaceOrder();
        cartPage.purchaseProducts(name, country, city, card, month, year);
        String purchaseText = driver.findElement(By.xpath("//p[contains(@class, \"lead text-muted\")]")).getText();

        Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), \"Thank you for your purchase!\")]"
                )).isDisplayed());
        Assert.assertTrue(purchaseText.contains(name), "Name is not correct. Expected: " + name);
        Assert.assertTrue(purchaseText.contains(card), "Card is not correct. Expected: " + card);
        Assert.assertTrue(purchaseText.contains(price), "Price is not correct. Expected: " + price);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}