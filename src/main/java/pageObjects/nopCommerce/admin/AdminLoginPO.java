package pageObjects.nopCommerce.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerator;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, email);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
    }


    public AdminDashboardPO clickToLoginButton() {
        waitForElementClickAble(driver, AdminLoginPageUI.BUTTON_LOGIN);
        clickToElement(driver, AdminLoginPageUI.BUTTON_LOGIN);
        return PageGenerator.getAdminDashboardPO(driver);
    }
}



