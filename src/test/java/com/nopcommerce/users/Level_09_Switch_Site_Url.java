package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.admin.AdminDashboardPO;
import pageObjects.nopCommerce.admin.AdminLoginPO;
import pageObjects.nopCommerce.users.UserHomePO;
import pageObjects.nopCommerce.users.UserLoginPO;
import pageObjects.nopCommerce.users.UserRegisterPO;

public class Level_09_Switch_Site_Url extends BaseTest {

    private WebDriver driver;
    private UserHomePO userhomePage;
    private UserRegisterPO userregisterPage;
    private UserLoginPO userloginPage;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;

    String emailAddress, firstName, lastName, password, companyName, day, month, year;
    String userUrlValue , adminUrlValue , adminEmail , adminPassword;


    @Parameters({"browser" , "userUrl" , "adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String userUrl, String adminUrl) {
        userUrlValue = userUrl;
        adminUrlValue = adminUrl;
        adminEmail = "admin@yourstore.com";
        adminPassword = "admin";

        driver = getBrowserDriver(browserName, userUrlValue);

        emailAddress = "Kaka" + getRandomNumber() + "@gmail.net";
        firstName = "Ricardo";
        lastName = "Kaka";
        day = "18";
        month = "September";
        year = "2022";
        password = "123456";
        companyName = "Milan";

        userhomePage = PageGenerator.getUserHomePage(driver);

        // Pre-Condition ---------------------
        userregisterPage =  userhomePage.clickToRegisterLink();

        userregisterPage.clickToMaleRadio();
        userregisterPage.enterToFirstName(firstName);
        userregisterPage.enterToLastName(lastName);
        userregisterPage.selectDayDropdown(day);
        userregisterPage.selectMonthDropdown(month);
        userregisterPage.selectYearDropdown(year);

        userregisterPage.enterToEmailTextbox(emailAddress);
        userregisterPage.enterToCompanyTextbox(companyName);
        userregisterPage.enterToPasswordTextbox(password);
        userregisterPage.enterToConfirmPasswordTextbox(password);
        userregisterPage.clickToRegisterButton();

        Assert.assertEquals(userregisterPage.getRegisteredSuccessMessage() , "Your registration completed");
    }


    @Test
    public void User_01_User_Site_To_Admin_Site() {
        userregisterPage.clickToLogoutButton(); // quay ra homePage

        userloginPage = userhomePage.clickToLoginLink(); // đang ở trang homePage gọi ra loginPage

        userloginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(userhomePage.isMyAccountLinkDisplayed());

        // step 1 de order 1 product nao do

        //Qua trang admin de verify  / approve cai order o tren voi quyen admin

        userhomePage.openPageUrl(driver, adminUrlValue);
        adminLoginPage = PageGenerator.getAdminLoginPO(driver);

        adminLoginPage.enterToEmailTextbox(adminEmail);
        adminLoginPage.enterToPasswordTextbox(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();
        sleepInSecond(3);

    }

    @Test
    public void User_02_Admin_Site_To_User_Site() {
        // Vao thao tac .....

        adminDashboardPage.openPageUrl(driver, userUrlValue);
        userhomePage = PageGenerator.getUserHomePage(driver);

        // Action tuy y ....

    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
