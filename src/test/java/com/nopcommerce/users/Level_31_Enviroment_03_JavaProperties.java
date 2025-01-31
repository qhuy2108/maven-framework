package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.enviroment.PropertiesConfig;

import java.lang.reflect.Method;

public class Level_31_Enviroment_03_JavaProperties extends BaseTest {

	private PropertiesConfig propertiesConfig;
	
	@Parameters({"browser", "server"})
	@BeforeClass
	public void beforeClass (String browserName, String serverName) {
		propertiesConfig = PropertiesConfig.getProperties(serverName);

		driver = getBrowserDriver(browserName, propertiesConfig.getApplicationUrl());
		
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
