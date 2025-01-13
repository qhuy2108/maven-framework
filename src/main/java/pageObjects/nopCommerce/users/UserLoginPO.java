package pageObjects.nopCommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerator;
import pageUIs.nopCommerce.users.UserLoginPageUI;

public class UserLoginPO extends BasePage {
    public UserLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton() {
        waitForElementClickAble(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
    }

    public UserHomePO loginToSystem(String emailAddress, String password) {
        enterToEmailTextbox(emailAddress);
        enterToPasswordTextbox(password);
        clickToLoginButton();
        return PageGenerator.getUserHomePage(driver);
    }
}
