package pageObjects.nopCommerce.users;

import org.openqa.selenium.WebDriver;

public class UserRewardPointPO extends SidebarPageObject {
    public UserRewardPointPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    } // // khi generate hàm này tự lên đầu vì nó sẽ chạy trước private WebDriver driver ở sau cũng dc

    private WebDriver driver;


}
