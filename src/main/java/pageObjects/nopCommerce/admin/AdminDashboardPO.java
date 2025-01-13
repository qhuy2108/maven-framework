package pageObjects.nopCommerce.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPO extends BasePage {
    public AdminDashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
}
