package com.jquery.cases;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePO;
import pageObjects.jquery.PageGenerator;

public class Level_11_DataTable extends BaseTest {
    private WebDriver driver;
    private HomePO homePO;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        homePO = PageGenerator.getHomePage(driver);
    }


    //@Test
    public void Table_01_To_Page_Number() {
        // navigate to any page (paging)
        homePO.openPageByNumber("15");
        Assert.assertTrue(homePO.isPageNumberActived("15"));

    }

    //@Test
    public void Table_02_Search() {
        homePO.enterToTextboxByHeaderName("Country", "Nepal");
        sleepInSecond(2);
        Assert.assertTrue(homePO.isRowDataValueDisplayed("322179","Nepal","339162","661364"));
        homePO.refreshCurrentPage(driver);

        homePO.enterToTextboxByHeaderName("Males", "756");
        sleepInSecond(2);
        Assert.assertTrue(homePO.isRowDataValueDisplayed("750","Aruba","756","1504"));
        homePO.refreshCurrentPage(driver);

        homePO.enterToTextboxByHeaderName("Females", "276880");
        sleepInSecond(2);
        Assert.assertTrue(homePO.isRowDataValueDisplayed("276880","Angola","276472","553353"));
        homePO.refreshCurrentPage(driver);

    }

    //@Test
    public void Table_03_Delete() {
        // search truoc xem co khong
        homePO.enterToTextboxByHeaderName("Country", "Argentina");
        sleepInSecond(2);
        // click delete button
        homePO.deleteRowByCountryName("Argentina");
        //homePO.refreshCurrentPage(driver);

        homePO.deleteRowByCountryName("Armenia");
        sleepInSecond(2);
        //homePO.refreshCurrentPage(driver);


        // Edit button
        homePO.editRowByCountryName("Aruba");
        sleepInSecond(2);
    }

    @Test
    public void Table_04_Get_All_Value_Row_Or_Column() {
        homePO.getAllValueAtColumnName("Country");
    }

    //@Test
    public void Table_05_Action_By_Index() {
        homePO.openPageUrl(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePO.clickToLoadButton();

        homePO.enterToTextboxByIndex("4","Contact Person","Automatichuy");
        homePO.enterToTextboxByIndex("2","Company","AutoFC");
        sleepInSecond(3);

        homePO.selectToDropdownByIndex("6","Country","Malaysia");
        homePO.selectToDropdownByIndex("7","Country","Taiwan");
        homePO.selectToDropdownByIndex("8","Country","Japan");

        homePO.checkToCheckboxByIndex("4","NPO?",false);
        homePO.checkToCheckboxByIndex("5","NPO?",false);
        homePO.checkToCheckboxByIndex("6","NPO?",true);

        homePO.clickToIconByIndex("8","Move Up");
        homePO.clickToIconByIndex("8","Remove");
        homePO.clickToIconByIndex("7","Insert");
        homePO.clickToIconByIndex("3","Remove");

    }



    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
