package pageObjects.nopCommerce.users;

import org.openqa.selenium.WebDriver;

public class UserAddressPO extends SidebarPageObject {
    public UserAddressPO(WebDriver driver) {
        super(driver); // topic 74
        this.driver = driver;
    } // // khi generate hàm này tự lên đầu vì nó sẽ chạy trước private WebDriver driver ở sau cũng dc

    private WebDriver driver;


}
