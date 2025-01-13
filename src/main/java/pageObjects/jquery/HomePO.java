package pageObjects.jquery;

import commons.BasePage;
import pageUIs.jquery.HomePageUI;
import pageUIs.jquery.UpLoadBasePageUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class HomePO extends BasePage {
    public HomePO(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void openPageByNumber(String pageNumber) {
        waitForElementClickAble(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        clickToElement(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        sleepInSecond(2);
    }

    public boolean isPageNumberActived(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        return getElementAttribute(driver, HomePageUI.DYNAMIC_PAGE_LINK, "class", pageNumber).endsWith("active");
    }

    public void enterToTextboxByHeaderName(String headerName, String keysToSend) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, headerName);
        sendkeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, keysToSend, headerName);
        pressKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, Keys.ENTER, headerName);
    }

    public boolean isRowDataValueDisplayed(String females, String country, String males, String total) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_DATA_ROW, females, country, males, total);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_DATA_ROW, females, country, males, total);
    }

    public void deleteRowByCountryName(String countryName) {
        waitForElementClickAble(driver, HomePageUI.DYNAMIC_DELETE_BUTTON_BY_COUNTRY, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_DELETE_BUTTON_BY_COUNTRY, countryName);
        sleepInSecond(2);
    }

    public void editRowByCountryName(String countryName) {
        waitForElementClickAble(driver, HomePageUI.DYNAMIC_EDIT_BUTTON_BY_COUNTRY, countryName);
        clickToElement(driver, HomePageUI.DYNAMIC_EDIT_BUTTON_BY_COUNTRY, countryName);
        sleepInSecond(2);
    }

    public List<String> getAllValueAtColumnName(String columnName) {
        int columnIndexNumber = getListElement(driver, HomePageUI.COLUMN_BY_NAME_WEB1, columnName).size() + 1;
        String columnIndex = String.valueOf(columnIndexNumber);

        List<WebElement> allValueAtColumn = getListElement(driver, HomePageUI.ALL_VALUE_BY_COLUMN_INDEX, columnIndex);
        List<String> allTextValue = new ArrayList<String>();
        for (WebElement element : allValueAtColumn) {
            allTextValue.add(element.getText());
        }
        out.println(allTextValue);
        return allTextValue;
    }

// -----------------------------------------------------------------------

    public void clickToLoadButton() {
        waitForElementClickAble(driver, HomePageUI.LOAD_BUTTON);
        clickToElement(driver, HomePageUI.LOAD_BUTTON);
    }

    public void enterToTextboxByIndex(String rowIndex, String columnName, String keysToSend) {
        // lay gia tri index cua column
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_COLUMN_BY_NAME, columnName).size() + 1;

        //convert qua dang text truoc
        String columnIndex = String.valueOf(columnIndexNumber);

        sendkeyToElement(driver,HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, keysToSend, rowIndex, columnIndex);
    }

    public void selectToDropdownByIndex(String rowIndex, String columnName, String valueToSelect) {
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_COLUMN_BY_NAME, columnName).size() + 1;

        String columnIndex = String.valueOf(columnIndexNumber);

        selectItemInDropdown(driver,HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_AND_COLUMN_INDEX, valueToSelect, rowIndex, columnIndex);
    }

    public void checkToCheckboxByIndex(String rowIndex, String columnName, boolean checkOrUncheck) {
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_COLUMN_BY_NAME, columnName).size() + 1;
        String columnIndex = String.valueOf(columnIndexNumber);

        if (checkOrUncheck){
            checkToCheckbox(driver,HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX,rowIndex, columnIndex);
        } else {
            uncheckToCheckbox(driver,HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX,rowIndex, columnIndex);
        }
    }

    public void clickToIconByIndex(String rowIndex, String iconName) {
        waitForElementClickAble(driver, HomePageUI.DYNAMIC_ICON_NAME_BY_ROW_INDEX, rowIndex, iconName);
        clickToElement(driver, HomePageUI.DYNAMIC_ICON_NAME_BY_ROW_INDEX, rowIndex, iconName);
    }
    
    // UpLoad ---------------------------------------------------------------------------------------------------------
    
    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, UpLoadBasePageUI.FILE_NAME_LOADED, fileName);
        return isElementDisplayed(driver, UpLoadBasePageUI.FILE_NAME_LOADED, fileName);
    }

    public void clickToUploadButton() {
        List<WebElement> starButtons = getListElement(driver, UpLoadBasePageUI.STAR_UPLOAD_BUTTON);

        for (WebElement starButton : starButtons) {
            starButton.click();
            sleepInSecond(3);
        }
    }

    public boolean isFileUpLoaded(String fileName) {
        waitForElementVisible(driver, UpLoadBasePageUI.FILE_UPLOADED_SUCCESS_BY_NAME, fileName);
        return isElementDisplayed(driver, UpLoadBasePageUI.FILE_UPLOADED_SUCCESS_BY_NAME, fileName);
    }
}
