package com.nopcommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Pass_CluodFlare_Turnstile_By_BrowserProfile {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        /*// Brave
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");
        options.addArguments("--user-data-dir=C:/Users/Qhuy/AppData/Local/BraveSoftware/Brave-Browser/User Data/");
        options.addArguments("--profile-directory=Profile 1");
        driver = new ChromeDriver(options);*/

        /*ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/Users/Qhuy/AppData/Local/Google/Chrome/User Data/");
        options.addArguments("--profile-directory=Profile 1");
        driver = new ChromeDriver(options);*/

        /*EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:/Users/Qhuy/AppData/Local/Microsoft/Edge/User Data/");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver(edgeOptions);*/

        String profilePath = "C:/Users/Quanghuy/AppData/Roaming/Mozilla/Firefox/Profiles/HuyTest";  // Sửa lại đường dẫn cho đúng
        FirefoxProfile profile = new FirefoxProfile(new java.io.File(profilePath));
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        driver = new FirefoxDriver(firefoxOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NopCommerce() {

        driver.get("https://demo.nopcommerce.com/");
        sleepInSecond();
        driver.findElement(By.className("ico-register")).click();
        driver.findElement(By.cssSelector("input#gender-male")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Auto");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Testing");

        new Select(driver.findElement(By.cssSelector("select[name= 'DateOfBirthDay']"))).selectByVisibleText("22");
        new Select(driver.findElement(By.cssSelector("select[name= 'DateOfBirthMonth']"))).selectByVisibleText("September");
        new Select(driver.findElement(By.cssSelector("select[name= 'DateOfBirthYear']"))).selectByVisibleText("1988");

        driver.findElement(By.cssSelector("input#Email")).sendKeys("emailAddress@email.com");
        driver.findElement(By.cssSelector("input#Company")).sendKeys("JiraTesting");

        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
    public void sleepInSecond() {
        try {
            Random rand = new Random();
            int sleepTime = 1000 + rand.nextInt(3000);  // Random độ trễ từ 2s đến 5s (2000 ms đến 5000 ms)
            Thread.sleep(sleepTime);  // Dừng thực thi trong khoảng thời gian ngẫu nhiên
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
