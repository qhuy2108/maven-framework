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

public class Level_02_BasePage_3_Inheritance extends BasePage{
    WebDriver driver;
    String emailAddress;
    //BasePage basePage; Sau khi đã kế thừa thì không cần khai báo đối tượng

    @BeforeClass
    public void beforeClass() {
        // Không cần nữa : basePage = getbBasePage(); // Khởi tạo tương đương với "basePage = new BasePage();"
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
        waitForElementClickAble(driver,"//a[@class= 'ico-register']");
        clickToElement(driver,"//a[@class= 'ico-register']");

        waitForElementClickAble(driver,"//input[@id= 'gender-male']");
        clickToElement(driver,"//input[@id= 'gender-male']");

        sendkeyToElement(driver,"//input[@id= 'FirstName']", "Auto");
        sendkeyToElement(driver,"//input[@id= 'LastName']", "Testing");

        selectItemInDropdown(driver, "//select[@name= 'DateOfBirthDay']", "22");
        selectItemInDropdown(driver, "//select[@name= 'DateOfBirthMonth']", "September");
        selectItemInDropdown(driver, "//select[@name= 'DateOfBirthYear']", "1988");

        sendkeyToElement(driver,"//input[@id= 'Email']", emailAddress);
        sendkeyToElement(driver,"//input[@id= 'Company']", "JiraTesting");
        sendkeyToElement(driver,"//input[@id= 'Password']", "123456");
        sendkeyToElement(driver,"//input[@id= 'ConfirmPassword']", "123456");

        waitForElementClickAble(driver,"//button[@id= 'register-button']");
        clickToElement(driver,"//button[@id= 'register-button']");

        Assert.assertEquals(getElementText(driver,"//div[@class= 'result']"), "Your registration completed");

    }

    @Test
    public void TC_02_User_Login() {
        waitForElementClickAble(driver,"//a[@class= 'ico-logout']");
        clickToElement(driver,"//a[@class= 'ico-logout']");

        waitForElementClickAble(driver,"//a[@class= 'ico-login']");
        clickToElement(driver,"//a[@class= 'ico-login']");

        sendkeyToElement(driver,"//input[@id= 'Email']", emailAddress);
        sendkeyToElement(driver,"//input[@id= 'Password']", "123456");

        waitForElementClickAble(driver,"//button[@class= 'button-1 login-button']");
        clickToElement(driver,"//button[@class= 'button-1 login-button']");

        Assert.assertTrue(isElementDisplayed(driver, "//a[@class= 'ico-account']"));

    }

    @Test
    public void TC_03_MyAccount() {
        waitForElementClickAble(driver,"//a[@class= 'ico-account']");
        clickToElement(driver,"//a[@class= 'ico-account']");

        Assert.assertTrue(isElementSelected(driver, "//input[@id= 'gender-male']"));

        Assert.assertEquals(getElementAttribute(driver, "//input[@id= 'FirstName']", "value"), "Auto");
        Assert.assertEquals(getElementAttribute(driver, "//input[@id= 'LastName']", "value"), "Testing");

        Assert.assertEquals(getElementAttribute(driver, "//input[@id= 'LastName']", "value"), "Testing");

        Assert.assertEquals(getselectedItemInDropdown(driver, "//select[@name= 'DateOfBirthDay']"), "22");
        Assert.assertEquals(getselectedItemInDropdown(driver, "//select[@name= 'DateOfBirthMonth']"), "September");
        Assert.assertEquals(getselectedItemInDropdown(driver, "//select[@name= 'DateOfBirthYear']"), "1988");


        Assert.assertEquals(getElementAttribute(driver, "//input[@id= 'Email']", "value"), emailAddress);
        Assert.assertEquals(getElementAttribute(driver, "//input[@id= 'Company']", "value"), "JiraTesting");
    }


    public int getRandomNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
