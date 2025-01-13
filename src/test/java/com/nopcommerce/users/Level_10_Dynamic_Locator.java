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

public class Level_10_Dynamic_Locator extends BaseTest {
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

        Assert.assertEquals(registerPage.getRegisteredSuccessMessage() , "Your registration completed");

    }

    @Test
    public void User_02_Login() {
        registerPage.clickToLogoutButton(); // quay ra homePage

        // đang ở trang homePage gọi ra loginPage
        loginPage = homePage.clickToLoginLink();

        loginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());


    }

    @Test
    public void User_03_MyAccount() {
        customerInfoPage = homePage.clickToMyAccountLink();

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);

        Assert.assertEquals(customerInfoPage.getDayDropdownSelectedValue(), day);
        Assert.assertEquals(customerInfoPage.getMonthDropdownSelectedValue(), month);
        Assert.assertEquals(customerInfoPage.getYearDropdownSelectedValue(),year);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(),companyName);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(),emailAddress);

    }

    //@Test
    public void User_04_Dynamic_Locator() {
        addressPage = (UserAddressPO) customerInfoPage.openSidebarLinkByPageName("Addresses");

        rewardPointPage = (UserRewardPointPO) addressPage.openSidebarLinkByPageName("Reward points");

        orderPage = (UserOrderPO) rewardPointPage.openSidebarLinkByPageName("Orders");

        addressPage = (UserAddressPO) orderPage.openSidebarLinkByPageName("Addresses");

        customerInfoPage = (UserCustomerInfoPO) addressPage.openSidebarLinkByPageName("Customer info");

        rewardPointPage = (UserRewardPointPO) customerInfoPage.openSidebarLinkByPageName("Reward points");

        addressPage = (UserAddressPO) rewardPointPage.openSidebarLinkByPageName("Addresses");

    }

    @Test
    public void User_05_Dynamic_Locator_C2() {

        addressPage.openSidebarByPageNames("Reward points");
        rewardPointPage = PageGenerator.getUserRewardPointPage(driver);
        sleepInSecond(2);

        rewardPointPage.openSidebarByPageNames("Orders");
        orderPage = PageGenerator.getUserOrderPage(driver);
        sleepInSecond(2);

        orderPage.openSidebarByPageNames("Addresses");
        addressPage = PageGenerator.getUserAddressPage(driver);
        sleepInSecond(2);

        addressPage.openSidebarByPageNames("Customer info");
        customerInfoPage = PageGenerator.getUserCustomerInfoPage(driver);
        sleepInSecond(2);

        customerInfoPage.openSidebarByPageNames("Reward points");
        rewardPointPage = PageGenerator.getUserRewardPointPage(driver);
        sleepInSecond(2);

        rewardPointPage.openSidebarByPageNames("Addresses");
        addressPage = PageGenerator.getUserAddressPage(driver);
        sleepInSecond(2);

    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
