package commons;

import pageUIs.jquery.UpLoadBasePageUI;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.nopCommerce.users.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    // Tuân theo nguyen tắc của tính đóng gói
    // Hàm BasePage có thể truy cập trực tiếp từ phạm vi Class
    public static BasePage getbBasePage() {
        return new BasePage();
    }


    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public void getPageTittle(WebDriver driver) {
        driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }


    public Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String keysToSend) {
        waitAlertPresence(driver).sendKeys(keysToSend);
    }


    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }



    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator, String ... restParameter) {
        return driver.findElements(getByLocator(castParameter(locator, restParameter)));
    }

    private String castParameter (String locator, String ... restParameter) {
        return String.format(locator, (Object[]) restParameter);
    }

    public By getByLocator(String prefixlocator) {
        By by = null;
        if (prefixlocator.toLowerCase().startsWith("id=")) {
            prefixlocator = prefixlocator.substring(3);
            by = By.id(prefixlocator);
        } else if (prefixlocator.toLowerCase().startsWith("class=")) {
            prefixlocator = prefixlocator.substring(6);
            by = By.className(prefixlocator);
        } else if (prefixlocator.toLowerCase().startsWith("name=")) {
            prefixlocator = prefixlocator.substring(5);
            by = By.name(prefixlocator);
        } else if (prefixlocator.toLowerCase().startsWith("tagname=")) {
            prefixlocator = prefixlocator.substring(8);
            by = By.tagName(prefixlocator);
        } else if (prefixlocator.toLowerCase().startsWith("css=")) {
            prefixlocator = prefixlocator.substring(4);
            by = By.cssSelector(prefixlocator);
        } else if (prefixlocator.toLowerCase().startsWith("xpath=")) {
            prefixlocator = prefixlocator.substring(6);
            by = By.xpath(prefixlocator);
        } else {
            throw new RuntimeException("---------> Your prefixlocator is not support <----------");
        }
        return by;
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
        //Đến topic 76 chuyển sang dung locator prefix
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for(Cookie cookie : cookies){
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restParameter) {
        getElement(driver, castParameter(locator, restParameter)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String keysToSend) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(keysToSend);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String keysToSend, String... restParameter) {
        getElement(driver, castParameter(locator, restParameter)).clear();
        getElement(driver, castParameter(locator, restParameter)).sendKeys(keysToSend);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem) {
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem, String... restParameter) {
        new Select(getElement(driver, castParameter(locator, restParameter))).selectByVisibleText(textItem);
    }

    public String getselectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        driver.findElement(getByLocator(parentLocator)).click();
        sleepInSecond(2);
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));
        sleepInSecond(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public String getElementAttribute(WebDriver driver , String locator , String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver , String locator , String attributeName , String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver , String locator) {
        return getElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver , String locator, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).getText();
    }

    public String getElementCssValue(WebDriver driver , String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver , String locator) {
        return getListElement(driver, locator).size();
    }

    public void checkToCheckbox(WebDriver driver , String locator) {
        WebElement element = getElement(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkToCheckbox(WebDriver driver , String locator, String... restParameter) {
        WebElement element = getElement(driver,  castParameter(locator, restParameter));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver , String locator) {
        WebElement element = getElement(driver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver , String locator, String... restParameter) {
        WebElement element = getElement(driver, castParameter(locator, restParameter));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        try {
            return getElement(driver, locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).isDisplayed();
    }

    public void overrideGlobalTimeout(WebDriver driver, long timeInSecond) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSecond));
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... restParameter) {
        overrideGlobalTimeout(driver,GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListElement(driver, locator);
        overrideGlobalTimeout(driver,GlobalConstants.LONG_TIMEOUT);

        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).isSelected();
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }


    public void switchToFrame(WebDriver driver , String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }
    public void switchToDefaulContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }



    public void doubleClickToElement(WebDriver driver , String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }
    public void hoverToElement(WebDriver driver , String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }
    public void rightClickToElement(WebDriver driver , String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }
    public void dragDropElement(WebDriver driver , String sourceLocator , String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator) , getElement(driver, targetLocator)).perform();
    }
    public void clickAndHoldToElement(WebDriver driver , String locator) {
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }
    public void releaseLeftMouse(WebDriver driver) {
        new Actions(driver).release().perform();
    }
    public void clickToElementByAction(WebDriver driver , String locator) {
        new Actions(driver).click(getElement(driver, locator)).perform();
    }
    public void pressKeyToElement(WebDriver driver , String locator, Keys key) {
        new Actions(driver).sendKeys(getElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver , String locator, Keys key, String... restParameter) {
        new Actions(driver).sendKeys(getElement(driver, castParameter(locator, restParameter)), key).perform();
    }

    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }



    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(driver, locator));
    }



    public void waitForElementVisible(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator,restParameter))));
    }

    public void waitForElementAttribute(WebDriver driver, String locator, String attributeName, String attributeValue, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.attributeToBe(getByLocator(castParameter(locator,restParameter)), attributeName, attributeValue));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator,restParameter))));
    }

    public void waitForElementPresence(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }
    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }
    public void waitForElementClickAble(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickAble(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator,restParameter))));
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        // cắt ký tự xuống dòng \n ở hai đầu chuỗi đi
        fullFileName = fullFileName.trim();

        System.out.println(fullFileName);
        getElement(driver, UpLoadBasePageUI.UPLOAD_FILE).sendKeys(fullFileName);
    }


    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enterToTextboxByID(WebDriver driver, String textboxID, String value) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
        sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxID);
    }

    public void clickToButtonByText(WebDriver driver, String buttonText) {
        waitForElementClickAble(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
    }

    public void clickToRadioByID(WebDriver driver, String radioID) {
        waitForElementClickAble(driver, BasePageUI.RADIO_BY_ID, radioID);
        clickToElement(driver, BasePageUI.RADIO_BY_ID, radioID);
    }

    public String getTextboxValueByID(WebDriver driver, String textboxID) {
        waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
        return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID,"value", textboxID);
    }
}
