package pageObjects.livetechpanda;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
    public static HomePO getLoginPage(WebDriver driver) {
        return new HomePO(driver);
    }
}
