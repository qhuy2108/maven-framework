package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.users.*;

public class Level_13_Verify extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInfoPage;

    //topic 74
    private UserRewardPointPO rewardPointPage;
    private UserOrderPO orderPage;
    private UserAddressPO addressPage;
    // -------------------------------------------------------------------------------------

    String emailAddress, firstName, lastName, password, companyName, day, month, year;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);

        emailAddress = "Kaka" + getRandomNumber() + "@gmail.net";
        firstName = "Ricardo";
        lastName = "Kaka";
        day = "18";
        month = "September";
        year = "2022";
        password = "123456";
        companyName = "Milan";

        homePage = PageGenerator.getUserHomePage(driver);
    }


    @Test
    public void User_01_Register() {
        registerPage =  homePage.clickToRegisterLink();

        //Assert 01 ==> co tinh de false
        VerifyEquals(registerPage.getRegisterPageTitle(), "REGISTER");

        registerPage.clickToMaleRadio();
        registerPage.enterToFirstName(firstName);
        registerPage.enterToLastName(lastName);
        registerPage.selectDayDropdown(day);
        registerPage.selectMonthDropdown(month);
        registerPage.selectYearDropdown(year);

        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToCompanyTextbox(companyName);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();

        // False
        VerifyEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed !!!! ");

    }

    @Test
    public void User_02_Login() {
        registerPage.clickToLogoutButton(); // quay ra homePage

        // đang ở trang homePage gọi ra loginPage
        loginPage = homePage.clickToLoginLink();

        loginPage.loginToSystem(emailAddress, password);

        VerifyTrue(homePage.isMyAccountLinkDisplayed());


    }

    @Test
    public void User_03_MyAccount() {
        customerInfoPage = homePage.clickToMyAccountLink();

        VerifyTrue(customerInfoPage.isGenderMaleSelected());
        VerifyEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        VerifyEquals(customerInfoPage.getLastNameTextboxValue(), lastName);

        VerifyEquals(customerInfoPage.getDayDropdownSelectedValue(), day);
        VerifyEquals(customerInfoPage.getMonthDropdownSelectedValue(), month);
        VerifyEquals(customerInfoPage.getYearDropdownSelectedValue(),year);
        VerifyEquals(customerInfoPage.getCompanyTextboxValue(),companyName);
        VerifyEquals(customerInfoPage.getEmailTextboxValue(),emailAddress);

    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
