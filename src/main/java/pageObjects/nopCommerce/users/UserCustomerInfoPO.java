package pageObjects.nopCommerce.users;

import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.users.UserCustomerInfoPageUI;

public class UserCustomerInfoPO extends SidebarPageObject {

    private WebDriver driver;

    public UserCustomerInfoPO(WebDriver driver) {
        super(driver); // topic 74
        this.driver = driver;
    } // khi generate hàm này tự lên đầu vì nó sẽ chạy trước private WebDriver driver ở sau cũng dc


    public boolean isGenderMaleSelected() {
        waitForElementSelected(driver, UserCustomerInfoPageUI.GENDER_MALE_RADIO);
        return isElementSelected(driver, UserCustomerInfoPageUI.GENDER_MALE_RADIO);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX,"value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX,"value");
    }

    public String getDayDropdownSelectedValue() {
        waitForElementClickAble(driver, UserCustomerInfoPageUI.DAY_DROP_DOWN);
        return getselectedItemInDropdown(driver, UserCustomerInfoPageUI.DAY_DROP_DOWN);
    }

    public String getMonthDropdownSelectedValue() {
        waitForElementClickAble(driver, UserCustomerInfoPageUI.MONTH_DROP_DOWN);
        return getselectedItemInDropdown(driver, UserCustomerInfoPageUI.MONTH_DROP_DOWN);
    }

    public String getYearDropdownSelectedValue() {
        waitForElementClickAble(driver, UserCustomerInfoPageUI.YEAR_DROP_DOWN);
        return getselectedItemInDropdown(driver, UserCustomerInfoPageUI.YEAR_DROP_DOWN);
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX,"value");
    }

    public String getCompanyTextboxValue() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.COMPANY_NAME_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.COMPANY_NAME_TEXTBOX,"value");
    }


}
