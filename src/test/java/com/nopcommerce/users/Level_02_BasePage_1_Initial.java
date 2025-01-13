package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_1_Initial {
    WebDriver driver;
    String emailAddress;
    BasePage basePage;

    @BeforeClass
    public void beforeClass() {
        basePage = new BasePage(); // Initial
        emailAddress = "JohnTerry" + getRandomNumber() + "@gmail.net";

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:/Users/QuangHuy/AppData/Local/Microsoft/Edge/User Data/");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver(edgeOptions);


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().setSize(new Dimension(1600,900));
        driver.get("https://demo.nopcommerce.com/");
    }


    @Test
    public void TC_01_User_Register() {
        basePage.waitForElementClickAble(driver,"//a[@class= 'ico-register']");
        basePage.clickToElement(driver,"//a[@class= 'ico-register']");

        basePage.waitForElementClickAble(driver,"//input[@id= 'gender-male']");
        basePage.clickToElement(driver,"//input[@id= 'gender-male']");

        basePage.sendkeyToElement(driver,"//input[@id= 'FirstName']", "Auto");
        basePage.sendkeyToElement(driver,"//input[@id= 'LastName']", "Testing");

        basePage.selectItemInDropdown(driver, "//select[@name= 'DateOfBirthDay']", "22");
        basePage.selectItemInDropdown(driver, "//select[@name= 'DateOfBirthMonth']", "September");
        basePage.selectItemInDropdown(driver, "//select[@name= 'DateOfBirthYear']", "1988");

        basePage.sendkeyToElement(driver,"//input[@id= 'Email']", emailAddress);
        basePage.sendkeyToElement(driver,"//input[@id= 'Company']", "JiraTesting");
        basePage.sendkeyToElement(driver,"//input[@id= 'Password']", "123456");
        basePage.sendkeyToElement(driver,"//input[@id= 'ConfirmPassword']", "123456");

        basePage.waitForElementClickAble(driver,"//button[@id= 'register-button']");
        basePage.clickToElement(driver,"//button[@id= 'register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class= 'result']"), "Your registration completed");

    }

    @Test
    public void TC_02_User_Login() {
        basePage.waitForElementClickAble(driver,"//a[@class= 'ico-logout']");
        basePage.clickToElement(driver,"//a[@class= 'ico-logout']");

        basePage.waitForElementClickAble(driver,"//a[@class= 'ico-login']");
        basePage.clickToElement(driver,"//a[@class= 'ico-login']");

        basePage.sendkeyToElement(driver,"//input[@id= 'Email']", emailAddress);
        basePage.sendkeyToElement(driver,"//input[@id= 'Password']", "123456");

        basePage.waitForElementClickAble(driver,"//button[@class= 'button-1 login-button']");
        basePage.clickToElement(driver,"//button[@class= 'button-1 login-button']");

        Assert.assertTrue(basePage.isElementDisplayed(driver, "//a[@class= 'ico-account']"));

    }

    @Test
    public void TC_03_MyAccount() {
        basePage.waitForElementClickAble(driver,"//a[@class= 'ico-account']");
        basePage.clickToElement(driver,"//a[@class= 'ico-account']");

        Assert.assertTrue(basePage.isElementSelected(driver, "//input[@id= 'gender-male']"));

        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id= 'FirstName']", "value"), "Auto");
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id= 'LastName']", "value"), "Testing");

        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id= 'LastName']", "value"), "Testing");

        Assert.assertEquals(basePage.getselectedItemInDropdown(driver, "//select[@name= 'DateOfBirthDay']"), "22");
        Assert.assertEquals(basePage.getselectedItemInDropdown(driver, "//select[@name= 'DateOfBirthMonth']"), "September");
        Assert.assertEquals(basePage.getselectedItemInDropdown(driver, "//select[@name= 'DateOfBirthYear']"), "1988");


        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id= 'Email']", "value"), emailAddress);
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@id= 'Company']", "value"), "JiraTesting");
    }


    public int getRandomNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
