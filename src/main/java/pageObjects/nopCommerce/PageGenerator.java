package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.admin.AdminDashboardPO;
import pageObjects.nopCommerce.admin.AdminLoginPO;
import pageObjects.nopCommerce.users.*;

public class PageGenerator {
    public static UserHomePO getUserHomePage(WebDriver driver) {
        return new UserHomePO(driver);
    }
    public static UserLoginPO getUserLoginPage(WebDriver driver) {
        return new UserLoginPO(driver);
    }
    public static UserRegisterPO getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPO(driver);
    }
    public static UserCustomerInfoPO getUserCustomerInfoPage(WebDriver driver) {
        return new UserCustomerInfoPO(driver);
    }

    public static UserAddressPO getUserAddressPage(WebDriver driver) {
        return new UserAddressPO(driver);
    }
    public static UserRewardPointPO getUserRewardPointPage(WebDriver driver) {
        return new UserRewardPointPO(driver);
    }
    public static UserOrderPO getUserOrderPage(WebDriver driver) {
        return new UserOrderPO(driver);
    }

    public static AdminDashboardPO getAdminDashboardPO(WebDriver driver) {
        return new AdminDashboardPO(driver);
    }

    public static AdminLoginPO getAdminLoginPO(WebDriver driver) {
        return new AdminLoginPO(driver);
    }
}
