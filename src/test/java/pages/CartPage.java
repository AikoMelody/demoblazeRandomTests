package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private static WebDriver driver;

    CartPage(WebDriver driver){
        CartPage.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void deleteProduct(String productName){
        driver.findElement(By.xpath(
                "//tbody[@id = \"tbodyid\"]//td[text() = \"" + productName + "\"]/..//td/a[text() = \"Delete\"]"))
                .click();
    }

    public void clickPlaceOrder() {
        driver.findElement(By.xpath("//button[text() = \"Place Order\"]")).click();
    }

    public void purchaseProducts(String name, String country, String city, String card, String month, String year) {
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("card")).sendKeys(card);
        driver.findElement(By.id("month")).sendKeys(month);
        driver.findElement(By.id("year")).sendKeys(year);
        driver.findElement(By.xpath("//button[text() = \"Purchase\"]")).click();
    }
}