package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private static WebDriver driver;

    ProductPage(WebDriver driver){
        ProductPage.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart")));

        driver.findElement(By.linkText("Add to cart")).click();
    }

}