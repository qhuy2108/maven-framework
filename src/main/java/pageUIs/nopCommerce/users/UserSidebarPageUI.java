package pageUIs.nopCommerce.users;

// [Online 29] - Topic 73 (Framework 21 - Switch Page Object) BasePageUI bắt đầu tạo từ bài này ----------------------------------
// Topic 74 đổi method
// Topic 76 đổi sang prefix locator

public class UserSidebarPageUI {
    public static final String ADDRESSES_LINK = "xpath=//div[contains(@class, 'block-account-navigation')]//a[text()= 'Addresses']";
    public static final String REWARD_POINT_LINK = "xpath=//div[contains(@class, 'block-account-navigation')]//a[text()= 'Reward points']";
    public static final String CUSTOMER_INFO_LINK = "xpath=//div[contains(@class, 'block-account-navigation')]//a[text()= 'Customer info']";
    public static final String ORDER_LINK = "xpath=//div[contains(@class, 'block-account-navigation')]//a[text()= 'Orders']";

    // topic 77 dynamic locator
    public static final String DYNAMIC_LINK_BY_PAGE_NAME = "xpath=//div[contains(@class, 'block-account-navigation')]//a[text()= '%s']";
}
