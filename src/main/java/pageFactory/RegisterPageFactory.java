package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageFactory extends BasePageFactory {
    public RegisterPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;

    @FindBy(xpath = "//input[@id= 'gender-male']")
    private WebElement genderMaleRadio;

    @FindBy(id = "FirstName")
    private WebElement firstNameTextbox;

    @FindBy(id = "LastName")
    private WebElement lastNameTextbox;

    @FindBy(name = "DateOfBirthDay")
    private WebElement dayDropdown;

    @FindBy(name = "DateOfBirthMonth")
    private WebElement monthDropdown;

    @FindBy(name = "DateOfBirthYear")
    private WebElement yearDropdown;

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Company")
    private WebElement companyTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmpasswordTextbox;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(xpath = "//div[@class= 'result']")
    private WebElement registerMessageSuccess;

    @FindBy(xpath = "//a[@class= 'ico-logout']")
    private WebElement logoutButton;

    public void clickToMaleRadio() {
        waitForElementClickAble(driver, genderMaleRadio);
        clickToElement(genderMaleRadio);
    }

    public void enterToFirstName(String firstName) {
        waitForElementVisible(driver, firstNameTextbox);
        sendKeyToElement(firstNameTextbox, firstName);
    }

    public void enterToLastName(String lastName) {
        waitForElementVisible(driver, lastNameTextbox);
        sendKeyToElement(lastNameTextbox,lastName);
    }

    public void selectDayDropdown(String day) {
        waitForElementClickAble(driver, dayDropdown);
        selectItemInDropdown(dayDropdown, day);
    }

    public void selectMonthDropdown(String month) {
        waitForElementClickAble(driver, monthDropdown);
        selectItemInDropdown(monthDropdown, month);
    }

    public void selectYearDropdown(String year) {
        waitForElementClickAble(driver, yearDropdown);
        selectItemInDropdown(yearDropdown, year);
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendKeyToElement(emailTextbox, emailAddress);
    }

    public void enterToCompanyTextbox(String companyName) {
        waitForElementVisible(driver, companyTextbox);
        sendKeyToElement(companyTextbox, companyName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(passwordTextbox, password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, confirmpasswordTextbox);
        sendKeyToElement(confirmpasswordTextbox, password);
    }

    public void clickToRegisterButton() {
        waitForElementClickAble(driver, registerButton);
        clickToElement(registerButton);
    }

    public String getRegisteredSuccessMessage() {
        waitForElementVisible(driver, registerMessageSuccess);
        return getElementText(registerMessageSuccess);
    }

    public void clickToLogoutButton() {
        waitForElementClickAble(driver, logoutButton);
        clickToElement(logoutButton);
    }
}
