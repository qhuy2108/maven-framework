package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePageFactory extends BasePageFactory {
    private WebDriver driver;

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy (how = How.CLASS_NAME, using = "ico-register")
    private WebElement registerLink;

    @FindBy (className = "ico-account")
    private WebElement myAccountLink;

    @FindBy (className = "ico-login")
    private WebElement loginLink;

    public void clickToRegisterLink() {
        waitForElementClickAble(driver , registerLink);
        clickToElement(registerLink);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, myAccountLink);
        return isElementDisplayed(myAccountLink);
    }

    public void clickToMyAccountLink() {
        waitForElementClickAble(driver , myAccountLink);
        clickToElement(myAccountLink);
    }

    public void clickToLoginLink() {
        waitForElementClickAble(driver , loginLink);
        clickToElement(loginLink);
    }
}
