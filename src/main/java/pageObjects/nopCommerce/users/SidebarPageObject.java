package pageObjects.nopCommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerator;
import pageUIs.nopCommerce.users.UserSidebarPageUI;

public class SidebarPageObject extends BasePage {
    public SidebarPageObject(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    // sau khi topic 74 thì có thể bỏ WebDriver driver ở các hàm dưới vì đã có ham Contructor ở trên rồi
    // nhưng ở đây giữ lại vì sẽ ảnh hưởng đến level_07_switch

    public UserRewardPointPO openRewardPointPage() {
        waitForElementClickAble(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        clickToElement(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        return  PageGenerator.getUserRewardPointPage(driver);
    }

    public UserCustomerInfoPO openCustomerInfoPage() {
        waitForElementClickAble(driver, UserSidebarPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, UserSidebarPageUI.CUSTOMER_INFO_LINK);
        return  PageGenerator.getUserCustomerInfoPage(driver);
    }

    public UserAddressPO openAddressPage() {
        waitForElementClickAble(driver, UserSidebarPageUI.ADDRESSES_LINK);
        clickToElement(driver, UserSidebarPageUI.ADDRESSES_LINK);
        return PageGenerator.getUserAddressPage(driver);
    }


    public UserOrderPO openOrderPage() {
        waitForElementClickAble(driver, UserSidebarPageUI.ORDER_LINK);
        clickToElement(driver, UserSidebarPageUI.ORDER_LINK);
        return  PageGenerator.getUserOrderPage(driver);
    }


    // chi phu hop cho so luong page it
    public SidebarPageObject openSidebarLinkByPageName(String pageName) {
        waitForElementClickAble(driver, UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);

        switch (pageName) {
            case "Addresses":
                return  PageGenerator.getUserAddressPage(driver);
            case "Reward points":
                return  PageGenerator.getUserRewardPointPage(driver);
            case "Customer info":
                return  PageGenerator.getUserCustomerInfoPage(driver);
            case "Orders":
                return  PageGenerator.getUserOrderPage(driver);
            default:
                throw new RuntimeException("Page name is not valid !");
        }
    }
    //---------------------------------------------------------------------------------------------------


    public void openSidebarByPageNames(String pageName) {
        waitForElementClickAble(driver, UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserSidebarPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
    }


}
