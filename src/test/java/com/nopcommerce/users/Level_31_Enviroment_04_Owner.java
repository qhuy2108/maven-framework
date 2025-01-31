package com.nopcommerce.users;

import commons.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.enviroment.EnviromentConfig;

import java.lang.reflect.Method;

public class Level_31_Enviroment_04_Owner extends BaseTest {

	private EnviromentConfig enviromentConfig;
	
	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass (String browserName) {
		String serverName = System.getProperty("env");
		ConfigFactory.setProperty("server", serverName);
		enviromentConfig = ConfigFactory.create(EnviromentConfig.class);


//		ConfigFactory.setProperty("server", serverName);
//		enviromentConfig = ConfigFactory.create(EnviromentConfig.class);


		System.out.println(enviromentConfig.appUrl());
		System.out.println(enviromentConfig.appUserName());
		System.out.println(enviromentConfig.appPassword());
		System.out.println(enviromentConfig.dbUrl());

		driver = getBrowserDriver(browserName, enviromentConfig.appUrl());
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
