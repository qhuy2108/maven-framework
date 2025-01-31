package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class Level_31_Enviroment_01_Parameter_Name extends BaseTest {	
	
	@Parameters({"browser", "server"})
	@BeforeClass
	public void beforeClass (String browserName, String serverName) {
		driver = getBrowserEnviroment(browserName, serverName);
		
	}


	@Test
	public void User_01_Register(Method method) {
		
	}
	
	@Test
	public void User_02_Login(Method method) {
		
	}
	
			
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		// closeBrowserDriver();
	}	
	private WebDriver driver;
}
