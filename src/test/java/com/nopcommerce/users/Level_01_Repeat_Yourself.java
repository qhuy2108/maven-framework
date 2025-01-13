package com.nopcommerce.users;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_01_Repeat_Yourself {
    WebDriver driver;
    String emailAddress;

    @BeforeClass
    public void beforeClass() {

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
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("input#gender-male")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Auto");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Testing");

        new Select(driver.findElement(By.cssSelector("select[name= 'DateOfBirthDay']"))).selectByVisibleText("22");
        new Select(driver.findElement(By.cssSelector("select[name= 'DateOfBirthMonth']"))).selectByVisibleText("September");
        new Select(driver.findElement(By.cssSelector("select[name= 'DateOfBirthYear']"))).selectByVisibleText("1988");

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Company")).sendKeys("JiraTesting");

        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");


    }

    @Test
    public void TC_02_User_Login() {
        driver.findElement(By.cssSelector("a.ico-logout")).click();
        driver.findElement(By.cssSelector("a.ico-login")).click();

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button.login-button")).click();

        // verify my account link displayed
        Assert.assertTrue(driver.findElement(By.cssSelector("a.ico-account")).isDisplayed());

    }

    @Test
    public void TC_03_MyAccount() {
        driver.findElement(By.cssSelector("a.ico-account")).click();

        // verify
        Assert.assertTrue(driver.findElement(By.cssSelector("input#gender-male")).isSelected());
        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),"Auto" );
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"),"Testing" );

        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name= 'DateOfBirthDay']"))).getFirstSelectedOption().getText(), "22");
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name= 'DateOfBirthMonth']"))).getFirstSelectedOption().getText(), "September");
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name= 'DateOfBirthYear']"))).getFirstSelectedOption().getText(), "1988");

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAddress);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), "JiraTesting");
    }


    public int getRandomNumber() {
        return new Random().nextInt(9999);
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

}
