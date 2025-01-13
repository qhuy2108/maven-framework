package pageObjects.livetechpanda;

import commons.BasePage;
import pageUIs.livetechpanda.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePO extends BasePage {
    WebDriver driver;

    public HomePO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToHeaderAccountButton() {
        waitForElementClickAble(driver, HomePageUI.ACCOUNT_BUTTON);
        clickToElement(driver, HomePageUI.ACCOUNT_BUTTON);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
    }

    public boolean isNotMyAccountLinkDisplayed() {
        waitForElementInvisible(driver, HomePageUI.MY_ACCOUNT_LINK);
        return isElementUndisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
    }

}
