package pageObjects.nopCommerce.users;

import org.openqa.selenium.WebDriver;

public class UserOrderPO extends SidebarPageObject {
    public UserOrderPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    } // // khi generate hàm này tự lên đầu vì nó sẽ chạy trước private WebDriver driver ở sau cũng dc

    private WebDriver driver;


}
