package pageObjects.nopCommerce.users;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerator;
import pageUIs.nopCommerce.users.UserHomePageUI;

public class UserHomePO extends BasePage {
    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    } // // khi generate hàm này tự lên đầu vì nó sẽ chạy trước private WebDriver driver ở sau cũng dc

    private WebDriver driver;

    @Step("Open Register page") //Topic 85 Allure Report
    public UserRegisterPO clickToRegisterLink() {
        waitForElementClickAble(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGenerator.getUserRegisterPage(driver);
    }

    @Step("Verify My Account Link is Displayed")
    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
    }

    @Step("Open Customer Info page")
    public UserCustomerInfoPO clickToMyAccountLink() {
        waitForElementClickAble(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return PageGenerator.getUserCustomerInfoPage(driver);
    }

    @Step("Open Login page")
    public UserLoginPO clickToLoginLink() {
        waitForElementClickAble(driver, UserHomePageUI.LOGIN_LINK);
        clickToElement(driver, UserHomePageUI.LOGIN_LINK);
        return PageGenerator.getUserLoginPage(driver);
    }
}
