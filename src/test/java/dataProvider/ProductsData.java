package dataProvider;

import org.testng.annotations.DataProvider;

public class ProductsData {
    @DataProvider(name = "userData")
    public static Object[][] getUserData() {
        return new Object[][] { {"demoblazeUser6", "demoblazeUser6"} };
    }
    @DataProvider(name = "productName")
    public static Object[][] getProduct() {
        return new Object[][] {
                {"Samsung galaxy s6"},
                {"Nexus 6"}
        };
    }
    @DataProvider(name = "purchaseData")
    public static Object[][] getPurchaseData() {
        return new Object[][] { {"Nexus 6", "650", "Name", "Country", "City", "Card", "Month", "Year"} };
    }
}