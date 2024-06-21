package pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private static WebDriver driver;

    public HomePage(WebDriver driver){
        HomePage.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickSignUpButton() {
        driver.findElement(By.id("signin2")).click();
    }

    public void completeUserDataAndSignUp(String user, String password) {
        driver.findElement(By.id("sign-username")).sendKeys(user);
        driver.findElement(By.id("sign-password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text() = \"Sign up\"]")).click();
    }

    public void clickLoginButton() {
        driver.findElement(By.id("login2")).click();
    }

    public void completeUserDataAndLogin(String user, String password) {
        driver.findElement(By.id("loginusername")).sendKeys(user);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text() = \"Log in\"]")).click();
    }

    public ProductPage clickProduct(String productName) {
        driver.findElement(By.xpath("//a[text() = \"" + productName + "\"]")).click();

        return new ProductPage(driver);
    }

    public CartPage clickCart() {
        driver.findElement(By.id("cartur")).click();

        return new CartPage(driver);
    }
}